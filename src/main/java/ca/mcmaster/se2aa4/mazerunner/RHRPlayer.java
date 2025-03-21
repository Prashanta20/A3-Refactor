package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.HashMap;

public class RHRPlayer extends Player {
    // Attributes

    // Contructor
    public RHRPlayer() {
        super();
    }

    public RHRPlayer(int position_x, int position_y) {
        super(position_x, position_y);
    }

    public void exploreMaze(HashMap<Direction, Tile> options) {
        // 3 conditions to move
        // 1. Right hand wall, Forward pass: move forward
        // 2. Right hand not wall: turn right
        // 3. Right hand wall, Forward wall: turn left
        Tile rightTile = rightTile(options);
        Tile forwardTile = forwardTile(options);

        if (rightTile.isWall()) {
            // Right tile is a wall
            if (forwardTile.isWall()) {
                // forward is also wall (3)
                turnLeft();

            } else {
                // forward is a pass (1)
                moveForward();

            }

        } else {
            // right hand is not a wall (2)
            turnRight();
            moveForward();

        }

    }

}