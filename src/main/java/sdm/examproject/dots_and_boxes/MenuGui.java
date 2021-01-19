package sdm.examproject.dots_and_boxes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MenuGui {

    private final static String IMAGE_PATH = "src/images/dots-and-boxes-ICON.PNG";
    private int rows, cols;
    private String me, player2Type, ip, player2Name;
    private Color color1, color2;
    private final JFrame rulesPage = new RulesPage();
    private String startGame = null;

    private static JFrame frame;
    private JLabel playerError, colorError;

    private final String[] playersType = {"Computer Easy", "Computer Medium", "Computer Hard", "Human"};
    private final String[] colors = {"<html><font color='" + Color.RED.getRGBstring() + "'>RED", "<html><font color='" + Color.BLU.getRGBstring() + "'>BLU", "<html><font color='" + Color.GREEN.getRGBstring() + "'>GREEN", "<html><font color='" + Color.PURPLE.getRGBstring() + "'>PURPLE"};
    private final Color[] colorsObject = {Color.RED, Color.BLU, Color.GREEN, Color.PURPLE};
    private final String[] size = {"1", "2", "3", "4", "5"};


    private final JTextField player1Name;
    private final JTextField ipAddress;
    private final JComboBox<String> optionsPlayer2;
    private final JComboBox<String> colorBoxPlayer1;
    private final JComboBox<String> colorBoxPlayer2;
    private final JComboBox<String> colSelection;
    private final JComboBox<String> rowSelection;

    private final JRadioButton[] localOrRemote;

    private static final class resetTextField extends FocusAdapter {
        final JTextField fieldTxt;
        final String text;

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

    private final ActionListener selectPlayer2Listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (optionsPlayer2.getSelectedItem().equals("Human")) {

                player2Name = JOptionPane.showInputDialog(null, "Select the name for the human player",
                        "Human Name Player 2", JOptionPane.INFORMATION_MESSAGE);

                if (player2Name != null && !player2Name.equals("") ){
                    if (!optionsPlayer2.getItemAt(3).equals("Human")) {
                        optionsPlayer2.removeItemAt(3);
                    }
                    optionsPlayer2.insertItemAt(player2Name, 3);
                }
                optionsPlayer2.setSelectedIndex(3);
                player2Name = optionsPlayer2.getItemAt(3);
            }
        }
    };

    private final ActionListener manageInputVisibilityWhenLocalSelected = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ipAddress.setEnabled(false);
            optionsPlayer2.setEnabled(true);
            colorBoxPlayer1.setEnabled(true);
            colorBoxPlayer2.setEnabled(true);
            colSelection.setEnabled(true);
            rowSelection.setEnabled(true);
        }
    };

    private final ActionListener manageInputVisibilityWhenHostSelected = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ipAddress.setEnabled(false);
            optionsPlayer2.setEnabled(false);
            colorBoxPlayer1.setEnabled(true);
            colorBoxPlayer2.setEnabled(false);
            colSelection.setEnabled(false);
            rowSelection.setEnabled(false);

        }
    };

    private final ActionListener manageInputVisibilityWhenJoinSelected = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ipAddress.setEnabled(true);
            optionsPlayer2.setEnabled(false);
            colorBoxPlayer1.setEnabled(true);
            colorBoxPlayer2.setEnabled(false);
            colSelection.setEnabled(false);
            rowSelection.setEnabled(false);

        }
    };

    private final ActionListener submitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            me = player1Name.getText();
            int typeOfPlayerIndex = optionsPlayer2.getSelectedIndex();
            color1 = colorsObject[colorBoxPlayer1.getSelectedIndex()];
            color2 = colorsObject[colorBoxPlayer2.getSelectedIndex()];
            cols = Integer.parseInt(colSelection.getSelectedItem().toString());
            rows = Integer.parseInt(rowSelection.getSelectedItem().toString());

            if (me.equals(player2Name)) {
                playerError.setText("You MUST select 2 different names for players");
            } else if (color1.equals(color2)) {
                colorError.setText("You MUST select 2 different colors for players");
            } else if (localOrRemote[2].isSelected()) {
                ip = ipAddress.getText();
                startGame = "join";
            } else if (localOrRemote[1].isSelected()) {
                startGame = "host";
            } else if (optionsPlayer2.getSelectedItem().equals("Human")) {
                playerError.setText("You MUST select Human Name for Player 2");
            } else {
                playerError.setText("");
                player2Type = optionsPlayer2.getItemAt(typeOfPlayerIndex);

                startGame =
                        switch (typeOfPlayerIndex) {
                            case 0 -> "pvc1";
                            case 1 -> "pvc2";
                            case 2 -> "pvc3";
                            default -> "pvp";
                        };

            }
        }

    };

    public MenuGui() {
        frame = new JFrame("Dots and Boxes - Main Menu");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        player1Name = new JTextField("Bob");
        optionsPlayer2 = new JComboBox<>(playersType);
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
        ButtonGroup sizeGroup = new ButtonGroup();
        localOrRemote[0] = new JRadioButton("Local", true);
        localOrRemote[1] = new JRadioButton("Host");
        localOrRemote[2] = new JRadioButton("Join");
        localOrRemote[0].addActionListener(manageInputVisibilityWhenLocalSelected);
        localOrRemote[1].addActionListener(manageInputVisibilityWhenHostSelected);
        localOrRemote[2].addActionListener(manageInputVisibilityWhenJoinSelected);
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


        player1Name.addFocusListener(new resetTextField(player1Name, "Bob"));
        optionsPlayer2.addActionListener(selectPlayer2Listener);


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

        ruleButton.addActionListener(e -> rulesPage.setVisible(true));
        demoButton.addActionListener(e -> startGame = "demo");
        submitButton.addActionListener(submitListener);

        submissionPanel.add(ruleButton);
        submissionPanel.add(demoButton);
        submissionPanel.add(submitButton);
        ++constraints.gridy;
        grid.add(submissionPanel, constraints);


        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);

        Image icon = Toolkit.getDefaultToolkit().getImage(IMAGE_PATH);
        frame.setIconImage(icon);
        frame.setContentPane(grid);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        optionsPlayer2.requestFocus();
        localOrRemote[0].doClick();

        waitForCorrectUserAction();

        frame.dispose();

        startGame();

    }

    private void startGame() {
        switch (startGame) {
            case "demo" -> GameFactory.createDemoGameWithGUI(3, 3, new Player("Player 1", Color.BLU), new Player("Player 2", Color.RED)).startGame();
            case "pvp" -> GameFactory.create2PlayerGameWithGUI(rows, cols, new Player(me, color1), new Player(player2Name, color2)).startGame();
            case "pvc1" -> GameFactory.createPlayerVsComputerGameWithGUI(rows, cols, new Player(me, color1), new Player(player2Type, color2), Difficulty.EASY).startGame();
            case "pvc2" -> GameFactory.createPlayerVsComputerGameWithGUI(rows, cols, new Player(me, color1), new Player(player2Type, color2), Difficulty.MEDIUM).startGame();
            case "pvc3" -> GameFactory.createPlayerVsComputerGameWithGUI(rows, cols, new Player(me, color1), new Player(player2Type, color2), Difficulty.HARD).startGame();
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
        new MenuGui().initMenu();
    }

}
