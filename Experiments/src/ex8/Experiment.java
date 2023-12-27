package ex8;

import org.junit.jupiter.api.Test;

import java.util.*;

public class Experiment {
    public static void main(String[] args) {
        UndirectedGraph g = new UndirectedGraph(List.of(List.of(1,2),List.of(0,2,3),List.of(0,1),List.of(1)));
        //dijkstra(g,0);
        Scanner sc = new Scanner(System.in);
        System.out.println("input start and end:");
        int start = sc.nextInt();
        int end = sc.nextInt();
        sc.close();
        Experiment e = new Experiment();
        e.getRelation(g,start,end);
    }
    @Test
    public void test(){
        UndirectedGraph g = new UndirectedGraph(List.of(List.of(1,2),List.of(0,2,3),List.of(0,1),List.of(1)));
        getRelation(g,0,3);
    }
    public UndirectedGraph create(){
        Scanner sc = new Scanner(System.in);
        System.out.println("第一行输入人数n");
        System.out.println("接下来输入n行n列邻接矩阵");
        List<List<Integer>> list = new ArrayList<>();
        int n = sc.nextInt();
        int t = 0;
        while(t<n){
            int m = 0;
            List<Integer> arr = new ArrayList<>();
            while(m<n){
                m++;
                int temp = sc.nextInt();
                if(temp==1){
                    arr.add(m);
                }
            }
            list.add(arr);
            t++;
        }
        sc.close();
        return new UndirectedGraph(list);
    }
    int[] distanceList;
    int[] prevList;
    public void dijkstra(UndirectedGraph g, int start) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < g.vexNum; i++) {
            list.add(i);
        }
        distanceList = new int[g.vexNum];
        prevList = new int[g.vexNum];
        RefreshVertex();
        distanceList[start] = 0;
        while (!list.isEmpty()) {
            int curVertex = chooseMinDistanceVertex(list);
            updateNeighborDistance(g,curVertex,list);
            list.remove((Integer) curVertex);
            /*这里讲一下，如果在这里使用curVex.isVisited=true,
             * 在底下的updateNeighborDistance方法里就可以不传参数list
             * 其中的if条件应该改为if(!v.isVisited())*/
        }
        for (int v = 0;v<g.vexNum;v++) {
            System.out.println("Vertex-"+v+":Distance="+distanceList[v]+"prev="+(prevList[v]!=-1
                    ?prevList[v]:" null"));
            //带v.prev部分就是记录路径用的
        }
        RefreshVertex();
    }

    private void RefreshVertex() {
        Arrays.fill(distanceList, 65536);
        Arrays.fill(prevList, -1);
    }

    private int chooseMinDistanceVertex(ArrayList<Integer> list) {
        Integer min = list.get(0);
        for (int v : list) {
            if(distanceList[v]<distanceList[min]){
                min = v;
            }
        }
        return min;
    }
    /*记录走过路径的Dijkstra算法
     * 给顶点属性加一个prev
     * 初始为null
     * 更新邻居的距离时，如果发现有更小距离，就记录一下
     */
    private void updateNeighborDistance(UndirectedGraph g,int curVertex,ArrayList<Integer> list) {
        for (int v : g.adj[curVertex]) {
            if(list.contains(v)){
                int newDistance = distanceList[curVertex]+1;
                if(distanceList[v]>newDistance){
                    distanceList[v] = newDistance;
                    prevList[v] = curVertex;//记录路径对应这句
                }
            }
        }
    }

    public void getRelation(UndirectedGraph g,int start,int end){
        /*获取v和w之间的所有联系路径以及最小经过几人*/
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < g.vexNum; i++) {
            list.add(i);
        }
        distanceList = new int[g.vexNum];
        prevList = new int[g.vexNum];
        RefreshVertex();
        distanceList[start] = 0;
        while (!list.isEmpty()) {
            int curVertex = chooseMinDistanceVertex(list);
            updateNeighborDistance(g,curVertex,list);
            list.remove((Integer) curVertex);
            /*这里讲一下，如果在这里使用curVex.isVisited=true,
             * 在底下的updateNeighborDistance方法里就可以不传参数list
             * 其中的if条件应该改为if(!v.isVisited())*/
        }
        for (int v = 0;v<g.vexNum;v++) {
            if(v==end) {
                int temp = v;
                while(prevList[temp]!=-1){
                    System.out.print(temp+"的prev为:"+prevList[temp]+",");
                    temp = prevList[temp];
                }
                System.out.println("Vertex-" + v + ":Distance=" + distanceList[v] + (prevList[v] != -1
                        ? "" : " prev= null"));
            }
        }
        RefreshVertex();
    }

    /*广度优先搜索*/
    public void BFS(UndirectedGraph g,int v) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[g.vexNum];
        visited[v] = true;
        q.offer(v);
        while(!q.isEmpty()){
            int j = q.poll();
            System.out.print(j+",");
            LinkedList<Integer> vexAdj = g.adj[j];
            for (Integer k : vexAdj) {
                if(!visited[k]){
                    visited[k] = true;
                    q.offer(k);
                    }
                }
            }
        }

    //使用无向图来模拟这个实验场景
    public static class UndirectedGraph {
        public int vexNum;
        public int edgeNum;
        public LinkedList<Integer>[] adj;//邻接表

        public int getVexNum() {
            return vexNum;
        }

        public int getEdgeNum() {
            return edgeNum;
        }
        public void addEdge(int v1,int v2){
            //在无向图中，边是没有方向的,每个顶点都要添加
            adj[v1].offer(v2);
            adj[v2].offer(v1);
            edgeNum++;
        }
        public LinkedList<Integer> getAdj(int v){//获取v的邻接表
            return adj[v];
        }

        @SuppressWarnings("all")
        public UndirectedGraph(int vexNum) {
            //构造一个含vexNum个离散点的点集
            this.vexNum = vexNum;
            this.edgeNum = 0;
            this.adj = new LinkedList[vexNum];
            for (int i = 0; i < vexNum; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        /*通过输入一个二维表来创建朋友关系图*/
        @SuppressWarnings("all")
        public UndirectedGraph(List<List<Integer>> adjList) {
            this.adj = new LinkedList[adjList.size()];
            for (int i = 0; i < adjList.size(); i++) {
                this.adj[i] = new LinkedList<>();
                for (int j = 0; j < adjList.get(i).size(); j++) {
                    this.adj[i].offer(adjList.get(i).get(j));
                }
            }
            this.vexNum = adjList.size();
            this.edgeNum = 0;
        }
    }
}
