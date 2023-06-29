import java.util.ArrayList;

public class Pawn extends Piece {

    //sets icon to the black or white unicode chess character upon creation.
    Pawn (boolean w) {
        this.name = "pawn";
        this.moveDesc = "Pawns may move one space toward the opposite end of the board, unless it is their first move or they are capturing an enemy piece. " +
                "On their first move, pawns may move one or two spaces. " +
                "Pawns may only capture an enemy piece if it is one space away on a diagonal toward the opposite end of the board.";
        this.white = w;
        this.hasMoved = false;
        if (w) {
            icon = "\u2659";
        } else {
            icon = "\u265F";
        }
    }

    @Override
    public ArrayList<Move> showMoves(Square[][] squares, int row, int col) {

        ArrayList<Move> moves = new ArrayList<>();
        int multiplier = 1;

        if (!this.white) { // invert the direction for black
            multiplier = -1;
        }

        // make sure the pawns aren't at their furthest row (this should end up being a moo point eventually with pawn promotion)
        if (((row - multiplier) >= 0) && ((row - multiplier) < 8)) {

            // can move forward one space if it is empty
            if (!squares[row - multiplier][col].hasPiece) {
                moves.add(new Move((row - multiplier), col));
            }

            // hasn't moved yet, can move one or two spaces
            if (!this.hasMoved) {
                if (!squares[row - multiplier*2][col].hasPiece && !squares[row - multiplier][col].hasPiece) {
                    moves.add(new Move((row - (multiplier * 2)), col));
                }
            }

            // can capture a piece
            if (((col + 1) < 8) && squares[row - multiplier][col + 1].hasPiece && (squares[row - multiplier][col + 1].getPiece().white != squares[row][col].getPiece().white)) {
                moves.add(new Move((row - multiplier), (col + 1)));
            }
            if (((col - 1) >= 0) && squares[row - multiplier][col - 1].hasPiece && (squares[row - multiplier][col - 1].getPiece().white != squares[row][col].getPiece().white)) {
                moves.add(new Move((row - multiplier), (col - 1)));
            }
        }
        return moves;
    }

    void printMove () {
        System.out.println(moveDesc);
    }
}
