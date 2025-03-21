package ca.mcmaster.se2aa4.mazerunner;

public class Tile {
    // Attributes
    private boolean isWall;

    // Contructor
    public Tile() {
        this.isWall = true;
    }

    public Tile(boolean isWall) {
        this.isWall = isWall;
    }

    // Getters and Setters
    public boolean isWall() {
        return isWall;
    }

    public void setIsWall(boolean isWall) {
        this.isWall = isWall;
    }

    // Methods
}