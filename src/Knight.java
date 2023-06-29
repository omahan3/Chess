import java.util.ArrayList;

public class Knight extends Piece {



    //sets icon to the black or white unicode chess character upon creation.
    Knight (boolean w) {
        this.name = "knight";
        this.moveDesc = "Knights may only move in an \"L\" shape. This means two spaces in one of the four horizontal or vertical directions " +
                "followed by one space in a direction perpendicular to the original direction.";
        this.white = w;
        this.hasMoved = false;
        if (w) {
            icon = "\u2658";
        } else {
            icon = "\u265E";
        }
    }

    @Override
    public ArrayList<Move> showMoves(Square[][] squares, int row, int col) {

        int checkRow, checkCol;
        ArrayList<Move> moves = new ArrayList<>();

        if (((checkRow = row - 2) >= 0) && ((checkCol = col - 1) >= 0)) {  // top left
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        if (((checkRow = row - 2) >= 0) && ((checkCol = col + 1) < 8)) {  // top right
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        if (((checkRow = row - 1) >= 0) && ((checkCol = col + 2) < 8)) {  // right top
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        if (((checkRow = row + 1) < 8) && ((checkCol = col + 2) < 8)) {  // right bottom
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        if (((checkRow = row + 2) < 8) && ((checkCol = col + 1) < 8)) {  // bottom right
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        if (((checkRow = row + 2) < 8) && ((checkCol = col - 1) >= 0)) {  // bottom left
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        if (((checkRow = row + 1) < 8) && ((checkCol = col - 2) >= 0)) {  // left bottom
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        if (((checkRow = row - 1) >= 0) && ((checkCol = col - 2) >= 0)) {  // left top
            if (!squares[checkRow][checkCol].hasPiece) {
                moves.add(new Move(checkRow, checkCol));
            } else if (this.white != squares[checkRow][checkCol].getPiece().white) {
                moves.add(new Move(checkRow, checkCol));
            }
        }
        return moves;
    }
}
