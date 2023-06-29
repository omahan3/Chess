import java.util.ArrayList;

public class Bishop extends Piece{


    //sets icon to the black or white unicode chess character upon creation.
    Bishop (boolean w) {
        this.name = "bishop";
        this.moveDesc = "Bishops may move in any one of the four diagonal directions.";
        this.white = w;
        this.hasMoved = false;
        if (w) {
            icon = "\u2657";
        } else {
            icon = "\u265D";
        }
    }

    @Override
    public ArrayList<Move> showMoves(Square[][] squares, int row, int col) {

        int checkRow = row;
        int checkCol = col;
        ArrayList<Move> moves = new ArrayList<>();

        while ((--checkRow >= 0) && (--checkCol >= 0)) {
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
        checkCol = col;
        while ((--checkRow >= 0) && (++checkCol < 8)) {
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
        checkCol = col;
        while ((++checkRow < 8) && (++checkCol < 8)) {
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
        checkCol = col;
        while ((++checkRow < 8) && (--checkCol >= 0)) {
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
