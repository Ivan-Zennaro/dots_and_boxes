public class Graphic {

    private String board [][];

    public Graphic (int boardRows, int boardCols){
        board = new String[boardRows * 2 + 1][boardCols + 1];
    }

    public void updateMove (Move move){

    }

    public String getStringBoard(Board board) {
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
