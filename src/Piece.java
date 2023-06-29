import java.util.ArrayList;

public class Piece {
    // parent class for all the pieces. They all have an icon and a description of their movements.
    public String name;
    public String moveDesc;
    public boolean hasMoved;
    public ArrayList<Move> showMoves(Square[][] squares, int row, int col) {
        return null;
    }
    public String icon;
    public boolean white;
}
