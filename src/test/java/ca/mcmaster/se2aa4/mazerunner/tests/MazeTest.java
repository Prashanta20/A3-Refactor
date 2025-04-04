package ca.mcmaster.se2aa4.mazerunner.tests;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.mazerunner.Maze;
import ca.mcmaster.se2aa4.mazerunner.Player;
import ca.mcmaster.se2aa4.mazerunner.Tile;
import ca.mcmaster.se2aa4.mazerunner.Maze.MazeBuilder;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
        @Test
        void testMazeInitialization() {
                Tile[][] grid = {
                                { new Tile(false), new Tile(true), new Tile(false) },
                                { new Tile(false), new Tile(false), new Tile(false) },
                                { new Tile(false), new Tile(true), new Tile(false) }
                };

                Maze maze = new MazeBuilder()
                                .setGrid(grid)
                                .build();
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
                Maze maze = new MazeBuilder()
                                .setGrid(grid)
                                .build();
                assertTrue(maze.isEnd(0, 0));
        }

        @Test
        void testIsEndAtRightmostTile() {
                Tile[][] grid = {
                                { new Tile(false), new Tile(true), new Tile(false) },
                                { new Tile(false), new Tile(false), new Tile(false) },
                                { new Tile(false), new Tile(true), new Tile(false) }
                };
                Maze maze = new MazeBuilder()
                                .setGrid(grid)
                                .build();
                assertTrue(maze.isEnd(0, 2));
        }

        @Test
        void testIsNotEndAtMiddleTile() {
                Tile[][] grid = {
                                { new Tile(false), new Tile(true), new Tile(false) },
                                { new Tile(false), new Tile(false), new Tile(false) },
                                { new Tile(false), new Tile(true), new Tile(false) }
                };
                Maze maze = new MazeBuilder()
                                .setGrid(grid)
                                .build();
                assertFalse(maze.isEnd(1, 1));
        }

        @Test
        void testValidPathWithCorrectSequence() {
                Tile[][] grid = { { new Tile(false), new Tile(false), new Tile(false) },
                                { new Tile(true), new Tile(false), new Tile(true) },
                                { new Tile(true), new Tile(true), new Tile(true) } };
                Maze maze = new MazeBuilder()
                                .setGrid(grid)
                                .build();
                assertTrue(maze.validPath("FF")); // Assuming this path is valid
        }

        @Test
        void testValidPathWithIncorrectSequence() {
                Tile[][] grid = { { new Tile(false), new Tile(true), new Tile(false) },
                                { new Tile(false), new Tile(false), new Tile(false) },
                                { new Tile(false), new Tile(true), new Tile(false) } };

                Maze maze = new MazeBuilder()
                                .setGrid(grid)
                                .build();

                assertFalse(maze.validPath("LLLL")); // Assuming this path is invalid
        }

        @Test
        void testValidPathWithEmptyPath() {
                Tile[][] grid = { { new Tile(false), new Tile(true), new Tile(false) },
                                { new Tile(false), new Tile(false), new Tile(false) },
                                { new Tile(false), new Tile(true), new Tile(false) } };

                Maze maze = new MazeBuilder()
                                .setGrid(grid)
                                .build();

                assertFalse(maze.validPath("")); // Empty path should be invalid
        }

        @Test
        void testValidPathWithSingleStep() {
                Tile[][] grid = { { new Tile(false), new Tile(true), new Tile(false) },
                                { new Tile(false), new Tile(false), new Tile(false) },
                                { new Tile(false), new Tile(true), new Tile(false) } };

                Maze maze = new MazeBuilder()
                                .setGrid(grid)
                                .build();

                assertFalse(maze.validPath("F")); // Assuming single forward move is not enough
        }

        @Test
        void testValidPathWithWallCollision() {

                Tile[][] grid = { { new Tile(false), new Tile(true), new Tile(false) },
                                { new Tile(false), new Tile(false), new Tile(false) },
                                { new Tile(false), new Tile(true), new Tile(false) } };

                Maze maze = new MazeBuilder()
                                .setGrid(grid)
                                .build();

                assertFalse(maze.validPath("FRFLLF")); // Path colliding with wall should be invalid
        }
}
