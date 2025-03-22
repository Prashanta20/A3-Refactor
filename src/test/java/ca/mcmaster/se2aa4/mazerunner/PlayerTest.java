package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

class PlayerTest {
    @Test
    void ForwardMove() {
        RHRPlayer player = new RHRPlayer();
        // facing east
        HashMap<Direction, Tile> directions = new HashMap<>();
        Tile F_Tile = new Tile(false);
        Tile R_Tile = new Tile(true);

        directions.put(Direction.EAST, F_Tile);
        directions.put(Direction.SOUTH, R_Tile);

        player.exploreMaze(directions); // make the move
        Character move = player.getPath().getLast();

        assertEquals('F', move);
    }

    @Test
    void RightTurn() {
        RHRPlayer player = new RHRPlayer();
        // facing east
        HashMap<Direction, Tile> directions = new HashMap<>();
        Tile F_Tile = new Tile(true);
        Tile R_Tile = new Tile(false);

        directions.put(Direction.EAST, F_Tile);
        directions.put(Direction.SOUTH, R_Tile);

        // since this case the player makes to moves at once
        // check second last move was right turn
        player.exploreMaze(directions); // make the move
        Character move = player.getPath().getFirst();

        assertEquals('R', move);
    }

    @Test
    void LeftTurn() {
        RHRPlayer player = new RHRPlayer();
        // facing east
        HashMap<Direction, Tile> directions = new HashMap<>();
        Tile F_Tile = new Tile(true);
        Tile R_Tile = new Tile(true);

        directions.put(Direction.EAST, F_Tile);
        directions.put(Direction.SOUTH, R_Tile);

        player.exploreMaze(directions); // make the move
        Character move = player.getPath().getLast();

        assertEquals('L', move);
    }
}
