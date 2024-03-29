import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class MyPanel extends JPanel implements ActionListener, MouseListener,ChangeListener, ItemListener,MouseMotionListener {
    private int cellDiameter;
    private Cell[][] cells;
    private JButton btn=new JButton();
    private JSlider speed=new JSlider(0,400,200);
    private GameOfLifeCore game = new GameOfLifeCore();
    private Timer timer;
    private boolean isInitialState =true;

    private Color color = new Color(148,0,211);

    MyPanel(int _width, Cell[][] _cells){
        super();
        this.cellDiameter =_width;
        this.cells=_cells;
        this.timer=new Timer(200,this);

        this.setLayout(null);
        Dimension screen  = Toolkit.getDefaultToolkit().getScreenSize();
        int widthScreen = screen.width;

        this.btn.setBounds(widthScreen /2-70,70,80,30);
        this.btn.setText("Start");
        this.btn.addActionListener(this);
        JButton btnColor = new JButton("Color");
        btnColor.setBounds(widthScreen /2-160,70,80,30);
        btnColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = JColorChooser.showDialog(null,"Select color", Color.PINK);
                if(color == null){
                    color = Color.RED;
                }
            }
        });

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.speed.setBounds(0,10,widthScreen /2,50 );
        this.speed.setPaintTrack(true);
        this.speed.setMajorTickSpacing(20);
        this.speed.setMinorTickSpacing(5);
        this.speed.setPaintTicks(true);
        this.speed.setPaintLabels(true);
        this.speed.setOrientation(SwingConstants.HORIZONTAL);
        this.speed.setInverted(false);
        this.speed.addChangeListener(this);

        String[] items={"None", "Random", "X", "Z"};
        JComboBox<String> selectInit = new JComboBox<>(items);
        selectInit.setBounds(8,70,80,30);
        selectInit.addItemListener(this);


        this.add(this.btn);
        this.add(btnColor);
        this.add(this.speed);
        this.add(selectInit);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i=1;i<this.cells.length-1;i++){
            for(int j=1;j<this.cells[i].length-1;j++){
                if(cells[i][j].getCurState())
                    g.setColor(color);
                else
                    g.setColor(Color.white);
                g.fillOval(this.cellDiameter *i, this.cellDiameter *j+100, this.cellDiameter, this.cellDiameter);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btn){
            if(e.getSource()==this.btn){
                if(btn.getText().equals("Start")){
                    this.isInitialState =false;
                    btn.setText("Stop");
                    this.timer.start();
                }else {
                    this.isInitialState =true;
                    btn.setText("Start");
                    this.timer.stop();
                }
            }
        }
        this.game.stateTransform(this.cells);
        this.repaint();
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            if(isInitialState){
                int x=e.getX();
                int y=e.getY();
                if(!(x<0||x>800||y<100||y>900)){
                    int i=x/this.cellDiameter;
                    int j=(y-100)/this.cellDiameter;
                    this.cells[i][j].setCurState(!this.cells[i][j].getCurState());
                    this.repaint();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
       int x = e.getX();
        int y = e.getY();
        int i=x/this.cellDiameter;
        int j=(y-100)/this.cellDiameter;
        this.cells[i][j].setCurState(!this.cells[i][j].getCurState());
        this.repaint();

    }


    @Override
    public void stateChanged(ChangeEvent e) {
        timer.setDelay(400-this.speed.getValue());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(isInitialState){
            if(e.getStateChange()==ItemEvent.SELECTED){
                switch (e.getItem().toString()){
                    case "Random":
                        this.cells=this.game.initCellMatrix(this.cells);
                        break;
                    case "X":
                        this.cells=this.game.CellMatrixX(this.cells);
                        break;
                    case "Z":
                        this.cells=this.game.CellMatrixZ(this.cells);
                        break;
                    case "None":
                        this.game.clear(this.cells);
                }
                this.repaint();
            }
        }
    }

}
