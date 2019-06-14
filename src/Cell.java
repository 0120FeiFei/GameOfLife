public class Cell {
    private boolean curState ; //当前状态
    private boolean nextState ; // 下一状态


    public void setCurState(boolean curState) {
        this.curState = curState;
    }

    public void setNextState(boolean nextState) {
        this.nextState = nextState;
    }

    public boolean getCurState() {
        return curState;
    }

    public boolean getNextState() {
        return nextState;
    }

}
