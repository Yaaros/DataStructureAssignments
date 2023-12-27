package p13;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Problems {
    @Test
    public void test(){
        System.out.println("Problem1:");
        System.out.println("原序列为:\n先序:"+Arrays.toString("GFKDAIEBCHJ".split(""))+"\n后序:"+Arrays.toString("DIAEKFCJHBG".split("")));
        System.out.println("建树过程及建好的树(的先序序列)");
        Problem1("GFKDAIEBCHJ".split(""),"DIAEKFCJHBG".split(""));
        System.out.println("----------------------------------------------");
        printList(new ListNode(1,new ListNode(2,new ListNode(3,null))));
        /*printList(reverseList(new ListNode(1,new ListNode(2,new ListNode(3,
                new ListNode(4,new ListNode(5,new ListNode(6,null))))))));*/
        System.out.println("Problem2,Example1:");
        ListNode lst1 = new ListNode(1,new ListNode(2,new ListNode(3,
                new ListNode(4,new ListNode(5,new ListNode(6))))));
        ListNode lst2 = new ListNode(1,new ListNode(2,new ListNode(3,
                new ListNode(4,new ListNode(5,new ListNode(6,new ListNode(7)))))));
        Problem2(lst1);
        System.out.println("-------------------");
        System.out.println("Problem2,Example2:");
        Problem2(lst2);
        System.out.println("Problem3:");
        Problem3(new int[]{-1,0,9},new int[]{-25,-10,10,11},new int[]{2,9,17,30,41});
    }

    //根据树的先序和后序，即对应二叉树的先序和中序构造该树
    //树转换成的二叉树的中序其实就是原树的后序序列
    //树的叶子转换到二叉树上有什么特点
    //森林上面和二叉树上的左子树和右子树对应，才能求原树的高度
    public void Problem1(String[] preOrder,String[] postOrder){
        printTree(buildTree(preOrder, postOrder));
        //buildTree(preOrder,postOrder);
    }

    private void printTree(TreeNode root){
        System.out.println(treeTraversal(root));
    }

    private String treeTraversal(TreeNode root) {
        if(root==null){
            return "";
        }
        return root.getValue()+treeTraversal(root.getFirstChild())+treeTraversal(root.getNextSibling());
    }

    private TreeNode buildTree(String[] preOrder, String[] postOrder) {
        if(postOrder.length==0||preOrder.length==0){
            return null;
        }
        TreeNode root = new TreeNode(preOrder[0]);
        int n = postOrder.length;
        System.out.println("n = "+n);
        for (int i = 0; i < n; i++) {//i=childSize
            if(Objects.equals(postOrder[i], preOrder[0])){
                System.out.println("i = "+i);
                String[] newPreOrderLeft = Arrays.copyOfRange(preOrder,1,i+1);
                String[] newPreOrderRight = Arrays.copyOfRange(preOrder,i+1,preOrder.length);
                String[] newPostOrderLeft = Arrays.copyOfRange(postOrder,0,i);
                String[] newPostOrderRight = Arrays.copyOfRange(postOrder,i+1,n);
                System.out.println(Arrays.toString(newPreOrderLeft)+
                        Arrays.toString(newPreOrderRight)+
                        Arrays.toString(newPostOrderLeft)+
                        Arrays.toString(newPostOrderRight)
                        );
                System.out.println("-----------------------------");
                root.setFirstChild(buildTree(newPreOrderLeft,newPostOrderLeft));
                root.setSibling(buildTree(newPreOrderRight,newPostOrderRight));
            }
        }
        return root;
    }


    //给一个链表，给链表重排成(a1,an,a2,an-1,a3,an-2,...)的形式，不允许使用额外空间，时间复杂度尽量低
    public void Problem2(ListNode head){
        System.out.println("初始链表为:");
        printList(head);
        ListNode p,q,r;
        p = q = r = head;//initialize
        int i = 0;
        while(q.next!=null){//用p指向中间位序
            i++;
            if(i%2==0){
                p = p.next;
            }
            q = q.next;
        }
        q = p.next;//把q设置为后半段链表的首结点
        p.next = null;//断开前后两段链表
        q = reverseList(q);//反转后半段链表
        //q是后半段链表的首结点，不停的移动前半段链表使之插入
        while(r!=null){
            p = r.next;
            r.next = q;
            if(q!=null){
                q = q.next;
            }
            if(r.next!=null){
                r.next.next = p;
            }else{
                r.next = p;
            }
            r = p;
        }
        System.out.println("\n重排后的链表为:");
        printList(head);
    }

    //有必要说明的是，我并没有想出来数学公式方面有什么优化空间，绝对值相关知识忘完了
    //这里只是从本科目的角度来优化,把暴力的O(N^3)化为O(N)
    public int Problem3(int[] arrA,int[] arrB,int[] arrC){
        System.out.println("所用数据为:\n"+
                "S1="+Arrays.toString(arrA)+'\n'+
                "S2="+Arrays.toString(arrB)+'\n'+
                "S3="+Arrays.toString(arrC));
        Queue<Integer> q1,q2,q3;
        int[] ans = new int[3];
        //语言特性:如果用q1=q2=q3=new LinkedList<>()会只创建一个队列
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
        q3 = new LinkedList<>();
        int min = getDistance(arrA[0],arrB[0],arrC[0]);
        for (int i : arrA) {
            q1.offer(i);
        }
        for (int i : arrB) {
            q2.offer(i);
        }
        for (int i : arrC) {
            q3.offer(i);
        }
        while(!q1.isEmpty()&&!q2.isEmpty()&&!q3.isEmpty()){
            int a = q1.peek();
            int b = q2.peek();
            int c = q3.peek();
            int tmp = getDistance(a,b,c);
            if(tmp<min){
                min = tmp;
                //输出三元组
                ans[0] = a;
                ans[1] = b;
                ans[2] = c;
            }
            if(getMin(a,b,c)==a){
                q1.poll();
            }else if(getMin(a,b,c)==b){
                q2.poll();
            }else{
                q3.poll();
            }
        }
        System.out.println("所求三元组为:"+Arrays.toString(ans));
        System.out.println("最小值为:"+min);
        return min;
    }

    private int getDistance(int a,int b,int c){
        return getAbs(a,b)+getAbs(b,c)+getAbs(c,a);
    }

    private int getAbs(int a,int b){
        return a>b?a-b:b-a;
    }
    private int getMin(int a,int b,int c){
        if(a<b&&a<c){
            return a;
        }else if(b<a&&b<c){
            return b;
        }else{
            return c;
        }
    }

    private void printList(ListNode head) {
        ListNode p = head;
        StringBuilder stb = new StringBuilder();
        stb.append("[");
        while(p!=null){
            stb.append(p.val).append(",");
            p = p.next;
        }
        stb.delete(stb.length()-1,stb.length());
        stb.append("]");
        System.out.println(stb);
    }

    /*把它看成两部分，已经排好序的新链表和没排好序的旧链表
    当o1的next是null(即"旧链表"为空)时退出循环
    * 具体步骤:
    1.o1=lst[1],o2=lst[2],n1=lst[1]
    2.o2从链表中断开,o1->lst[3]
    3.o2结点变成链表头部
    4.n1 = o2
    5.o2=o1.next,o2回到o1的下一个结点并重复以上步骤直到o2指向null*/
    //双指针法实现链表的就地逆置(力扣206)
    private ListNode reverseList(ListNode o1) {
        if(o1==null||o1.next==null){
            return o1;
        }
        ListNode n1 = o1;
        ListNode o2 = o1.next;
        while (o2!=null){
            o1.next = o2.next;//2.
            o2.next = n1;//3.
            n1 = o2;//4.
            o2 = o1.next;//5.
        }
        return n1;
    }


    static class ListNode{
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    static class TreeNode{
        String value;
        TreeNode firstChild;
        TreeNode nextSibling;
        public TreeNode(String value, TreeNode firstChild, TreeNode nextSibling) {
            this.value = value;
            this.firstChild = firstChild;
            this.nextSibling = nextSibling;
        }
        public TreeNode(String value, TreeNode firstChild,boolean isChild) {
            this.value = value;
            if(isChild){
                this.firstChild = firstChild;
                this.nextSibling = null;
            }else{
                this.firstChild = null;
                this.nextSibling = firstChild;
            }
        }


        public TreeNode(String value) {
            this.value = value;
            this.firstChild = null;
            this.nextSibling = null;
        }
        public void setSibling(TreeNode Sibling) {
            this.nextSibling = Sibling;
        }

        public TreeNode getNextSibling() {
            return nextSibling;
        }

        public TreeNode getFirstChild() {
            return firstChild;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setFirstChild(TreeNode firstChild) {
            this.firstChild = firstChild;
        }

    }
}
