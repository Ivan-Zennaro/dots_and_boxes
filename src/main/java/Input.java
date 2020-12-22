public class Input {

    public Move parseMove(String Input){
        try {
            if(Input.length()!=3)
                throw new Exception();
            int x = Integer.parseInt(String.valueOf(Input.charAt(0)));
            int y = Integer.parseInt(String.valueOf(Input.charAt(1)));
            Side side;
            char sideAsChar = Input.charAt(2);
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
