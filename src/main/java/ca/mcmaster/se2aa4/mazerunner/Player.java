package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.HashMap;

enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}

public abstract class Player implements MazeSolver {
    // Attributes
    protected int position_x;
    protected int position_y;
    protected Direction direction = Direction.EAST; // Start with direction always facing East
    protected ArrayList<Character> path = new ArrayList<>();

    // Contructor
    public Player() {
        this.position_x = -1;
        this.position_y = -1;
    }

    public Player(int position_x, int position_y) {
        this.position_x = position_x;
        this.position_y = position_y;
    }

    // Getters and Setters
    public final int getX() {
        return position_x;
    }

    public final void setX(int position_x) {
        this.position_x = position_x;
    }

    public final int getY() {
        return position_y;
    }

    public final void setY(int position_y) {
        this.position_y = position_y;
    }

    public final Direction getDirection() {
        return direction;
    }

    public final void setDirection(Direction direction) {
        this.direction = direction;
    }

    public final ArrayList<Character> getPath() {
        return path;
    }

    // Methods

    public final void moveForward() {
        if (direction == Direction.NORTH) {
            // Facing North
            position_y--; // increase y by one
        } else if (direction == Direction.EAST) {
            // Facing East
            position_x++;
        } else if (direction == Direction.SOUTH) {
            // Facing South
            position_y++;
        } else if (direction == Direction.WEST) {
            // Facing West
            position_x--;
        } else {
            // Error no movement
        }
        path.add('F');
    }

    public final void turnRight() {
        if (direction == Direction.NORTH) {
            // Facing North
            direction = Direction.EAST; // turn to EAST
        } else if (direction == Direction.EAST) {
            // Facing East
            direction = Direction.SOUTH; // turn to SOUTH
        } else if (direction == Direction.SOUTH) {
            // Facing South
            direction = Direction.WEST; // turn to WEST
        } else if (direction == Direction.WEST) {
            // Facing West
            direction = Direction.NORTH; // turn to NORTH
        } else {
            // Error no movement
        }
        path.add('R');
    }

    public final void turnLeft() {
        if (direction == Direction.NORTH) {
            // Facing North
            direction = Direction.WEST; // turn to WEST
        } else if (direction == Direction.EAST) {
            // Facing East
            direction = Direction.NORTH; // turn to NORTH
        } else if (direction == Direction.SOUTH) {
            // Facing South
            direction = Direction.EAST; // turn to EAST
        } else if (direction == Direction.WEST) {
            // Facing West
            direction = Direction.SOUTH; // turn to SOUTH
        } else {
            // Error no movement
        }
        path.add('L');
    }

    public final void factorizedPath() {
        if (path.isEmpty()) {
            return; // no path
        }

        StringBuilder factorizedPath = new StringBuilder(); // start new string
        char current = path.get(0); // start with the first move
        int count = 0; // set starting count to 0

        for (char move : path) {
            if (current == move) {
                // if the current is the same as the previous moves
                // add one to count
                count++;
            } else {
                // it is a new type of move
                if (count > 1) {
                    factorizedPath.append(count); // add the number to the string
                }
                factorizedPath.append(current).append(" "); // add the move to the string
                current = move; // update the current move
                count = 1; // reset count
            }
        }

        // Add the last set of moves
        if (count > 1) {
            factorizedPath.append(count);
        }
        factorizedPath.append(current);

        System.out.println("Path: " + factorizedPath.toString()); // print the factorized path
    }

    // Explore maze different based on type of player
    public abstract void exploreMaze(HashMap<Direction, Tile> options);

    protected final Tile forwardTile(HashMap<Direction, Tile> options) {
        // return the forward tile for the player
        if (options.containsKey(direction)) {
            return options.get(direction);
        } else {
            return null;
        }
    }

    protected final Tile rightTile(HashMap<Direction, Tile> options) {
        Tile rightTile = new Tile();
        if (direction == Direction.NORTH) {
            // Facing North
            rightTile = options.get(Direction.EAST); // EAST tile
        } else if (direction == Direction.EAST) {
            // Facing East
            rightTile = options.get(Direction.SOUTH); // SOUTH tile
        } else if (direction == Direction.SOUTH) {
            // Facing South
            rightTile = options.get(Direction.WEST); // WEST tile
        } else if (direction == Direction.WEST) {
            // Facing West
            rightTile = options.get(Direction.NORTH); // NORTH tile
        } else {
            // Error no movement
            rightTile = null; // no tile
        }

        return rightTile;
    }

}