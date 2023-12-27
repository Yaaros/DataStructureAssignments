package p10;

import org.junit.jupiter.api.Test;

import java.util.Stack;
import java.util.function.Consumer;

public class BinaryTree {
    public void preOrder1(Consumer<Integer> c, TreeNode root){
        if(root==null){
            return;
        }
        c.accept(root.val);
        preOrder1(c,root.left);
        preOrder1(c,root.right);
    }
    public void inOrder1(Consumer<Integer> c, TreeNode root){
        if(root==null){
            return;
        }
        inOrder1(c,root.left);
        c.accept(root.val);
        inOrder1(c,root.right);
    }
    public void postOrder1(Consumer<Integer> c, TreeNode root){
        if(root==null){
            return;
        }
        postOrder1(c,root.left);
        postOrder1(c,root.right);
        c.accept(root.val);
    }
    @Test
    public void test(){
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4)),
                new TreeNode(3,
                        new TreeNode(5),
                        new TreeNode(6)));
        SqTraversal(root);
    }

    //层序遍历
    public void SqTraversal(TreeNode root){
        Queue<TreeNode> queue = new Queue<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode n = queue.poll();
            System.out.println(n);
            if(n.left!=null){
                queue.offer(n.left);
            }
            if(n.right!=null){
                queue.offer(n.right);
            }
        }
    }

    public void preOrder2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr!=null||!stack.isEmpty()){
            if(curr!=null){
                colorPrint("前序遍历:"+curr.val,32);
                stack.push(curr);
                curr = curr.left;
            }else {
                TreeNode pop = stack.pop();
                curr = pop.right;//再遍历右子树
            }
        }
    }

    public void inOrder2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr!=null||!stack.isEmpty()){
            if(curr!=null){
                stack.push(curr);
                curr = curr.left;
            }else {
                TreeNode pop = stack.pop();
                colorPrint("中序遍历:"+pop.val,31);
                curr = pop.right;//再遍历右子树
            }
        }
    }
    public void PostOrder2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode pop = null;
        while(curr!=null||!stack.isEmpty()){
            if(curr!=null){
                stack.push(curr);
                curr = curr.left;
            }else {
                TreeNode peek = stack.peek();
                if(peek.right==null||peek.right==pop){//若子树已经处理完成
                    /*第一个条件peek.right!=null只能处理叶子的情况
                     * 第二个条件是说最新弹栈的元素是不是已经处理过的元素*/
                    pop = stack.pop();
                    colorPrint("后序遍历:"+pop.val,34);//蓝色

                }else{
                    curr = peek.right;//再让右子树进入下一轮处理

                }
            }
        }
    }

    @Test
    public void Test(){
        TreeNode root = new TreeNode(1,
            new TreeNode(2,
                new TreeNode(4)),
            new TreeNode(3,
                new TreeNode(5),
                new TreeNode(6)));
        preOrder1((i)->colorPrint(String.valueOf(i),32),root);
        preOrder2(root);

    }

    public void colorPrint(String toPrint, int color) {
        System.out.printf("\033[%dm%s\033[0m%n",color,toPrint+" ");
    }
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
        public TreeNode(int val, TreeNode left) {
            this.val = val;
            this.left = left;
            this.right = null;
        }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(this.val);
        }
    }

}
