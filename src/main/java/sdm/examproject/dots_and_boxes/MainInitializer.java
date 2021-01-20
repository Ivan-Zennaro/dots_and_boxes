package sdm.examproject.dots_and_boxes;

public class MainInitializer {
    public static void main(String[] args) {
        if (args.length != 0)
            switch (args[0]) {
                case "gui" -> MenuGui.mainGUI();
                case "cli" -> MenuCli.mainCLI();
                default -> System.out.println("Wrong argument\n");
            }
        System.out.println("USAGE: dots_and_boxes.jar  [gui, cli]\n");
    }

}
