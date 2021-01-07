import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameComputer {

    /*

    @ParameterizedTest
    @CsvSource({"5,2,TRUE", "-1,2,FALSE","1,1,TRUE","0,0,FALSE","0,1,FALSE"})
    public void board_cols_and_row_are_valid(int boardRow, int boardCols, boolean validity){
        ComputerGame game = new ComputerGame();
        Assertions.assertEquals(validity,game.validCoordinate(boardRow)&&game.validCoordinate(boardCols));

    }

    @Test
    public void matrix_converted_into_list (){
        Integer[][] matrix = {{1,2},{3,4}};
        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);list.add(4);
        Assertions.assertEquals(list, ComputerGame.matrixToList(matrix));
    }

    @Test
    public void get_missing_side_from_box (){
        assertAll(
                () -> assertEquals(Side.LEFT, ComputerGame.getMissingSideFromBox(new Box(true,false,true,true))),
                () -> assertEquals(Side.RIGHT, ComputerGame.getMissingSideFromBox(new Box(true,true,true,false))),
                () -> assertEquals(Side.DOWN, ComputerGame.getMissingSideFromBox(new Box(true,true,false,true))),
                () -> assertEquals(Side.UP, ComputerGame.getMissingSideFromBox(new Box(false,true,true,true))),
                () -> assertEquals(Side.INVALID, ComputerGame.getMissingSideFromBox(new Box(false,false,true,true)))
        );
    }

    @Test
    public void draw_line_in_a_missing_position_of_a_box(){
        ComputerGame game = new ComputerGame();
        game.defaultInitialize();
        game.turn(new Move(1,1,Side.LEFT));
        game.turn(new Move(1,1,Side.RIGHT));
        game.turn(new Move(1,1,Side.DOWN));
        Assertions.assertEquals(new Move(1,1,Side.UP),game.getComputerMove());
    }

    @Test
    public void draw_line_in_a_random_box_with_not_2_side_completed(){
        ComputerGame game = new ComputerGame();
        game.defaultInitialize();
        game.turn(new Move(1,1,Side.LEFT));
        game.turn(new Move(1,1,Side.DOWN));
        game.turn(new Move(0,0,Side.UP));
        game.turn(new Move(0,0,Side.LEFT));
        game.turn(new Move(0,1,Side.UP));
        game.turn(new Move(0,1,Side.RIGHT));
        Move move = game.getComputerMove();
        Assertions.assertEquals(1,move.getX());
        Assertions.assertEquals(0,move.getY());
    }

*/

    /*domanda per fare questo test come faccio?*/  //non potresti solo dare una board dove tutte le box sono nella fase di interesse? Così la mossa del computer sarebbe forzatamente quella che cerchi
   /* @Test
    public void draw_line_in_a_random_box_with_not_2_side_completed(){
        PlayerVsComputerGame game = new PlayerVsComputerGame();
        game.defaultInitialize();
        Assertions.assertEquals(game.get,game.getSideBoxByMove(new Move(0,1,Side.LEFT)));

    }*/

}
