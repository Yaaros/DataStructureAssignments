package p12;

public class Tree {

    private TreeNode root;

    public Tree(char[][] arr){

    }

    //应该用双向链表好一些
    static class LinkList{
        private Node head;

        public LinkList() {
            head = null;
        }
        private boolean isEmpty(){
            return head == null;
        }
        private Node findLast(){
            if(isEmpty()) return null;
            Node p = head;
            while(p.next!=null){
                p = p.next;
            }
            return p;
        }
        private Node findNode(int index){
            int i = 0;
            for(Node p = head; p!=null; p=p.next,i++){
                if(i==index){
                    return p;
                }
            }
            return null;
        }
        public void addFirst(char value){
            if (isEmpty()){
                head = new Node(value,null);
                return;
            }
            head.next = new Node(value,head.next);
        }
        public void addLast(char value){
            if (isEmpty()){
                addFirst(value);
                return;
            }
            assert findLast() != null;
            findLast().next = new Node(value,null);
        }
        public char remove(int index){
            if(isEmpty()){
                return '$';
            }
            if(index == 0){
                char value = head.value;
                head = head.next;
                return value;
            }
            Node n = findNode(index-1);
            if(n == null){
                return '$';
            }
            char value = n.next.value;
            n.next = n.next.next;
            return value;
        }
        public char get(int index) {
            Node node = findNode(index);
            if(node==null){
                throw new IllegalArgumentException(String.format("[%d] is illegal",index));
            }
            return node.value;
        }
    }
    static class Node{
        public Node(char value, Node next) {
            this.value = value;
            this.next = next;
        }

        char value;
        Node next;
    }
    static class TreeNode{
        char value;

        TreeNode parent;
        LinkList children;
        public TreeNode(char value) {
            this.value = value;
            this.children = null;
            this.parent = null;
        }

        public TreeNode(char value, LinkList children) {
            this.value = value;
            this.children = children;
            this.parent = null;
        }

        public TreeNode(char value, TreeNode parent, LinkList children) {
            this.value = value;
            this.parent = parent;
            this.children = children;
        }
    }
}
