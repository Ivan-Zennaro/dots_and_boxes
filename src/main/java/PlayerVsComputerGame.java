import java.util.*;
import java.util.stream.Collectors;

public class PlayerVsComputerGame extends Game {
    Scanner keyboard;

    @Override
    public void startGame() {

        initializeBoard();
        while (!isGameFinished()) {

            printScoreBoard();
            turn(getComputerMove());
            try {
                Thread.sleep(1500);
            }catch (Exception e){}

            /*if (currentPlayer == player1) {
                printScoreBoard();
                System.out.println("Insert move [x y side:U,D,L,R]?");
                turn(keyboard.nextLine());
            } else {
                printScoreBoard();
                turn(getComputerMove());
                try {
                    Thread.sleep(400);
                }catch (Exception e){}

            }*/
        }
        keyboard.close();
    }

    public void defaultInitialize(){
        board = new Board(2, 2);
        graphic = new Graphic(2, 2);
    }

    @Override
    public Board initializeBoard() {
        keyboard = new Scanner(System.in);
        int boardRow, boardCols;
        do {
            System.out.println("Insert boardRow:");
            boardRow = keyboard.nextInt();
            System.out.println("Insert boardCols:");
            boardCols = keyboard.nextInt();
        } while (!validCoordinate(boardRow,boardCols));

        board = new Board(boardRow, boardCols);
        graphic = new Graphic(boardRow, boardCols);

        return board;
    }

    public boolean validCoordinate (int boardRow, int boardCols){
        return boardRow > 0 && boardCols > 0;
    }

    public Move getComputerMove(){
        List<Box> boxes = matrixToList(board.getBoard());
        Move move = completeA3SideBox(boxes);
        if (move.isValid())
            return move;

        move = randomMoveInBoxWithNot2SideCompleted(boxes);
        if (move.isValid())
            return move;

        move = randomMove(boxes);
        if (move.isValid())
            return move;

        return Move.getInvalidMove();
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

    public static <T> T getRandomElementFromList(List<T> list) {
        if (list == null || list.size() < 1) return null;
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }


    public Move completeA3SideBox(List<Box> boxes) {
        List<Box> notFullBoxes = boxes.stream().filter(box -> !box.isCompleted()).collect(Collectors.toList());
        Optional<Box> boxToComplete = notFullBoxes.stream().
                filter(box -> box.getNumberOfDrawLine() == 3).findFirst();
        if (boxToComplete.isPresent()){
            Box box = boxToComplete.get();
            int indexCandidate = boxes.indexOf(box);
            int rowCandidate = indexCandidate / board.getBoardColumns();
            int ColCandidate = indexCandidate % board.getBoardColumns();
            Side candidateSide = getMissingSideFromBox(box);
            return new Move(rowCandidate,ColCandidate,candidateSide);
        }
        return new Move(-1,-1,Side.INVALID);
    }

    public Move randomMoveInBoxWithNot2SideCompleted(List<Box> boxes) {
        List<Box> notFullBoxes = boxes.stream().filter(box -> !box.isCompleted()).collect(Collectors.toList());
        List<Box> candidateBoxes = notFullBoxes.stream().filter(box -> box.getNumberOfDrawLine() != 2).collect(Collectors.toList());
        if (candidateBoxes.size() > 0){
            Box box = candidateBoxes.get(new Random().nextInt(candidateBoxes.size()));
            int indexCandidate = boxes.indexOf(box);
            int rowCandidate = indexCandidate / board.getBoardColumns();
            int ColCandidate = indexCandidate % board.getBoardColumns();
            List<Side> sides = Arrays.asList(Side.DOWN,Side.UP,Side.LEFT,Side.RIGHT);
            Side candidateSide;
            do {
                candidateSide = getRandomElementFromList(sides);
            }while (box.hasLineBySide(candidateSide));
            return new Move(rowCandidate,ColCandidate,candidateSide);
        }
        return new Move(-1,-1,Side.INVALID);
    }

    public Move randomMove(List<Box> boxes) {
        List<Box> notFullBoxes = boxes.stream().filter(box -> !box.isCompleted()).collect(Collectors.toList());
        if (notFullBoxes.size() == 0) return new Move(-1,-1,Side.INVALID);

        Box box = getRandomElementFromList(notFullBoxes);
        int indexCandidate = boxes.indexOf(box);
        int rowCandidate = indexCandidate / board.getBoardColumns();
        int ColCandidate = indexCandidate % board.getBoardColumns();
        List<Side> sides = Arrays.asList(Side.DOWN,Side.UP,Side.LEFT,Side.RIGHT);
        Side candidateSide;
        do {
            candidateSide = getRandomElementFromList(sides);
        }while (box.hasLineBySide(candidateSide));
        return new Move(rowCandidate,ColCandidate,candidateSide);
    }




}
