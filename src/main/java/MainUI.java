import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainUI {

    private int rows, cols;
    private String me, otherPlayer;
    private Color color1, color2;
    private RulesPage rulesPage = new RulesPage();

    private static JFrame frame;
    private JLabel modeError, colorError;

    String[] playersType = {"Select player", "Human", "Computer Easy", "Computer Medium", "Computer Hard"};
    String[] colors = {"<html><font color='" + Color.RED.getRGBstring() + "'>RED", "<html><font color='" + Color.BLU.getRGBstring() + "'>BLU", "<html><font color='" + Color.GREEN.getRGBstring() + "'>GREEN", "<html><font color='" + Color.PURPLE.getRGBstring() + "'>PURPLE"};
    Color[] colors2 = {Color.RED, Color.BLU, Color.GREEN, Color.PURPLE};
    String[] size = {"1", "2", "3", "4", "5"};


    JTextField player1Name;
    DefaultComboBoxModel<String> optionsPlayer2;
    JComboBox<String> comboBox;
    JComboBox<String> colorBoxPlayer1;
    JComboBox<String> colorBoxPlayer2;
    JComboBox<String> colSelection;
    JComboBox<String> rowSelection;
    private JRadioButton[] localOrRemote;
    ButtonGroup sizeGroup;
    JTextField ipAddress;
    JTextField humanName;
    private JFrame frame1;

    JButton Enter;

    String ip;

    public MainUI() {

        frame = new JFrame("Dots and Boxes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        player1Name = new JTextField();
        optionsPlayer2 = new DefaultComboBoxModel<>(playersType);
        comboBox = new JComboBox<>(optionsPlayer2);

        ipAddress = new JTextField("Opponent IP address");

        colorBoxPlayer1 = new JComboBox<>(colors);
        colorBoxPlayer2 = new JComboBox<>(colors);
        colorBoxPlayer2.setSelectedIndex(1);

        colSelection = new JComboBox<>(size);
        colSelection.setSelectedIndex(2);
        rowSelection = new JComboBox<>(size);
        rowSelection.setSelectedIndex(2);

        localOrRemote = new JRadioButton[3];
        sizeGroup = new ButtonGroup();
        localOrRemote[0] = new JRadioButton("Local", true);
        localOrRemote[1] = new JRadioButton("Host");
        localOrRemote[2] = new JRadioButton("Join");
        sizeGroup.add(localOrRemote[0]);
        sizeGroup.add(localOrRemote[1]);
        sizeGroup.add(localOrRemote[2]);


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

    private KeyListener key = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (player1Name.getText().equals("Your Name"))
                player1Name.setText("");
            player1Name.setText(player1Name.getText());
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

    private KeyListener keyIp = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            if (ipAddress.getText().equals("Opponent IP address"))
                ipAddress.setText("");
            ipAddress.setText(ipAddress.getText());

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    private ActionListener demo = e -> startGame = "demo";


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
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);
            }


        }
    };


    private ActionListener submitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            me = player1Name.getText();
            int typeOfPlayerIndex = comboBox.getSelectedIndex();
            color1 = colors2[colorBoxPlayer1.getSelectedIndex()];
            color2 = colors2[colorBoxPlayer2.getSelectedIndex()];
            cols = Integer.parseInt(colSelection.getSelectedItem().toString());
            rows = Integer.parseInt(rowSelection.getSelectedItem().toString());


            if (me.equals("")) {
                modeError.setText("You MUST type player1 name");
            } else if (color1.equals(color2)) {
                colorError.setText("You MUST select 2 different colors for players");
            } else if (localOrRemote[2].isSelected()) {
                ip = ipAddress.getText();
                startGame = "join";
            } else if (localOrRemote[1].isSelected()) {
                startGame = "host";
            } else if (typeOfPlayerIndex == 0) {
                modeError.setText("You MUST select player2 type");
            } else {
                modeError.setText("");
                otherPlayer = playersType[typeOfPlayerIndex];
                startGame =
                        switch (typeOfPlayerIndex) {
                            case 1 -> "pvp";
                            case 2 -> "pvc1";
                            case 3 -> "pvc2";
                            case 4 -> "pvc3";
                            default -> "pvp";
                        };
            }
        }

    };

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
        modePanel.add(player1Name);
        modePanel.add(getEmptyLabel(new Dimension(50, 25)));
        modePanel.add(comboBox);

        player1Name.setText("Your Name");
        player1Name.addKeyListener(key);

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
        JLabel messageLabel = new JLabel("Select the size of the board:", SwingConstants.CENTER);
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

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);

        ++constraints.gridy;
        JLabel messageLabel2 = new JLabel("Select Local or Remote Game?:");
        messageLabel.setPreferredSize(new Dimension(400, 50));
        grid.add(messageLabel2, constraints);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);


        ++constraints.gridy;
        JPanel serverPanel = new JPanel(new GridLayout(2, 3));
        serverPanel.setPreferredSize(new Dimension(400, 50));
        for (int i = 0; i < 3; i++)
            serverPanel.add(localOrRemote[i]);
        serverPanel.add(new JLabel());
        serverPanel.add(new JLabel());
        ipAddress.addKeyListener(keyIp);
        serverPanel.add(ipAddress);
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
        frame.setResizable(false);
        frame.setVisible(true);

        waitForCorrectUserAction();

        frame.dispose();

        startGame();
    }

    private void startGame() {
        switch (startGame) {
            case "demo" -> GameFactory.createComputerVsComputerGameWithGUI(3, 3, new Player("Player 1", Color.BLU), new Player("Payer 2", Color.RED)).startGame();
            case "pvp" -> GameFactory.create2PlayerGameWithGUI(rows, cols, new Player(me, color1), new Player(otherPlayer, color2)).startGame();
            case "pvc1" -> GameFactory.createPlayerVsComputerGameWithGUI(rows, cols, new Player(me, color1), new Player(otherPlayer, color2), Difficulty.EASY).startGame();
            case "pvc2" -> GameFactory.createPlayerVsComputerGameWithGUI(rows, cols, new Player(me, color1), new Player(otherPlayer, color2), Difficulty.MEDIUM).startGame();
            case "pvc3" -> GameFactory.createPlayerVsComputerGameWithGUI(rows, cols, new Player(me, color1), new Player(otherPlayer, color2), Difficulty.HARD).startGame();
            case "host" -> GameFactory.createServerGameWithGUI(3, 3, new Player(me, color1), new Player("Batman", Color.BLU)).startGame();
            case "join" -> GameFactory.createClientGameWithGUI(3, 3, new Player("Batman", Color.BLU), new Player(me, color1), ip).startGame();

        }
    }

    private void waitForCorrectUserAction() {
        while (startGame == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MainUI().initGUI();
    }

}
