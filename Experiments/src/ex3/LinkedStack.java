package ex3;

public class LinkedStack {
    private Node tail = new Node(99,null);
    private Node head = new Node(66,tail);


    public LinkedStack(String str){
        String[] strs = str.split("");
        int l = str.length();
        for (int i = l-1;i>=0;i--) {
            this.push(Integer.parseInt(strs[i]));
        }
    }

    public void push(int value) {
        head.next = new Node(value,head.next);
    }

    public int pop() {
        if(isEmpty()){
            return -1;
        }
        Node first = head.next;
        head.next = first.next;
        return first.value;
    }

    public int peek() {
        if(isEmpty()){
            return -1;
        }
        return head.next.value;
    }

    public boolean isEmpty() {
        return head.next==tail;
    }

    @Override
    public String toString() {
        Node p = head.next;
        StringBuilder stb = new StringBuilder();
        stb.append("[");
        boolean flag = false;
        while(p !=tail){
            flag = true;
            stb.append(p.value).append(",");
            p = p.next;
        }
        if(flag){
            stb.deleteCharAt(stb.length()-1);
        }
        stb.append("]");
        return stb.toString();
    }
}
