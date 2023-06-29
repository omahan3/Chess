public class Move {
    int row;
    int col;

    public Move() {
        this.row = 0;
        this.col = 0;
    }
    public Move(int checkRow, int checkCol) {
        this.row = checkRow;
        this.col = checkCol;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

}
