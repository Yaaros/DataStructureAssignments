package ex7;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Exp7 {
    BinarySearchTreeNode root ;
    public static void main(String[] args) {
        Exp7 exp7 = new Exp7();
        TreeNode n = new TreeNode(
                'a',new TreeNode('b',new TreeNode('d'),new TreeNode('e')),
                new TreeNode('c',new TreeNode('f'))
        );
        System.out.println("先序序列");
        exp7.preOrder(n);
        System.out.println();
        System.out.println("中序序列");
        exp7.inOrder(n);
        System.out.println();
        System.out.println("后序序列");
        exp7.postOrder(n);
        System.out.println();
        System.out.println("层序序列");
        exp7.levelOrder(n);
        System.out.println();
        System.out.println("最大深度:"+exp7.getMaxDepthPostOrder(n));
        System.out.println("最大宽度:"+exp7.getMaxWidth(n));
        int[] degrees = exp7.getNodeNumWithDegree(n);
        System.out.println("度数为0的个数"+ degrees[0]);
        System.out.println("度数为1的个数"+ degrees[1]);
        System.out.println("度数为2的个数"+ degrees[2]);
        System.out.println("叶子个数"+exp7.getLeafNum(n));

        exp7.root = new BinarySearchTreeNode(5,3);
        exp7.put(5,4);
        exp7.put(3,1);
        exp7.put(4,2);
        exp7.put(1,0);
        exp7.put(6,9);
        exp7.put(7,10);
        exp7.put(8,11);
        System.out.println();
        System.out.println("先序序列");
        exp7.preOrder(exp7.root);
        System.out.println();
        System.out.println("中序序列");
        exp7.inOrder(exp7.root);
        System.out.println();
        System.out.println("后序序列");
        exp7.postOrder(exp7.root);
        System.out.println();
        System.out.println("层序序列");
        exp7.levelOrder(exp7.root);
        System.out.println();
        System.out.println("最大深度:"+exp7.getMaxDepthPostOrder(exp7.root));
        System.out.println("最大宽度:"+exp7.getMaxWidth(exp7.root));
        int[] degrees2 = exp7.getNodeNumWithDegree(exp7.root);
        System.out.println("度数为0的个数"+ degrees2[0]);
        System.out.println("度数为1的个数"+ degrees2[1]);
        System.out.println("度数为2的个数"+ degrees2[2]);
        System.out.println("叶子个数"+exp7.getLeafNum(exp7.root));
        System.out.println("删除一个节点后");
        exp7.Delete(3);
        exp7.preOrder(exp7.root);
    }

    public void preOrder(TreeNode root){
        if(root==null)return;
        colorPrint(root.val,31);
        preOrder(root.left);
        preOrder(root.right);
    }
    public void inOrder(TreeNode root){
        if(root==null)return;
        inOrder(root.left);
        colorPrint(root.val,32);
        inOrder(root.right);
    }
    public void postOrder(TreeNode root){
        if(root==null)return;
        postOrder(root.left);
        postOrder(root.right);
        colorPrint(root.val,33);
    }
    public void levelOrder(TreeNode root){
        if(root!=null){
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while(!q.isEmpty()){
                TreeNode p = q.poll();
                colorPrint(p.val,34);
                if(p.left!=null){
                    q.offer(p.left);
                }
                if(p.right!=null){
                    q.offer(p.right);
                }
            }
        }
    }
    //拿层序改造的求所有节点的度数
    public int[] getNodeNumWithDegree(TreeNode root){
        int[] ans = new int[3];
        if(root!=null){
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while(!q.isEmpty()){
                TreeNode p = q.poll();
                if(p.left==null&&p.right==null){
                    ans[0]++;
                }
                else if(p.left!=null&&p.right==null){
                    q.offer(p.left);
                    ans[1]++;
                }
                else if(p.right!=null&&p.left==null){
                    q.offer(p.right);
                    ans[1]++;
                }
                else{
                    q.offer(p.left);
                    q.offer(p.right);
                    ans[2]++;
                }
            }
        }
        return ans;
    }
    //Leaf=Node(Degree==1)
    public int getLeafNum(TreeNode root){
        int ans = 0;
        if(root!=null){
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while(!q.isEmpty()){
                TreeNode p = q.poll();
                if(p.left==null&&p.right==null){
                    ans++;
                }
                if(p.left!=null){
                    q.offer(p.left);
                }
                if(p.right!=null){
                    q.offer(p.right);
                }
            }
        }
        return ans;
    }
    //后序遍历求最大深度
    public int getMaxDepthPostOrder(TreeNode root){
        if(root==null)return 0;
        int left = getMaxDepthPostOrder(root.left);
        int right = getMaxDepthPostOrder(root.right);
        return 1+(Math.max(left, right));
    }
    /*层序遍历求最大宽度，应用求最小深度中所用的在层序里标识每一层的办法
    * int size= q.size()
    * size.fori即可
    * 每层存一个temp，在遍历完每层后更新ans*/
    public int getMaxWidth(TreeNode root){
        int ans = 0;
        if(root!=null){
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while(!q.isEmpty()){
                int size = q.size();
                int temp = 0;
                for (int i = 0; i < size; i++) {
                    temp++;
                    TreeNode p = q.poll();
                    assert p != null;
                    if(p.left!=null){
                        q.offer(p.left);
                    }
                    if(p.right!=null){
                        q.offer(p.right);
                    }
                }
                ans = Math.max(ans, temp);
            }
        }
        return ans;
    }


    public void colorPrint(char toPrint, int color) {
        System.out.printf("\033[%dm%s\033[0m",color,toPrint+",");
    }

    public void colorPrint2(Object toPrint, int color) {
        System.out.printf("\033[%dm%s\033[0m",color,toPrint+",");
    }

    /*增删改查的增和改是一个逻辑，都是put*/
    public void put(int key,Object value){
        BinarySearchTreeNode pointer = root;
        BinarySearchTreeNode parent = null;
        while(pointer!=null){
            parent = pointer;
            if(key<pointer.key){
                pointer = pointer.left;
            }else if(key>pointer.key){
                pointer = pointer.right;
            }else{
                //找到了结点对象
                pointer.val = value;
                return;
            }
        }
        /*没找到时，要给新节点找到父节点
         * 其实是要把pointer被更新成null之前的结点记录下来即可
         * 具体做法是在更新pointer之前，每次循环一开始就存一份pointer,
         * 这样pointer变成null就直接拿这个parent结点即可
         * 本情景中pointer不为null*/
        if(parent.key<key){
            parent.right = new BinarySearchTreeNode(key,value);
        }else{
            //不会出现等于的情况，直接变else分支语句即可,默认最好是left
            parent.left = new BinarySearchTreeNode(key,value);
        }
    }
    //查
    public Object get(int key,BinarySearchTreeNode node){
        while(node!=null){
            if(key<node.key){
                node = node.left;
            }else if(key>node.key){
                node = node.right;
            }else{
                return node.val;
            }
        }
        return null;
    }
    /*有必要说明这个删除算法是只删除key对应的结点，它原先的子树会被挂到它的前驱上
     * 删除有四种情况，最后一种还有两种子情况
     * 1.2.被删结点没有左/右孩子，也即只有单个孩子，此时把被删节点的孩子挂到被删结点自己的位置上即可
     * 3.被删结点没有孩子，则直接删掉，结合1和2可以写成同样的逻辑，把null放到自己这里
     * 4.被删结点有两个孩子:
     * 先让后任（具体看前面）顶上去，shift(parent,p,p.successor)
     * 如果后任就是被删结点的孩子/或称二者相邻，那么到此结束
     * 如果后任不是被删结点的孩子/或称二者不相邻，
     * 将后任的孩子托孤给在shift(parent,p,p.successor)操作之前 后任的父亲
     * */
    public Object Delete(int key){
        ArrayList<Object> result = new ArrayList<>();
        root = deleteInRecursion(root,key,result);
        return result.isEmpty()?null:result.get(0);
    }

    private BinarySearchTreeNode deleteInRecursion(BinarySearchTreeNode node, int key, ArrayList<Object> result) {
        if(node==null)return null;
        if(key<node.key){
            node.left = deleteInRecursion(node.left,key,result);//建立父子关系
            return node;
        }
        if (key>node.key) {
            node.right = deleteInRecursion(node.right,key,result);
            return node;
        }
        result.add(node.val);
        if(node.left==null){
            return node.right;
        }
        if(node.right==null){
            return node.left;
        }
        //有两个孩子的时候最麻烦
        BinarySearchTreeNode successor = node.right;
        while(successor.left!=null){
            successor = successor.left;
        }
        //successor在结束时就是后继节点
        //在结点和后继结点不相邻的情况下最麻烦，要以node.right为起点删除该后继结点，这一段极其抽象
        //这里传的ArrayList用不上
        successor.right = deleteInRecursion(node.right,successor.key,new ArrayList<>());
        successor.left = node.left;

        return successor;
    }

    //非递归的这个有问题
    @Deprecated
    public Object delete(int key){
        BinarySearchTreeNode p = root;
        BinarySearchTreeNode parent = null;
        while(p!=null){
            if(key<p.key){
                parent = p;//记得记录父节点
                p = p.left;
            }else if(key>p.key){
                parent = p;
                p = p.right;
            }else{
                break;
            }
        }
        if(p==null){//没找到
            return null;
        }
        //可以简化成if(p.left==null)走right，elseif(p.right==null)走left，再到else就是情况4
        if(p.left==null&&p.right==null){//情况1
            shift(parent,p, null);
        }else if(p.left==null){//情况2
            shift(parent,p,p.right);
        }else if(p.right==null){//情况3
            shift(parent,p,p.left);
        }else{//情况4
            BinarySearchTreeNode successor = p.right;//4.1找后继
            BinarySearchTreeNode sParent = p;
            while(successor.left!=null){
                sParent = successor;
                successor = successor.left;
            }
            if(sParent!=p){//不相邻的情况
                shift(sParent,successor,successor.right);//把后任的孩子托孤给后任的父亲，注意，后任不可能有left
                successor.right = p.right;
            }
            shift(parent,p,successor);//4.2把后任托孤给p的父亲
            sParent.left = p.left;
        }
        return p.val;
    }
    //shift上浮/托孤/把cur的孩子交给父亲
    private void shift(BinarySearchTreeNode parent,BinarySearchTreeNode cur,BinarySearchTreeNode child){
        if(parent==null){//被删除节点就是根节点
            root = child;
        } else if (cur == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }


    public void preOrder(BinarySearchTreeNode root){
        if(root==null)return;
        colorPrint2(root.val,31);
        preOrder(root.left);
        preOrder(root.right);
    }
    public void inOrder(BinarySearchTreeNode root){
        if(root==null)return;
        inOrder(root.left);
        colorPrint2(root.val,32);
        inOrder(root.right);
    }
    public void postOrder(BinarySearchTreeNode root){
        if(root==null)return;
        postOrder(root.left);
        postOrder(root.right);
        colorPrint2(root.val,33);
    }
    public void levelOrder(BinarySearchTreeNode root){
        if(root!=null){
            Queue<BinarySearchTreeNode> q = new LinkedList<>();
            q.offer(root);
            while(!q.isEmpty()){
                BinarySearchTreeNode p = q.poll();
                colorPrint2(p.val,34);
                if(p.left!=null){
                    q.offer(p.left);
                }
                if(p.right!=null){
                    q.offer(p.right);
                }
            }
        }
    }
    //拿层序改造的求所有节点的度数
    public int[] getNodeNumWithDegree(BinarySearchTreeNode root){
        int[] ans = new int[3];
        if(root!=null){
            Queue<BinarySearchTreeNode> q = new LinkedList<>();
            q.offer(root);
            while(!q.isEmpty()){
                BinarySearchTreeNode p = q.poll();
                if(p.left==null&&p.right==null){
                    ans[0]++;
                }
                else if(p.left!=null&&p.right==null){
                    q.offer(p.left);
                    ans[1]++;
                }
                else if(p.right!=null&&p.left==null){
                    q.offer(p.right);
                    ans[1]++;
                }
                else{
                    q.offer(p.left);
                    q.offer(p.right);
                    ans[2]++;
                }
            }
        }
        return ans;
    }
    //Leaf=Node(Degree==1)
    public int getLeafNum(BinarySearchTreeNode root){
        int ans = 0;
        if(root!=null){
            Queue<BinarySearchTreeNode> q = new LinkedList<>();
            q.offer(root);
            while(!q.isEmpty()){
                BinarySearchTreeNode p = q.poll();
                if(p.left==null&&p.right==null){
                    ans++;
                }
                if(p.left!=null){
                    q.offer(p.left);
                }
                if(p.right!=null){
                    q.offer(p.right);
                }
            }
        }
        return ans;
    }
    //后序遍历求最大深度
    public int getMaxDepthPostOrder(BinarySearchTreeNode root){
        if(root==null)return 0;
        int left = getMaxDepthPostOrder(root.left);
        int right = getMaxDepthPostOrder(root.right);
        return 1+(Math.max(left, right));
    }
    /*层序遍历求最大宽度，应用求最小深度中所用的在层序里标识每一层的办法
     * int size= q.size()
     * size.fori即可
     * 每层存一个temp，在遍历完每层后更新ans*/
    public int getMaxWidth(BinarySearchTreeNode root){
        int ans = 0;
        if(root!=null){
            Queue<BinarySearchTreeNode> q = new LinkedList<>();
            q.offer(root);
            while(!q.isEmpty()){
                int size = q.size();
                int temp = 0;
                for (int i = 0; i < size; i++) {
                    temp++;
                    BinarySearchTreeNode p = q.poll();
                    assert p != null;
                    if(p.left!=null){
                        q.offer(p.left);
                    }
                    if(p.right!=null){
                        q.offer(p.right);
                    }
                }
                ans = Math.max(ans, temp);
            }
        }
        return ans;
    }

    private static class TreeNode{
        char val;
        TreeNode left;
        TreeNode right;
        TreeNode(char val){
            this.val = val;
        }
        TreeNode(char val, TreeNode left) {
            this.val = val;
            this.left = left;
            this.right = null;
        }
        TreeNode(char val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private static class BinarySearchTreeNode{
        int key;
        Object val;
        BinarySearchTreeNode left;
        BinarySearchTreeNode right;

        public BinarySearchTreeNode(int key, Object val, BinarySearchTreeNode left, BinarySearchTreeNode right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public BinarySearchTreeNode(int key, Object val) {
            this.key = key;
            this.val = val;
        }
    }
}
