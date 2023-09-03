import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Input -1 to read from file('in.txt')");
        System.out.println("If you are willing to read from inputted data,");
        System.out.println("please input numbers in sequence:");
        System.out.println("Firstly,input 0 ");
        System.out.println("Secondly,input the number of generation");
        System.out.println("Then input the number of row and col");
        System.out.println("Lastly,the coordinates of living cells.");
        System.out.println("If you are willing to end inputting,input' -1 -1 '.");
        System.out.println("For example, '0',When 'Game Start!' get displayed," +
                "input '5 3 3 0 0 1 1 0 1 1 0 -1 -1'.");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n==-1){
            new IO();
        }else {
            new Input();
        }
        sc.close();
    }
}
