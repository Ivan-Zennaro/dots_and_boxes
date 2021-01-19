package sdm.examproject.dots_and_boxes;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComputerSolver {

    private final List<Box> boxes;
    private final Board board;
    private final Difficulty difficulty;

    private static final Random rand = new Random();

    public ComputerSolver(Board board, Difficulty difficulty) {
        this.boxes = Arrays.stream(board.getBoxes())
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        this.board = board;
        this.difficulty = difficulty;
    }

    public Move getComputerMove() {

        Move move = getMove_thatClosesABox();
        if (move != null && move.isValid()) return move;

        move = getMove_thatDoesNotPutTheThirdLineInABox();
        if (move != null && move.isValid() && difficulty != Difficulty.EASY) return move;

        move = getMove_thatLimitOpponentPoints();
        if (move != null && move.isValid() && difficulty == Difficulty.HARD) return move;

        move = getRandomMove();
        if (move != null && move.isValid()) return move;

        return Move.getInvalidMove();
    }

    private Move getMove_thatClosesABox() {
        return getMoveWithConstraint(box -> box.getNumberOfDrawnLine() == 3, (box, side) -> true);
    }

    private Move getMove_thatDoesNotPutTheThirdLineInABox() {
        return getMoveWithConstraint(box -> box.getNumberOfDrawnLine() != 2,
                (box, side) -> sideOfBoxHasNotANeighbourWith_2_Drawn_lines(box,side));
    }


    private Move getMove_thatLimitOpponentPoints() {
        return getMoveWithConstraint(box -> box.getNumberOfDrawnLine() == 2, (box, side) ->
            otherMissingSideHasNotANeighbourWith_2_Drawn_lines(box,side) &&
                    sideOfBoxHasNotANeighbourWith_2_Drawn_lines(box,side));
    }

    private boolean sideOfBoxHasNotANeighbourWith_2_Drawn_lines (Box box,Side side){
        Box sideBox = getNeighbourBox(box, side);
        if (sideBox == null) return true;
        return sideBox.getNumberOfDrawnLine() != 2;
    }

    private boolean otherMissingSideHasNotANeighbourWith_2_Drawn_lines (Box box,Side side){
        Box copyOfCurrentBox = getACopyOfTheBox(box);
        copyOfCurrentBox.drawLine(side);
        Side missingSide = getMissingSideFromBox(copyOfCurrentBox);

        return sideOfBoxHasNotANeighbourWith_2_Drawn_lines(box,missingSide);
    }

    private Move getRandomMove() {
        return getMoveWithConstraint((box) -> true, (box, side) -> true);
    }

    private Move getMoveWithConstraint(Predicate<Box> predicateBox,
                                       BiPredicate<Box, Side> predicateSide) {

        List<Box> candidateBoxes = boxes.stream()
                .filter(box -> !box.isCompleted())
                .filter(predicateBox)
                .collect(Collectors.toList());

        if (candidateBoxes.isEmpty()) return Move.getInvalidMove();

        List<Move> candidateMoves = new ArrayList<>();

        for (Box box : candidateBoxes) {
            int indexCandidate = boxes.indexOf(box);
            int rowCandidate = indexCandidate / board.getBoardColumns();
            int colCandidate = indexCandidate % board.getBoardColumns();
            Stream.of(Side.DOWN, Side.UP, Side.LEFT, Side.RIGHT)
                    .filter(side -> !box.hasLineBySide(side))
                    .filter(side -> predicateSide.test(box, side))
                    .forEach(side -> candidateMoves.add(new Move(rowCandidate, colCandidate, side)));
        }

        if (!candidateMoves.isEmpty())
            return getRandomElementFromList(candidateMoves);

        return Move.getInvalidMove();
    }

    private int getRowBox_b_in_boxes(Box b) {
        return boxes.indexOf(b) / board.getBoardColumns();
    }

    private int getColBox_b_in_boxes(Box b) {
        return boxes.indexOf(b) % board.getBoardColumns();
    }

    private Box getNeighbourBox(Box currentBox, Side side) {
        int row = getRowBox_b_in_boxes(currentBox);
        int col = getColBox_b_in_boxes(currentBox);
        Move sideMove = board.getNeighbourSideMove(new Move(row, col, side));
        if (!sideMove.isValid()) return null;
        return board.getBoxByMove(sideMove);
    }

    private static Side getMissingSideFromBox(Box box) {
        if (box.getNumberOfDrawnLine() != 3) return Side.INVALID;
        return Arrays.stream(Side.values()).filter
                (side -> !box.hasLineBySide(side)).findFirst().orElse(Side.INVALID);
    }

    private static <T> T getRandomElementFromList(List<T> list) {
        if (list == null || list.isEmpty()) return null;

        return list.get(rand.nextInt(list.size()));
    }

    private Box getACopyOfTheBox(Box box) {
        Box copyBox = new Box();
        Arrays.stream(Side.values()).filter(side -> box.hasLineBySide(side)).
                forEach(side -> copyBox.drawLine(side));

        return copyBox;
    }
}
