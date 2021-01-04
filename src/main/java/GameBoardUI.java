import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class GameBoardUI {
    private final static int size = 8;
    private final static int dist = 40;
    public static final java.awt.Color TRANSPARENT_COLOR = java.awt.Color.getColor("#ffffff00");

    public static final java.awt.Color DEFAULT_BORDER_LINE_COLOR = java.awt.Color.WHITE;
    public static final java.awt.Color DEFAULT_BACKGROUND_LINE_COLOR = TRANSPARENT_COLOR;
    public static final java.awt.Color BLUE_COLOR = new java.awt.Color(59, 105, 177);
    public static final java.awt.Color RED_COLOR = new java.awt.Color(244, 67, 54);

    private int n;
    private boolean mouseEnabled;

    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;

    protected Board board;
    protected Graphic graphic;

    GameSolver redSolver, blueSolver, solver;
    String redName, blueName;
    MainUI parent;

    private JLabel[][] hEdge, vEdge, box;
    private boolean[][] isSetHEdge, isSetVEdge;

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
            Move location = getSource(mouseEvent.getSource());
            int x = location.getXtwoMatrixRepresentation(), y = location.getYtwoMatrixRepresentation();
            if (location.isHorizontal()) {
                if (isSetHEdge[x][y]) return;
                hEdge[x][y].setBackground(currentPlayer.getColor().getAwtColor());
            } else {
                if (isSetVEdge[x][y]) return;
                vEdge[x][y].setBackground(currentPlayer.getColor().getAwtColor());
            }
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            if (!mouseEnabled) return;
            Move location = getSource(mouseEvent.getSource());
            int x = location.getXtwoMatrixRepresentation(), y = location.getYtwoMatrixRepresentation();
            if (location.isHorizontal()) {
                if (isSetHEdge[x][y]) return;
                hEdge[x][y].setBackground(DEFAULT_BACKGROUND_LINE_COLOR);
            } else {
                if (isSetVEdge[x][y]) return;
                vEdge[x][y].setBackground(DEFAULT_BACKGROUND_LINE_COLOR);
            }
        }
    };


    private void processMove(Move move) {
        int x = move.getXtwoMatrixRepresentation(), y = move.getYtwoMatrixRepresentation();
        ArrayList<Point> ret;
        if (move.isHorizontal()) {
            if (isSetHEdge[x][y]) return;
            board.drawLine(move);
            hEdge[x][y].setBackground(currentPlayer.getColor().getAwtColor()); //otherwise DARK_GREY
            isSetHEdge[x][y] = true;
        } else {
            if (isSetVEdge[x][y]) return;
            board.drawLine(move);
            vEdge[x][y].setBackground(currentPlayer.getColor().getAwtColor());
            isSetVEdge[x][y] = true;
        }
        graphic.updateMove(move, currentPlayer);

        boolean atLeastOnePointScoredByCurrentPlayer = false;

        if (board.isBoxCompleted(move)) {
            currentPlayer.onePointDone();
            graphic.addCompletedBox(move.getX(), move.getY(), currentPlayer.getId());
            box[move.getX()][move.getY()].setBackground(currentPlayer.getColor().getAwtColor());
            atLeastOnePointScoredByCurrentPlayer = true;
        }

        Move otherMove = board.getNeighbourSideMove(move);
        if (otherMove.getSide() != Side.INVALID && board.isBoxCompleted(otherMove)) {
            currentPlayer.onePointDone();
            graphic.addCompletedBox(otherMove.getX(), otherMove.getY(), currentPlayer.getId());
            box[otherMove.getX()][otherMove.getY()].setBackground(currentPlayer.getColor().getAwtColor());
            atLeastOnePointScoredByCurrentPlayer = true;
        }

        printScoreBoard();

        redScoreLabel.setText(String.valueOf(player1.getPoints()));
        blueScoreLabel.setText(String.valueOf(player2.getPoints()));

        if (boardIsComplete()) {
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

    public void printScoreBoard() {
        System.out.println(graphic.getStringBoard());
        System.out.println("Player " + player1.getId() + " got " + player1.getPoints() + " points");
        System.out.println("Player " + player2.getId() + " got " + player2.getPoints() + " points");
        System.out.println("Is the turn of Player" + currentPlayer.getId());
    }

    private boolean boardIsComplete() {
        return player1.getPoints() + player2.getPoints() >= n;
    }

    private void manageGame() {
        while (!boardIsComplete()) {
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

    private Move getSource(Object object) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < (n - 1); j++)
                if (hEdge[i][j] == object)
                    return new Move(i, j, true, n - 1, n - 1);
        for (int i = 0; i < (n - 1); i++)
            for (int j = 0; j < n; j++)
                if (vEdge[i][j] == object)
                    return new Move(i, j, false, n - 1, n - 1);
        return new Move(-1, -1, Side.INVALID);
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
        label.setBorder(new LineBorder(java.awt.Color.BLACK,10,true));
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
        this.redSolver = redSolver;
        this.blueSolver = blueSolver;
        this.redName = redName;
        this.blueName = blueName;
        initGame();
    }

    private boolean goBack;

    private ActionListener backListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            goBack = true;
        }
    };


    private void initGame() {

        board = new Board(n - 1, n - 1);
        int boardWidth = n * size + (n - 1) * dist;

        player1 = new Player('A', Color.BLU);
        player2 = new Player('B', Color.RED);
        currentPlayer = player1;
        solver = redSolver;
        graphic = new Graphic(n - 1, n - 1);

        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        JPanel playerPanel = new JPanel(new GridLayout(2, 2));
        if (n > 3) playerPanel.setPreferredSize(new Dimension(2 * boardWidth, dist));
        else playerPanel.setPreferredSize(new Dimension(2 * boardWidth, 2 * dist));
        playerPanel.add(new JLabel("<html><font color='red'>Player-1:", SwingConstants.CENTER));
        playerPanel.add(new JLabel("<html><font color='blue'>Player-2:", SwingConstants.CENTER));
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


        hEdge = new JLabel[n][n - 1];
        isSetHEdge = new boolean[n][n - 1];

        vEdge = new JLabel[n - 1][n];
        isSetVEdge = new boolean[n - 1][n];

        box = new JLabel[n - 1][n - 1];

        for (int i = 0; i < (2 * n - 1); i++) {
            JPanel pane = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            if (i % 2 == 0) {
                pane.add(getDot());
                for (int j = 0; j < (n - 1); j++) {
                    hEdge[i / 2][j] = getHorizontalEdge();
                    pane.add(hEdge[i / 2][j]);
                    pane.add(getDot());
                }
            } else {
                for (int j = 0; j < n; j++) {
                    vEdge[i / 2][j] = getVerticalEdge();
                    pane.add(vEdge[i / 2][j]);

                    if (j < n - 1) {
                        box[i / 2][j] = getBox();
                        pane.add(box[i / 2][j]);
                    }
                }
                //vEdge[n - 1][i / 2] = getVerticalEdge();
                //pane.add(vEdge[n - 1][i / 2]);
            }
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
