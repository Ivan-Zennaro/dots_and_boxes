public class ColorManager {

    public static String getColoredString(String string, Color color) {
        return (
                switch (color) {
                    case BLU -> "\u001B[34m";
                    case RED ->"\u001B[31m";
                    case PURPLE ->"\u001B[35m";
                    case GREEN ->"\u001B[32m";
                    default -> "\u001B[0m";
                }
        ) + string + "\u001B[0m";
    }
}
