package p2;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class Problems {
    /*Problem5写在独立的java文件CountingNumbers中*/
    public double solution1(){
        double x = 0;
        for(int i = 1;i<=100;i++){
            x = Math.sqrt(x+i);
        }
        return x;
    }
    /*这个实际上是两层循环*/
    public double solution2(){
        double x = 2;
        for (int i = 1; i <= 100; i++) {
            /*获取分母*/
            x *= 2/getDenominator(i);
        }
        return x;
    }
    private double getDenominator(int x) {
        double toReturn = 0;
        for (int i = 1; i <= x; i++) {
            toReturn = Math.sqrt(2+toReturn);
        }
        return toReturn;
    }
    /*这是改进过的，只有一层循环，在循环中复用了上一项的分母,运行时间在文件TestTime.java中查看*/
    public double solution2Method2(){
        double x = 2;
        double Denominator = 0;
        for (int i = 1; i <= 100; i++) {
            /*获取分母*/
            Denominator = Math.sqrt(2+Denominator);
            x *= 2/Denominator;
        }
        return x;
    }


/* 需要说明的是，虽然solution2和solution3都是取Pi的不同算法的前100项和或积，但是2比3精确的多*/
    public double solution3(){
        double x = 1;
        double Unit = 1;
        for (int i = 1; i <= 99; i++) {
            Unit *= ((double) i /(2*i+1));
            x += Unit;
        }
        return 2*x;
    }

    public ArrayList<ArrayList<Integer>> solution4(){
        int j;
        ArrayList<ArrayList<Integer>> toReturn = new ArrayList<>();
        for(int i = 66;i<=99;i++){
            j = i - 56;
            if((i*i)%100 == (j*j)%100){
                ArrayList<Integer> component = new ArrayList<>();
                component.add(i);
                component.add(j);
                toReturn.add(component);
            }
        }
        return toReturn;
    }


    @Test
    public void TestMethod(){
        System.out.println(solution1());
        System.out.println(solution2());
        System.out.println(solution2Method2());
        System.out.println(solution3());
        System.out.println(solution4());
    }
}
