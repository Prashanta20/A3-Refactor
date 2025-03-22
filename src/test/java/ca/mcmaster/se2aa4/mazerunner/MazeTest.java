package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    @Test
    void testMazeInitialization() {
        Tile[][] grid = {
                { new Tile(false), new Tile(true), new Tile(false) },
                { new Tile(false), new Tile(false), new Tile(false) },
                { new Tile(false), new Tile(true), new Tile(false) }
        };

        Maze maze = new Maze(grid);
        Player player = maze.getPlayer();

        assertEquals(0, player.getY()); // Check start row
        assertEquals(0, player.getX()); // Check start column
    }

    @Test
    void testIsEndAtLeftmostTile() {
        Tile[][] grid = {
                { new Tile(false), new Tile(true), new Tile(false) },
                { new Tile(false), new Tile(false), new Tile(false) },
                { new Tile(false), new Tile(true), new Tile(false) }
        };
        Maze maze = new Maze(grid);
        assertTrue(maze.isEnd(0, 0));
    }

    @Test
    void testIsEndAtRightmostTile() {
        Tile[][] grid = {
                { new Tile(false), new Tile(true), new Tile(false) },
                { new Tile(false), new Tile(false), new Tile(false) },
                { new Tile(false), new Tile(true), new Tile(false) }
        };
        Maze maze = new Maze(grid);
        assertTrue(maze.isEnd(0, 2));
    }

    @Test
    void testIsNotEndAtMiddleTile() {
        Tile[][] grid = {
                { new Tile(false), new Tile(true), new Tile(false) },
                { new Tile(false), new Tile(false), new Tile(false) },
                { new Tile(false), new Tile(true), new Tile(false) }
        };
        Maze maze = new Maze(grid);
        assertFalse(maze.isEnd(1, 1));
    }

    @Test
    void testValidPathWithCorrectSequence() {
        Maze maze = new Maze(new Tile[][] {
                { new Tile(false), new Tile(false), new Tile(false) },
                { new Tile(true), new Tile(false), new Tile(true) },
                { new Tile(true), new Tile(true), new Tile(true) }
        });
        assertTrue(maze.validPath("FF")); // Assuming this path is valid
    }

    @Test
    void testValidPathWithIncorrectSequence() {
        Maze maze = new Maze(new Tile[][] {
                { new Tile(false), new Tile(true), new Tile(false) },
                { new Tile(false), new Tile(false), new Tile(false) },
                { new Tile(false), new Tile(true), new Tile(false) }
        });
        assertFalse(maze.validPath("LLLL")); // Assuming this path is invalid
    }

    @Test
    void testValidPathWithEmptyPath() {
        Maze maze = new Maze(new Tile[][] {
                { new Tile(false), new Tile(true), new Tile(false) },
                { new Tile(false), new Tile(false), new Tile(false) },
                { new Tile(false), new Tile(true), new Tile(false) }
        });
        assertFalse(maze.validPath("")); // Empty path should be invalid
    }

    @Test
    void testValidPathWithSingleStep() {
        Maze maze = new Maze(new Tile[][] {
                { new Tile(false), new Tile(true), new Tile(false) },
                { new Tile(false), new Tile(false), new Tile(false) },
                { new Tile(false), new Tile(true), new Tile(false) }
        });
        assertFalse(maze.validPath("F")); // Assuming single forward move is not enough
    }

    @Test
    void testValidPathWithWallCollision() {
        Maze maze = new Maze(new Tile[][] {
                { new Tile(false), new Tile(true), new Tile(false) },
                { new Tile(false), new Tile(false), new Tile(false) },
                { new Tile(false), new Tile(true), new Tile(false) }
        });
        assertFalse(maze.validPath("FRFLLF")); // Path colliding with wall should be invalid
    }
}
