package p10;

import java.util.Iterator;

public class Stack<E> implements Iterable<E>{
    private int capacity;
    private int size;
    private Node<E> head = new Node<>(null,null);

    public Stack(int capacity) {
        this.capacity = capacity;
    }

    public Stack(){
        this.capacity = Integer.MAX_VALUE-1;
    }

    public boolean push(E value) {
        if(isFull()){
            return false;
        }
        head.next = new Node<>(value,head.next);
        size++;
        return true;
    }

    public E pop() {
        if(isEmpty()){
            return null;
        }
        Node<E> first = head.next;
        head.next = first.next;
        size--;
        return first.value;
    }

    public E peek() {
        if(isEmpty()){
            return null;
        }
        return head.next.value;
    }


    public boolean isEmpty() {
        return head.next==null;
    }


    public boolean isFull() {
        return size==capacity;
    }

    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append("[");
        for(E e:this){
            stb.append(e);
            stb.append(",");
        }
        int l = stb.length()-1;
        return stb.replace(l,l+1,"]").toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p!=null;
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
