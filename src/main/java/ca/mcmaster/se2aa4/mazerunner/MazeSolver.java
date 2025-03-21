package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashMap;

public interface MazeSolver {
    public void exploreMaze(HashMap<Direction, Tile> options); // method to explore a maze
}
