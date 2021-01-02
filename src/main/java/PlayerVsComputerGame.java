import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PlayerVsComputerGame extends Game {
    Scanner keyboard;

    @Override
    public void startGame() {

        initializeBoard();

        while (!isGameFinished()) {

            /*
            printScoreBoard();
            turn(getComputerMove());
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }*/


            if (currentPlayer == player1) {
                printScoreBoard();
                System.out.println("Insert move [x y side:U,D,L,R]?");
                turn(keyboard.nextLine());
            } else {
                printScoreBoard();
                turn(getComputerMove());
                try {
                    Thread.sleep(400);
                }catch (Exception e){}
            }
        }
        keyboard.close();
    }

    public void defaultInitialize() {
        board = new Board(2, 2);
        graphic = new Graphic(2, 2);
    }

    @Override
    public Board initializeBoard() {
        keyboard = new Scanner(System.in);
        int boardRow = 0, boardCols = 0;
        do {
            System.out.println("Insert boardRow:");
            try {
            boardRow = Integer.parseInt(keyboard.nextLine());
            }catch(NumberFormatException e){}
        } while (!validCoordinate(boardRow));

        do {
            System.out.println("Insert boardCols:");
            try {
            boardCols = Integer.parseInt(keyboard.nextLine());
            }catch(NumberFormatException e){}
        } while (!validCoordinate(boardCols));

        board = new Board(boardRow, boardCols);
        graphic = new Graphic(boardRow, boardCols);

        return board;
    }

    public boolean validCoordinate(int boardSize) {
        return boardSize > 0;
    }

    public Move getComputerMove() {
        List<Box> boxes = matrixToList(board.getBoard());

        Move move = getMoveWithConstraint(box -> box.getNumberOfDrawLine() == 3,(box, side) -> {
            Box sideBox = getBoxInSideOfCurrentBox(box, side, boxes);
            if (sideBox == null) return false;
            return sideBox.getNumberOfDrawLine() == 2;
        }, boxes);
        if (move.isValid())  return move;

        move = getMoveWithConstraint(box -> box.getNumberOfDrawLine() == 3,(box, side) -> true, boxes);
        if (move.isValid()) return move;

        move = getMoveWithConstraint(box -> box.getNumberOfDrawLine() != 2,
                (box, side) -> {
                    Box sideBox = getBoxInSideOfCurrentBox(box, side, boxes);
                    if (sideBox == null) return true;
                    return sideBox.getNumberOfDrawLine() != 2;
                }, boxes);
        if (move.isValid()) return move;

        move = getMoveWithConstraint((box) -> true, (box, side) -> true, boxes);
        if (move.isValid()) return move;

        return Move.getInvalidMove();
    }


    private Move getMoveWithConstraint(Predicate<Box> predicateBox, BiPredicate<Box, Side> predicateSide, List<Box> boxes) {
        List<Box> candidateBoxes = boxes.stream().filter(box -> !box.isCompleted()).filter(predicateBox).collect(Collectors.toList());
        if (candidateBoxes.size() == 0) return Move.getInvalidMove();

        List<Move> candidateMoves = new ArrayList<>();

        for (Box box: candidateBoxes) {
            int indexCandidate = boxes.indexOf(box);
            int rowCandidate = indexCandidate / board.getBoardColumns();
            int colCandidate = indexCandidate % board.getBoardColumns();
            List<Side> candidateSides = Arrays.asList(Side.DOWN, Side.UP, Side.LEFT, Side.RIGHT).stream().filter(side -> !box.hasLineBySide(side)).filter(side -> predicateSide.test(box, side)).collect(Collectors.toList());
            for (Side side: candidateSides) {
                candidateMoves.add(new Move(rowCandidate, colCandidate, side));
            }
        }

        if (!candidateMoves.isEmpty())
            return getRandomElementFromList(candidateMoves);

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

    public int getRowBox_b_in_boxes(List<Box> boxes, Box b) {
        return boxes.indexOf(b) / board.getBoardColumns();
    }
    public int getColBox_b_in_boxes(List<Box> boxes, Box b) {
        return boxes.indexOf(b) % board.getBoardColumns();
    }

    public Box getBoxInSideOfCurrentBox (Box currentBox, Side side, List<Box> boxes){
        int row = getRowBox_b_in_boxes(boxes, currentBox);
        int col = getColBox_b_in_boxes(boxes, currentBox);
        Move sideMove = board.getNeighbourSideMove(new Move(row, col, side));
        if (!sideMove.isValid()) return null;
        return board.getBoxByMove(sideMove);
    }

}
