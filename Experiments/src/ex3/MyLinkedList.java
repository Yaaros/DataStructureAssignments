package ex3;

import java.util.Iterator;

public class MyLinkedList implements Iterable<Integer>{

    private static class Node{

        private Node prev;
        private Node next;
        private int value;

        public Node(Node prev, Node next, int value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }

    private Node head = new Node(null,null,666);

    private Node tail = new Node(null,null,999);
    public MyLinkedList() {
        head.next = tail;
        tail.prev = head;

    }
    public void addFirst(int num){
        Node toAdd = new Node(head,head.next,num);
        head.next.prev = toAdd;
        head.next = toAdd;
    }

    public void addLast(int num){
        Node toAdd = new Node(tail.prev,tail,num);
        tail.prev.next = toAdd;
        tail.prev = toAdd;
    }

    public String getString(){
        StringBuilder stb = new StringBuilder();
        for(int i : this){
            stb.append(i);
        }
        return stb.toString();
    }

    @Override
    public String toString() {
        Node p = head.next;
        StringBuilder stb = new StringBuilder();
        stb.append("[");
        while(p!=tail){
            stb.append(p.value).append(",");
            p = p.next;
        }
        stb.deleteCharAt(stb.length()-1).append("]");
        return stb.toString();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            Node p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public Integer next() {
                int value = p.value;
                p = p.next;
                return value;
            }
        };
    }
}
