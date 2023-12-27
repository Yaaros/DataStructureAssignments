import java.util.Scanner;

public class Experiment {
    public static void main(String[] args){
        System.out.println("Input ROW and COL");
        Scanner sc = new Scanner(System.in);
        int ROW = sc.nextInt();
        int COL = sc.nextInt();
        sc.close();
        new GameGUI("Game of Life", new World(ROW,COL));
    }
}

