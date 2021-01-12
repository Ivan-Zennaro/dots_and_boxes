import javax.swing.*;

public class RulesPage {
    private JFrame frame;
    private JTextArea textArea;
    private JPanel panel;
    private String rulesText = """
            Welcome to Dots and Boxes!\s
                        
            The rules are:
            1. Choose the players' identity and colour and the grid size;
            2. At each Player's turn, a line from the game grid must be selected;
            3. The player who completes a box gets a point and has an extra turn;
            4. Two players compete to complete most boxes in the game grid\s
            and to get more points!
            5.You can also choose to host or join a server-based game on the main menu.
                        
            Enjoy the game!""";


    public RulesPage() {
        this.frame = new JFrame("Dots and Boxes rules");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.textArea = new JTextArea(8, 40);
        textArea.setEditable(false);
        textArea.append(rulesText);

        this.panel = new JPanel();
        panel.add(textArea);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public void seeFrame() {
        frame.setVisible(true);
    }
}
