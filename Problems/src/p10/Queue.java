package p10;

import java.util.Iterator;

public class Queue<E> implements Iterable<E>{
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
