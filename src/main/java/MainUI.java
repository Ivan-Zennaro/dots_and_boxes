import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainUI {

    private int rows, cols;
    private String me, otherPlayer, ip, player2Name;
    private Color color1, color2;
    private RulesPage rulesPage = new RulesPage();
    private String startGame = null;

    private static JFrame frame;
    private JLabel playerError, colorError;

    List<String> playersType = new ArrayList<>(List.of("Select player", "Human", "Computer Easy", "Computer Medium", "Computer Hard"));
    String[] colors = {"<html><font color='" + Color.RED.getRGBstring() + "'>RED", "<html><font color='" + Color.BLU.getRGBstring() + "'>BLU", "<html><font color='" + Color.GREEN.getRGBstring() + "'>GREEN", "<html><font color='" + Color.PURPLE.getRGBstring() + "'>PURPLE"};
    Color[] colorsObject = {Color.RED, Color.BLU, Color.GREEN, Color.PURPLE};
    String[] size = {"1", "2", "3", "4", "5"};


    private JTextField player1Name, ipAddress, humanName;
    private DefaultComboBoxModel<String> optionsPlayer2Model;
    private JComboBox<String> optionsPlayer2, colorBoxPlayer1, colorBoxPlayer2, colSelection, rowSelection;

    private JRadioButton[] localOrRemote;
    private ButtonGroup sizeGroup;

    private JFrame frame1;
    private JButton confirmPlayer2HumanName;

    private static final class resetTextField extends FocusAdapter {
        JTextField fieldTxt;
        String text;

        public resetTextField(JTextField fieldTxt, String text) {
            super();
            this.fieldTxt = fieldTxt;
            this.text = text;
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (fieldTxt.getText().equals(text))
                fieldTxt.setText("");
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (fieldTxt.getText().equals(""))
                fieldTxt.setText(text);
        }
    }

    private ActionListener demo = e -> startGame = "demo";

    private ActionListener select = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (optionsPlayer2.getSelectedItem() == "Human") {
                player2Name = JOptionPane.showInputDialog(null, "Insert the name for the Human Player","Human Player 2", JOptionPane.INFORMATION_MESSAGE);

                if(player2Name!=null && !player2Name.equals("") && !optionsPlayer2.getItemAt(2).equals("Human")) {
                    optionsPlayer2Model.insertElementAt(player2Name, 1);
                    playersType.add(1,player2Name);
                }
                else if ((player2Name!=null && !player2Name.equals("") && optionsPlayer2.getItemAt(2).equals("Human"))){
                        playersType.remove(1);
                        playersType.add(1,player2Name);
                        optionsPlayer2Model.removeElementAt(1);
                        optionsPlayer2Model.insertElementAt(player2Name, 1);
                }

                optionsPlayer2.setSelectedIndex(1);
                player2Name = playersType.get(1);

            }
        }
    };


    private ActionListener submitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            me = player1Name.getText();
            int typeOfPlayerIndex = optionsPlayer2.getSelectedIndex();
            color1 = colorsObject[colorBoxPlayer1.getSelectedIndex()];
            color2 = colorsObject[colorBoxPlayer2.getSelectedIndex()];
            cols = Integer.parseInt(colSelection.getSelectedItem().toString());
            rows = Integer.parseInt(rowSelection.getSelectedItem().toString());


            if (me.equals("")) {
                playerError.setText("You MUST type player1 name");
            } else if (color1.equals(color2)) {
                colorError.setText("You MUST select 2 different colors for players");
            } else if (localOrRemote[2].isSelected()) {
                ip = ipAddress.getText();
                startGame = "join";
            } else if (localOrRemote[1].isSelected()) {
                startGame = "host";
            } else if (typeOfPlayerIndex == 0) {
                playerError.setText("You MUST select player2 type");
            } else {
                playerError.setText("");
                otherPlayer = playersType.get(typeOfPlayerIndex);

                int sum=0;
                if(playersType.size()==6){
                    startGame =
                            switch (typeOfPlayerIndex) {
                                case 1 -> "pvp";
                                case 3 -> "pvc1";
                                case 4 -> "pvc2";
                                case 5 -> "pvc3";
                                default -> "pvp";
                            };

                }
                else {
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
        }

    };

    public MainUI() {

        instantiateMainFrameObjects();

        instantiateHumanNameFrameObjects();
    }

    private void instantiateHumanNameFrameObjects() {
        frame1 = new JFrame("Player-2");
        frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        humanName = new JTextField("Human Name");
    }

    private void instantiateMainFrameObjects() {
        frame = new JFrame("Dots and Boxes - Main Menu");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        player1Name = new JTextField("Your Name");
        optionsPlayer2Model = new DefaultComboBoxModel<String>(playersType.toArray(new String[0]));
        optionsPlayer2 = new JComboBox<>(optionsPlayer2Model);
        optionsPlayer2.setSelectedIndex(0);

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
    }

    private JLabel getEmptyLabel(Dimension d) {
        JLabel label = new JLabel();
        label.setPreferredSize(d);
        return label;
    }

    public void initMenu() {
        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);

        playerError = new JLabel("", SwingConstants.CENTER);
        playerError.setForeground(Color.RED.getAwtColor());
        playerError.setPreferredSize(new Dimension(500, 25));
        ++constraints.gridy;
        grid.add(playerError, constraints);

        JPanel modePanel = new JPanel(new GridLayout(2, 3));
        modePanel.setPreferredSize(new Dimension(400, 50));
        modePanel.add(new JLabel("<html><font color='Black'>Player-1:", SwingConstants.CENTER));
        modePanel.add(getEmptyLabel(new Dimension(50, 25)));
        modePanel.add(new JLabel("<html><font color='Black'>Player-2:", SwingConstants.CENTER));
        modePanel.add(player1Name);
        modePanel.add(getEmptyLabel(new Dimension(50, 25)));
        modePanel.add(optionsPlayer2);


        player1Name.addFocusListener(new resetTextField(player1Name, "Your Name"));
        optionsPlayer2.addActionListener(select);


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
        ipAddress.addFocusListener(new resetTextField(ipAddress, "Opponent IP address"));
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
            case "demo" -> GameFactory.createComputerVsComputerGameWithGUI(3, 3, new Player("Player 1", Color.BLU), new Player("Player 2", Color.RED)).startGame();
            case "pvp" -> GameFactory.create2PlayerGameWithGUI(rows, cols, new Player(me, color1), new Player(player2Name, color2)).startGame();
            case "pvc1" -> GameFactory.createPlayerVsComputerGameWithGUI(rows, cols, new Player(me, color1), new Player(otherPlayer, color2), Difficulty.EASY).startGame();
            case "pvc2" -> GameFactory.createPlayerVsComputerGameWithGUI(rows, cols, new Player(me, color1), new Player(otherPlayer, color2), Difficulty.MEDIUM).startGame();
            case "pvc3" -> GameFactory.createPlayerVsComputerGameWithGUI(rows, cols, new Player(me, color1), new Player(otherPlayer, color2), Difficulty.HARD).startGame();
            case "host" -> GameFactory.createServerGameWithGUI(new Player(me, color1), new Player("Remote opponent", Color.BLU)).startGame();
            case "join" -> GameFactory.createClientGameWithGUI(new Player("Remote opponent", Color.BLU), new Player(me, color1), ip).startGame();
            default -> throw new IllegalStateException("Unexpected value: " + startGame);
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
        new MainUI().initMenu();
    }

}
