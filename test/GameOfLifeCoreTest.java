import org.junit.Assert;
import org.junit.Test;

public class GameOfLifeCoreTest {
    private int n = 5;
    private GameOfLifeCore game = new GameOfLifeCore();
    private Cell[][] cells = new Cell[n][n];
    /**
     * 测试所用的细胞矩阵初始化
     * @param cells 细胞矩阵
     */
    private void init(Cell[][] cells){
        for(int i = 0 ; i < n ;i++){
            for(int j = 0 ; j < n ; j++){
                cells[i][j] = new Cell();
            }
        }
    }

    @Test
    public void initCellMatrixTest() {
        init(cells);
        game.initCellMatrixTest(cells);
        Assert.assertTrue(cells[1][2].getCurState());
        Assert.assertTrue(cells[1][3].getCurState());
        Assert.assertTrue(cells[2][3].getCurState());
        System.out.println("initCellMatrix test passed!");
    }

    @Test
    public void calculateLifeCell() {
        init(cells);
        cells = game.initCellMatrixTest(cells);
        int num = game.calculateLifeCell(1,3,cells);
        Assert.assertEquals(2,num);
        System.out.println("calculateLifeCell test passed!");
    }

    @Test
    public void stateTransform() {
        init(cells);
        cells = game.initCellMatrixTest(cells);
        game.stateTransform(cells);
        Assert.assertTrue(cells[2][2].getCurState());
        Assert.assertFalse(cells[1][1].getCurState());
        Assert.assertTrue(cells[1][2].getCurState());
        Assert.assertTrue(cells[1][3].getCurState());
        Assert.assertFalse(cells[2][1].getCurState());
        Assert.assertTrue(cells[2][3].getCurState());
        Assert.assertFalse(cells[3][1].getCurState());
        Assert.assertFalse(cells[3][2].getCurState());
        Assert.assertFalse(cells[3][3].getCurState());
        System.out.println("stateTransform test passed!");
    }
}