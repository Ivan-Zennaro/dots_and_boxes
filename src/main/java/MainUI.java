import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;


public class MainUI {

    private int n;
   // private GameSolver redSolver, blueSolver;
    private String me, otherPlayer, address;
    private Color color1, color2;
    private RulesPage rulesPage = new RulesPage();


    private static JFrame frame;
    private JLabel modeError, sizeError;

    String[] playersType = {"Select player", "Human", "Computer Facile", "Computer Difficile", "Random"};
    String[] colors = {"<html><font color='Red'>RED","<html><font color='blue'>BLU","<html><font color='green'>GREEN","<html><font color='purple'>PURPLE"};
    Color[] colors2 = {Color.RED,Color.BLU,Color.GREEN,Color.PURPLE};
    private JRadioButton[] sizeButton;

    JTextField meTextField;
    DefaultComboBoxModel<String> optionsPlayer2;
    JComboBox<String> comboBox;
    JComboBox<String> colorBoxPlayer1;
    JComboBox<String> colorBoxPlayer2;
    ButtonGroup sizeGroup;
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
            if(comboBox.getSelectedIndex()==1 && comboBox.getSelectedItem()== "Human") {

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
                humanName.addMouseListener(clickonit1);
                Enter.addActionListener(close);
                ++constraints.gridy;

                gridName.add(namePanel, constraints);


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
                color1=colors2[colorBoxPlayer1.getSelectedIndex()];
                color2=colors2[colorBoxPlayer2.getSelectedIndex()];
                comboBox.removeItemAt(1);
                comboBox.insertItemAt("Human",1);



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

    //TODO
    //these two methods are for manual test only...TO REMOVE!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static JFrame getFrame(){
        return frame;
    }
    public static void setFrame(JFrame frame) {
        MainUI.frame = frame;
    }

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
        modeError.setForeground(Color.RED.getAwtColor());
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
        sizeError.setForeground(Color.RED.getAwtColor());
        sizeError.setPreferredSize(new Dimension(500, 25));

        ++constraints.gridy;
        grid.add(sizeError, constraints);

        JPanel colorPanel = new JPanel(new GridLayout(2, 3));
        colorPanel.setPreferredSize(new Dimension(400, 50));
        colorPanel.add(new JLabel("<html><font color='Black'>Color-Player-1:", SwingConstants.CENTER));
        colorPanel.add(getEmptyLabel(new Dimension(50,25)));
        colorPanel.add(new JLabel("<html><font color='Black'>Color-Player-2:", SwingConstants.CENTER));
        colorPanel.add(colorBoxPlayer1);
        colorPanel.add(getEmptyLabel(new Dimension(50,25)));
        colorPanel.add(colorBoxPlayer2);

        ++constraints.gridy;
        grid.add(colorPanel,constraints);


        JPanel serverPanel = new JPanel(new GridLayout(2,3));
        serverPanel.setPreferredSize(new Dimension(400,50));
        serverPanel.add(joinGame);
        serverPanel.add(getEmptyLabel(new Dimension(50,25)));
        serverPanel.add(getEmptyLabel(new Dimension(50,25)));
        serverPanel.add(hostGame);
        serverPanel.add(getEmptyLabel(new Dimension(50,25)));
        serverPanel.add(ipAddress);

        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);
        ++constraints.gridy;
        grid.add(serverPanel,constraints);

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

        JPanel submissionPanel = new JPanel(new GridLayout(1,3));
        JButton submitButton = new JButton("Start Game");
        JButton ruleButton = new JButton("Rules");
        ruleButton.addActionListener(e -> rulesPage.seeFrame());

        submitButton.addActionListener(submitListener);
        submissionPanel.add(ruleButton);
        submissionPanel.add(getEmptyLabel(new Dimension(60,25)));
        submissionPanel.add(submitButton);
        ++constraints.gridy;
        grid.add(submissionPanel, constraints);


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
        //new GameBoardUI(this, frame, n, null, null, me, otherPlayer);
        TwoPlayersNewGame game = null;
        try {
            new TwoPlayersNewGame(n,n,new Player(me, color1), new Player(otherPlayer,color2), Gui.class).startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
        game.startGame();
    }

    public static void main(String[] args) {
        new MainUI().initGUI();
    }

}
