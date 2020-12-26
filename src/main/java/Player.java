public class Player {

    private char id;
    private int points = 0;
    private Color color;

    public Player(char id, Color color){
        this.id = id;
        this.color = color;
    }

    public int getPoints(){
        return this.points;
    }

    public Color getColor(){
        return this.color;
    }

    public char getId(){
        return this.id;
    }

    public int increasePoint(int points){
        this.points += points;
        return this.points;
    }


}
