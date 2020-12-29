import java.util.*;

public class PlayerVsComputerGame extends Game {

    @Override
    public void startGame() {

        initializeBoard();

        Scanner keyboard = new Scanner(System.in);

        while (!isGameFinished()) {
            printScoreBoard();
            if (currentPlayer == player1) {
                System.out.println("Insert move [x y side:U,D,L,R]?");
                turn(keyboard.nextLine());
            }
            else {
                turn("");
            }
        }
        keyboard.close();
    }

    @Override
    public Board initializeBoard() {
        Scanner keyboard = new Scanner(System.in);
        int boardRow, boardCols;
        do {
            System.out.println("Insert boardRow:");
            boardRow = keyboard.nextInt();
            System.out.println("Insert boardCols:");
            boardCols = keyboard.nextInt();
        } while (!validCoordinate(boardRow,boardCols));

        board = new Board(boardRow, boardCols);
        graphic = new Graphic(boardRow, boardCols);

        keyboard.close();
        return board;
    }

    public boolean validCoordinate (int boardRow, int boardCols){
        return boardRow > 0 && boardCols > 0;
    }

    public Move getComputerMove(){
        List<Box> boxes = matrixToList(board.getBoard());
        boxes.removeIf(box -> box.isCompleted());
        Optional<Box> candidateBox = boxes.stream().
                filter(box -> box.getNumberOfDrawLine() == 3).findFirst();
        if (candidateBox.isPresent()){
            Box box = candidateBox.get();
            int indexCandidate = boxes.indexOf(box);
            int rowCandidate = indexCandidate / board.getBoardRows();
            int ColCandidate = indexCandidate % board.getBoardColumns();



           // return new Move()

        }


        long numberOfNotCompletedBoxWith = boxes.stream()
                .filter(box -> box.getNumberOfDrawLine() != 2)
                .count();
        if (boxes.size() == 0) return new Move(-1,-1,Side.INVALID);
        return null;
    }

    public static <T> List<T> matrixToList(T[][] matrix) {
        List<T> list = new ArrayList<T>();
        for (T[] array : matrix)
            list.addAll(Arrays.asList(array));
        return list;
    }

    public static Side getMissingSideFromBox(Box box) {
        if (box.getNumberOfDrawLine() != 3) return Side.INVALID;
        if (!box.hasLineLeft()) return Side.LEFT;
        if (!box.hasLineDown()) return Side.DOWN;
        if (!box.hasLineRight()) return Side.RIGHT;
        if (!box.hasLineUp()) return Side.UP;
        return Side.INVALID;
    }


}
