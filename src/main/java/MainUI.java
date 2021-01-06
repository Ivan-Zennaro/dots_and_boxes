import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;

public class MainUI {

    private int n;
   // private GameSolver redSolver, blueSolver;
    private String me, blueName;

    private JFrame frame;
    private JLabel modeError, sizeError;

    String[] playersType = {"Select player", "Human", "Computer Facile", "Computer Difficile", "Random"};
    private JRadioButton[] sizeButton;

    JComboBox<String> blueList;
    JTextField meTextField;
    ButtonGroup sizeGroup;

    public MainUI() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        meTextField = new JTextField();
        blueList = new JComboBox<String>(playersType);

        sizeButton = new JRadioButton[8];
        sizeGroup = new ButtonGroup();
        for(int i=0; i<8; i++) {
            String size = String.valueOf(i+3);
            sizeButton[i] = new JRadioButton(size + " x " + size);
            sizeGroup.add(sizeButton[i]);
        }
    }

    private JLabel getEmptyLabel(Dimension d) {
        JLabel label = new JLabel();
        label.setPreferredSize(d);
        return label;
    }

    private boolean startGame;

    /*private GameSolver getSolver(int level) {
        if(level == 1) return new RandomSolver();
        else if(level == 2) return new GreedySolver();
        else if(level == 3) return new MinimaxSolver();
        else if(level == 4) return new AlphaBetaSolver();
        else if(level == 5) return new MCSolver();
        else return null;
    }*/

    private MouseListener clickonit = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            meTextField.setText("");
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    private ActionListener submitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String meName = meTextField.getText();
            int bIndex = blueList.getSelectedIndex();
            if(meName.equals("") || bIndex==0) {
                modeError.setText("You MUST select the players before continuing.");
                return;
            }
            else {
                modeError.setText("");
                me = meName;
                blueName = playersType[bIndex];
                //if(meName > 1) redSolver = getSolver(meName - 1);
                //if(bIndex > 1) blueSolver = getSolver(bIndex - 1);
            }
            for(int i=0; i<8; i++) {
                if(sizeButton[i].isSelected()) {
                    n = i+3;
                    startGame = true;
                    return;
                }
            }
            sizeError.setText("You MUST select the size of board before continuing.");
        }
    };

    public void initGUI() {

       // redSolver = null;
       // blueSolver = null;

        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        //JLabel titleLabel = new JLabel(new ImageIcon(getClass().getResource("title.png")));
        //grid.add(titleLabel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500,25)), constraints);

        modeError = new JLabel("", SwingConstants.CENTER);
        modeError.setForeground(Color.RED);
        modeError.setPreferredSize(new Dimension(500, 25));
        ++constraints.gridy;
        grid.add(modeError, constraints);

        JPanel modePanel = new JPanel(new GridLayout(2, 2));
        modePanel.setPreferredSize(new Dimension(400, 50));
        modePanel.add(new JLabel("<html><font color='Black'>Player-1:", SwingConstants.CENTER));
        modePanel.add(new JLabel("<html><font color='Black'>Player-2:", SwingConstants.CENTER));
        modePanel.add(meTextField);
        modePanel.add(blueList);
        meTextField.setText("Your Name");
        meTextField.addMouseListener(clickonit);

        blueList.setSelectedIndex(0);
        ++constraints.gridy;
        grid.add(modePanel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500,25)), constraints);

        sizeError = new JLabel("", SwingConstants.CENTER);
        sizeError.setForeground(Color.RED);
        sizeError.setPreferredSize(new Dimension(500, 25));
        ++constraints.gridy;
        grid.add(sizeError, constraints);

        ++constraints.gridy;
        JLabel messageLabel = new JLabel("Select the size of the board:");
        messageLabel.setPreferredSize(new Dimension(400, 50));
        grid.add(messageLabel, constraints);

        JPanel sizePanel = new JPanel(new GridLayout(4, 2));
        sizePanel.setPreferredSize(new Dimension(400, 100));
        for(int i=0; i<8; i++)
            sizePanel.add(sizeButton[i]);
        sizeGroup.clearSelection();
        ++constraints.gridy;
        grid.add(sizePanel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);

        JButton submitButton = new JButton("Start Game");
        submitButton.addActionListener(submitListener);
        ++constraints.gridy;
        grid.add(submitButton, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);

        frame.setContentPane(grid);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        startGame = false;
        while(!startGame) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new GameBoardUI(this, frame, n, null, null, me, blueName);
       //new GamePlay(this, frame, n, redSolver, blueSolver, redName, blueName);
    }

    public static void main(String[] args) {
        new MainUI().initGUI();
    }

}
