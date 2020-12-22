public class ColorManager {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static String getColoredString (String s, Color color){
        if (color == Color.RED)
            return ANSI_RED + s + ANSI_RESET ;
        else return ANSI_BLUE + s + ANSI_RESET;
    }
}
