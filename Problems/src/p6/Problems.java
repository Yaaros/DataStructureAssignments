package p6;


import org.testng.annotations.Test;

import java.util.Scanner;

public class Problems {
    /*public static void main(String[] args) {
        LinkList sql = new LinkList();
        sql.insert(0,2);
        sql.insert(1,1);
        sql.insert(2,3);
        sql.insert(2,4);
        sql.remove(2);
        System.out.println(sql);
    }*/
    //Problem1
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n;
        int i;
        int Obj;
        LinkList sql = new LinkList();
        System.out.println("是否要对线性表进行插入和删除？（Y/N）");
        while(!(((n = sc.next()).equals("N"))||(n.equals("n")))){
            if(n.equals("Y")||n.equals("y")){
                System.out.println("进行插入还是删除？（1--插入，2--删除）");
                if(sc.next().equals("1")){
                    System.out.println("输入插入位置：");
                    i = sc.nextInt();
                    System.out.println("输入插入元素：");
                    Obj = sc.nextInt();
                    try {
                        sql.insert(i,Obj);
                    } catch (Exception e) {
                        System.out.println("插入位置有误");
                    }
                    System.out.println("当前线性表为:"+sql);
                    System.out.println("是否要对线性表进行插入和删除？（Y/N）");
                } else if (sc.next().equals("2")) {
                    System.out.println("输入删除位置：");
                    i = sc.nextInt();
                    try {
                        sql.remove(i);
                    } catch (Exception e) {
                        System.out.println("删除位置有误");
                    }
                    System.out.println("当前线性表为:"+sql);
                    System.out.println("是否要对线性表进行插入和删除？（Y/N）");
                }else{
                    System.out.println("输入不合法,请输入(1/2)");
                }
            }else{
                System.out.println("输入不合法,请输入(Y/N)");
            }
        }
        System.out.println("对线性表处理完毕");
    }
    @Test
    public void Problem2And3(){
        LinkList l1 = new LinkList("1,2,3,4,5,6,7,8,9");
        LinkList l2 = new LinkList("1,2,3,4,5,6,7,8,9");
        System.out.println(getKthMin(l1,l2,3));
        LinkList l3 = new LinkList("1,4,5,6,7,8,9");
        LinkList l4 = new LinkList("1,3,4,5,6,7,8,9");
        System.out.println(getKthMin(l3,l4,3));
        System.out.println(getLastK(l1,4));
    }

    //Problem2
    public int getKthMin(LinkList lst1,LinkList lst2,int k){
        Node p = lst1.head.next;
        Node q = lst2.head.next;
        while(p!=null&&q!=null){
            if(k==1)return p.value;
            if(p.value<q.value){
                p = p.next;
            }else if(p.value>q.value){
                q = q.next;
            }else{
                p = p.next;
                q = q.next;
            }
            k--;
        }
        while(p!=null){
            if(k==1)return p.value;
            k--;
            p = p.next;
        }
        while(q!=null){
            if(k==1)return q.value;
            k--;
            q = q.next;
        }
        return -1;
    }
    //Problem3
    public int getLastK(LinkList lst,int k){
        Node p = lst.head.next;
        Node q = lst.head.next;
        for (int i = 0; i < k; i++) {
            if(q==null)return -1;
            q = q.next;
        }
        while(q!=null){
            p = p.next;
            q = q.next;
        }
        assert p!=null;
        return p.value;
    }
    static class LinkList{
        Node head = new Node(666,null);
        int curLen;
        LinkList(){
            curLen = 0;
        }
        LinkList(String str){
            curLen = 0;
            Node p = head;
            String[] ss = str.split(",");
            for(int i = 0;i<ss.length;i++){
                p.next = new Node(Integer.parseInt(ss[i]),null);
                p = p.next;
                curLen++;
            }
        }
        void insert(int index,int value){
            if(index<0||index>curLen){
                throw new RuntimeException("插入位置不合法");
            }
            Node p = head;
            int i = 0;
            while(p.next!=null){
                if(i==index)break;
                p = p.next;
                i++;
            }
            Node q = p.next;
            p.next = new Node(value,q);
            curLen++;
        }
        void remove(int index){
            Node p = head;
            int i = 0;
            while(i<=index&&p.next!=null){
                p = p.next;
                i++;
            }
            if(p.next==null){
                throw new RuntimeException("删除位置不合法");
            }
            p.next = p.next.next;
            curLen--;
        }
        public String toString(){
            Node p = head.next;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while(p!=null){
                sb.append(p.value+",");
                p = p.next;
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("]");
            return sb.toString();
        }
    }
    static class Node{
        int value;
        Node next;
        Node(int value,Node next){
            this.value = value;
            this.next = next;
        }
    }
}
