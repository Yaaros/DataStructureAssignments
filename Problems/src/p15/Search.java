package p15;

import java.util.Scanner;

public class Search {
    public static int[] getOrderedArray(int n){
        int[] arr = new int[n];
        for (int i = 0,j = 0; i < 5*n; i+=5,j++) {
            arr[j] = i;
        }
        return arr;
    }
    public static int[] getUnorderedArray(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random()*1000);
        }
        return arr;
    }
    public static int sqSearch(int[] arr,int n,int x){
        for (int i = 0; i < n; i++) {
            if(arr[i]==x){
                return i;
            }
        }
        return -1;
    }
    public static int binarySearch1(int[] arr,int x){
        return binarySearchRecursion(arr,0,arr.length-1,x);
    }

    private static int binarySearchRecursion(int[] arr, int left, int right, int x) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) >>> 1;
        if (arr[mid] == x) {
            return mid;
        } else if (arr[mid] > x) {
            return binarySearchRecursion(arr, left, mid - 1, x);
        } else {
            return binarySearchRecursion(arr, mid + 1, right, x);
        }
    }

    public static int binarySearch2(int[] arr,int x){
        int left = 0;
        int right = arr.length-1;
        while(left<=right){
            int mid = (left+right)>>1;
            if(arr[mid]==x){
                return mid;
            }else if(arr[mid]>x){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入n,m,x和一个布尔值，最后的布尔值表示是否有序");
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        boolean isOrdered = sc.nextBoolean();
        sc.close();
        int[] arr = isOrdered?getOrderedArray(n):getUnorderedArray(n);
        System.out.println("顺序查找用时:");
        long start = System.currentTimeMillis();
        for (int i = 0; i < m; i++) {
            sqSearch(arr,n,x);
        }
        System.out.println((System.currentTimeMillis()-start));
        System.out.println("查找成功了吗?"+(sqSearch(arr,n,x)!=-1));
        if(isOrdered){
            System.out.println("递归二分查找用时:");
            start = System.currentTimeMillis();
            for (int i = 0; i < m; i++) {
                binarySearch1(arr,x);
            }
            System.out.println((System.currentTimeMillis()-start));
            System.out.println("非递归二分查找用时:");
            start = System.currentTimeMillis();
            for (int i = 0; i < m; i++) {
                binarySearch2(arr,x);
            }
            System.out.println((System.currentTimeMillis()-start));
        }
        while(sqSearch(arr,n,x)!=-1){
            x = (int) (Math.random()*100);
            start = System.currentTimeMillis();
            for (int i = 0; i < m-1; i++) {
                sqSearch(arr,n,x);
            }
            int ans = sqSearch(arr,n,x);
            if(ans==-1){
                System.out.println("查找失败用时:"+(System.currentTimeMillis()-start));
            }
        }
    }
}
