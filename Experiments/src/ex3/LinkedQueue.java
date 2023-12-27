package ex3;

import java.util.function.Consumer;

public class LinkedQueue {
    /*必须实现loop方法*/
    private Node head = new Node(0,null);
    private Node tail = head;

    public LinkedQueue() {
        tail.next = head;
    }

    public LinkedQueue(String str){
        tail.next = head;
        String[] strs = str.split("");
        /*int l = str.length();
        for (int i = l-1;i>=0;i--) {
            this.offer(Integer.parseInt(strs[i]));
        }*/
        for (String s : strs) {
            this.offer(Integer.parseInt(s));
        }
    }

    public boolean isEmpty() {
        return head==tail;
    }

    //不考虑容量时为永真
    public void offer(int value) {
        Node toAdd = new Node(value,head);
        //从尾部添加的逻辑
        tail.next = toAdd;
        tail = toAdd;
    }

    public int poll() {
        if(isEmpty()){
            return -1;
        }
        Node first = head.next;
        //如果删除了唯一的结点，要把队列变为空
        if(first==tail){
            tail = head;
        }
        head.next = first.next;
        return first.value;
    }

    public int peek() {
        if(isEmpty()){
            return -1;
        }
        return head.next.value;
    }

    public boolean contains(int num){
        Node p = head.next;
        while(p!=head){
            if(num==p.value){
                return true;
            }
            p=p.next;
        }
        return false;
    }

    public MyLinkedList getNumBehind(int repeat){
        Node p = head.next;
        Node r = head;
        MyLinkedList myList = new MyLinkedList();
        while(p!=head){
            if(repeat==p.value){
                Node q = p;
                while(q!=head){
                    myList.addLast(q.value);
                    q=q.next;
                }
                //这里我想把队列截断使重复部分消失,问题在于是head还是head.next
                r.next = head;

                myList.addLast(repeat);
                return myList;
            }
            p=p.next;
            r=r.next;
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedQueue m = new LinkedQueue();
        m.offer(2);
        m.offer(1);
        m.offer(2);
        m.offer(3);
        m.offer(4);
        m.offer(5);
        m.offer(1);
        System.out.println(m);
        m.getNumBehind(1);
        System.out.println(m);
    }

    public void loop(Consumer<Integer> c){
        Node p = head.next;
        while(p!=head){
            c.accept(p.value);
            p=p.next;
        }
    }
    @Override
    public String toString() {
        Node p = head.next;
        StringBuilder stb = new StringBuilder();
        stb.append("[");
        boolean flag = false;
        while(p !=head){
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
