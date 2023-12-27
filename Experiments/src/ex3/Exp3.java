package ex3;

public class Exp3 {
    public static void main(String[] args) {
        //Java中的LinkedList实现了Queue接口，因此可以当Queue来用
        LinkedStack stackA = new LinkedStack("23561");
        System.out.println("A的牌:" + stackA);
        LinkedStack stackB = new LinkedStack("15429");
        System.out.println("B的牌:" + stackB);
        LinkedQueue cardHeap = new LinkedQueue();
        int tick = 1;
        while((!stackA.isEmpty())&&(!stackB.isEmpty())){
            if(tick%2==1){
                int a = stackA.pop();
                System.out.println("A 出 "+a);
                if(cardHeap.contains(a)){
                    MyLinkedList lst = cardHeap.getNumBehind(a);
                    System.out.println("出现重复,放回A手里的是"+lst);
                    for (Integer i : lst) {
                        stackA.push(i);
                    }
                    System.out.println("此时A手里的卡牌为"+stackA);
                }
                else{
                    cardHeap.offer(a);
                }
            }
            else {
                int b = stackB.pop();
                System.out.println("B 出 "+b);
                if(cardHeap.contains(b)){
                    MyLinkedList lst = cardHeap.getNumBehind(b);
                    System.out.println("出现重复,放回B手里的是"+lst);
                    for (Integer i : lst) {
                        stackB.push(i);
                    }
                    System.out.println("此时B手里的卡牌为"+stackB);
                }
                else{
                    cardHeap.offer(b);
                }
            }
            tick++;
        }
        if(stackA.isEmpty()){
            System.out.println("A win");
        }else{
            System.out.println("B win");
        }
    }
}
