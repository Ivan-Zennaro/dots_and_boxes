import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ComputerSolver {

    private Board board;
    private Difficulty difficulty;

    public ComputerSolver(Board board, Difficulty difficulty) {
        this.board = board;
        this.difficulty = difficulty;
    }

    public Move getComputerMove() {
        List<Box> boxes = matrixToList(board.getBoard());

        Move move = getMove_thatCloseAtLeast2Boxes(boxes);
        if (move.isValid() && difficulty == Difficulty.HARD ) return move;

        move = getMove_thatClosesABox(boxes);
        if (move.isValid()) return move;

        move = getMove_thatDoesNotPutTheThirdLineInABox(boxes);
        if (move.isValid() && difficulty != Difficulty.EASY  ) return move;

        move = getRandomMove(boxes);
        if (move.isValid()) return move;

        return Move.getInvalidMove();
    }

    private Move getMove_thatCloseAtLeast2Boxes(List<Box> boxes) {
        return getMoveWithConstraint(box -> box.getNumberOfDrawnLine() == 3, (box, side) -> {
            Box sideBox = getNeighbourBox(box, side, boxes);
            if (sideBox == null) return false;
            return sideBox.getNumberOfDrawnLine() == 2;
        }, boxes);
    }

    private Move getMove_thatClosesABox(List<Box> boxes) {
        return getMoveWithConstraint(box -> box.getNumberOfDrawnLine() == 3, (box, side) -> true, boxes);
    }

    private Move getMove_thatDoesNotPutTheThirdLineInABox(List<Box> boxes) {
        return getMoveWithConstraint(box -> box.getNumberOfDrawnLine() != 2,
                (box, side) -> {
                    Box sideBox = getNeighbourBox(box, side, boxes);
                    if (sideBox == null) return true;
                    return sideBox.getNumberOfDrawnLine() != 2;
                }, boxes);
    }

    private Move getRandomMove(List<Box> boxes) {
        return getMoveWithConstraint((box) -> true, (box, side) -> true, boxes);
    }

    private Move getMoveWithConstraint(Predicate<Box> predicateBox, BiPredicate<Box, Side> predicateSide, List<Box> boxes) {
        List<Box> candidateBoxes = boxes.stream().filter(box -> !box.isCompleted()).filter(predicateBox).collect(Collectors.toList());
        if (candidateBoxes.size() == 0) return Move.getInvalidMove();

        List<Move> candidateMoves = new ArrayList<>();

        for (Box box : candidateBoxes) {
            int indexCandidate = boxes.indexOf(box);
            int rowCandidate = indexCandidate / board.getBoardColumns();
            int colCandidate = indexCandidate % board.getBoardColumns();
            List<Side> candidateSides = Arrays.asList(Side.DOWN, Side.UP, Side.LEFT, Side.RIGHT).stream().filter(side -> !box.hasLineBySide(side)).filter(side -> predicateSide.test(box, side)).collect(Collectors.toList());
            for (Side side : candidateSides) {
                candidateMoves.add(new Move(rowCandidate, colCandidate, side));
            }
        }

        if (!candidateMoves.isEmpty())
            return getRandomElementFromList(candidateMoves);

        return Move.getInvalidMove();
    }

    public int getRowBox_b_in_boxes(List<Box> boxes, Box b) {
        return boxes.indexOf(b) / board.getBoardColumns();
    }

    public int getColBox_b_in_boxes(List<Box> boxes, Box b) {
        return boxes.indexOf(b) % board.getBoardColumns();
    }

    public Box getNeighbourBox(Box currentBox, Side side, List<Box> boxes) {
        int row = getRowBox_b_in_boxes(boxes, currentBox);
        int col = getColBox_b_in_boxes(boxes, currentBox);
        Move sideMove = board.getNeighbourSideMove(new Move(row, col, side));
        if (!sideMove.isValid()) return null;
        return board.getBoxByMove(sideMove);
    }

    public static Side getMissingSideFromBox(Box box) {
        if (box.getNumberOfDrawnLine() != 3) return Side.INVALID;
        if (!box.hasLineLeft()) return Side.LEFT;
        if (!box.hasLineDown()) return Side.DOWN;
        if (!box.hasLineRight()) return Side.RIGHT;
        if (!box.hasLineUp()) return Side.UP;
        return Side.INVALID;
    }

    public static <T> List<T> matrixToList(T[][] matrix) {
        List<T> list = new ArrayList<T>();
        for (T[] array : matrix)
            list.addAll(Arrays.asList(array));
        return list;
    }

    public static <T> T getRandomElementFromList(List<T> list) {
        if (list == null || list.size() < 1) return null;
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }


}
