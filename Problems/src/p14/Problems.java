package p14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class Problems {

    /*Problem1直接取Graph里的文件就行*/

    //全国卷第一次考图算法
    //欧拉路径
    //其实就是判断图里度为奇数的顶点数是不是0或者2
    public boolean Problem2(MyGraph g){
        int degree = 0;//记录遍历过程中每个结点的度
        int countOfVexOfOddDegree = 0;
        for (int i = 0; i < g.vexNum; i++) {
            for (int j = 0; j < g.vexNum; j++) {
                if(g.adj[i][j] > 0){
                    degree++;
                }
            }
            if(degree%2 != 0){
                countOfVexOfOddDegree++;
            }
            degree = 0;
        }
        return countOfVexOfOddDegree == 0 || countOfVexOfOddDegree == 2;
    }
    //纯粹按照定义走的方法
    //这办法真心不咋样，就是往后递归就行了
    //但test2的数据告诉我们，其实这个方法更快，原因是我另一个方法写的不太好，主方法里的复杂度从n变成了2n
    public boolean Problem3_1(int[] arr){
        return check(arr,0);
    }

    private boolean check(int[] arr,int i){
        if(i>=arr.length){
            return true;
        }
        int j = 2*i+1;
        int k = 2*i+2;
        if(j < arr.length&&k < arr.length){
            if((arr[j]==-1||arr[j]==0)&&(arr[k]==-1||arr[k]==0)){
                return check(arr,j)&&check(arr,k);
            }else if(arr[j]==-1||arr[j]==0){
                return (arr[i]<=arr[k])&&check(arr,j)&&check(arr,k);
            }else if(arr[k]==-1||arr[k]==0){
                return (arr[i]>=arr[j])&&check(arr,j)&&check(arr,k);
            }else{
                return arr[i]>=arr[j]&&arr[i]<=arr[k]&&check(arr,j)&&check(arr,k);
            }
        }
        return true;
    }


    int[] inSerial;
    int index = 0;
    //按照二叉排序树的性质：中序序列是递增的
    //只需要会顺序存储的二叉树如何中序遍历即可
    //但这个效率就是有点慢
    public boolean Problem3_2(int[] arr){
        int n = arr.length;
        inSerial = new int[n];
        inOrder(arr,0);
        index = 0;
        int i = 0;
        while(i < n-1){
            //若下一个为0则遍历结束，否则拿下一个和这个比
            if(inSerial[++i]==0){
                break;
            }
            if(inSerial[i]<inSerial[i-1]){
                return false;
            }
        }
        return true;
    }

    private void inOrder(int[] arr,int start) {
        if(start >= arr.length){
            return;
        }
        int i = start*2+1;
        int j = start*2+2;
        inOrder(arr,i);
        if(arr[start]!=-1&&arr[start]!=0){
            inSerial[index++] = arr[start];
        }
        inOrder(arr,j);
    }

    //非递归方法烂完了
    public boolean Problem3_3(int[] arr){
        int n = arr.length;
        inSerial = new int[n];
        inOrder2(arr);
        index = 0;
        int i = 0;
        while(i < n-1){
            //若下一个为0则遍历结束，否则拿下一个和这个比
            if(inSerial[++i]==0){
                break;
            }
            if(inSerial[i]<inSerial[i-1]){
                return false;
            }
        }
        return true;
    }

    private void inOrder2(int[] arr){
        Stack<Integer> stack = new Stack<>();
        int curr = 0;
        int n = arr.length;
        while(curr<n||!stack.isEmpty()){
            if(curr<n){
                stack.push(curr);
                curr = curr*2+1;
            }else{
                int pop = stack.pop();
                if(arr[pop]!=-1&&arr[pop]!=0){
                    inSerial[index++] = arr[pop];
                }
                curr = pop*2+2;
            }
        }
    }

    @Test
    public void test(){
        MyGraph g = new MyGraph(3,3,new char[]{'A','B','C'},new int[][]{{0,1,0},{1,0,1},{0,1,0}});
        boolean p2 = Problem2(g);
        System.out.println("Problem2:");
        Assertions.assertTrue(Problem2(g));
        System.out.println(p2);
        int[] tree1 = new int[]{40,25,60,-1,30,-1,80,-1,-1,27,0,0};
        int[] tree2 = new int[]{40,50,60,-1,30,-1,-1,-1,-1,-1,35,0};
        System.out.println("Problem3方法1:");
        System.out.println(Problem3_1(tree1));
        System.out.println(Problem3_1(tree2));
        System.out.println("Problem3方法2:");
        System.out.println(Problem3_2(tree1));
        System.out.println(Problem3_2(tree2));
        System.out.println("Problem3方法3:");
        System.out.println(Problem3_3(tree1));
        System.out.println(Problem3_3(tree2));

    }
    /*比较Problem3的三种方法究竟有多大差距*/
    @Test
    public void test2(){
        int[] tree1 = new int[]{40,25,60,-1,30,-1,80,-1,-1,27,0,0};
        int[] tree2 = new int[]{40,50,60,-1,30,-1,-1,-1,-1,-1,35,0};
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {
            Problem3_1(tree1);
            Problem3_1(tree2);
        }
        System.out.println("方法1用时:");
        System.out.println(System.currentTimeMillis()-start);
        start = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {
            Problem3_2(tree1);
            Problem3_2(tree2);
        }
        System.out.println("方法2用时:");
        System.out.println(System.currentTimeMillis()-start);
        start = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {
            Problem3_3(tree1);
            Problem3_3(tree2);
        }
        System.out.println("方法3用时:");
        System.out.println(System.currentTimeMillis()-start);
    }
    /*番外：求一个无环连通图或称自由树的任意两点之间最短路径的最大值
    * 拿弗洛伊德算法求几次是可以的，但是有没有办法很快地做出来?*/
}
class MyGraph{
    int vexNum;
    int edgeNum;
    char[] vexList = new char[vexNum];
    int[][] adj = new int[vexNum][vexNum];//邻接矩阵

    public MyGraph(int vexNum, int edgeNum, char[] vexList, int[][] adj) {
        this.vexNum = vexNum;
        this.edgeNum = edgeNum;
        this.vexList = vexList;
        this.adj = adj;
    }
}