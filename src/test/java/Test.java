import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Test {

    @org.junit.jupiter.api.Test
    public void check(){
        Main main = new Main();
        assertAll(
                () -> assertEquals(1, main.ciao()),
                () -> assertEquals(1, main.ciao())
        );
    }


    @ParameterizedTest
    @ValueSource(ints = {3, 6, 24})
    void multiple_of_3_to_Fizz(int number){
        Main fizzBuzz = new Main();
        assertEquals(1,fizzBuzz.ciao());
    }

}
