import javax.swing.*;

public class RulesPage{
    private JFrame  frame ;
    private JTextArea textArea;
    private JPanel panel;
    private String rulesText = "Welcome to Dots and Boxes! \n" +
            "The rules are:\n"+
            "1. Choose the players'identity and colour and the grid size;\n"+
            "2. At each Player's turn, a line from the game grid must be selected;\n"+
            "3. The player who completes a box gets a point and has an extra turn;\n "+
            "4. Two players compete to complete most boxes in the game grid \n and to get more points!\n \n" +
            "5.You can also choose to host or join a server-based game on the main menu\n"+
            "Enjoy the game!";


    public RulesPage(){
        this.frame = new JFrame("Dots and Boxes rules");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.textArea = new JTextArea(8, 40);
        textArea.setEditable(false);
        textArea.append(rulesText);

        this.panel = new JPanel();
        panel.add(textArea);

        frame.add(panel);
        frame.pack();
        frame.setResizable(false);



    }
    public void seeFrame(){
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        RulesPage rulesPage = new RulesPage();
    }

}
