public class Player {

    private int points = 0;
    private Color color;
    private String name;


    public Player(String name, Color color) {
        this.color = color;
        this.name = name;
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
        this.points ++;
    }


}
