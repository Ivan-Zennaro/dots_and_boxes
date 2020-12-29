import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

public class TestGameComputer {

    @ParameterizedTest
    @CsvSource({"5,2,TRUE", "-1,2,FALSE","1,1,TRUE","0,0,FALSE","0,1,FALSE"})
    public void board_cols_and_row_are_valid(int boardRow, int boardCols, boolean validity){
        PlayerVsComputerGame game = new PlayerVsComputerGame();
        Assertions.assertEquals(validity,game.validCoordinate(boardRow,boardCols));
    }

    @Test
    public void matrix_converted_into_list (){
        Integer[][] matrix = {{1,2},{3,4}};
        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);list.add(4);
        Assertions.assertEquals(list,PlayerVsComputerGame.matrixToList(matrix));

    }

}
