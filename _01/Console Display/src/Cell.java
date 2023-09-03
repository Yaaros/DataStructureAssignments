public class Cell {
    private boolean state;
    private int x;
    private int y;

    public Cell() {
        state = false;
    }

    public Cell(int x, int y) {
        this.state = false;
        this.x = x;
        this.y = y;
    }

    public Cell(boolean state, int x, int y) {
        this.state = state;
        this.x = x;
        this.y = y;
    }

    /**
     * 获取
     * @return state
     */
    public boolean isState() {
        return state;
    }

    /**
     * 设置
     * @param state
     */
    public void setState(boolean state) {
        this.state = state;
    }

    /**
     * 获取
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * 设置
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 获取
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * 设置
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    public String toString() {
        return "Cell{state = " + state + ", x = " + x + ", y = " + y + "}";
    }
}
