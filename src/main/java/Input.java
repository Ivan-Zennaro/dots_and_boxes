public class Input {

    public Move parseMove(String input){
        try {

            String[] splitInput = input.split(" ");
            if(splitInput.length!=3 && splitInput[2].length() != 1 )
                throw new Exception();
            int x = Integer.parseInt(String.valueOf(splitInput[0]));
            int y = Integer.parseInt(String.valueOf(splitInput[1]));
            Side side;
            char sideAsChar =splitInput[2].charAt(0);
            for (Side refSide : Side.values()) {
                if (refSide.asChar() == sideAsChar)
                    return new Move(x, y, refSide);
            }
        }catch( Exception e){
                return new Move(-1, -1, Side.INVALID);
        }
        return new Move(-1, -1, Side.INVALID);
    }
}
