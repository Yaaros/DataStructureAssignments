package p16;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Problems {
    public static void main(String[] args) {
        int[] vexs = new int[]{0,1,2,3,4,5};
        String[] edges = new String[]{"0-1","0-2","0-4","1-3","2-5","3-4","4-5"};
        UnDirectedGraph g = new UnDirectedGraph(vexs,edges);
        System.out.println("原图："+g);
        //System.out.println("是否有路径"+dfs(g,0,5));
        //dfsPrint(g,0);
        //System.out.println(getAllPath(g,0,5));
        Scanner sc = new Scanner(System.in);
        System.out.println("input start and end:");
        int start = sc.nextInt();
        int end = sc.nextInt();
        sc.close();
        System.out.println("是否有路径"+dfs(g,start,end));
        System.out.println(getAllPath(g,start,end));
    }

    /**
     * Problem1
     * @param graph
     * @param start
     * @param end
     * @return ans:Whether there is a path from start to end
     */
    public static boolean dfs(UnDirectedGraph graph,int start,int end){
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        boolean[] visited = new boolean[graph.vexNum];
        while(!stack.isEmpty()){
            int top = stack.pop();
            if(top==end){
                return true;
            }
            for (Integer integer : graph.adjList.get(top)) {
                if(!visited[integer]){
                    stack.push(integer);
                }
                visited[integer] = true;
            }
        }
        return false;
    }

    public static ArrayList<ArrayList<Integer>> getAllPath(UnDirectedGraph graph, int start, int end) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        stack.push(start);
        dfs(graph,start,end,stack,ans);
        return ans;
    }

    private static void dfs(UnDirectedGraph graph, int start, int end, Stack<Integer> stack, ArrayList<ArrayList<Integer>> ans) {
        if(start==end){
            ans.add(new ArrayList<>(stack));
            return;
        }
        for (Integer integer : graph.adjList.get(start)) {
            if(!stack.contains(integer)){
                stack.push(integer);
                dfs(graph, integer, end, stack, ans);
                stack.pop();
            }
        }
    }

    public static void dfsPrint(UnDirectedGraph graph,int start){
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        boolean[] visited = new boolean[graph.vexNum];
        while(!stack.isEmpty()){
            int top = stack.pop();
            if(!visited[top]){
                System.out.println(top);
                visited[top] = true;
            }
            for (Integer integer : graph.adjList.get(top)) {
                if(!visited[integer]){
                    stack.push(integer);
                }
            }
        }
    }

    /**
     * 这是力扣797，人家是有向图，我真不理解为什么学校这么喜欢无向图
     */
    List<List<Integer>> LeetCodeAns = new ArrayList<>();
    Stack<Integer> LeetCodeStack = new Stack<>();
    int n ;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        n = graph.length;
        LeetCodeStack.push(0);
        dfs(graph,0,n-1);
        return LeetCodeAns;
    }
    private void dfs(int[][] graph, int v,int w) {
        if(v==w){
            LeetCodeAns.add(new ArrayList<>(LeetCodeStack));
            return;
        }
        for (int u : graph[v]) {
            LeetCodeStack.push(u);
            dfs(graph,u,w);
            LeetCodeStack.pop();
        }
    }

    static class UnDirectedGraph{
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        ArrayList<Integer> vertexList = new ArrayList<>();
        int vexNum,edgeNum;

        /**
         * 你可以以任意边集的形式传进来，我只会读取每个string的首字符和尾字符并加入邻接表
         * @param Vertexs:点集
         * @param Edges：边集
         */
        UnDirectedGraph(int[] Vertexs, String[] Edges){
            int j = 0;
            for (int vertex : Vertexs) {
                if (!vertexList.contains(vertex)) {
                    vertexList.add(vertex);
                    j++;
                }
            }
            this.vexNum = j;
            for (int i = 0; i < vexNum; i++) {
                adjList.add(new ArrayList<>());
            }
            for (String s : Edges) {
                String[] strs = s.split("");
                int v1 = Integer.parseInt(strs[0]);
                int v2 = Integer.parseInt(strs[strs.length-1]);
                adjList.get(v1).add(v2);
                adjList.get(v2).add(v1);
                edgeNum++;
            }
        }

        @Override
        public String toString() {
            return adjList.toString();
        }
    }
}
