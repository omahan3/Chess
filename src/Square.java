import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Square extends JButton {
    //create a button to add things to for the board square.
    boolean hasPiece; // whether there is a piece occupying this square
    private Piece piece;
    private boolean isBlack;
    private boolean highlighted;
    private boolean emphasized;

    Square(boolean black) {
        if (black) {
            this.setBackground(Color.GRAY);
        } else {
            this.setBackground(Color.LIGHT_GRAY);
        }

        this.setBlack(black);
        this.setFocusable(false);
        hasPiece = false;
        highlighted = false;
        this.setFont(new Font("DejaVu Sans", Font.PLAIN, 96));
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setForeground(Color.BLACK);
        this.setBorderPainted(false);
        this.setOpaque(true);

    }

    public void setPiece(Piece p) {
        this.piece = p;
        this.hasPiece = true;
        this.setText(piece.icon);
    }

    // removes the piece from ths square. Returns piece removed, or null if there wasn't a piece to begin with.
    public Piece removePiece() {
        if (hasPiece) {
            Piece p = this.piece;
            this.piece = null;
            this.hasPiece = false;
            this.setText("");
            return p;
        } else {
            return null;
        }
    }

    public void setBlack(boolean b) {
        this.isBlack = b;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void highlight() {
        if (this.hasPiece) {
            this.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        } else if (Board.whitesTurn) {
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        } else {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        }

        this.setBorderPainted(true);
        highlighted = true;
    }

    public void unhighlight() {
        highlighted = false;
        emphasized = false;
        this.setBorderPainted(false);
    }

    public boolean isEmphasized() {
        return emphasized;
    }

    public void emphasize() {
        emphasized = true;
        this.setBorder(BorderFactory.createLineBorder(Color.PINK, 5));
        this.setBorderPainted(true);
    }
}