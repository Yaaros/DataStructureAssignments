import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class World {
    private int ROW,COL;

    private static final boolean DEAD = false;
    private static final boolean LIVE = true;


    private int nowGeneration;

    private ArrayList<ArrayList<Cell>> cellList = new ArrayList<>();
    public World(int ROW,int COL){
        this.ROW=ROW;
        this.COL=COL;
        nowGeneration=0;
        for(int x=0;x<ROW;x++) {
            ArrayList<Cell> arr = new ArrayList<>();
            for (int y = 0; y < COL; y++) {
                arr.add(new Cell(x, y)) ;
            }
            cellList.add(arr);
        }
    }

    public int getROW() {
        return ROW;
    }

    public int getCOL() {
        return COL;
    }

    public boolean getCellState(int x, int y){
        return cellList.get(x).get(y).isLive();
    }

    public int getNowGeneration() {
        return nowGeneration;
    }

    //随机初始化细胞
    public void randomInitCell(){
        for(int x=0;x<ROW;x++)
            for(int y=0;y<COL;y++)
                cellList.get(x).get(y).setState(Math.random() > 0.65);
    }
    public void read() throws IOException {
        int row, col, countOfLivingCells;
        String readLine;
        BufferedReader fis = new BufferedReader(new FileReader("Graphical Interface\\in.txt"));
        while ((readLine = fis.readLine()) != null) {
            //数组Nums表示读取的数据列表，从前到后依次为:
            //活细胞总数，所有活细胞坐标(0<=x,y<row,col)
            String[] Nums = readLine.split(" ");
            countOfLivingCells = Integer.parseInt(Nums[0]);
            for (row = 0; row < ROW; row++) {
                ArrayList<Cell> arr = new ArrayList<>();
                for (col = 0; col < COL; col++) {
                    arr.add(new Cell(DEAD, row, col));
                }
                cellList.add(arr);
            }
            for (int i = 0; i < countOfLivingCells; i++) {
                row = Integer.parseInt(Nums[1 + i * 2]);
                col = Integer.parseInt(Nums[2 + i * 2]);
                if (0 <= row && row < ROW && 0 <= col && col < COL) {
                    cellList.get(row).get(col).setState(LIVE);
                }
            }
        }
    }

    //细胞清零
    public void deleteAllCell(){
        for(int x=0;x<ROW;x++)
            for(int y=0;y<COL;y++)
                cellList.get(x).get(y).setState(false);
        nowGeneration=0;
    }

    //繁衍换代
    public void updateOfCell(){
        int row,col;
        ArrayList<ArrayList<Cell>> cellListUpdate = new ArrayList<>();
        for(row = 0;row<ROW;row++){
            ArrayList<Cell> arr = new ArrayList<>();
            for(col=0;col<COL;col++){
                /*若它活着,且周围有2或3个活的，则仍为活的
                 * 若它死亡，周围恰好有三个活的，则会复活*/
                if((cellList.get(row).get(col).isLive()&&
                        (getLivingCellCountNearby(row,col)==2||getLivingCellCountNearby(row,col)==3))||
                        ((!cellList.get(row).get(col).isLive())&&getLivingCellCountNearby(row,col)==3))
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
        nowGeneration++;
    }
    //获得周围活着细胞的个数
    //注意边缘判定
    private int getLivingCellCountNearby(int row,int col){
        int c = 0;
        for (int i = row-1; i <= row+1; i++) {
            for (int j = col-1; j <= col+1; j++) {
                int temp_i = i;
                int temp_j = j;
                if(i<0){
                    temp_i = ROW-1;
                }
                if(i>=ROW){
                    temp_i = 0;
                }
                if(j<0){
                    temp_j = COL-1;
                }
                if(j>=COL){
                    temp_j = 0;
                }
                if(cellList.get(temp_i).get(temp_j).isLive()&&!(i==row&&j==col)){
                    c++;
                }
            }
        }
        return c;
    }
}