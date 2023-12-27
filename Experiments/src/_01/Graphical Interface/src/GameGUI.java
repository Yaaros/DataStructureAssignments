import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/*新增功能：
* 从文件中读取
* 鼠标点击可以生成活细胞(实现方法:每个按钮装上监听器,
* 用lambda改变细胞状态,开始游戏的逻辑再改为"有细胞存活时即可开始")*/
public class GameGUI extends JFrame implements ActionListener {
    int ROW,COL;
    private World world;
    private  JButton[][] TWorld;

    private  int[][] TWordTemp;
    private JLabel NowGeneration;
    private JButton readFromFile,randomInit,BeginAndOver,StopAndContinue,Next,Exit;
    private boolean isRunning;
    private Thread thread;

    public GameGUI(String name,World world){
        super(name);
        this.ROW=world.getROW();
        this.COL=world.getCOL();
        this.world=world;
        initGameGUI();
    }

    public void initGameGUI(){
        JPanel backPanel,bottomPanel,centerPanel;
        backPanel= new JPanel(new BorderLayout());
        bottomPanel=new JPanel();
        centerPanel=new JPanel(new GridLayout(ROW,COL));
        this.setContentPane(backPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        backPanel.add(centerPanel,"Center");
        backPanel.add(bottomPanel,"South");

        TWorld=new JButton[ROW][COL];
        NowGeneration=new JLabel("当前代数：0");
        readFromFile = new JButton("从文件中读取");
        randomInit=new JButton("随机生成细胞");
        BeginAndOver=new JButton("开始游戏");
        StopAndContinue=new JButton("暂停游戏");
        Next=new JButton("下一代");
        Exit=new JButton("退出");
        for(int x=0;x<ROW;x++){
            for(int y=0;y<COL;y++){
                TWorld[x][y]=new JButton("");
                TWorld[x][y].setBackground(new Color(160,160,160));
                int finalX = x;
                int finalY = y;//这两行是匿名内部类和lambda的语法要求
                TWorld[x][y].addActionListener(e -> {
                    world.setAlive(finalX, finalY);
                    showWorld();
                });
                centerPanel.add(TWorld[x][y]);
            }
        }

        bottomPanel.add(readFromFile);
        bottomPanel.add(randomInit);
        bottomPanel.add(BeginAndOver);
        bottomPanel.add(StopAndContinue);
        bottomPanel.add(Next);
        bottomPanel.add(NowGeneration);
        bottomPanel.add(Exit);

        //设置窗口
        int sizeROW,sizeCOL;
        sizeROW=Math.min((ROW+1)*40,800);
        sizeCOL=Math.max(COL*40,500);
        this.setSize(sizeCOL,sizeROW);
        this.setResizable(true);
        this.setLocationRelativeTo(null);//让窗口居中显示
        this.setVisible(true);

        //注册监听器
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e){
                System.exit(0);
            }
        });
        readFromFile.addActionListener(this);
        randomInit.addActionListener(this);
        BeginAndOver.addActionListener(this);
        StopAndContinue.addActionListener(this);
        Next.addActionListener(this);
        Exit.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == randomInit&&BeginAndOver.getText()=="开始游戏") {//随机生成第一代
            world.randomInitCell();
            showWorld();
            isRunning = false;
            thread = null;
            randomInit.setText("重新生成");
        } else if (e.getSource() == BeginAndOver && BeginAndOver.getText() == "开始游戏"&&AliveExited()) {//开始游戏
            isRunning = true;
            thread = new Thread(() -> {
                while (isRunning) {
                    Change();
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            thread.start();
            BeginAndOver.setText("结束游戏");
        } else if (e.getSource() == BeginAndOver && BeginAndOver.getText() == "结束游戏") {//结束游戏
            isRunning = false;
            thread = null;
            world.deleteAllCell();
            showWorld();
            BeginAndOver.setText("开始游戏");
            StopAndContinue.setText("暂停游戏");
            randomInit.setText("随机生成细胞");
            NowGeneration.setText("当前代数：0");
        } else if (e.getSource() == StopAndContinue && StopAndContinue.getText() == "暂停游戏") {//暂停
            isRunning = false;
            thread = null;
            StopAndContinue.setText("继续游戏");
        } else if (e.getSource() == StopAndContinue && StopAndContinue.getText() == "继续游戏") {//继续
            isRunning = true;
            thread = new Thread(() -> {
                while (isRunning) {
                    Change();
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            thread.start();
            StopAndContinue.setText("暂停游戏");
        } else if (e.getSource() == Next && StopAndContinue.getText() == "继续游戏") {//下一代
            Change();
            isRunning = false;
            thread = null;
        } else if(e.getSource()==Exit){//退出游戏
            isRunning = false;
            thread = null;
            this.dispose();
            System.exit(0);
        } else if (e.getSource()==readFromFile) {//从文件中读取
            try {
                world.read();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            showWorld();
            isRunning = false;
            thread = null;
            randomInit.setText("重新生成");
        }
    }

    private boolean AliveExited() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if(world.getCellState(i,j))return true;
            }
        }
        return false;
    }

    public void Change(){
        world.updateOfCell();
        showWorld();
        NowGeneration.setText("当前代数："+world.getNowGeneration());
    }
    //刷新一下

    public void showWorld(){
        for(int x=0;x<ROW;x++){
            for(int y=0;y<COL;y++){
                if(world.getCellState(x,y)){
                    TWorld[x][y].setBackground(Color.green);
                }
                else{
                    TWorld[x][y].setBackground(Color.white);
                }
            }
        }
    }
}