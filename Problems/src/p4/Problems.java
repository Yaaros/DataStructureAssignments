package p4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

public class Problems {
    @Test
    public void test(){
        example_1(5);
        System.out.println("--------------------------------------------------------------");
        example_2(5);
        System.out.println("--------------------------------------------------------------");
        example_3(5);
        System.out.println("--------------------------------------------------------------");
        solution1(4);
        System.out.println("--------------------------------------------------------------");
        System.out.println(solution2());
        System.out.println("--------------------------------------------------------------");
        solution3(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20});
        System.out.println("--------------------------------------------------------------");
        System.out.println(Arrays.toString(solution4()));
    }



    /*上三角*/
    public static void example_1(int n){
        for (int i = 1; i <= n; i++) {
            inner_cycle_1(n,i);
            System.out.println();
        }
    }

    private static void inner_cycle_1(int n,int i) {
        for (int j = 0; j < (n-i); j++) {
            System.out.print(" ");
        }
        for (int k = 0; k < 2*i-1; k++) {
            System.out.print("*");
        }
    }

    /*菱形*/
    public static void example_2(int n){
        example_1(n);
        for(int i = n-1;i >= 1;i--){
            inner_cycle_1(n,i);
            System.out.println();
        }
    }

    private static void inner_cycle_2(int n,int i){
        for (int j = 0; j < (n-i); j++) {
            System.out.print(" ");
        }
        for (int k = 0; k < 2*i-1; k++) {
            if(k%2==0){
                System.out.print("*");
            }
            else {
                System.out.print("#");
            }
        }
        System.out.println();
    }

    /*换符菱形*/
    public static void example_3(int n) {
        for (int i = 1; i <= n; i++) {
            inner_cycle_2(n, i);
        }
        for (int i = n; i > 0; i--) {
            inner_cycle_2(n, i);
        }
    }

    /*以下为上机题*/

    /*输入最大行里面的星号数的一半*/
    public static void solution1(int n){
        //每行由2(n-i)个前置空格,i个*号,2i个空格,再写i个*号
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < (2*(n-i)); j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("  ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    /*某人岁数的二次方是个四位数，三次方是个六位数，该四位数和六位数恰好用遍 0 至 9
共 10 个数字。求该人岁数。*/

    public static int solution2(){
        /*三次方四位数，四次方六位数*/
        /*for(int i = 10;i <= 17;i++){
            int a = i*i*i;
            int b = i*i*i*i;

        }*/
        for(int i = 47;i < 100;i++){
            int a = i*i;
            int b = i*i*i;
            String str = Integer.toString(a)+Integer.toString(b);
            String[] chs = str.split("");
            if(!isRepeated(chs)){
                return i;
            }
        }
        /*应该输出69*/
        return -1;
    }
    /*看一个String数组是否有重复*/
    private static boolean isRepeated(String[] chs){
        for(int j = 0;(j <= chs.length-1);j++){
            for (int i = j + 1; i < chs.length; i++) {
                if(Objects.equals(chs[i], chs[j])){
                    return true;
                }
            }
        }
        return false;
    }

    public static void solution3(int[] arr){
        quickSort(arr);
        for (int i = 0; i < arr.length/2; i++) {
            System.out.print(arr[i]+" "+arr[arr.length-i-1]+" ");
        }
    }

    public static void quickSort(int[] a) {
        if (a.length > 0) {
            quickSort(a, 0, a.length - 1);
        }

    }
    public static void quickSort(int[] a, int low, int height) {
        int i = low;
        int j = height;
        if (i > j) {//放在k之前，防止下标越界
            return;
        }
        int k = a[i];

        while (i < j) {
            while (i < j && a[j] > k) { //找出小的数
                j--;
            }
            while (i < j && a[i] <= k) { //找出大的数
                i++;
            }
            if (i < j) {//交换
                int swap = a[i];
                a[i] = a[j];
                a[j] = swap;
            }

        }
        //交换K
        k = a[i];
        a[i] = a[low];
        a[low] = k;

        //对左边进行排序,递归算法
        quickSort(a, low, i - 1);
        //对右边进行排序
        quickSort(a, i + 1, height);
    }
    public static int[] solution4() {
        int c = 0;
        int[] arr = new int[100];
        for(int i = 1;c<100;i++){
            if(((i - 1) % 2 == 0) || ((i - 1) % 3 == 0)){
                arr[c] = i;
                c++;
            }
        }
        return arr;
    }

    /*不会写蜂巢这个*/
    public static int solution5(int a,int b){

        return 0;
    }

    //获取层级/圈层
    public static int getLevel(int n){

        return 1;
    }
}
