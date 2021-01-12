import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainUI {

    private int rows, colum;
    private String me, otherPlayer;
    private Color color1, color2;
    private RulesPage rulesPage = new RulesPage();

    private static JFrame frame;
    private JLabel modeError,colorError;

    String[] playersType = {"Select player", "Human", "Computer Easy", "Computer Medium", "Computer Hard"};
    String[] colors = {"<html><font color='" + Color.RED.getRGBstring() + "'>RED", "<html><font color='" + Color.BLU.getRGBstring() + "'>BLU", "<html><font color='" + Color.GREEN.getRGBstring() + "'>GREEN", "<html><font color='" + Color.PURPLE.getRGBstring() + "'>PURPLE"};
    Color[] colors2 = {Color.RED, Color.BLU, Color.GREEN, Color.PURPLE};
    String[] size ={"1","2","3","4","5"};


    JTextField meTextField;
    DefaultComboBoxModel<String> optionsPlayer2;
    JComboBox<String> comboBox;
    JComboBox<String> colorBoxPlayer1;
    JComboBox<String> colorBoxPlayer2;
    JComboBox<String> colSelection;
    JComboBox<String> rowSelection;

    JButton hostGame;
    JButton joinGame;
    JTextField ipAddress;

    private JFrame frame1;
    JTextField humanName;
    JButton Enter;

    public MainUI() {

        frame = new JFrame("Dots and Boxes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        meTextField = new JTextField();
        optionsPlayer2 = new DefaultComboBoxModel<>(playersType);
        comboBox = new JComboBox<>(optionsPlayer2);

        hostGame = new JButton("Host Game");
        joinGame = new JButton("Join Game");
        ipAddress = new JTextField("Your IP");

        colorBoxPlayer1 = new JComboBox<>(colors);
        colorBoxPlayer2 = new JComboBox<>(colors);
        colorBoxPlayer2.setSelectedIndex(1);

        colSelection = new JComboBox<>(size);
        colSelection.setSelectedIndex(2);
        rowSelection = new JComboBox<>(size);
        rowSelection.setSelectedIndex(2);

        /*sizeButton = new JRadioButton[8];
        sizeGroup = new ButtonGroup();
        for (int i = 0; i < 8; i++) {
            String size = String.valueOf(i + 3);
            sizeButton[i] = new JRadioButton(size + " x " + size);
            sizeGroup.add(sizeButton[i]);
        }*/

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

    private String startGame = null;

    /*private GameSolver getSolver(int level) {
        if(level == 1) return new RandomSolver();
        else if(level == 2) return new GreedySolver();
        else if(level == 3) return new MinimaxSolver();
        else if(level == 4) return new AlphaBetaSolver();
        else if(level == 5) return new MCSolver();
        else return null;
    }*/

    private KeyListener key = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (meTextField.getText().equals("Your Name"))
                meTextField.setText("");

            meTextField.setText(meTextField.getText());
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    private KeyListener keyHuman = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (humanName.getText().equals("Human Name"))
                humanName.setText("");

            humanName.setText(humanName.getText());
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };
    private ActionListener demo = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            startGame = "demo";
        }
    };
    private ActionListener close = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Enter = (JButton) e.getSource();
            playersType[1] = humanName.getText();
            optionsPlayer2.insertElementAt(humanName.getText(), 1);
            optionsPlayer2.removeElementAt(2);
            frame1.dispose();
        }
    };
    private ActionListener select = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (comboBox.getSelectedIndex() == 1 && comboBox.getSelectedItem() == "Human") {

                JPanel gridName = new JPanel(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 0;
                constraints.gridy = 0;


                JLabel message = new JLabel("Insert the name for the Human Player", SwingConstants.CENTER);
                message.setPreferredSize(new Dimension(240, 50));
                ++constraints.gridy;
                gridName.add(message, constraints);


                JPanel namePanel = new JPanel(new GridLayout(4, 1));
                namePanel.setPreferredSize(new Dimension(150, 70));

                namePanel.add(humanName, constraints);
                namePanel.add(getEmptyLabel(new Dimension(150, 20)));
                namePanel.add(Enter);
                namePanel.add(getEmptyLabel(new Dimension(150, 20)));
                humanName.setText("Human Name");
                humanName.addKeyListener(keyHuman);
                Enter.addActionListener(close);
                ++constraints.gridy;

                gridName.add(namePanel, constraints);


                frame1.setContentPane(gridName);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setLocationRelativeTo(null);//center the frame
                frame1.setVisible(true);
            }


        }
    };


    private ActionListener submitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (validateInputPlayersAndGrid()) {
                startGame = "pvp";
            }

        }
    };

    private boolean validateInputPlayersAndGrid() {
        me = meTextField.getText();
        int bIndex = comboBox.getSelectedIndex();
        color1 = colors2[colorBoxPlayer1.getSelectedIndex()];
        color2 = colors2[colorBoxPlayer2.getSelectedIndex()];
        if (me.equals("") || bIndex == 0) {
            modeError.setText("You MUST select the players before continuing.");
            return false;
        } else if (color1.equals(color2)) {
            colorError.setText("You MUST select 2 different colors for players");
            return false;
        } else {
            modeError.setText("");
            otherPlayer = playersType[bIndex];
            colum = Integer.parseInt(colSelection.getSelectedItem().toString());
            rows = Integer.parseInt(rowSelection.getSelectedItem().toString());
            return true;
        }
    }


    public void initGUI() {


        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);

        modeError = new JLabel("", SwingConstants.CENTER);
        modeError.setForeground(Color.RED.getAwtColor());
        modeError.setPreferredSize(new Dimension(500, 25));
        ++constraints.gridy;
        grid.add(modeError, constraints);

        JPanel modePanel = new JPanel(new GridLayout(2, 3));
        modePanel.setPreferredSize(new Dimension(400, 50));
        modePanel.add(new JLabel("<html><font color='Black'>Player-1:", SwingConstants.CENTER));
        modePanel.add(getEmptyLabel(new Dimension(50, 25)));
        modePanel.add(new JLabel("<html><font color='Black'>Player-2:", SwingConstants.CENTER));
        modePanel.add(meTextField);
        modePanel.add(getEmptyLabel(new Dimension(50, 25)));
        modePanel.add(comboBox);

        meTextField.setText("Your Name");
        meTextField.addKeyListener(key);

        comboBox.setSelectedIndex(0);
        comboBox.addActionListener(select);


        ++constraints.gridy;
        grid.add(modePanel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);

        colorError = new JLabel("", SwingConstants.CENTER);
        colorError.setForeground(Color.RED.getAwtColor());
        colorError.setPreferredSize(new Dimension(500, 25));

        ++constraints.gridy;
        grid.add(colorError, constraints);

        JPanel colorPanel = new JPanel(new GridLayout(2, 3));
        colorPanel.setPreferredSize(new Dimension(400, 50));
        colorPanel.add(new JLabel("<html><font color='Black'>Color-Player-1:", SwingConstants.CENTER));
        colorPanel.add(getEmptyLabel(new Dimension(50, 25)));
        colorPanel.add(new JLabel("<html><font color='Black'>Color-Player-2:", SwingConstants.CENTER));
        colorPanel.add(colorBoxPlayer1);
        colorPanel.add(getEmptyLabel(new Dimension(50, 25)));
        colorPanel.add(colorBoxPlayer2);

        ++constraints.gridy;
        grid.add(colorPanel, constraints);

        ++constraints.gridy;
        JLabel messageLabel = new JLabel("Select the size of the board:");
        messageLabel.setPreferredSize(new Dimension(400, 50));
        grid.add(messageLabel, constraints);

        JPanel sizePanel = new JPanel(new GridLayout(2, 3));
        sizePanel.setPreferredSize(new Dimension(400, 50));
        sizePanel.add(new JLabel("<html><font color='Black'>Rows:", SwingConstants.CENTER));
        sizePanel.add(getEmptyLabel(new Dimension(50, 25)));
        sizePanel.add(new JLabel("<html><font color='Black'>Column:", SwingConstants.CENTER));
        sizePanel.add(rowSelection);
        sizePanel.add(getEmptyLabel(new Dimension(50, 25)));
        sizePanel.add(colSelection);

        ++constraints.gridy;
        grid.add(sizePanel, constraints);


        JPanel serverPanel = new JPanel(new GridLayout(2, 3));
        serverPanel.setPreferredSize(new Dimension(400, 50));
        serverPanel.add(joinGame);
        serverPanel.add(getEmptyLabel(new Dimension(50, 25)));
        serverPanel.add(getEmptyLabel(new Dimension(50, 25)));
       // hostGame.addActionListener(hostGameListener);
        serverPanel.add(hostGame);
        serverPanel.add(getEmptyLabel(new Dimension(50, 25)));
        serverPanel.add(ipAddress);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);
        ++constraints.gridy;
        grid.add(serverPanel, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);


        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);

        JPanel submissionPanel = new JPanel(new GridLayout(1, 3));
        JButton ruleButton = new JButton("Rules");
        JButton demoButton = new JButton("Demo");
        JButton submitButton = new JButton("Start Game");

        ruleButton.addActionListener(e -> rulesPage.seeFrame());
        demoButton.addActionListener(demo);

        submitButton.addActionListener(submitListener);
        submissionPanel.add(ruleButton);
        submissionPanel.add(demoButton);
        submissionPanel.add(submitButton);
        ++constraints.gridy;
        grid.add(submissionPanel, constraints);


        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);

        frame.setContentPane(grid);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        while (startGame == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        frame.dispose();
        if (startGame.equals("pvp")) {
            try {
                frame.dispose();
                new TwoPlayersNewGame(rows, colum, new Player(me, color1), new Player(otherPlayer, color2), Gui.class).startGame();


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (startGame.equals("demo")) {
            new ComputerVsComputerGame(3, 3, new Player("Player 1", Color.BLU), new Player("Payer 2", Color.RED), new Gui(3, 3, new Player("Player 1", Color.BLU), new Player("Player 2", Color.RED))).startGame();

        }
    }

    public static void main(String[] args) {
        new MainUI().initGUI();
    }

}
