package ex6;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Sort {
    //说是可以不用文件读取但是我觉得还是用文件吧
    public static int[] Compare_Nums = new int[6];

    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static int[] InsertionSort(int[] arr){
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int m = i - 1;
            int temp = arr[i];
            while(m>=0&&temp<arr[m]){
                if(temp<arr[m]){//该条件加入等号则为不稳定排序
                    arr[m+1] = arr[m];
                    m--;
                    Compare_Nums[0]++;
                }else{
                    break;
                }
            }
            arr[m+1] = temp;
        }
        return arr;
    }
    public static int[] SelectionSort(int[] arr){
        int n = arr.length;
        for (int bound = 0; bound < n - 1; bound++) {
            for (int cur = bound + 1; cur < n; cur++) {
                if(arr[bound] > arr[cur]){
                    swap(arr,bound,cur);
                    Compare_Nums[1]++;
                }
            }
        }
        return arr;
    }
    public static int[] ShellSort(int[] arr){
        int gap = arr.length/2;
        while(gap > 1){
            shellGapSort(arr,gap);
            gap/=2;
        }
        shellGapSort(arr,1);//当gap为1时再进行一次插入排序
        return arr;
    }
    private static void shellGapSort(int[] arr,int gap){
        for(int bound = gap;bound < arr.length;bound++){
            int temp = arr[bound];
            int cur = bound - gap;
            while(cur >= 0){
                if(temp < arr[cur]){
                    arr[cur+gap] = arr[cur];
                    cur-=gap;
                    Compare_Nums[2]++;
                }else{
                    break;
                }
            }
            arr[cur+gap] = temp;
        }
    }
    //写一个临时数组用于合并
    public static int[] MergeSort(int[] arr){
        int[] temp = new int[arr.length];
        split(arr,0,arr.length-1,temp);
        return arr;
    }

    private static void split(int[] arr, int left, int right, int[] temp) {
        //治
        if(left==right){
            return;
        }
        //分
        int mid = (left+right)>>>1;
        split(arr,left,mid, temp);
        split(arr,mid+1,right, temp);
        //合
        merge(arr,left,mid,mid+1,right,temp);
        System.arraycopy(temp,left,arr,left,right-left+1);
    }

    /**
     *合并两个有序数组
     * @param a1:原始数组
     * @param i：第一个有序范围开始
     * @param iEnd：第一个有序范围结束
     * @param j：第二个有序范围开始
     * @param jEnd：第二个有序范围结束
     * @param a2:临时数组，和原始数组大小一致
     */

    private static void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int p = i;
        int q = j;
        int k = i;//a2用的索引
        while(p<=iEnd&&q<=jEnd){
            if(a1[p]<a1[q]){
                Compare_Nums[3]++;
                a2[k++] = a1[p++];
            }else{
                a2[k++] = a1[q++];
            }
        }
        while(p<=iEnd){
            a2[k++] = a1[p++];
        }
        while(q<=jEnd){
            a2[k++] = a1[q++];
        }
        System.arraycopy(a2,i,a1,i,jEnd-i+1);
    }

    /*快速排序：
     * 有很多种分区思路，但架子都是一样的，即使用递归
     * 因此更改的只会是partition函数*/
    public static void QuickSortSingleEdge(int[] arr){
        quick(arr,0,arr.length-1,1);//用于递归,下同
    }

    public static void QuickSortDoubleEdge(int[] arr){
        quick(arr,0,arr.length-1,2);
    }

    public static void QuickSortRandom(int[] arr){
        quick(arr,0,arr.length-1,3);
    }

    public static void QuickSort(int[] arr){
        quickSortDefault(arr,0,arr.length-1);
    }

    /**/
    private static void quickSortDefault(int[] arr, int left, int right) {
        if(left>=right){
            return;
        }
        if(right-left<=5){
            insertionSort(arr,left,right);
            return;
        }
        int p = partition(arr, left, right,0);//分区方法
        quickSortDefault(arr,left,p-1);
        quickSortDefault(arr,p+1,right);
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left+1; i <= right; i++) {
            int m = i - 1;
            int temp = arr[i];
            while(m>=0&&temp<arr[m]){
                if(temp<arr[m]){//该条件加入等号则为不稳定排序
                    arr[m+1] = arr[m];
                    m--;
                    Compare_Nums[4]++;
                }else{
                    break;
                }
            }
            arr[m+1] = temp;
        }
    }

    private static void quick(int[] arr, int left, int right,int type) {
        if(left>=right){
            return;
        }
        int p = partition(arr, left, right,type);//分区方法
        quick(arr,left,p-1,type);
        quick(arr,p+1,right,type);
    }

    /**
     * 分区函数
     * @param arr
     * @param left
     * @param right
     * @param type:
     *   1=单边快排
     *  操作是：1.找最右边元素作为基准点，
     *          2.创建i和j两个指针,i和j一起前进
     *          3.j找比基准点小的，i找比基准点大的,一旦有一个找到，就把该指针停下来，让另一个继续走，
     *          一旦两个指针都找到了，二者进行交换,然后两个指针继续走
     *          4.直到j到头为止，最后基准点与i交换，i就是基准点的最终索引
     *   2=双边快排
     *  操作是：1.找最左边元素作为基准点，
     *         2.创建i和j两个指针,i从左向右,j从右向左
     *         3.j找比基准点小的，i找比基准点大的,一旦有一个找到，就把该指针停下来，让另一个继续走，
     *         一旦两个指针都找到了，二者进行交换,然后两个指针继续走
     *         4.直到i==j为止，最后基准点与i交换，i就是基准点的最终索引
     *   3=把双边快排第一步改为随机基准数
     *   4=处理重复
     @return p:基准点元素的索引
     */
    private static int partition(int[] arr, int left, int right,int type) {
        switch (type) {
            case 1 -> {
                return partition1(arr, left, right);
            }
            case 2 -> {
                return partition2(arr, left, right);
            }
            case 4 -> {
                return partition4(arr, left, right);
            }
            default -> {
                return partition3(arr, left, right);
            }
        }
    }


    private static int partition1(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if(arr[j] < pivot){
                if(i!=j){
                    swap(arr,i,j);
                }
                i++;
//这里有必要说明一下：i是在找到比基准点大的元素时停下来，也就是在没找到比基准点大的元素时要自增，
//也就是j对应值小于pivot才自增
            }
        }
        swap(arr,i,right);
        return i;
    }

    private static int partition2(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left;
        int j = right;
        while(i<j){
            //这里每一个前面也要有i<j条件，不然会换乱
            while(i<j&&arr[j] > pivot){
                j--;//先j后i
            }
            //注意这里的等号
            while(i < j && arr[i] <= pivot){
                i++;
            }
            swap(arr,i,j);
        }
        swap(arr,left,i);
        return i;
    }
    private static int partition3(int[] arr, int left, int right) {
        int pv = ThreadLocalRandom.current().nextInt(right- left + 1) + left;
        swap(arr,pv,left);//这里写个交换避免将多个left改为pv
        int pivot = arr[left];
        int i = left;
        int j = right;
        while(i<j){
            //这里每一个前面也要有i<j条件，不然会换乱
            while(i<j&&arr[j] > pivot){
                j--;//先j后i
                Compare_Nums[4]++;
            }
            //注意这里的等号
            while(i < j && arr[i] <= pivot){
                i++;
                Compare_Nums[4]++;
            }
            swap(arr,i,j);
        }
        swap(arr,left,i);
        return i;
    }
    //这个的基准点是j，所以先处理i
    //等于条件要加
    private static int partition4(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left+1;
        int j = right;
        while(i<=j){
            while(i<=j&&arr[i] < pivot){
                i++;
            }
            while(i<=j&&arr[j] > pivot){
                j--;
            }
            if(i<=j){
                swap(arr,i,j);
                i++;
                j--;
            }
        }
        swap(arr,left,j);
        return j;
    }

    public static int[] HeapSort(int[] arr){
        int n = arr.length;
        CreateHeap(arr);//建堆
        for (int i = 0; i < n - 1; i++) {
            swap(arr,0,n-i-1);//每次堆里含有的元素数为n-i,因此每次操作的最后一个数下标为n-i-1
            shiftDown(arr,n-i-1,0);//对所交换的元素执行下潜
        }
        return arr;
    }

    private static void CreateHeap(int[] arr) {
        int n = arr.length;
        for(int i = (n - 1 - 1) / 2; i >= 0;i--){
            shiftDown(arr,n,i);
        }
    }
    private static void shiftDown(int[] arr, int heapLength, int index) {
        int parent = index;
        int child = 2 * index + 1;
        while(child < heapLength){
            if(child + 1 < heapLength && arr[child + 1] > arr[child]){
                //将child调整到左右子树中较大的树
                child++;
                Compare_Nums[5]++;
            }
            if(arr[child] > arr[parent]){
                swap(arr,child,parent);
                parent = child;
                child = 2 * child + 1;
                Compare_Nums[5]++;
            }else{
                break;
            }
        }
    }
    private static void spawnArraysTxt(int numOfArray) throws IOException {
        File file = new File("Experiments/src/ex6/Arrays.txt");
        FileWriter fw =new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        System.out.println("原始数组:");
        for (int i = 0; i < numOfArray; i++) {
            int[] arr = new int[(int) (Math.random()*100)+1];
            for (int j = 0; j < arr.length; j++) {
                int n = (int) (Math.random()*100+1);
                arr[j] = n;
                bw.write(n+" ");
            }
            bw.write('\n');
            System.out.println(Arrays.toString(arr));
        }
        bw.close();
        fw.close();
    }


    private static void TestMethod(int type) throws IOException {
        File file = new File("Experiments/src/ex6/Arrays.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String str;
        while((str = br.readLine())!=null){
            int[] arr = Arrays.stream(str.split(" ")).mapToInt(Integer::parseInt).toArray();
            switch (type) {
                case 1 -> {
                    InsertionSort(arr);
                    System.out.println("插入排序结果:"+Arrays.toString(arr));
                }
                case 2 -> {
                    SelectionSort(arr);
                    System.out.println("选择排序结果:"+Arrays.toString(arr));
                }
                case 3 -> {
                    ShellSort(arr);
                    System.out.println("希尔排序结果:"+Arrays.toString(arr));
                }
                case 4 -> {
                    MergeSort(arr);
                    System.out.println("归并排序结果:"+Arrays.toString(arr));
                }
                case 5 -> {
                    QuickSort(arr);
                    System.out.println("快速排序结果:"+Arrays.toString(arr));
                }
                case 6 -> {
                    HeapSort(arr);
                    System.out.println("堆排序结果:"+Arrays.toString(arr));
                }
            }
        }
        br.close();
        fr.close();
    }
    private static long TestMethodOuter(int type) throws IOException {
        long start = System.currentTimeMillis();
        int i = 0;
        while(i<2000){
            TestMethod(type);
            i++;
        }
        return (System.currentTimeMillis()-start);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("第一个数字:输入1表示清除原txt中的内容并重新生成数组，输入2表示不改变原有内容");
        System.out.println("第二个数字:输入一个数n,在Arrays.txt里创建n个数组," +
                "若之前输入2,可通过输入0来只对原有数组进行排序");
        Scanner sc = new Scanner(System.in);
        int mode = sc. nextInt();
        int num = sc.nextInt();
        sc.close();
        if(mode == 1){
            File file = new File("Experiments/src/ex6/Arrays.txt");
            if(file.delete()){
                spawnArraysTxt(num);
            }else{
                System.out.println("删除原文件失败,请关闭Array.txt重试!");
                return;
            }
        }else{
            spawnArraysTxt(num);
        }
        long[] timeArray = new long[7];
        for (int i = 0; i < 7; i++) {
            timeArray[i] = TestMethodOuter(i);
        }
        System.out.println("对应时间:");
        for (int i = 1; i < 7; i++) {
            System.out.println(timeArray[i]);
        }
        System.out.println("关键字比较次数:"+Arrays.toString(Compare_Nums));
    }
}
