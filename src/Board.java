import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Board extends JFrame implements ActionListener {

    //JFrame board = new JFrame();

    static Square[][] squares;  // the squares on the chessboard

    public static boolean whitesTurn;

    SidePanel sidePanel;
    JPanel chessBoard;
    JMenuBar menuBar;
    JMenu gameMenu;
    JMenu viewMenu; // for setting options related to the layout of the windows
    JMenu newGameMenu;
    JMenuItem optionsItem; // for setting options related to the gameplay
    JMenuItem untimedGame;
    JMenuItem timedGame;
    public static Move whiteKingLoc;
    public static Move blackKingLoc;


    Board() {
        menuBar = new JMenuBar();
        gameMenu = new JMenu("File");
        viewMenu = new JMenu("View");
        newGameMenu = new JMenu("New Game");
        optionsItem = new JMenuItem("Options");
        untimedGame = new JMenuItem("New Untimed Game");
        timedGame = new JMenuItem("New Timed Game");

        optionsItem.addActionListener(this);
        untimedGame.addActionListener(this);
        timedGame.addActionListener(this);

        newGameMenu.add(untimedGame);
        newGameMenu.add(timedGame);
        gameMenu.add(newGameMenu);
        gameMenu.add(optionsItem);

        menuBar.add(gameMenu);
        menuBar.add(viewMenu);

        this.setJMenuBar(menuBar);

        chessBoard = new JPanel();
        squares = new Square[8][8];

        chessBoard.setLayout(new GridLayout(8, 8, 0, 0));

//create an 8x8 array of buttons with a chessboard color pattern
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Square s;
                if (i % 2 == j % 2) {
                    s = new Square(false);
                }
                else {
                    s = new Square(true);
                }
                squares[i][j] = s;
            }
        }

//add the pieces to the buttons
        HelperMethods.addPieces(squares);

// add the array of buttons to the game board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].addActionListener(this);
                chessBoard.add(squares[i][j]);
            }
        }

        whitesTurn = true;
        whiteKingLoc = new Move(7,4);
        blackKingLoc = new Move(0, 4);

        chessBoard.setPreferredSize(new Dimension(960, 960));
        sidePanel = new SidePanel();

        this.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
        this.add(chessBoard);
        this.add(sidePanel);
        this.setSize(1320, 1009);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }



    public static int lastRow, lastCol;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == untimedGame) {

            sidePanel.resetSidePanel();
            HelperMethods.resetBoard(squares);

        } else if (e.getSource() == timedGame) {

            sidePanel.resetSidePanel();
            HelperMethods.resetBoard(squares);

        } else { // one of the chessboard squares was clicked

            // find which square was clicked
            int i, j = 0;
            boolean found = false;
            for (i = 0; i < 8; i++) {
                for (j = 0; j < 8; j++) {
                    if (e.getSource() == squares[i][j]) {
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }

            HelperMethods.squareClicked(squares, i, j, sidePanel);

        }
    } // actionPerformed()
}
