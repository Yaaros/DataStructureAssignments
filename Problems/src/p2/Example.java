package p2;

import java.util.Scanner;
/*求sin(sin(sin(sin(...sin(x)...)))(嵌套n个)*/
public class Example {
    public double solution1(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double x = sc.nextDouble();
        for (int i = 0; i < n; i++) {
            x = Math.sin(x);
        }
        return x;
    }
}
