package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;

import ca.mcmaster.se2aa4.mazerunner.PathChecker;
import ca.mcmaster.se2aa4.mazerunner.RHRPlayer;

public class Maze {
    // Attributes
    private Tile[][] grid;

    private int[] endLeft;
    private int[] endRight;

    private Player mazePlayer;

    // Constructor is now private to enforce the use of Builder
    private Maze(MazeBuilder builder) {
        this.grid = builder.grid;
        this.endLeft = new int[2];
        this.endRight = new int[2];

        findEndLeft();
        findEndRight();

        this.mazePlayer = (builder.mazePlayer != null) ? builder.mazePlayer : new RHRPlayer(endLeft[1], endLeft[0]);

        // Set the player's position to the left endpoint
        this.mazePlayer.setX(endLeft[1]);
        this.mazePlayer.setY(endLeft[0]);
    }

    // Getters and Setters
    public Player getPlayer() {
        return mazePlayer;
    }

    public void setPlayer(Player mazePlayer) {
        this.mazePlayer = mazePlayer;
    }

    // Methods

    // diplay the maze for human chekcks
    public void displayMaze() {
        // Loop through the 2D grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isWall()) {
                    System.out.print("#"); // print "#" for wall
                } else {
                    System.out.print(" "); // print " " for pass
                }
            }
            System.out.print(System.lineSeparator());
        }
    }

    // determine if given row col is end tile
    public boolean isEnd(int row, int col) {
        if (col == 0 || col == grid[0].length - 1) { // check if we are at left or right most bound
            return !grid[row][col].isWall();
        }

        return false; // not at an end postion
    }

    // get the left end tile
    private void findEndLeft() {
        // Loop through the left column of the grid
        for (int i = 0; i < grid.length; i++) { // Iterate over columns
            if (isEnd(i, 0)) { // Check if this tile is the end on the left
                endLeft[0] = i;
                endLeft[1] = 0;
                return; // Exit once found
            }
        }
    }

    // get the right end tile
    private void findEndRight() {
        // Loop through the right column of the grid
        int len = grid[0].length; // Number of rows
        for (int i = 0; i < grid.length; i++) { // Iterate over columns
            if (isEnd(i, len - 1)) { // Check if this tile is the end on the right
                endRight[0] = i;
                endRight[1] = len - 1;
                return; // Exit once found
            }
        }
    }

    // loop through and find path for maze
    public void traverseMaze() {

        // Loop while the player is not at the right end
        while ((mazePlayer.getX() != endRight[1]) || (mazePlayer.getY() != endRight[0])) {
            // pass the adjancent tiles and let player make move decision
            mazePlayer.exploreMaze(adjacentTiles(mazePlayer.getY(), mazePlayer.getX()));
        }

        // display the factorized path
        mazePlayer.factorizedPath();
    }

    // check if given path is valid
    public boolean validPath(String givenPath) {
        PathChecker pathChecker = new PathChecker(givenPath, endLeft, endRight);
        // check if the path works for at least one of the ends
        if (pathChecker.isValidPath()) {
            System.out.println("The path: " + givenPath + " is a valid path");
            return true;
        } else {
            System.out.println("The path: " + givenPath + " is NOT a valid path");
            return false;
        }

    }

    // Helper Methods

    // get the adjancet tiles of the player
    private HashMap<Direction, Tile> adjacentTiles(int row, int col) {
        HashMap<Direction, Tile> options = new HashMap<>();
        // get the NORTH, EAST, SOUTH, WEST tiles

        // NORTH
        if (row > 0) {
            options.put(Direction.NORTH, grid[row - 1][col]);
        }
        // EAST
        if (col < grid[0].length - 1) {
            options.put(Direction.EAST, grid[row][col + 1]);
        }
        // SOUTH
        if (row < grid.length - 1) {
            options.put(Direction.SOUTH, grid[row + 1][col]);
        }
        // WEST
        if (col > 0) {
            options.put(Direction.WEST, grid[row][col - 1]);
        }

        return options; // Only returns valid tiles

    }

    // Builder Class
    public static class MazeBuilder {
        private Tile[][] grid;
        private Player mazePlayer;

        public MazeBuilder setGrid(Tile[][] grid) {
            this.grid = grid;
            return this;
        }

        public MazeBuilder setPlayer(Player player) {
            this.mazePlayer = player;
            return this;
        }

        public Maze build() {
            if (grid == null) {
                throw new IllegalStateException("Grid must be set before building the Maze");
            }
            return new Maze(this);
        }
    }
}