package sdm.examproject.dots_and_boxes;

public class Player {

    private final String name;
    private final Color color;
    private int points;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.points = 0;
    }

    public int getPoints() {
        return this.points;
    }

    public Color getColor() {
        return this.color;
    }

    public char getFirstLetterName() {
        return this.name.charAt(0);
    }

    public String getName() {
        return name;
    }

    public void onePointDone() {
        this.points++;
    }


}
