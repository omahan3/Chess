import java.util.ArrayList;

public class Rook extends Piece {


    //sets icon to the black or white unicode chess character upon creation.
    Rook (boolean w) {
        this.name = "rook";
        this.moveDesc = "Rooks may move any number of spaces in any one of the four vertical or horizontal directions.";
        this.white = w;
        this.hasMoved = false;
        if (w) {
            icon = "\u2656";
        } else {
            icon = "\u265C";
        }
    }

    @Override
    public ArrayList<Move> showMoves(Square[][] squares, int row, int col) {

        int checkRow = row;
        int checkCol = col;
        ArrayList<Move> moves = new ArrayList<>();

        while (--checkRow >= 0) { // up
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
                break;
            } else {
                break;
            }
        }
        checkRow = row;
        while (++checkRow < 8) { // down
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
                break;
            } else {
                break;
            }
        }
        checkRow = row;
        while (--checkCol >= 0) { // left
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
                break;
            } else {
                break;
            }
        }
        checkCol = col;
        while (++checkCol < 8) { // right
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
                break;
            } else {
                break;
            }
        }
        return moves;
    }
}
