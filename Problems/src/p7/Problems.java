package p7;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Consumer;
/*Supplier,Iterable,Consumer,Comparable*/
public class Problems {
    /*1.求导
    * 2.十个人的水桶分别为10,20,30...求所有人等待和接水的时间总和的最小值，
    * 而且要输出排队后的编号次序和总接水时间(具体顺序看pdf)
    * 3.下面两个算式中的每个汉字都代表 0 至 9 中的数字（相同的汉字代表相同的数字，不同的汉字代表不同的数字）。
    * 破译这两个算式。年年×岁岁=花相似;岁岁÷年年=人÷不同
    * 4.从 18,19,12,17,20,11,8,15,16,7 中找出所有两数之和为质数（素数）的数对，如（18，19）
    * 5.求 10 个最小的连续自然数，它们都是合数。
    * 6.写一个求全排列的方法*/

    @Test
    public void Problem1(){
        String a = "1x+x2+2x^3+5*x^4+6";
        String b = "0";
        SinglyLinkedList slst = new SinglyLinkedList(5,a);
        System.out.println(slst.getResult());
        System.out.println(new SinglyLinkedList(1,b).getResult());
    }

    @Test
    public void Problem2(){
        Person[] ps = new Person[10];
        ps[0] = new Person(1,60);
        ps[1] = new Person(2,30);
        ps[2] = new Person(3,80);
        ps[3] = new Person(4,20);
        ps[4] = new Person(5,90);
        ps[5] = new Person(6,40);
        ps[6] = new Person(7,100);
        ps[7] = new Person(8,10);
        ps[8] = new Person(9,70);
        ps[9] = new Person(10,50);
        Arrays.sort(ps);
        StringBuilder stb = new StringBuilder();
        stb.append("编号次序:");
        int time = 0;
        int i = 0;
        for (Person p : ps) {
            i++;
            stb.append(p.number).append(",");
            //p.time:接水的时间;(10-i)*p.time:等待时间
            time += (p.time+(10-i)*p.time);
        }
        System.out.println(stb +"总时间为:"+time+"s");
    }
    /*先说明:第三题原题是个推理题，时间最优解是自己推理然后直接return答案
    * 推理:年年×岁岁=花相似;岁岁÷年年=人÷不同
    *    0<x*y*121=100a+10b+c<1000,y*(10z+w)=r*x
    * -> 0<x*y<=8(->limit) 接下来交给计算机*/
    @Test
    public void Problem3() {
        int x,y,a,b,c;
        int limit = 1000/121;
        for(x=2;x<=limit;x++){
            for(y=1;x*y<=limit&&y<x;y++){
                int result1 = x*y*121;
                a = result1/100;
                b = (result1-a*100)/10;
                c = (result1-a*100)%10;
                if(a!=b&&b!=c&&a!=c&&a!=x&&b!=x&&c!=x&&a!=y&&b!=y&&c!=y){
                    int[] temp = new int[]{x,y,a,b,c};
                    int[] fr_temp = new int[5];
                    int j = 0;
                    for(int i = 0;i<=9;i++){
                        if(!isInArray(i,temp)){
                            fr_temp[j] = i;
                            j++;
                        }
                    }
                    for (int i : fr_temp) {
                        for (int k : fr_temp) {
                            for (int l : fr_temp) {
                                if(i!=k&&k!=l&&i!=l){
                                    if(y*(10*i+k)==l*x){
                                        int[] ans = new int[]{x,y,a,b,c,l,i,k};
                                        printResult(ans);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isInArray(int i,int[] arr){
        for (int a : arr) {
            if(a==i){
                return true;
            }
        }
        return false;
    }

    private void printResult(int[] arr){
        String[] strings = new String[]{"年","岁","花","相","似","人","不","同"};
        for (int i = 0; i < 8; i++) {
            System.out.print(strings[i]+":"+arr[i]+",");
        }
    }

    @Test
    public void Problem4(){
        int[] arr = new int[]{18,19,12,17,20,11,8,15,16,7};
        Arrays.sort(arr);
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            for (int j = i+1; j < l; j++) {
                if(isPrimeNum(arr[i]+arr[j])){
                    System.out.println("("+arr[i]+","+arr[j]+")");
                }
            }
        }
    }

    @Test
    public void Problem5(){
        LinkedList list = new LinkedList();
        int i = 4;
        while(list.size<10){
            if(!isPrimeNum(i)){
                list.addFirst(i);
            }else{
                list.clear();
            }
            i++;
        }
        System.out.println(list);
    }

    //是否为质数
    private boolean isPrimeNum(int n) {
        for(int i = 2;i*i<=n;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

    private static class Person implements Comparable<Person>{
        int number;
        int time;

        public Person(int number, int time) {
            this.number = number;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "number=" + number +
                    ", time=" + time +
                    '}';
        }
        @Override
        public int compareTo(Person o) {
            return this.time-o.time;
        }
    }
    static class LinkedList {
        int size;
        Node head = new Node(666, null);

        public LinkedList() {
            this.size = 0;
        }

        public void addFirst(int value) {
            head.next = new Node(value, head.next);
            size++;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void clear(){
            size=0;
            head.next = null;
        }

        public void loop(Consumer<Integer> consumer) {
            Node p = head.next;
            while (p != null) {
                consumer.accept(p.value);
                p = p.next;
            }
        }

        @Override
        public String toString(){
            StringBuilder stb = new StringBuilder();
            stb.append("[");
            loop((i)-> stb.append(i).append(","));
            int l = stb.length()-1;
            stb.delete(l,l+1);
            stb.append("]");
            return stb.toString();
        }
    }

    private static class Node{
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
