public class Example
/*这些是上课的例题*/
{
    //for用在循环次数不明朗的场合，while用在循环次数明朗的场合,do-while上来会先做一次再看条件
    //循环变量用整型
    public static void main(String[] args) {
        System.out.println(solution1_Method1(100));
        System.out.println(solution1_Method2(100));
        System.out.println(solution2_Method1(100));
        System.out.println(solution2_Method2(100));
        System.out.println(solution3_Method1(100));
        System.out.println(solution3_Method2(100));
        System.out.println(solution3_Method3(100));
        System.out.println(solution3_Method4(100));

    }
    //solution1:求1-n的和
    public static int solution1_Method1(int n){
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum+=i;
        }
        return sum;
    }
    public static int solution1_Method2(int n){
        return n*(n+1)/2;
    }
    //solution2:1+1/2+...+1/n
    //这个级数是diverge(发散)的,从1加到1/n
    public static double solution2_Method1(int n){
        double sum = 0.0;
        //为什么从右往左更好?
        for (int i = n; i >= 1; i--) {
            sum += 1.0/i;
        }
        return sum;
    }
    public static double solution2_Method2(int n){
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0/i;
        }
        return sum;
    }
    //solution3:1-1/2+1/3-1/4+...
    public static double solution3_Method1(int n){
        if (n == 1)return 1;
        double sum = 0.0;
        for (int i = 1; i <= n/2; i++) {
            sum += getUnit(i);
        }
        return sum;
    }
    public static double getUnit(double n){
        return 1/(2*n-1)-1/(2*n);
    }
    public static double solution3_Method2(int n){
        double sum = 0.0;
        for (int i = 1;i<=n;i++){
            sum+=Math.pow(-1,i+1)/i;
            //编程尽量不使用通项公式-->往往是最糟糕的解法
            /*老师所用例子是某语言的pow是硬乘出来的，但是JDK17+的pow不是硬乘的(鼠标中键看源码可知)*/
        }
        return sum;
    }
    public static double solution3_Method3(int n){
        double sum = 0.0;
        for (int i = 1;i<=n;i+=2){
            sum+=1.0/i;
        }
        for (int i = 2;i<=n;i+=2){
            sum-=1.0/i;
        }
        return sum;
    }
    public static double solution3_Method4(int n){
        double sum = 0.0;
        for (int i = 1;i<=n;i++){
            sum += (i%2==0)?-1.0/i:1.0/i;
        }
        return sum;
    }
}
