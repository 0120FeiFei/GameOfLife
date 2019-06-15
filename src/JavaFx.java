import javax.swing.*;
import java.awt.*;

public class JavaFx  {
    private JFrame frame;

    public static void main(String[] args) {
        JavaFx jf=new JavaFx();
        jf.CreateFrame("GameOfLive");
        jf.show();

    }

    private void CreateFrame(String DframeName){
        int n = 100; //初始化格子大小
        Cell[][] cellMatrix = new Cell[n][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                cellMatrix[i][j] = new Cell();
            }
        }
        GameOfLifeCore game = new GameOfLifeCore();
        game.clear(cellMatrix);
        this.frame=new JFrame("GameOfLive");
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int widthScreen = screen.width;
        int heightScreen = screen.height;
        this.frame.setSize(widthScreen/2+100, heightScreen);
        this.frame.setLayout(null);
        int panelWidth = widthScreen/2+100;
        int width=panelWidth/n;
        MyPanel panel = new MyPanel(width, cellMatrix);
        panel.setBounds(30,10, panelWidth, panelWidth);

        this.frame.add(panel);
    }

    private void show() {
        this.frame.setVisible(true);
    }


}

