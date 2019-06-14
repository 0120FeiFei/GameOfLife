class GameOfLifeCore {
    /**
     * 随机初始化细胞矩阵
     *
     * @param cellMatrix 待初始化细胞矩阵
     */

    Cell[][] initCellMatrix(Cell[][] cellMatrix) {
        this.clear(cellMatrix);

        for (int i = 1; i < cellMatrix.length - 1; i++) {
            for (int j = 1; j < cellMatrix[0].length - 1; j++) {
                int num = (int) (Math.random() * 20);
                if(num>17){
                    cellMatrix[i][j].setCurState(true);
                }else{
                    cellMatrix[i][j].setCurState(false);
                }
            }
        }
        return cellMatrix;
    }

    /**
     * 此initCellMatrix用来基础测试逻辑是否正确
     *
     * @param cellMatrix 待初始化细胞矩阵
     * @return 初始化后的细胞矩阵
     */
    Cell[][] initCellMatrixTest(Cell[][] cellMatrix) {
        this.clear(cellMatrix);
        for (int i = 1; i < cellMatrix.length - 1; i++) {
            for (int j = 1; j < cellMatrix[0].length - 1; j++) {
                if (i == 1 && j == 2 || i == 1 && j == 3 || i == 2 && j == 3) {
                    cellMatrix[i][j].setCurState(true);
                }
            }
        }
        return cellMatrix;
    }

    void clear(Cell[][] cellMatrix) {
        for (int i = 1; i < cellMatrix.length - 1; i++) {
            for (int j = 1; j < cellMatrix[0].length - 1; j++) {
                cellMatrix[i][j].setCurState(false);
            }
        }
    }

    /**
     * Z形状的细胞矩阵
     *
     * @param cellMatrix 待初始化细胞矩阵
     * @return 初始化后的细胞矩阵
     */
    Cell[][] CellMatrixZ(Cell[][] cellMatrix) {
        this.clear(cellMatrix);
        for (int i = 1; i < cellMatrix.length - 1; i++) {
            for (int j = 1; j < cellMatrix[0].length - 1; j++) {
                if (j == 1 || j == cellMatrix.length - 2 || i + j == cellMatrix.length - 1) {
                    cellMatrix[i][j].setCurState(true);
                }
            }
        }
        return cellMatrix;
    }

    Cell[][] CellMatrixX(Cell[][] cellMatrix) {
        this.clear(cellMatrix);
        for (int i = 1; i < cellMatrix.length - 1; i++) {
            for (int j = 1; j < cellMatrix[0].length - 1; j++) {
                if (i == j || i + j == cellMatrix.length - 1) {
                    cellMatrix[i][j].setCurState(true);
                }
            }
        }
        return cellMatrix;
    }

    /**
     * 计算每个细胞周围活着的细胞数
     *
     * @param x 横坐标
     * @param y 纵坐标
     * @return 每个细胞周围的活细胞数
     */
     int calculateLifeCell(int x, int y, Cell[][] cellMatrix) {
        int countCell = 0; //记录(x,y)周围活着的细胞数量
        int[][] dir = {{0, 1}, {1, 0}, {1, 1}, {0, -1}, {-1, 0}, {-1, -1}, {1, -1}, {-1, 1}};
        for (int i = 0; i < 8; i++) {
            if (cellMatrix[x + dir[i][0]][y + dir[i][1]].getCurState()) {
                countCell += 1;
            }
        }

        return countCell;
    }

    /**
     * 状态转移
     *
     * @param cellMatrix 当前细胞矩阵
     */
    void stateTransform(Cell[][] cellMatrix) {
        for (int i = 1; i < cellMatrix.length - 1; i++) {
            for (int j = 1; j < cellMatrix[0].length - 1; j++) { // 外层为一层虚拟空间，处理边缘问题
                if (calculateLifeCell(i, j, cellMatrix) == 3) {
                    cellMatrix[i][j].setNextState(true);   //当前细胞周围活细胞为3，则当前细胞设置为生
                } else if (calculateLifeCell(i, j, cellMatrix) == 2) {
                    cellMatrix[i][j].setNextState(cellMatrix[i][j].getCurState());        //当前细胞周围活细胞为2，则当前细胞保持不变
                } else {
                    cellMatrix[i][j].setNextState(false);        //其他情况，当前细胞转为死
                }
            }
        }
        for (int i = 1; i < cellMatrix.length - 1; i++) {  //将nextState设置为curState，便于绘制时使用
            for (int j = 1; j < cellMatrix[0].length - 1; j++) {
                cellMatrix[i][j].setCurState(cellMatrix[i][j].getNextState());
            }
        }

    }

}
