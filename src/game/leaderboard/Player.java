package game.leaderboard;

public class Player {

    private String name;
    private int score;
    
    // Constructor
    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
