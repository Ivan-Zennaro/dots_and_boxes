public class ColorManager {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";


    public static String getColoredString (String s, Color color){
        return ANSI_RED+s+ANSI_RESET ;
    }
}
