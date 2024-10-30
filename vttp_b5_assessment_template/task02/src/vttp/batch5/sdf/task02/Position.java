package vttp.batch5.sdf.task02;

public class Position {
    private int x;
    private int y;
    private int utility;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.utility = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }
}
