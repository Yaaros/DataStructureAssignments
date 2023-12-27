package p11;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;


public class Problems {
    public TreeNode<String> getBinaryTree(String[] arr){
        /*使用队列把层序序列转成二叉树*/
        //中序遍历不能用于交换左右子树
        Queue<TreeNode<String>> queue = new Queue<>();
        if(Arrays.equals(arr, new String[]{})){
            return null;
        }
        int i = 0;
        int n = arr.length;
        TreeNode<String> root = new TreeNode<>(arr[i++]);
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode<String> temp = queue.poll();
            if(i<n){
                if(!arr[i].equals("#")){
                    temp.left = new TreeNode<>(arr[i]);
                    queue.offer(temp.left);
                }
                i++;
            }
            if(i<n){
                if(!arr[i].equals("#")){
                    temp.right = new TreeNode<>(arr[i]);
                    queue.offer(temp.right);
                }
                i++;
            }
        }
        return root;
    }
    public ListNode delRepeat(ListNode head,int n){
        /*删除链表中绝对值重复的元素
        * 我看到这个链表示意图是带头结点的，所以写成这种跳过第一个结点的
        * 如果没有头结点，那应该写成
        * while(p1!=null){
        *   temp = Math.abs(p1.val);
            if(hash[temp]==0){
                hash[temp]++;
                p2 = p2.next;
            }
            else{
                p2.next = p1.next;
            }
            p1 = p1.next
        * }
        * */
        int[] hash = new int[n+1];
        ListNode p1 = head;
        ListNode p2 = head;
        int temp;
        while(p1.next!=null){
            p1 = p1.next;
            temp = Math.abs(p1.val);
            if(hash[temp]==0){
                hash[temp]++;
                p2 = p2.next;
            }
            else{
                p2.next = p1.next;
            }
        }
        return head;
    }
    public void Problem3(){
        TreeNode<String> op = getBinaryTree(new String[]{"+","*","/","1","2","3","4"});
        System.out.println(op.inOrder3(op));
        TreeNode<String> out = getBinaryTree(new String[]{"+","*","/","1","2","3","-","#","#","#","#","#","#","#","4"});
        System.out.println(out.inOrder3(out));
    }
    @Test
    public void TestMethod(){
        ListNode test = new ListNode(666,new ListNode(1,new ListNode(2,new ListNode(3,new ListNode(4,new ListNode(-4,new ListNode(-3,new ListNode(-2,new ListNode(-1,new ListNode(0))))))))));
        delRepeat(test,5);
        System.out.println(test.next);
        TreeNode<String> tr = getBinaryTree(new String[]{"1","2","3","#","4","5","#"});
        tr.inOrder();
        System.out.println();
        Problem3();
    }
}
class TreeNode<E> {
    E val;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode(E val, TreeNode<E> left, TreeNode<E> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    public void inOrder(){
        TreeNode<E> p = this;
        inOrder2(p);
    }
    public void inOrder2(TreeNode<E> node){
        if(node==null){
            return;
        }
        inOrder2(node.left);
        System.out.print(node.val+",");
        inOrder2(node.right);
    }
    //实现二叉树转中缀表达式含括号
    public String inOrder3(TreeNode<E> node){
        if(node==null){
            return "";
        }
        String s = "";
        if(node.left==null^node.right==null){
            s="(";
            s+=node.val;
            /*这里会有一个提示可以修改成lambda
            * s += Objects.requireNonNullElseGet(node.left, () -> node.right).val;
            * 但这样没有可读性
             */
            if(node.left!=null){
                s+=node.left.val;
            }else{
                s+=node.right.val;
            }
            return s+")";
        }
        if(node!=this&&(node.left!=null||node.right!=null)){
            s = "(";
        }
        s+=inOrder3(node.left);
        s+=node.val;
        s+=inOrder3(node.right);
        if(node!=this&&(node.left!=null||node.right!=null)){
            s += ")";
        }
        return s;
    }
}
class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) { this.val = val; next = null; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        ListNode p = this;
        StringBuilder stb = new StringBuilder();
        stb.append("[");
        while(p != null){
            stb.append(p.val).append(",");
            p = p.next;
        }
        stb.deleteCharAt(stb.length()-1).append("]");
        return stb.toString();
    }
}
class Queue<E> implements Iterable<E>{
    //这还是有哨兵的
    Node <E> head = new Node<>(null,null);;
    Node <E> tail = head;

    public Queue() {
        tail.next = head;//这会使head.next也变为sentinel
    }

    public boolean isEmpty() {
        return head==tail;
    }

    //不考虑容量时为永真
    public boolean offer(E value) {
        Node<E> toAdd = new Node<>(value,head);
        //从尾部添加的逻辑
        tail.next = toAdd;
        tail = toAdd;
        return true;
    }

    public E poll() {
        if(isEmpty()){
            return null;
        }
        Node<E> first = head.next;
        //如果删除了唯一的结点，要把队列变为空
        if(first==tail){
            tail = head;
        }
        head.next = first.next;
        return first.value;
    }

    public E peek() {
        if(isEmpty()){
            return null;
        }
        return head.next.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p!=head;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }
    private static class Node<E>{
        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }

        E value;
        Node<E> next;

    }
}
