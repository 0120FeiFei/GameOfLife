import javax.swing.*;

public class JavaFx  {
    private JFrame frame;

    public static void main(String[] args) {
        JavaFx jf=new JavaFx();
        jf.CreateFrame("GameOfLive");
        jf.show();

    }

    private void CreateFrame(String frameName){
        int n = 200; //初始化格子大小
        Cell[][] cellMatrix = new Cell[n][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                cellMatrix[i][j] = new Cell();
            }
        }
        GameOfLifeCore game = new GameOfLifeCore();
        game.clear(cellMatrix);
        this.frame=new JFrame("GameOfLive");
        this.frame.setSize(1000,1000);
        this.frame.setLayout(null);
        int panelWidth = 1000;
        int width=(panelWidth -200)/n;
        MyPanel panel = new MyPanel(width, cellMatrix);
        panel.setBounds(30,10, panelWidth, panelWidth);

        this.frame.add(panel);
    }

    private void show() {
        this.frame.setVisible(true);
    }


}

