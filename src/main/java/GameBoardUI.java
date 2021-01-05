import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameBoardUI extends Game {


    private final static int size = 8;
    private final static int dist = 40;
    public static final java.awt.Color TRANSPARENT_COLOR = java.awt.Color.getColor("#ffffff00");

    public static final java.awt.Color DEFAULT_BORDER_LINE_COLOR = java.awt.Color.WHITE;
    public static final java.awt.Color DEFAULT_BACKGROUND_LINE_COLOR = TRANSPARENT_COLOR;
    public static final java.awt.Color BLUE_COLOR = new java.awt.Color(59, 105, 177);
    public static final java.awt.Color RED_COLOR = new java.awt.Color(244, 67, 54);

    private int n;
    private int nBoxInRows;
    private int nBoxInCols;
    private boolean atLeastOnePointScoredByCurrentPlayer;


    private boolean mouseEnabled;



    GameSolver redSolver, blueSolver, solver;
    String redName, blueName;
    MainUI parent;

    private JLabel graphicBoard[][];
    private JLabel[][] box;
    private boolean[][] isSetEdge;

    private JFrame frame;
    private JLabel redScoreLabel, blueScoreLabel, statusLabel;

    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (!mouseEnabled) return;
            processMove(getSource(mouseEvent.getSource()));
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            if (!mouseEnabled) return;
            Move move = getSource(mouseEvent.getSource());
            int x = mapX(move), y = mapY(move);
            if (isSetEdge[x][y]) return;
            graphicBoard[x][y].setBackground(currentPlayer.getColor().getAwtColor());
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            if (!mouseEnabled) return;
            Move move = getSource(mouseEvent.getSource());
            int x = mapX(move), y = mapY(move);
            if (isSetEdge[x][y]) return;
            graphicBoard[x][y].setBackground(DEFAULT_BACKGROUND_LINE_COLOR);
        }
    };


    public Board initializeBoard() {
        return null;
    }


    public static int mapY(Move move) {
        if (move.getSide() == Side.RIGHT)
            return move.getY() + 1;
        else return move.getY();
    }

    public static int mapX(Move move) {
        int tempX = move.getX() * 2 + 1;
        if (move.getSide() == Side.UP)
            return tempX - 1;
        if (move.getSide() == Side.DOWN)
            return tempX + 1;
        else return tempX;
    }

    private void processMove(Move move) {
        int x = mapX(move), y = mapY(move);

        if (isSetEdge[x][y]) return;
        board.drawLine(move);
        graphicBoard[x][y].setBackground(currentPlayer.getColor().getAwtColor()); //otherwise DARK_GREY
        isSetEdge[x][y] = true;

        cmd.updateMove(move, currentPlayer);

        atLeastOnePointScoredByCurrentPlayer = false;


        fillBoxIfCompletedAndUpdateScore(move);
        Move otherMove = board.getNeighbourSideMove(move);
        fillBoxIfCompletedAndUpdateScore(otherMove);

        printScoreBoard();

        redScoreLabel.setText(String.valueOf(player1.getPoints()));
        blueScoreLabel.setText(String.valueOf(player2.getPoints()));

        if (isGameFinished()) {
            finalGraphics();
            setWinnerLabel();
        }

        if (!atLeastOnePointScoredByCurrentPlayer) {
            if (currentPlayer == player1) {
                currentPlayer = player2;
                solver = blueSolver;
                statusLabel.setText("Player-2's Turn...");
                statusLabel.setForeground(BLUE_COLOR);
            } else {
                currentPlayer = player1;
                solver = redSolver;
                statusLabel.setText("Player-1's Turn...");
                statusLabel.setForeground(RED_COLOR);
            }
        }
    }

    private void setWinnerLabel() {
        if (player1.getPoints() > player2.getPoints()) {
            statusLabel.setText("Player-1 is the winner!");
            statusLabel.setForeground(RED_COLOR);
        } else if (player2.getPoints() > player1.getPoints()) {
            statusLabel.setText("Player-2 is the winner!");
            statusLabel.setForeground(BLUE_COLOR);
        } else {
            statusLabel.setText("Game Tied!");
            statusLabel.setForeground(java.awt.Color.BLACK);
        }
    }

    private void fillBoxIfCompletedAndUpdateScore(Move lastMove){
        if (lastMove.getSide() != Side.INVALID && board.isBoxCompleted(lastMove)) {
            currentPlayer.onePointDone();
            cmd.addCompletedBox(lastMove.getX(), lastMove.getY(), currentPlayer.getId());
            box[lastMove.getX()][lastMove.getY()].setBackground(currentPlayer.getColor().getAwtColor());
            atLeastOnePointScoredByCurrentPlayer = true;
        }
    }

    private void manageGame() {
        while (!isGameFinished()) {
            if (goBack) return;
            if (solver == null) {
                mouseEnabled = true;
            } else {
                mouseEnabled = false;
                //processMove(solver.getNextMove(board, turn));
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private Move toMove(int x, int y) {
        if (x % 2 == 0) {
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

    private Move getSource(Object object) {
        for (int i = 0; i < graphicBoard.length; i++) {
            JPanel pane = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            for (int j = 0; (i % 2 == 0 && j < graphicBoard[0].length - 1) || (i % 2 != 0 && j < graphicBoard[0].length); j++) {
                if (graphicBoard[i][j] == object) {
                    return toMove(i, j);
                }
            }
        }
        return Move.getInvalidMove();
    }

    private JLabel getHorizontalEdge() {
        return getjLabel(dist, size);
    }

    private JLabel getVerticalEdge() {
        return getjLabel(size, dist);
    }

    private JLabel getjLabel(int size, int dist) {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(size, dist));
        label.setBorder(BorderFactory.createLineBorder(DEFAULT_BORDER_LINE_COLOR));
        label.setBackground(DEFAULT_BACKGROUND_LINE_COLOR);
        label.setOpaque(true);
        label.addMouseListener(mouseListener);
        return label;
    }

    private JLabel getDot() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(size, size));
        //label.setBackground(java.awt.Color.BLACK);
        label.setOpaque(true);
        label.setBorder(new LineBorder(java.awt.Color.BLACK, 10, true));
        return label;
    }

    private JLabel getBox() {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(dist, dist));
        label.setOpaque(true);
        return label;
    }

    private JLabel getEmptyLabel(Dimension d) {
        JLabel label = new JLabel();
        label.setPreferredSize(d);
        return label;
    }

    public GameBoardUI(MainUI parent, JFrame frame, int numberOfDotsInARow, GameSolver redSolver, GameSolver blueSolver, String redName, String blueName) {
        this.parent = parent;
        this.frame = frame;
        this.n = numberOfDotsInARow;
        this.nBoxInRows = n - 1;
        this.nBoxInCols = n - 1;
        this.redSolver = redSolver;
        this.blueSolver = blueSolver;
        this.redName = redName;
        this.blueName = blueName;
        startGame();
    }

    private boolean goBack;

    private ActionListener backListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            goBack = true;
        }
    };


    public void startGame() {

        board = new Board(n - 1, n - 1);
        int boardWidth = n * size + (n - 1) * dist;

        player1 = new Player('A', Color.RED);
        player2 = new Player('B', Color.BLU);
        currentPlayer = player1;
        solver = redSolver;
        cmd = new Cmd(n - 1, n - 1);

        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        JPanel playerPanel = new JPanel(new GridLayout(2, 2));
        if (n > 3) playerPanel.setPreferredSize(new Dimension(2 * boardWidth, dist));
        else playerPanel.setPreferredSize(new Dimension(2 * boardWidth, 2 * dist));
        playerPanel.add(new JLabel("<html><font color='red'>Player 1:", SwingConstants.CENTER));
        playerPanel.add(new JLabel("<html><font color='blue'>Player 2:", SwingConstants.CENTER));
        playerPanel.add(new JLabel("<html><font color='red'>" + redName, SwingConstants.CENTER));
        playerPanel.add(new JLabel("<html><font color='blue'>" + blueName, SwingConstants.CENTER));
        ++constraints.gridy;
        grid.add(playerPanel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        JPanel scorePanel = new JPanel(new GridLayout(2, 2));
        scorePanel.setPreferredSize(new Dimension(2 * boardWidth, dist));
        scorePanel.add(new JLabel("<html><font color='red'>Score:", SwingConstants.CENTER));
        scorePanel.add(new JLabel("<html><font color='blue'>Score:", SwingConstants.CENTER));
        redScoreLabel = new JLabel("0", SwingConstants.CENTER);
        redScoreLabel.setForeground(RED_COLOR);
        scorePanel.add(redScoreLabel);
        blueScoreLabel = new JLabel("0", SwingConstants.CENTER);
        blueScoreLabel.setForeground(BLUE_COLOR);
        scorePanel.add(blueScoreLabel);
        ++constraints.gridy;
        grid.add(scorePanel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

/*        hEdge = new JLabel[n - 1][n];
        isSetHEdge = new boolean[n - 1][n];

        vEdge = new JLabel[n][n - 1];
        isSetVEdge = new boolean[n][n - 1];*/

        int mappedRows = nBoxInRows * 2 + 1;
        int mappedCols = nBoxInCols + 1;
        graphicBoard = new JLabel[mappedRows][mappedCols];
        isSetEdge = new boolean[mappedRows][mappedCols];

        box = new JLabel[nBoxInRows][nBoxInCols];


        for (int i = 0; i < mappedRows; i++) {
            JPanel pane = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            for (int j = 0; (i % 2 == 0 && j < mappedCols - 1) || (i % 2 != 0 && j < mappedCols); j++) {
                if (i % 2 == 0) {
                    pane.add(getDot());
                    graphicBoard[i][j] = getHorizontalEdge();
                    pane.add(graphicBoard[i][j]);
                } else {
                    graphicBoard[i][j] = getVerticalEdge();
                    pane.add(graphicBoard[i][j]);
                    if (j < mappedCols - 1) {
                        box[i / 2][j] = getBox();
                        pane.add(box[i / 2][j]);
                    }
                }

            }
            if (i % 2 == 0)
                pane.add(getDot());

            ++constraints.gridy;
            grid.add(pane, constraints);
        }

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        statusLabel = new JLabel("Player-1's Turn...", SwingConstants.CENTER);
        statusLabel.setForeground(RED_COLOR);
        statusLabel.setPreferredSize(new Dimension(2 * boardWidth, dist));
        ++constraints.gridy;
        grid.add(statusLabel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        JButton goBackButton = new JButton("Go Back to Main Menu");
        goBackButton.setPreferredSize(new Dimension(boardWidth, dist));
        goBackButton.addActionListener(backListener);
        ++constraints.gridy;
        grid.add(goBackButton, constraints);

        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();

        frame.setContentPane(grid);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        goBack = false;
        manageGame();

        while (!goBack) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        parent.initGUI();
    }
}
