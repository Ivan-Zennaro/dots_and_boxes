public class ColorManager {

    public static String getColoredString(String string, Color color) {

        String colorPattern;

        switch (color) {
            case BLU:
                colorPattern = "\u001B[34m";
                break;
            case RED:
                colorPattern = "\u001B[31m";
                break;
            case PURPLE:
                colorPattern = "\u001B[35m";
                break;
            case GREEN:
                colorPattern = "\u001B[32m";
                break;
            default:
                colorPattern = "\u001B[0m";
        }
        return colorPattern + string + "\u001B[0m";
    }
}
