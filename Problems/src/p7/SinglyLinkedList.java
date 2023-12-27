package p7;

public class SinglyLinkedList {

    Node head = new Node(666,666,null);

    public SinglyLinkedList() {
    }

    /*@param:
    * m:项数
    * formula:原始算式,范式为:"c1xe1+c2xe2+c3xe3+...cmxem"*/
    public SinglyLinkedList(int m,String formula) {
        String[] strs = formula.split("\\+");
        String temp = "";
        String[] arr;
        for (int i = 0; i < m; i++) {
            temp = strs[i];
            /*处理一下x的一次方和系数为1的情况*/
            if(temp.startsWith("x")){
                temp = "1"+temp;
            }
            if(temp.endsWith("x")){
                temp+="1";
            }
            arr = temp.split("\\**x\\^*");
            if(arr.length==0){
                throw Illegal();
            } else if (arr.length==1) {
                addFirst(new Node(0,0,null));
            }else{
                addFirst(new Node(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),null));
            }
        }
    }

    public void addFirst(Node n){
        n.next = head.next;
        head.next=n;
    }

    public void loop(Consumer consumer){
        Node p = head;
        while(p.next!=null){
            p=p.next;
            if(p.coef!=0){
                consumer.accept(p.coef,p.expn);
            }else{
                consumer.accept(0,0);
            }
        }
    }

    /*实现求导功能*/
    public SinglyLinkedList getResult(){
        Node p = head;
        SinglyLinkedList toReturn = new SinglyLinkedList();
        loop((i,j)->{
            if(j!=0){
                toReturn.addFirst(new Node(i*j,j-1,null));
            }else{
                toReturn.addFirst(new Node(0,0,null));
            }
        });
        /*原始方法:
        while(p.next!=null){
            p=p.next;
            if(p.expn!=0){
                toReturn.addFirst(new Node(p.coef*p.expn,p.expn-1,null));
            }else{
                toReturn.addFirst(new Node(0,0,null));
            }
        }*/
        return toReturn;
    }

    @Override
    public String toString() {
            StringBuilder stb = new StringBuilder();
            loop((i,j)-> {
                if(i!=0&&j!=0&&j!=1){
                    stb.append(i).append("x").append("^").append(j).append("+");
                } else if (i!=0&&j==0) {
                    stb.append(i).append("+");
                } else if (i!=0&&j==1) {
                    stb.append(i).append("x").append("+");
                } else{
                    stb.append("0").append("+");
                }
            });
            int l = stb.length()-1;
            stb.delete(l,l+1);
            String temp = stb.toString();
            //这个字符串设计是如果contains"+0"就不等于0了
            if(!(temp.equals("0"))&&temp.contains("+0")){
                stb.delete(temp.indexOf("+0"),temp.indexOf("+0")+1);
            }
            return stb.toString();
    }

    private interface Consumer{
        void accept(int a,int b);
    }

    private static class Node{

        /*@param: coef 系数
        *         expn 指数
        *         next 指针*/
        public Node(int coef, int expn, Node next) {
            this.coef = coef;
            this.expn = expn;
            this.next = next;
        }

        int coef;
        int expn;
        Node next;
    }
    private IllegalArgumentException Illegal() {
        return new IllegalArgumentException("输入有误,请检查");
    }
}
