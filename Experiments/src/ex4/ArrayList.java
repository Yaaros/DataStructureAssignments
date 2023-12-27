package ex4;

import java.util.function.Consumer;

public class ArrayList<E> {
    private E[] elements;
    private int size;//当前长度
    private int capacity;

    public ArrayList() {
        this.size = 0;
        this.capacity = 65536;
        this.elements = (E[]) new Object[capacity];
    }

    public ArrayList(E[] elements) {
        this.size = elements.length;
        this.elements = elements;
    }

    @SuppressWarnings("All")
    public ArrayList(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.elements = (E[]) new Object[capacity];
    }

    public void clear(){
        this.size = 0;
    }
    public boolean isEmpty(){
        return this.size==0;
    }
    public int length(){
        return size;
    }
    public E get(int i){
        if(i>=size||i<0){
            throw illegal(i);
        }
        return elements[i];
    }
    public void insert(int i,E value){
        if(i>=size||i<0){
            throw illegal(i);
        }
        for(int j = size-1;j >= i;j--){
            this.elements[j+1] = this.elements[j];
        }
        this.elements[i] = value;
        size++;
    }
    public E remove(int i){
        if(i>=size||i<0){
            throw illegal(i);
        }
        E toReturn = elements[i];
        for(int j = i;j<size;j++){
            this.elements[j] = this.elements[j+1];
        }
        size--;
        return toReturn;

    }
    public int indexOf(E e){
        for(int i = 0;i<size;i++){
            if(elements[i]==e){
                return i;
            }
        }
        return -1;
    }

    public void loop(Consumer<E> c){
        for(int i = 0;i < size;i++){
            c.accept(this.elements[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append("[");
        if(!isEmpty()){
            for(int i = 0;i<size;i++){
                stb.append(this.elements[i]);
                if(i!=size-1){
                    stb.append(",");
                }
            }
        }
        stb.append("]");
        return stb.toString();
    }

    public IllegalArgumentException illegal(int i){
        return new IllegalArgumentException(String.format("index %d is not legal",i));
    }
}
