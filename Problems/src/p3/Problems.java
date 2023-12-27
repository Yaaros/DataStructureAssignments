package p3;

import java.util.ArrayList;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

public class Problems {
    /*给出了的最佳方法：先把每一位的四次方存起来，再到后面应用
    * 一个乘除大概等于十次加减*/
    public static ArrayList<Integer> solution1(){
        int[] c = new int[10];
        for (int i = 0; i <= 9; i++) {
            c[i] = i*i*i*i;
        }
        int n = 999;//数字起始点
        int ic,jc,kc;
        ArrayList<Integer> toReturn = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            ic = c[i];
            for (int j = 0; j <=9; j++) {
                jc = c[j];
                for (int k = 0; k <= 9; k++) {
                    kc = c[k];
                    for (int l = 0; l <= 9; l++) {
                        n++;
                        if(ic+jc+kc+l*l*l*l==n){
                            toReturn.add(n);
                        }
                    }
                }
            }
        }
        return toReturn;
    }

    public static ArrayList<Integer> solution2(){
        ArrayList<Integer> toReturn = new ArrayList<>();
        int result;
        for (int i = 10; i <= 99; i++) {
            result = 11*i;
            if(getEveryNumPow2(result)==i){
                toReturn.add(result);
            }
        }
        return toReturn;
    }

    private static int getEveryNumPow2(int num) {
        int i = num/100;
        int j = num/10-i*10;
        int k = num%10;
        return i*i+j*j+k*k;
    }

    /*写在solution3前面：
    * a^2+b^2 = (a+b)q + r; q^2 + r = 2008;
    * q^2<=2008,q<=44
    * (a^2+b^2)/(a+b)>=(a+b)/2>r/2;
    * q>r/2-1
    * 证明q<=43不可能
    * r = 2008-q^2 >=2008-43^2 = 159
    * r/2-1 >= 159/2-1 >= 78;
    * 而q>r/2-1,即q>78,这是不可能的
    * 故q=44,r=72*/
    public static ArrayList<ArrayList<Integer>> solution3Normal(){
        int q,r;
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                q = (i*i+j*j)/(i+j);
                r = (i*i+j*j)%(i+j);
                if(q*q+r==2008){
                    ArrayList<Integer> a = new ArrayList<>(2);
                    a.add(i);
                    a.add(j);
                    arr.add(a);
                }
            }
        }
        return arr;
    }

    public static ArrayList<ArrayList<Integer>> solution3(){
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if((i*i+j*j)==(i+j)*44+72&&(i+j)%72!=0&&(i+j)>72){
                    ArrayList<Integer> a = new ArrayList<>(2);
                    a.add(i);
                    a.add(j);
                    arr.add(a);
                }
            }
        }
        return arr;
    }

    /*队列没学过做的太烂
    public static p5.ArrayList<Integer> solution4(){
        int n = 0;
        p5.ArrayList<Integer> arr = new p5.ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(10, Comparator.comparingInt(o -> o));
        queue.offer(1);
        while(n!=10000){
            int t = queue.peek();
            arr.add(queue.poll());
            n++;
            queue.offer(t*2);
            if((t*3)%2==0){
                continue;
            }
            queue.offer(t*3);
        }
        return arr;
    }*/

    /**/
    public static ArrayList<Integer> solution4(){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        for (int i = 1; i <= 100; i++) {
            if(!arr.contains(i*2)){
                arr.add(i*2);
            }
            if(!arr.contains(i*3)) {
                arr.add(i*3);
            }
            if(!arr.contains(i*5)) {
                arr.add(i*5);
            }
        }
        arr.sort(Comparator.comparingInt(o -> o));
        return new ArrayList<>(arr.subList(0,100));
    }
    public static ArrayList<Integer> solutionAdvanced4(){
        int n = 1;
        int c = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        while(c<99){
            if(n%2==0||n%3==0||n%5==0){
                arr.add(n);
                c++;
            }
            n++;
        }
        return arr;
    }

    //Get Gcd(n ints)
    public static int solution5(int[] arr){
        int temp = arr[0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                temp = getGcd(temp,arr[j]);
            }
        }
        return temp;
    }
    private static int getGcd(int a,int b){
        if(b==0){
            return a;
        }
        else{
            return getGcd(b,a%b);
        }
    }

    /*
Given P integers 1, 2, 3, …, P, you can construct a list which contains L integers chosen from the
P integers, but the list can not have K or more than K consecutive 1’s. (1 ≤ P < 10, 1 < L < 31, 1
< K < L + 1),return the total number of the list.
*/
    public static int solution6(int P,int L,int K){
        int origin = (int) Math.pow(P,L);
        //只是一种情况int toReturn = (int) (origin - train1CanSet*Math.pow(P,(L-K)));
        for (int i = L; i >= K; i--) {
            int train1CanSet = i-K+1;
            int sub = (int) (train1CanSet*(Math.pow(P-1,i-K)));
            origin-=sub;
        }
        return origin;
    }

    @Test
    public void Test() {
        System.out.println(solution1());
        System.out.println(solution2());
        System.out.println(solution3Normal());
        System.out.println(solution3());
        System.out.println(solution4());
        System.out.println(solutionAdvanced4());
        System.out.println(solution5(new int[]{63,18,36}));
        System.out.println(solution6(2,3,2));
        System.out.println(solution6(3,3,3));
        System.out.println(solution6(3,3,2));

    }
}
