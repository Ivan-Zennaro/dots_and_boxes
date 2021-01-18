package sdm.examproject.dots_and_boxes;

import java.util.*;

public class Box {

    private Set<Side> drawnSides;

    public Box() {
        drawnSides = new HashSet<>();
    }

    public Box(Set<Side> sides) {
        drawnSides = sides;
    }

    public boolean hasLineBySide(Side line) {
        return drawnSides.contains(line);
    }

    public void drawLine(Side line) {
        if (line != Side.INVALID)
            drawnSides.add(line);
    }

    public int getNumberOfDrawnLine() {
        return drawnSides.size();
    }

    public boolean isCompleted() {
        return drawnSides.size() == 4;
    }
}
