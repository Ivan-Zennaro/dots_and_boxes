import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;

public class MainUI {

    private int n;
   // private GameSolver redSolver, blueSolver;
    private String me, otherPlayer;
    private RulesPage rulesPage = new RulesPage();
    private JFrame frame;
    private JLabel modeError, sizeError;

    String[] playersType = {"Select player", "Human", "Computer Facile", "Computer Difficile", "Random"};
    private JRadioButton[] sizeButton;

    DefaultComboBoxModel<String> blueList;
    JComboBox<String> comboBox;
    JTextField meTextField;
    ButtonGroup sizeGroup;

    private JFrame frame1;
    JTextField humanName;
    JButton Enter;

    public MainUI() {

        frame = new JFrame("Dots and Boxes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        meTextField = new JTextField();
        blueList = new DefaultComboBoxModel<>(playersType);
        comboBox = new JComboBox<>(blueList);

        sizeButton = new JRadioButton[8];
        sizeGroup = new ButtonGroup();
        for(int i=0; i<8; i++) {
            String size = String.valueOf(i+3);
            sizeButton[i] = new JRadioButton(size + " x " + size);
            sizeGroup.add(sizeButton[i]);
        }

        frame1 = new JFrame("Player-2");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        humanName = new JTextField();
        Enter = new JButton("Confirm Name");
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

    private MouseListener clickonit1 = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            humanName.setText("");
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

    private ActionListener close = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Enter = (JButton)e.getSource();
            playersType[1]= humanName.getText();
            blueList.insertElementAt(humanName.getText(),1);
            blueList.removeElementAt(2);
            frame1.dispose();
        }
    };
    private ActionListener select = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        if (comboBox.getSelectedIndex()==1) {
            JPanel gridName = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 0;

            JLabel message = new JLabel("Insert the name for the Human Player");
            message.setPreferredSize(new Dimension(240, 50));
            gridName.add(message, constraints);
            ++constraints.gridy;

            JPanel namePanel = new JPanel(new GridLayout(2,1));
            namePanel.setPreferredSize(new Dimension(150,150));

            namePanel.add(humanName,constraints);
            namePanel.add(Enter);
            humanName.addMouseListener(clickonit1);
            Enter.addActionListener(close);
            ++constraints.gridy;

            gridName.add(namePanel,constraints);


            frame1.setContentPane(gridName);
            frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame1.pack();
            frame1.setLocationRelativeTo(null);//center the frame
            frame1.setVisible(true);
            }

        }
    };

    private ActionListener submitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String meName = meTextField.getText();
            int bIndex = comboBox.getSelectedIndex();
            if(meName.equals("") || bIndex==0) {
                modeError.setText("You MUST select the players before continuing.");
                return;
            }
            else {
                modeError.setText("");
                me = meName;
                otherPlayer = playersType[bIndex];
                blueList.removeElementAt(bIndex);
                blueList.insertElementAt("Human",bIndex);

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

        JPanel modePanel = new JPanel(new GridLayout(2, 3));
        modePanel.setPreferredSize(new Dimension(400, 50));
        modePanel.add(new JLabel("<html><font color='Black'>Player-1:", SwingConstants.CENTER));
        modePanel.add(getEmptyLabel(new Dimension(50,25)));
        modePanel.add(new JLabel("<html><font color='Black'>Player-2:", SwingConstants.CENTER));
        modePanel.add(meTextField);
        modePanel.add(getEmptyLabel(new Dimension(50,25)));
        modePanel.add(comboBox);

        meTextField.setText("Your Name");
        meTextField.addMouseListener(clickonit);

        comboBox.setSelectedIndex(0);
        comboBox.addActionListener(select);


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

        JPanel submisionPanel = new JPanel(new GridLayout(1,3));
        JButton submitButton = new JButton("Start Game");
        JButton ruleButton = new JButton("Rules");
        ruleButton.addActionListener(e -> rulesPage.seeFrame());

        submitButton.addActionListener(submitListener);
        submisionPanel.add(ruleButton);
        submisionPanel.add(getEmptyLabel(new Dimension(60,25)));
        submisionPanel.add(submitButton);
        ++constraints.gridy;
        grid.add(submisionPanel, constraints);


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
        new GameBoardUI(this, frame, n, null, null, me, otherPlayer);
       //new GamePlay(this, frame, n, redSolver, blueSolver, redName, blueName);
    }

    public static void main(String[] args) {
        new MainUI().initGUI();
    }

}
