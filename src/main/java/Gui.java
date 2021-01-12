import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.stream.IntStream;

public class Gui extends IOManager {


    private final static int size = 16;
    private final static int dist = 80;

    public static final Color DEFAULT_BORDER_LINE_COLOR = Color.WHITE;
    public static final Color DEFAULT_BACKGROUND_LINE_COLOR = Color.getColor("#ffffff00");//transparent

    private Move bufferMove;

    private JFrame frame;
    private JLabel p1ScoreLabel, p2ScoreLabel, statusLabel;
    private JLabel[][] box;
    private boolean isSetLine[][];
    private JLabel graphicBoard[][];

    private Color player1Color;
    private Color player2Color;
    private Color currentPlayerColor;


    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            bufferMove = getSource(mouseEvent.getSource());
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            setJlabelBackgroundColorAtMouseEvent(mouseEvent, currentPlayerColor, true);

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            setJlabelBackgroundColorAtMouseEvent(mouseEvent, DEFAULT_BACKGROUND_LINE_COLOR, false);
        }

        private void setJlabelBackgroundColorAtMouseEvent(MouseEvent mouseEvent, Color color, boolean mouseEntered) {
            Move move = getSource(mouseEvent.getSource());
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
            frame.dispose();
            Thread thread = new Thread() {
                public void run() {
                    new MainUI().initGUI();
                }
            };
            thread.start();

        }
    };


    public Gui(int boardRows, int boardCols, Player p1, Player p2) {

        int mappedRows = boardRows * 2 + 1;
        int mappedCols = boardCols + 1;

        player1 = p1;
        player2 = p2;
        player1Color = p1.getColor().getAwtColor();
        player2Color = p2.getColor().getAwtColor();

        bufferMove = null;

        graphicBoard = IntStream.range(0, mappedRows)
                .mapToObj(r -> IntStream.range(0, mappedCols)
                        .mapToObj(c -> r % 2 == 0 ? getHorizontalEdge() : getVerticalEdge())
                        .toArray(JLabel[]::new))
                .toArray(JLabel[][]::new);

        isSetLine = new boolean[mappedRows][mappedCols];
        box = new JLabel[boardRows][boardCols];


        frame = new JFrame("Dots and Boxes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();

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

    private Move getSource(Object object) {
        for (int i = 0; i < graphicBoard.length; i++) {
            for (int j = 0; (i % 2 == 0 && j < graphicBoard[0].length - 1) || (i % 2 != 0 && j < graphicBoard[0].length); j++) {
                if (graphicBoard[i][j] == object) {
                    return toMove(i, j);
                }
            }
        }
        return Move.getInvalidMove();
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
        //label.setBackground(Color.BLACK);
        label.setOpaque(true);
        label.setBorder(new LineBorder(Color.BLACK, 10, true));
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
    public void initialize() {

    }

    @Override
    public void updateMove(Move move, Player player) {
        int mappedX = getMappedX(move);
        int mappedY = getMappedY(move);
        graphicBoard[mappedX][mappedY].setBackground(player.getColor().getAwtColor());
        setLine(mappedX, mappedY);
    }

    @Override
    public void updateCompletedBox(int x, int y, Player player) {
        box[x][y].setBackground(player.getColor().getAwtColor());
    }

    @Override
    public void updateGameInfo(Player currentPlayer) {

        currentPlayerColor = currentPlayer.getColor().getAwtColor();

        p2ScoreLabel.setText(String.valueOf(player1.getPoints()));
        p1ScoreLabel.setText(String.valueOf(player2.getPoints()));
        if (currentPlayer == player1) {
            //solver = blueSolver;
            statusLabel.setForeground(player1Color);
            statusLabel.setText("Player 1's Turn...");

        } else {
            //solver = redSolver;
            statusLabel.setForeground(player2Color);
            statusLabel.setText("Player 2's Turn...");
        }

    }

    @Override
    public void showWinner() {
        if (player1.getPoints() > player2.getPoints()) {
            statusLabel.setText("Player-1 is the winner!");
            statusLabel.setForeground(player1Color);
        } else if (player2.getPoints() > player1.getPoints()) {
            statusLabel.setText("Player-2 is the winner!");
            statusLabel.setForeground(player2Color);
        } else {
            statusLabel.setText("Game Tied!");
            statusLabel.setForeground(Color.BLACK);
        }
    }

    private JLabel getNewP1Label(String text){
        JLabel tempLabel = new JLabel(text, SwingConstants.CENTER);
        tempLabel.setForeground(player1Color);
        tempLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        return tempLabel;
    }
    private JLabel getNewP2Label(String text){
        JLabel tempLabel = new JLabel(text, SwingConstants.CENTER);
        tempLabel.setForeground(player2Color);
        tempLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        return tempLabel;
    }

    private void init() {
        int boardWidth = graphicBoard[0].length * size + (graphicBoard[0].length - 1) * dist;

        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        JPanel playerPanel = new JPanel(new GridLayout(2, 2));
        if (graphicBoard[0].length > 3) playerPanel.setPreferredSize(new Dimension(2 * boardWidth, dist));
        else playerPanel.setPreferredSize(new Dimension(2 * boardWidth, 2 * dist));


        playerPanel.add(getNewP1Label("Player 1:"));
        playerPanel.add(getNewP2Label("Player 2:"));
        playerPanel.add(getNewP1Label(player1.getName()));
        playerPanel.add(getNewP2Label(player2.getName()));
        ++constraints.gridy;
        grid.add(playerPanel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        JPanel scorePanel = new JPanel(new GridLayout(2, 2));
        scorePanel.setPreferredSize(new Dimension(2 * boardWidth, dist));

        scorePanel.add(getNewP1Label("Score:"));
        scorePanel.add(getNewP2Label("Score:"));

        p1ScoreLabel = getNewP1Label("0");
        scorePanel.add(p1ScoreLabel);
        p2ScoreLabel = getNewP2Label("0");
        scorePanel.add(p2ScoreLabel);
        ++constraints.gridy;
        grid.add(scorePanel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);


        for (int i = 0; i < graphicBoard.length; i++) {
            JPanel pane = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            for (int j = 0; (i % 2 == 0 && j < graphicBoard[0].length - 1) || (i % 2 != 0 && j < graphicBoard[0].length); j++) {
                if (i % 2 == 0) {
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
            if (i % 2 == 0)
                pane.add(getDot());

            ++constraints.gridy;
            grid.add(pane, constraints);
        }

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        statusLabel = getNewP1Label("Player-1's Turn...");
        ++constraints.gridy;
        grid.add(statusLabel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(2 * boardWidth, 10)), constraints);

        JButton goBackButton = new JButton("Go Back to Main Menu");
        goBackButton.setPreferredSize(new Dimension(boardWidth, dist));
        goBackButton.addActionListener(backListener);
        ++constraints.gridy;
        grid.add(goBackButton, constraints);


        if (frame != null) {
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();

            frame.setContentPane(grid);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

    }
}

