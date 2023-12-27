package p12;

import org.junit.jupiter.api.Test;

public class Problems {
    public int getMainEle(int[] arr){
        int n = arr.length;
        int[] hash = new int[n];
        for (int j : arr) {
            hash[j]++;
            if (hash[j] > n / 2) {
                return j;
            }
        }
        return -1;
    }
    public int getMinAbsentInt(int[] arr){
        int n = arr.length;
        int[] hash = new int[n+1];
        for (int i : arr) {
            if(i<0||i>n){
                continue;
            }
            hash[i]++;
        }
        for (int i = 1; i <= n; i++) {
            if(hash[i]==0){
                return i;
            }
        }
        return n+1;//找不出来的话说明全出现过了，应该是n+1
    }
    @Test
    public void test(){
        System.out.println(getMainEle(new int[]{0,5,5,3,5,7,5,5}));
        System.out.println(getMainEle(new int[]{0,5,5,3,5,1,5,7}));
        System.out.println(getMinAbsentInt(new int[]{-5,3,2,3}));
        System.out.println(getMinAbsentInt(new int[]{1,2,3,4,5}));
        System.out.println(getMinAbsentInt(new int[]{99,98,97}));
    }
}
