package p10;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

public class Problems {
    public static void main(String[] args) {
        //Problem1,@Test里面不能用键盘输入流因此只能写在main方法里
        Scanner sc = new Scanner(System.in);
        StringBuilder stb = new StringBuilder();
        String str;
        while(!(str=sc.next()).equals("0")){
            stb.append(str);
        }
        System.out.println(isReversal(stb.toString()));
    }
    public static boolean isReversal(String str){
        String[] arr = str.split("");
        Queue<String> queue = new Queue<>();
        Stack<String> stack = new Stack<>();
        for (String a : arr) {
            char c = a.charAt(0);
            if(Character.isUpperCase(c)) {
                a = a.toLowerCase();
            }
            if(!Character.isLetter(c)){
                continue;
            }
            queue.offer(a);
            stack.push(a);
        }
        while(!queue.isEmpty()){
            String str1 = queue.poll();
            String str2 = stack.pop();
            if(str1.equals(str2)){
                continue;
            }
            return false;
        }
        return true;
    }
    /*创建两个单词的公用链表的算法我写出来了，可以直接拿到交叉点
    * 但我还是会写一个方法去接收两个单链表头，然后寻找交叉点的方法*/
    @Test
    public void Problem2(){
        getWordsCross("loading","being");
    }

    public void getWordsCross(String word1,String word2){
        int l1 = word1.length()-1;
        int l2 = word2.length()-1;
        char[] words1 = word1.toCharArray();
        char[] words2 = word2.toCharArray();
        ListNode END = new ListNode('*');
        ListNode p = END;
        int endPoint1 = l1;
        int endPoint2 = l2;
        for(int i = l1, j = l2; i>=0 && j>=0 ; i--, j--){
            if(words1[i]==words2[j]){
                p = new ListNode(words2[j],p);
                endPoint1--;
                endPoint2--;
            }else{
                break;
            }
        }
        //交叉点在这里就可以直接给出来了
        ListNode cross = p;

        ListNode start1 = new ListNode('$');
        ListNode p1 = start1;
        ListNode start2 = new ListNode('$');
        ListNode p2 = start2;
        for (int i = 0; i < endPoint1; i++) {
            p1.next = new ListNode(words1[i]);
            p1 = p1.next;
        }
        p1.next = cross;
        for (int j = 0; j < endPoint2; j++) {
            p2.next = new ListNode(words2[j]);
            p2 = p2.next;
        }
        p2.next = cross;
        System.out.println(start1);
        System.out.println(start2);
        ListNode ans = getCross(start1,start2);
        if (ans == null) {
            System.out.println("No Cross");
            return;
        }
        System.out.println(ans.val);
    }

    /**
     *
     * @param start1:第一个链表头
     * @param start2:第二个链表头
     * @return 相交结点
     * 思路：先求出两个链表的长度差，然后把较长链表的指针移到同长度位置，再同时移动两个指针看是否相等
     */
    private ListNode getCross(ListNode start1, ListNode start2) {
        int l1 = 0,l2 = 0;
        ListNode p1 = start1;
        ListNode p2 = start2;
        while(p1.val!='*'){
            l1++;
            p1 = p1.next;
        }
        while(p2.val!='*'){
            l2++;
            p2 = p2.next;
        }
        p1 = start1;
        p2 = start2;
        if(l1>l2){
            for (int i = 0; i < l1 - l2; i++) {
                p1 = p1.next;
            }
        } else if (l1<l2) {
            for (int i = 0; i < l2 - l1; i++) {
                p2 = p2.next;
            }
        }
        while(p1!=p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1.val=='*'?null:p1;
    }


    /**
     *
     * @param str:输入字符串
     * @return ：输出结点头
     */
    public ListNode BuildList(String str){
        char[] chars = str.toCharArray();
        ListNode n = new ListNode('$');
        ListNode p = n;
        for (char c : chars) {
            p.next = new ListNode(c);
            p = p.next;
        }
        return n;
    }

    /**
     * ListNode是源自力扣结点的写法.
     * 采用这种方式而不是像之前一样写一个list类是因为Problem2的List是假List
     * 这里的List可以多对一，其实是一种图，所以只能拿结点开始表示
     */
    public static class ListNode {
        public char val;
        public ListNode next;

        public ListNode(char val) {
            this.val = val;
            this.next = null;
        }

        public ListNode(char val, ListNode next) {
            this.val = val;
            this.next = next;
        }
        /*以及一个toString方法*/

        @Override
        public String toString() {
            ListNode n = this;
            StringBuilder stb = new StringBuilder();
            stb.append("[");
            while(n!=null){
                stb.append(n.val);
                stb.append(",");
                n = n.next;
            }
            stb.deleteCharAt(stb.lastIndexOf(","));
            stb.append("]");
            return stb.toString();
        }
    }
//以下是Problem3
    @Test
    public void Problem3(){
        TreeNode root = new TreeNode(1,
            new TreeNode(2,
                new TreeNode(4)),
            new TreeNode(3,
                new TreeNode(5),
                new TreeNode(6)));
        //System.out.println(getWeight(root));
    }

    /*public int getWeight(TreeNode root){

    }
    public int getWeightInRecursion(int level,int sum){

    }
*/
    public static class TreeNode {
        public int weight;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int weight){
            this.weight = weight;
        }
        public TreeNode(int weight, TreeNode left) {
            this.weight = weight;
            this.left = left;
            this.right = null;
        }
        public TreeNode(int weight, TreeNode left, TreeNode right) {
            this.weight = weight;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.valueOf(this.weight);
        }
    }

}
