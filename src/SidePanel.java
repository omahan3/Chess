import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SidePanel extends JPanel {

    JLabel info = new JLabel("<html>White starts at the bottom of the board." +
            "<br><br>Queens may move any number of spaces in a straight line in any of the 8 directions." +
            "<br><br>Kings may move one space in any of the 8 directions." +
            "<br><br>Rooks may move any number of spaces in any one of the four vertical or horizontal directions." +
            "<br><br>Bishops may move any number of spaces in any one of the four diagonal directions." +
            "<br><br>Knights must move in an \"L\" shape. This means two spaces in one of the four horizontal or vertical directions " +
            "followed by one space in a direction perpendicular to the original direction." +
            "<br><br>Pawns may move one space toward the opposite end of the board, unless it is their first move or they are capturing an enemy piece. " +
            "On their first move, pawns may move one or two spaces. " +
            "Pawns may only capture an enemy piece if it is one space away on a diagonal toward the opposite end of the board.<html>"
    );

    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;
    private JLabel blackGraveyard;
    private JLabel whiteGraveyard;
    private JPanel centerPanel;
    private JLabel wtiia;



    SidePanel() {
        info.setBounds(0, 0, 600, 470);
        info.setFont(new Font(null, Font.PLAIN, 16));

        Color background = new Color(237, 221, 202);
        this.setPreferredSize(new Dimension(360, 960));
        this.setBackground(background);

        wtiia = new JLabel("White's Turn");
        wtiia.setFont(new Font("DejaVu Sans", Font.BOLD, 36));

        blackPieces = new ArrayList<>();
        whitePieces = new ArrayList<>();
        blackGraveyard = new JLabel();
        whiteGraveyard = new JLabel();
        centerPanel = new JPanel();

        blackGraveyard.setFont(new Font("DejaVu Sans", Font.PLAIN, 57));
        whiteGraveyard.setFont(new Font("DejaVu Sans", Font.PLAIN, 57));
        blackGraveyard.setVerticalAlignment(SwingConstants.BOTTOM);
        whiteGraveyard.setVerticalAlignment(SwingConstants.TOP);
        Dimension d = new Dimension(360, 242);
        blackGraveyard.setPreferredSize(d);
        whiteGraveyard.setPreferredSize(d);
        Border b = BorderFactory.createLoweredBevelBorder();
        Font f = new Font("DejaVu Sans", Font.PLAIN, 24);
        blackGraveyard.setBorder(BorderFactory.createTitledBorder(b, "Pieces captured by White", TitledBorder.LEFT, TitledBorder.BELOW_TOP, f));
        whiteGraveyard.setBorder(BorderFactory.createTitledBorder(b, "Pieces captured by Black", TitledBorder.LEFT, TitledBorder.ABOVE_BOTTOM, f));

        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(background);
        centerPanel.add(wtiia, 0);


        this.setLayout(new BorderLayout());
        this.add(whiteGraveyard, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(blackGraveyard, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public void addCapturedPiece(Piece piece) {
        if (piece.white) {
            whitePieces.add(piece);
        } else {
            blackPieces.add(piece);
        }
        displayCapturedPieces();
    }

    public void removeCapturedPiece(Piece piece) {

        if (piece.white) {
            for (Piece whitePiece : whitePieces) {
                if (piece.name.equals(whitePiece.name)) {
                    whitePieces.remove(whitePiece);
                    break;
                }
            }
        } else {
            for (Piece blackPiece : blackPieces) {
                if (piece.name.equals(blackPiece.name)) {
                    blackPieces.remove(blackPiece);
                    break;
                }
            }
        }
        displayCapturedPieces();
    }

    public void displayCapturedPieces() {
        StringBuilder whiteString = new StringBuilder();
        for (Piece whitePiece : whitePieces) {
            whiteString.append(whitePiece.icon).append(" ");
        }
        if (whiteString.length() > 0) {
            whiteString.deleteCharAt(whiteString.length()-1);
        }
        whiteGraveyard.setText("<html>" + whiteString + "<html>");

        StringBuilder blackString = new StringBuilder();
        for (Piece blackPiece : blackPieces) {
            blackString.append(blackPiece.icon).append(" ");
        }
        if (blackString.length() > 0) {
            blackString.deleteCharAt(blackString.length() - 1);
        }
        blackGraveyard.setText("<html>" + blackString + "<html>");
    }

    public void resetSidePanel() {
        blackGraveyard.setText("");
        blackPieces.clear();
        whiteGraveyard.setText("");
        whitePieces.clear();
        wtiia.setText("White's Turn");
    }

    public void changeTurn(boolean currentTurnWhite) {
        if (currentTurnWhite) {
            wtiia.setText("White's Turn");
        } else {
            wtiia.setText("Black's Turn");
        }
    }


}