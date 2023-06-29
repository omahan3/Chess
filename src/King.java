import java.util.ArrayList;

public class King extends Piece {

    //sets icon to the black or white unicode chess character upon creation.
    King (boolean w) {
        this.name = "king";
        this.moveDesc = "Kings can move one space in any of the 8 directions.";
        this.white = w;
        this.hasMoved = false;
        if (w) {
            icon = "\u2654";
        } else {
            icon = "\u265A";
        }
    }

    @Override
    public ArrayList<Move> showMoves(Square[][] squares, int row, int col) {
        int checkRow = row;
        int checkCol = col;
        ArrayList<Move> moves = new ArrayList<>();

        int tempRow;
        if (HelperMethods.canCastleLeft(squares, Board.whitesTurn)) {
            if (white) {
                tempRow = 7;
            } else {
                tempRow = 0;
            }
            moves.add(new Move(tempRow, 2));
        }

        if (HelperMethods.canCastleRight(squares, Board.whitesTurn)) {
            if (white) {
                tempRow = 7;
            } else {
                tempRow = 0;
            }
            moves.add(new Move(tempRow, 6));
        }

        if ((--checkCol >= 0)) { // left
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        checkCol = col;
        if ((--checkRow >= 0) && (--checkCol >= 0)) { // top left
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        checkRow = row;
        checkCol = col;
        if ((--checkRow >= 0)) { // top
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        checkRow = row;
        if ((--checkRow >= 0) && (++checkCol < 8)) { // top right
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        checkRow = row;
        checkCol = col;
        if ((++checkCol < 8)) { // right
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        checkCol = col;
        if ((++checkRow < 8) && (++checkCol < 8)) { // bottom right
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        checkRow = row;
        checkCol = col;
        if ((++checkRow < 8)) { // bottom
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        checkRow = row;
        if ((++checkRow < 8) && (--checkCol >= 0)) { // bottom left
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        return moves;
    }
}
