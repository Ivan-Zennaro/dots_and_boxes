import java.util.Arrays;

public class Box {

    private boolean lineUp;
    private boolean lineDown;
    private boolean lineLeft;
    private boolean lineRight;

    public Box() {
        this.lineUp = false;
        this.lineDown = false;
        this.lineLeft = false;
        this.lineRight = false;

    }

    public Box(boolean lineUp, boolean lineLeft, boolean lineDown, boolean lineRight) {
        this.lineUp = lineUp;
        this.lineDown = lineDown;
        this.lineLeft = lineLeft;
        this.lineRight = lineRight;
    }

    public boolean hasLineBySide(Side line) {
        switch (line) {
            case UP:
                return this.hasLineUp();
            case RIGHT:
                return this.hasLineRight();
            case DOWN:
                return this.hasLineDown();
            case LEFT:
                return this.hasLineLeft();
            default:
                return false;
        }
    }


    public boolean hasLineUp() {
        return lineUp;
    }

    public boolean hasLineDown() {
        return lineDown;
    }

    public boolean hasLineLeft() {
        return lineLeft;
    }

    public boolean hasLineRight() {
        return lineRight;
    }


    public void drawLineUp() {
        lineUp = true;
    }

    public void drawLineDown() {
        lineDown = true;
    }

    public void drawLineLeft() {
        lineLeft = true;
    }

    public void drawLineRight() {
        lineRight = true;
    }

    public void drawLine(Side line) {
        switch (line) {
            case UP:
                this.lineUp = true;
                break;
            case RIGHT:
                this.lineRight = true;
                break;
            case DOWN:
                this.lineDown = true;
                break;
            case LEFT:
                this.lineLeft = true;
                break;
        }
    }

    public int getNumberOfDrawLine() {
        Boolean [] lines = {lineLeft,lineDown,lineRight,lineUp};
        return (int) Arrays.stream(lines).filter(c -> c).count();
    }

    public boolean isCompleted() {
        return lineDown && lineLeft && lineRight && lineUp;
    }
}
