public class ColorManager {

    public static String getColoredString (String s, Color color){

        String s2 = s + "\u001B[0m";

        switch (color) {
            case BLU: s =  "\u001B[34m";
                        break;
            case RED: s =  "\u001B[31m";
                break;
            case PURPLE: s =  "\u001B[35m";
                break;
            case GREEN: s =  "\u001B[32m";
                break;
            default: s =  "\u001B[0m";
        }

        return s + s2;



        /*return (
            switch (color){
                case BLU -> "\u001B[34m";
                case RED -> "\u001B[31m";
                case PURPLE -> "\u001B[35m";
                case GREEN -> "\u001B[32m";
                default -> "\u001B[0m";
            }
        ) + s + "\u001B[0m" ;

         */
    }
}
