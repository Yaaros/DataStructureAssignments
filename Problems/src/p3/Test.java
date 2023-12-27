package p3;

public class Test {
    public static void main(String[] args) {
        System.out.println(getEveryNumPow2(200));
        System.out.println(getGcd(new int[]{63,72,18,36}));
    }
    private static int getEveryNumPow2(int num) {
        int i = num/100;
        int j = num/10-i*10;
        int k = num%10;
        System.out.println("i="+i);
        System.out.println("j="+j);
        System.out.println("k="+k);

        return i*i+j*j+k*k;
    }

    private static int getGcd(int a,int b){
        if(b==0){
            return a;
        }
        else{
            return getGcd(b,a%b);
        }
    }
    private static int getGcd(int[] arr){
        int temp = arr[0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                temp = getGcd(temp,arr[j]);
            }
        }
        return temp;
    }
}
