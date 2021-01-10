public class Player {

    private char id;
    private int points = 0;
    private Color color;



    private String name;

    public Player(char id, Color color) {
        this.id = id;
        this.color = color;
    }

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

    public char getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int onePointDone() {
        this.points += 1;
        return this.points;
    }


}
