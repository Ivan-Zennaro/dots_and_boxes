public class Graphic {

    private String board [][];

    public Graphic (int boardRows, int boardCols){
        board = new String[boardRows * 2 + 1][boardCols + 1];
    }

    public void updateMove (Move move, Player player){

    }

    public String getStringBoard() {
        String boardString_2x2 =
                " --- ---\n" +
                        "|   |   |\n" +
                        " --- ---\n" +
                        "|   |   |\n" +
                        " --- ---\n" +
                        "\n";

        return boardString_2x2;
    }

}
