import java.util.ArrayList;

public class HelperMethods {

    public static void addPieces(Square[][] squares) {
//create and add the pieces to an array we can iterate through
        Piece[] pieces = new Piece[64];

        // add black pieces first
        pieces[0] = new Rook(false);
        pieces[1] = new Knight(false);
        pieces[2] = new Bishop(false);
        pieces[3] = new Queen(false);
        pieces[4] = new King(false);
        pieces[5] = new Bishop(false);
        pieces[6] = new Knight(false);
        pieces[7] = new Rook(false);
        for (int i = 8; i < 16; i++) {
            pieces[i] = new Pawn(false);
        }

        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].setPiece(pieces[count]);
                count++;
            }
        }

        // then add white pieces
        for (int i = 16; i < 24; i++) {
            pieces[i] = new Pawn(true);
        }
        pieces[24] = new Rook(true);
        pieces[25] = new Knight(true);
        pieces[26] = new Bishop(true);
        pieces[27] = new Queen(true);
        pieces[28] = new King(true);
        pieces[29] = new Bishop(true);
        pieces[30] = new Knight(true);
        pieces[31] = new Rook(true);

        for (int i = 6; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].setPiece(pieces[count]);
                count++;
            }
        }
    }

    public static void unhighlightBoard(Square[][] squares) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].unhighlight();
            }
        }
    }

    public static void resetBoard(Square[][] squares) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].removePiece();
            }
        }
        addPieces(squares);
        Board.whitesTurn = true;
        unhighlightBoard(squares);
        Board.whiteKingLoc.setPosition(7, 4);
        Board.blackKingLoc.setPosition(0, 4);
    }

    public static void squareClicked(Square[][] squares, int row, int col, SidePanel sidePanel) {

        // if the previously clicked piece can move to the currently clicked square...
        if (squares[row][col].isHighlighted() && (squares[Board.lastRow][Board.lastCol].getPiece().white == Board.whitesTurn)) {

            Move previous = new Move(Board.lastRow, Board.lastCol);
            Piece capturedPiece = null;
            boolean pieceCaptured = false;
            // capture an opponent's piece
            if (squares[row][col].hasPiece) {
                capturedPiece = squares[row][col].getPiece();
                squares[row][col].setPiece(squares[Board.lastRow][Board.lastCol].removePiece());
                sidePanel.addCapturedPiece(capturedPiece);
                pieceCaptured = true;

            } else { // just move the piece

                // logic for castling
                if (squares[Board.lastRow][Board.lastCol].getPiece().name.equals("king") && HelperMethods.canCastleLeft(squares, Board.whitesTurn) && col == 2) {
                    squares[row][3].setPiece(squares[row][0].removePiece());
                } else if (squares[Board.lastRow][Board.lastCol].getPiece().name.equals("king") && HelperMethods.canCastleRight(squares, Board.whitesTurn) && col == 6) {
                    squares[row][5].setPiece(squares[row][7].removePiece());
                }

                // actually moving the piece
                squares[row][col].setPiece(squares[Board.lastRow][Board.lastCol].removePiece());

            }

    // pawn promotion
            if (squares[row][col].getPiece().name.equals("pawn")) {
                if (row == 0) {
                    // promote white pawn
                } else if (row == 7) {
                    // promote black pawn
                }
            }

            // for keeping track of where the kings are to make Check checks easier
            if (squares[row][col].getPiece().name.equals("king")) {
                if (squares[row][col].getPiece().white) {
                    Board.whiteKingLoc.setPosition(row, col);
                } else {
                    Board.blackKingLoc.setPosition(row, col);
                }
            }

            // check for Check
            ArrayList<Move> whiteCheck = checkForCheckOnWhite(squares, Board.whiteKingLoc.row, Board.whiteKingLoc.col);
            ArrayList<Move> blackCheck = checkForCheckOnBlack(squares, Board.whiteKingLoc.row, Board.whiteKingLoc.col);

            // player didn't just move into check
            if ((Board.whitesTurn && whiteCheck.isEmpty()) || (!Board.whitesTurn && blackCheck.isEmpty())) {
                squares[row][col].getPiece().hasMoved = true;
                Board.whitesTurn = !Board.whitesTurn;
                sidePanel.changeTurn(Board.whitesTurn);
                HelperMethods.unhighlightBoard(squares);
            } else {
                // can't move into check
                squares[previous.row][previous.col].setPiece(squares[row][col].removePiece());
                if (pieceCaptured) {
                    squares[row][col].setPiece(capturedPiece);
                    sidePanel.removeCapturedPiece(capturedPiece);
                }
                System.out.println("Can't move into check");
            }


/*          check / checkmate stuff. Still need to work on it.

            whiteCheck = checkForCheckOnWhite(squares, Board.whiteKingLoc.row, Board.whiteKingLoc.col);
            if (!whiteCheck.isEmpty()) {
                ArrayList<Move> whiteKingMoves = squares[Board.whiteKingLoc.row][Board.whiteKingLoc.col].getPiece().showMoves(squares, Board.whiteKingLoc.row, Board.whiteKingLoc.col);
                System.out.println("White king is in check");

                // checking for checkmate
                boolean checkmate = true;
                for (Move move : whiteKingMoves) {
                    if (checkForCheckOnWhite(squares, move.row, move.col).isEmpty()) { // there is an available square to which the king can move
                        checkmate = false;
                        break;
                    }
                }
                if (checkmate) {
                    System.out.println("Game over, Black won");
                }
            }

            blackCheck = checkForCheckOnBlack(squares, Board.blackKingLoc.row, Board.blackKingLoc.col);
            if (!blackCheck.isEmpty()) {
                ArrayList<Move> blackKingMoves = squares[Board.blackKingLoc.row][Board.blackKingLoc.col].getPiece().showMoves(squares, Board.blackKingLoc.row, Board.blackKingLoc.col);
                System.out.println("Black king is in check");

                boolean checkmate = true;
                for (Move move : blackKingMoves) {
                    if (checkForCheckOnBlack(squares, move.row, move.col).isEmpty()) {
                        checkmate = false;
                        break;
                    }
                }
                if (checkmate) {
                    System.out.println("Game over, White won");
                }
            }

 */


        } else if (squares[row][col].isEmphasized()) { // same piece is clicked
            HelperMethods.unhighlightBoard(squares);

        } else {

            HelperMethods.unhighlightBoard(squares);

            if (squares[row][col].hasPiece) {
                squares[row][col].emphasize();
                Board.lastRow = row;
                Board.lastCol = col;
                highlightSquares(squares, squares[row][col].getPiece().showMoves(squares, row, col));

            }
        }
    }

    public static void highlightSquares(Square[][] squares, ArrayList<Move> moves) {
        for (Move move: moves) {
            squares[move.row][move.col].highlight();
        }
    }

    public static boolean canCastleLeft(Square[][] squares, boolean whiteTurn) {

        int row;
        if (whiteTurn) {
            row = 7;
        } else {
            row = 0;
        }
        for (int i = 1; i < 4; i++) {
            if (squares[row][i].hasPiece) {
                return false;
            }
        }

        return squares[row][0].hasPiece && !squares[row][0].getPiece().hasMoved
                && squares[row][4].hasPiece && !squares[row][4].getPiece().hasMoved;
    }

    public static boolean canCastleRight(Square[][] squares, boolean whiteTurn) {

        int row;
        if (whiteTurn) {
            row = 7;
        } else {
            row = 0;
        }
        for (int i = 5; i < 7; i++) {
            if (squares[row][i].hasPiece) {
                return false;
            }
        }

        return squares[row][7].hasPiece && !squares[row][7].getPiece().hasMoved
                && squares[row][4].hasPiece && !squares[row][4].getPiece().hasMoved;
    }

    public static ArrayList<Move> checkForCheckOnWhite(Square[][] squares, int row, int col) {
        // go through all the squares to check for pieces, checking all the moves each piece can make to see if any of the valid moves are at the same position as the white king
        // returns a list of locations which have the white king in check

        ArrayList<Move> attackingPieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j].hasPiece && !squares[i][j].getPiece().white) {
                    ArrayList<Move> moves = squares[i][j].getPiece().showMoves(squares, i, j);
                    for (Move move : moves) {
                        if ((move.row == row) && (move.col == col)) {
                            attackingPieces.add(new Move(move.row, move.col));
                        } // added the attacker's location
                    } // going through the moves
                } // valid attacker on square
            } // cols
        } // rows

        return attackingPieces;
    }

    public static ArrayList<Move> checkForCheckOnBlack(Square[][] squares, int row, int col) {
        // go through all the squares to check for pieces, checking all the moves each piece can make to see if any of the valid moves are at the same position as the black king
        // returns a list of locations which have the black king in check

        ArrayList<Move> attackingPieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (squares[i][j].hasPiece && squares[i][j].getPiece().white) {
                    ArrayList<Move> moves = squares[i][j].getPiece().showMoves(squares, i, j);
                    for (Move move : moves) {
                        if ((move.row == row) && (move.col == col)) {
                            attackingPieces.add(new Move(move.row, move.col));
                        } // added the attacker's location
                    } // going through the moves
                } // valid attacker on square
            } // cols
        } // rows

        return attackingPieces;
    }
}
