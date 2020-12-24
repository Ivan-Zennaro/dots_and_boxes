public class Graphic {

    private String graphicBoard [][];

    public Graphic (int boardRows, int boardCols){
        int mappedRows = boardRows * 2 + 1;
        int mappedCols = boardCols + 1;
        graphicBoard = new String[mappedRows][mappedCols];
        for (int i = 0; i < mappedRows; i++){
            for (int j = 0 ; j < mappedCols ; j++) {
                if (i % 2 == 0)
                    graphicBoard[i][j] = " ---";
                else
                    graphicBoard[i][j] = "|   ";
                }
            }
    }


    public void updateMove (Move move, Player player){

        /*Color color = player.getColor();
        int mappedX = */
        graphicBoard[0][0] = ColorManager.getColoredString(" ---",Color.BLU);
    }

    public static int mapY (Move move){
        if (move.getSide() == Side.RIGHT)
            return move.getY() + 1;
        else return move.getY();
    }



    public String getStringBoard() {
        String s = "\n";
        for (int i = 0; i < graphicBoard.length; i++){
            for (int j = 0; (i % 2 == 0 &&  j < graphicBoard[0].length -1 ) || ( i % 2 != 0 &&  j < graphicBoard[0].length ); j++){
                s += graphicBoard[i][j];
            }
            s += "\n";
        }
        return s;
    }

}
