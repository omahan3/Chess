import java.util.ArrayList;

public class Queen extends Piece {


    //sets icon to the black or white unicode chess character upon creation.
    Queen (boolean w) {
        this.name = "queen";
        this.moveDesc = "Queens can move any number of spaces in a straight line in any of the 8 directions.";
        this.white = w;
        this.hasMoved = false;
        if (w) {
            icon = "\u2655";
        } else {
            icon = "\u265B";
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
        checkCol = col;
        while ((--checkRow >= 0) && (--checkCol >= 0)) { // top left
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
        while ((--checkRow >= 0) && (++checkCol < 8)) { // top right
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
        while ((++checkRow < 8) && (++checkCol < 8)) { // bottom right
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
        while ((++checkRow < 8) && (--checkCol >= 0)) { // bottom left
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
