package p15;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Problems {

    public boolean hasCycle(MyGraph g) {
        if (g == null) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int t = g.getNonSuccessorVex(stack);
        while(true){
            stack.push(t);
            t = g.getNonSuccessorVex(stack);
            if(g.isEmpty(stack)){
                return false;
            }
            if(t==-1){
                return true;
            }
        }
    }


    /*给你一个n个正整数的集合(n>=2)，把它划分成A1和A2,满足：A1、A2的元素个数为n1、n2，元素和为S1、S2
    * 请你给出满足|n1-n2|最小且|S1-S2|最大的划分
    * 分析：第一个条件就是对半分
    * 第二个条件就是排序，选小的一半和大的一半*/
    public int[][] Problem2(int[] arr){
        int[] sortedArr = MergeSort(arr);
        int n = arr.length;
        int n1 = n>>>1;
        int[] ans1 = new int[n1];
        int[] ans2 = new int[n-n1];
        for(int i = n-1,j = 0;i>=n1;i--,j++){
            ans1[j] = sortedArr[i];
        }
        for(int i = n-n1-1,j = 0;i>=0;i--,j++){
            ans2[j] = sortedArr[i];
        }
        return new int[][]{ans1,ans2};
    }

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

    //使用快排的思想在未完全排序的情况下做出这个
    //但我是使用选出来第K小的再扫一遍数组求和的方法
    public int[][] Problem2AnotherVer(int[] arr){
        int K = arr.length/2;
        int ans = findKthLargest(arr,K);
        int[] ans1 = new int[K];
        int[] ans2 = new int[arr.length-K];
        int j = 0;
        int k = 0;
        for (int value : arr) {
            if (value > ans) {
                ans2[j++] = value;
            } else if (value < ans) {
                ans1[k++] = value;
            } else {
                if (k < K) {
                    ans1[k++] = value;
                } else {
                    ans2[j++] = value;
                }
            }
        }
        return new int[][]{ans1,ans2};
    }
    public int findKthLargest(int[] _nums, int k) {
        int n = _nums.length;
        return quickSelect(_nums, 0, n - 1, n - k);
    }
    private int quickSelect(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do {
                i++;
            }while (nums[i] < x);
            do {
                j--;
            } while (nums[j] > x);
            if (i < j){
                swap(nums, i, j);
            }
        }
        if (k <= j) {
            return quickSelect(nums, l, j, k);
        }
        else{
            return quickSelect(nums, j + 1, r, k);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    /*Problem3：输出有向图MyGraph2中所有出度大于入度的顶点个数*/
    public int PrintVertices(MyGraph2 g){
        int ans = 0;
        for (int i = 0; i < g.VexNum; i++) {
            int outDegree = getOutDegree(g, i);
            int inDegree = getInDegree(g, i);
            if(outDegree > inDegree){
                System.out.print(g.vexList[i]+",");
                ans++;
            }
        }
        return ans;
    }

    private int getInDegree(MyGraph2 g, int i) {
        int ans = 0;
        for (int j = 0; j < g.VexNum; j++) {
            ans+=g.AdjMatrix[j][i];
        }
        return ans;
    }

    private int getOutDegree(MyGraph2 g, int i) {
        int ans = 0;
        for (int e : g.AdjMatrix[i]) {
            ans+=e;
        }
        return ans;
    }

    @Test
    public void test(){
        String s = "AB,AC,AD,BC,BE,DE";
        MyGraph g = new MyGraph("ABCDE",s.split(","));
        System.out.println(g);
        System.out.println(hasCycle(g));
        String s2 = "AC,CF,CE,BD,BE";
        MyGraph g2 = new MyGraph("ABCDEF",s2.split(","));
        System.out.println(g2);
        System.out.println(hasCycle(g2));
    }

    private class MyGraph{//有向图
        int vexNum,edgeNum;
        char[] vexList;
        List<Integer>[] ReverseAdjList;//逆邻接表，谁到i有线谁才记录

        public int getIndexOfVex(char vex){
            for (int i = 0; i < vexNum; i++) {
                if(vexList[i] == vex){
                    return i;
                }
            }
            return -1;
        }

        //查找一个没有后继的结点
        public int getNonSuccessorVex(Stack<Integer> stack){
            br:for (int i = 0; i < vexNum; i++) {
                for (List<Integer> list : ReverseAdjList) {
                    if(list.contains(i)){
                        continue br;
                    }
                }
                if(!stack.contains(i)){
                    return i;
                }
            }
            return -1;
        }

        public boolean isEmpty(Stack<Integer> stack){
            return stack.size() == vexNum;
        }

        public MyGraph(int vexNum, int edgeNum, char[] vexList, List<Integer>[] reverseAdjList) {
            this.vexNum = vexNum;
            this.edgeNum = edgeNum;
            this.vexList = vexList;
            ReverseAdjList = reverseAdjList;
        }

        public MyGraph(int vexNum, int edgeNum, List<Integer>[] reverseAdjList) {
            this.vexNum = vexNum;
            this.edgeNum = edgeNum;
            this.vexList = new char[vexNum];
            for (int i = 0; i < vexNum; i++) {
                vexList[i] = (char)(64+i);
            }
            ReverseAdjList = reverseAdjList;
        }

        public MyGraph(String vexList, String[] edgeList) {
            this.vexList = vexList.toCharArray();
            this.vexNum = vexList.length();
            List<Integer>[] ans = new ArrayList[vexNum];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = new ArrayList<>();
            }
            for (String s : edgeList) {
                edgeNum++;
                char[] chars = s.toCharArray();
                ans[getIndexOfVex(chars[1])].add(getIndexOfVex(chars[0]));
            }
            this.ReverseAdjList = ans;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[VexNum="+vexNum+"],[EdgeNum="+edgeNum+"],[VexList="+ Arrays.toString(vexList)+"]\n");
            sb.append("[ReverseAdjList=\n"+Arrays.toString(ReverseAdjList)+"]\n");
            return sb.toString();
        }
    }
    private class MyGraph2{
        int VexNum,EdgeNum;
        int[][] AdjMatrix;
        char[] vexList;
    }
}
