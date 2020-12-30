import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class TestInputMoves {


   //private final Input input = new Input();

    @Test
    public void testMoveParser(){
        Move inputMove = Move.parseMove("10 5 U");
        Move testMove = new Move(10,5,Side.UP);

        Assertions.assertEquals(testMove,inputMove);
    }
    @Test
    public void testMoveNull(){
        Move inputMove = Move.parseMove(null);
        Move testMove = new Move(-1,-1,Side.INVALID);

        Assertions.assertEquals(testMove,inputMove);
    }

    @ParameterizedTest
    @ValueSource(strings = {"4 5 k", "u p k", "487 K"}) //L'input è per le stringhe di lunghezza 3, la convenzione si può modificare come preferiamo, basta decidere
    public void testInvalidMoveFromInput(String inpt){
        Move inputMove = Move.parseMove(inpt);
        Move testMove = new Move(-1,-1,Side.INVALID);

        Assertions.assertEquals(testMove,inputMove);
    }
    @Test
    public void testPlayerInput() {
        String data = "4 5 U";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner scanner = new Scanner(System.in);
            Move inputMove = Move.parseMove(scanner.nextLine());
            Move testMove = new Move(4,5,Side.UP);

            Assertions.assertEquals(testMove,inputMove);

        } finally {
            System.setIn(stdin);
        }


    }

    @Test
    public void testPlayerMultipleInput() {
        String data = "4 5 U \n3 5 D \n10 11 L";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner scanner = new Scanner(System.in);


            Move inputMove = Move.parseMove(scanner.nextLine());
            Move testMove = new Move(4,5,Side.UP);

            Assertions.assertEquals(testMove,inputMove);
            inputMove = Move.parseMove(scanner.nextLine());
            testMove = new Move(3,5,Side.DOWN);

            Assertions.assertEquals(testMove,inputMove);


            inputMove = Move.parseMove(scanner.nextLine());
            testMove = new Move(10,11,Side.LEFT);

            Assertions.assertEquals(testMove,inputMove);

        } finally {
            System.setIn(stdin);
        }


    }
}


