import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Gui extends IOManager {

    private final static int DOT_SIZE = 16;
    private final static int BOX_SIZE = 80;
    public static final Color DEFAULT_BORDER_LINE_COLOR = Color.WHITE;
    public static final Color DEFAULT_BACKGROUND_LINE_COLOR = Color.getColor("#ffffff00");

    private JFrame frame;
    private JLabel p1ScoreLabel, p2ScoreLabel, statusLabel;
    private JLabel[][] box, graphicBoard;

    private boolean[][] isSetLine;
    private Move bufferMove;
    private Color currentPlayerColor;

    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            bufferMove = getCoordinateOfTheClickedLine(mouseEvent.getSource());
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            setLabelBackgroundColorAtMouseEvent(mouseEvent, currentPlayerColor, true);
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            setLabelBackgroundColorAtMouseEvent(mouseEvent, DEFAULT_BACKGROUND_LINE_COLOR, false);
        }

        private void setLabelBackgroundColorAtMouseEvent(MouseEvent mouseEvent, Color color, boolean mouseEntered) {
            Move move = getCoordinateOfTheClickedLine(mouseEvent.getSource());
            int x = getMappedX(move), y = getMappedY(move);

            if (mouseEntered && !isSetLine(x, y))
                graphicBoard[x][y].setCursor(new Cursor(Cursor.HAND_CURSOR));
            else
                graphicBoard[x][y].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            if (!isSetLine(x, y))
                graphicBoard[x][y].setBackground(color);
        }

    };

    private ActionListener backListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            backPress = true;

            frame.dispose();
            Thread thread = new Thread(() -> new MainUI().initMenu());
            thread.start();

        }
    };


    public Gui(int boardRows, int boardCols, Player p1, Player p2) {
        super(boardRows, boardCols, p1, p2);

        graphicBoard = new JLabel[mappedRows][mappedCols];

        isSetLine = new boolean[mappedRows][mappedCols];
        box = new JLabel[boardRows][boardCols];

        frame = new JFrame("Dots and Boxes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        prepareGrid();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public boolean isSetLine(int x, int y) {
        return isSetLine[x][y];
    }

    private void setLine(int x, int y) {
        isSetLine[x][y] = true;
    }

    public boolean isSetBox(int x, int y) {
        return box[x][y].getBackground().equals(player1.getColor().getAwtColor()) || box[x][y].getBackground().equals(player2.getColor().getAwtColor());
    }

    private Move getCoordinateOfTheClickedLine(Object object) {
        for (int i = 0; i < graphicBoard.length; i++) {
            for (int j = 0; isValidPositionInMatrix(i, j); j++) {
                if (graphicBoard[i][j] == object) {
                    return getMoveFromGraphicBoardCoordinates(i, j);
                }
            }
        }
        return Move.getInvalidMove();
    }

    private Move getMoveFromGraphicBoardCoordinates(int x, int y) {
        if (isaRawOfHorizontalLines(x)) {
            if (x < graphicBoard.length - 1) {
                return new Move(x / 2, y, Side.UP);
            } else {
                return new Move(x / 2 - 1, y, Side.DOWN);
            }
        } else {
            if (y < graphicBoard[0].length - 1) {
                return new Move(x / 2, y, Side.LEFT);
            } else {
                return new Move(x / 2, y - 1, Side.RIGHT);
            }
        }
    }

    private JLabel getHorizontalEdge() {
        return getLabel(BOX_SIZE, DOT_SIZE);
    }

    private JLabel getVerticalEdge() {
        return getLabel(DOT_SIZE, BOX_SIZE);
    }

    private JLabel getLabel(int size, int dist) {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(size, dist));
        label.setBorder(BorderFactory.createLineBorder(DEFAULT_BORDER_LINE_COLOR));
        label.setBackground(DEFAULT_BACKGROUND_LINE_COLOR);
        label.setOpaque(true);
        label.addMouseListener(mouseListener);
        return label;
    }

    private JLabel getDot() {
        JLabel label = getEmptyLabel(new Dimension(DOT_SIZE, DOT_SIZE));
        label.setBorder(new LineBorder(Color.BLACK, 10, true));
        return label;
    }

    private JLabel getBox() {
        JLabel label = getEmptyLabel(new Dimension(BOX_SIZE, BOX_SIZE));
        label.setOpaque(true);
        return label;
    }

    private JLabel getEmptyLabel(Dimension d) {
        JLabel label = new JLabel();
        label.setPreferredSize(d);
        return label;
    }

    @Override
    public Move readMove() {
        while (bufferMove == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Move moveToPass = bufferMove;
        bufferMove = null;
        return moveToPass;
    }


    @Override
    public void updateMove(Move move, Player player) {
        int mappedX = getMappedX(move);
        int mappedY = getMappedY(move);
        graphicBoard[mappedX][mappedY].setBackground(player.getColor().getAwtColor());
        setLine(mappedX, mappedY);
    }

    @Override
    public void updateCompletedBox(Move move, Player player) {
        int x = move.getX(), y = move.getY();
        box[x][y].setBackground(player.getColor().getAwtColor());
    }

    @Override
    public void updateGameInfo(Player currentPlayer) {
        currentPlayerColor = currentPlayer.getColor().getAwtColor();

        p1ScoreLabel.setText(String.valueOf(player1.getPoints()));
        p2ScoreLabel.setText(String.valueOf(player2.getPoints()));

        statusLabel.setForeground(currentPlayer.getColor().getAwtColor());
        statusLabel.setText(currentPlayer.getName() + "'s Turn...");
    }

    @Override
    public void showWinner() {
        if (player1.getPoints() == player2.getPoints()) {
            statusLabel.setText("Game Tied!");
            statusLabel.setForeground(Color.BLACK);
        } else {
            Player winner = (player1.getPoints() > player2.getPoints() ? player1 : player2);
            statusLabel.setText(winner.getName() + " is the winner!");
            statusLabel.setForeground(winner.getColor().getAwtColor());
        }
    }

    @Override
    public void errorHandler(String msg) {
        if (!getBackPress()) {
            JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.INFORMATION_MESSAGE);
            frame.setVisible(false);
            frame.dispose();
            new Thread(() -> new MainUI().initMenu()).start();
        }
    }

    private JLabel getNewPlayerLabel(String text, Player player) {
        JLabel tempLabel = new JLabel(text, SwingConstants.CENTER);
        tempLabel.setForeground(player.getColor().getAwtColor());
        tempLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        return tempLabel;
    }

    private void prepareGrid() {
        int boardWidth = graphicBoard[0].length * DOT_SIZE + (graphicBoard[0].length - 1) * BOX_SIZE;

        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        JPanel playerPanel = new JPanel(new GridLayout(2, 2));
        if (graphicBoard[0].length > 3) playerPanel.setPreferredSize(new Dimension(2 * boardWidth, BOX_SIZE));
        else playerPanel.setPreferredSize(new Dimension(2 * boardWidth, 2 * BOX_SIZE));


        playerPanel.add(getNewPlayerLabel("Player 1:", player1));
        playerPanel.add(getNewPlayerLabel("Player 2:", player2));
        playerPanel.add(getNewPlayerLabel(player1.getName(), player1));
        playerPanel.add(getNewPlayerLabel(player2.getName(), player2));
        ++constraints.gridy;
        grid.add(playerPanel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        JPanel scorePanel = new JPanel(new GridLayout(2, 2));
        scorePanel.setPreferredSize(new Dimension(2 * boardWidth, BOX_SIZE));

        scorePanel.add(getNewPlayerLabel("Score:", player1));
        scorePanel.add(getNewPlayerLabel("Score:", player2));

        p1ScoreLabel = getNewPlayerLabel("0", player1);
        scorePanel.add(p1ScoreLabel);
        p2ScoreLabel = getNewPlayerLabel("0", player2);
        scorePanel.add(p2ScoreLabel);
        ++constraints.gridy;
        grid.add(scorePanel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);


        addGraphicBoardDesign(grid, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        statusLabel = getNewPlayerLabel(player1.getName() + "'s Turn...", player1);
        ++constraints.gridy;
        grid.add(statusLabel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        JButton goBackButton = new JButton("Go Back to Main Menu");
        goBackButton.setPreferredSize(new Dimension(boardWidth, BOX_SIZE));
        goBackButton.addActionListener(backListener);
        ++constraints.gridy;
        grid.add(goBackButton, constraints);

        frame.setContentPane(grid);
    }

    private void addGraphicBoardDesign(JPanel grid, GridBagConstraints constraints) {
        for (int i = 0; i < graphicBoard.length; i++) {
            JPanel pane = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            for (int j = 0; isValidPositionInMatrix(i, j); j++) {
                if (isaRawOfHorizontalLines(i)) {
                    pane.add(getDot());
                    graphicBoard[i][j] = getHorizontalEdge();
                    pane.add(graphicBoard[i][j]);
                } else {
                    graphicBoard[i][j] = getVerticalEdge();
                    pane.add(graphicBoard[i][j]);
                    if (j < graphicBoard[0].length - 1) {
                        box[i / 2][j] = getBox();
                        pane.add(box[i / 2][j]);
                    }
                }

            }
            if (isaRawOfHorizontalLines(i))
                pane.add(getDot());

            ++constraints.gridy;
            grid.add(pane, constraints);
        }
    }

    private boolean isaRawOfHorizontalLines(int i) {
        return i % 2 == 0;
    }
}

