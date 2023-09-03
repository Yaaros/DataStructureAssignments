import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Input {
    static int ROW;
    static int COL;
    static final boolean DEAD = false;
    static final boolean LIVE = true;
    static ArrayList<ArrayList<Cell>> cellList = new ArrayList<>();
    static ArrayList<ArrayList<Cell>> cellListUpdate = new ArrayList<>();

    public Input() throws NoSuchElementException {
        System.out.println("Start Input:");
        int generation = initGame();
        System.out.println("Game Start!");
        System.out.println("---------------------------------------");
        System.out.println("Generation 1");
        showState();
        for(int i = 0;i<generation;i++){
            System.out.println("Generation "+(i+2));
            spawnNextGeneration();
            showState();
        }
    }


    //初始化:生成一个初状态(不进行显示)
    private static int initGame() {
        int row,col;
        Scanner in = new Scanner(System.in);
        int generation = in.nextInt()-1;
        ROW = in.nextInt();
        COL= in.nextInt();
        for (row = 0; row < ROW; row++) {
            ArrayList<Cell> arr = new ArrayList<>();
            for (col = 0; col < COL; col++) {
                arr.add(new Cell(DEAD,row,col));
            }
            cellList.add(arr);
        }
        while(true){
            row = in.nextInt();
            col = in.nextInt();
            if(0<=row&&row<ROW&&0<=col&&col<COL){
                cellList.get(row).get(col).setState(LIVE);
            } else if (row==-1 && col==-1) {
                break;
            }else{
                System.out.println("Out of Bounds!");
            }
        }
        in.close();
        return generation;
    }

    //把初始化或某一代结果显示出来
    private static void showState() {
        int row,col;
        System.out.print("\nState of Cell:\n");
        //第一行
        for (col = 0; col < COL; col++) {
            System.out.printf("%3s","___");
        }
        System.out.print("\n");
        for(row=0;row<ROW;row++){
            for(col=0;col<COL;col++){
                if (cellList.get(row).get(col).isState()) {
                    System.out.printf("%3s","│O│");
                }else{
                    System.out.printf("%3s","│X│");
                }
            }
            System.out.println();
            //输出水平分割线
            for(col=0;col<COL;col++){
                System.out.printf("%3s","___");
            }
            System.out.println();
        }
    }

    //演化下一代
    private static void spawnNextGeneration() {
        int row,col;
        for(row = 0;row<ROW;row++){
            ArrayList<Cell> arr = new ArrayList<>();
            for(col=0;col<COL;col++){
                /*若它活着,且周围有2或3个活的，则仍为活的
                * 若它死亡，周围恰好有三个活的，则会复活*/
                if((cellList.get(row).get(col).isState()&&
                        (getLivingCellCountNearby(row,col)==2||getLivingCellCountNearby(row,col)==3))||
                        ((!cellList.get(row).get(col).isState())&&getLivingCellCountNearby(row,col)==3))
                {
                    arr.add(new Cell(LIVE,row,col));
                }
                else{
                    arr.add(new Cell(DEAD,row,col));
                }
            }
            cellListUpdate.add(arr);
        }
        cellList = cellListUpdate;
    }
    //获得周围活着细胞的个数
    private static int getLivingCellCountNearby(int row,int col){
        int c = 0;
        for (int i = row-1; i <= row+1; i++) {
            for (int j = col-1; j <= col+1; j++) {
                if(i<0||i>=ROW||j<0||j>=COL){
                    continue;
                }
                if(cellList.get(i).get(j).isState()&&!(i==row&&j==col)){
                    c++;
                }
            }
        }
/*测试用的代码
        System.out.println("("+row+","+col+")"+c);
*/
        return c;
    }
    //这是一段测试用的代码
    /*private static void getState(){
        for(int i = 0;i < ROW;i++){
            for(int j = 0;j < COL;j++){
                System.out.println(cellListUpdate[i][j].isState());
            }
        }
    }*/
}
