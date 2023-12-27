package p7;

public class Test {
    public static void main(String[] args) {
        /*String a = "1x2+2x^3";
        String[] strs = a.split("\\+");
        for (String str : strs) {
            System.out.println(Arrays.toString(str.split("x\\^*")));
        }*/
        String a = "1x+x2+2x^3+5*x^4+6";
        String b = "0";
        SinglyLinkedList slst = new SinglyLinkedList(5,a);
        System.out.println(slst.getResult());
        System.out.println(new SinglyLinkedList(1,b).getResult());
    }
}
