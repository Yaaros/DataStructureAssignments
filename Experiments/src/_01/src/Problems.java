import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class Problems {
    //1+1/2^2+1/3^2+...巴塞尔级数
    //1.求根号下6(1+1/2^2+1/3^2+...+1/100^2)
    public static double solution1(int n){
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0/(i*i);
        }
        return Math.sqrt(6*sum);
    }
    //2.求4(1-1/3+1/5-1/7+1/9+...+1/19997-1/19999)
    public static double solution2(int n){
        double sum = 0.0;
        int j = 0;
        for (int i = 1; i <= n; i+=2) {
            j++;
            if(j%2==1){
                sum += 1.0/i;
            }
            else {
                sum -= 1.0/i;
            }
        }
        return 4*sum;
    }
    //3.求2(2/1)x(2/3)x(4/3)x(4/5)x(6/5)x(6/7)x(8/7)x...x(19998/19997)x(19998/19999)
    public static double solution3(int n){
        double plus = 1.0;
        double j = 2.0;
        double k = 1.0;
        for (int i = 1; i <= n; i++) {
            plus*=j/k;
        if(i%2==0){
            j += 2;
            }
        else{
            k += 2;
            }
        }
        return plus;
    }
    //4.问2^1000的后三位怎么算(如果是024,输出24还是024?)
    //如果需要返回'024'...:
    public static String solution4(int base,int power){
        return String.format("%03d",solution4Method1(base,power));
    }
    //(a*b)%p=(a%p*b%p),*换成+-也成立  O(n)
    public static int solution4CoreMethod(int base,int power){
        int result = 1;
        for(int i = 1;i<=power;i++){
            result*=base;
            result%=1000;
        }
        return result;
    }
    //快速幂求后3位 O(logN)
    public static int solution4Method1(int base,int power){
        int result = 1;
        while(power>0){
            if (power % 2 != 0) {
                power--;
                result = result*base%1000;
            }
            power/=2;
            base=base*base%1000;
        }
        return result;
    }

    //怎么算2^1000真实值:
    public static void getBigDecimal(){
        System.out.println(BigDecimal.valueOf(2).pow(1000));
    }
    //某个整数在数组中出现了超过一半的次数，求这个数
    //solution5用的是HashMap
    public static int solution5(int[] arr){
        HashMap<Integer,Integer> map= new HashMap<>();
        for (int j : arr) {
            if (map.containsKey(j)) {
                map.put(j, (map.get(j) + 1));
            } else {
                map.put(j, 1);
            }
            if (map.get(j) >= (arr.length * 1.0 / 2)) {
                return j;
            }
        }
        return -1;
    }
    @Test
    public void TestMethod(){
        System.out.println(solution1(100));
        System.out.println(solution2(19999));
        System.out.println(solution3(19998));//与wolfram结果相差后三位
        assertEquals(solution4CoreMethod(2,1000),solution4Method1(2,1000),376);
        System.out.println(solution4(2,1000));
        assertEquals(1,solution5(new int[]{1,2,1,1,2,1,1,1,1,2,2,5,1,2}));
        assertEquals(1,solution5(new int[]{1,2,1}));

    }
}
