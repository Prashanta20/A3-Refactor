package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.mazerunner.Maze.MazeBuilder;

import org.apache.commons.cli.*;

public class Main {

    private static Maze maze;
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        // Create Options object
        Options options = new Options();

        // Add -i option
        options.addOption("i", "input", true, "load maze (requires file path)");

        // Add -p option
        options.addOption("p", "path", true, "verify path (requires path string)");

        // Create ArrayList for storing Maze grid
        ArrayList<Tile[]> gridList = new ArrayList<>();

        try {
            // Create parser for command-line arguments
            CommandLineParser parser = new DefaultParser();
            CommandLine commandLine = parser.parse(options, args);

            // Ensure that the required `-i` flag is provided
            if (!commandLine.hasOption("i")) {
                throw new Exception("Missing required -i flag for input file");
            }

            // Read the maze from the input file specified by the `-i` flag
            String mazeFilePath = commandLine.getOptionValue("i");
            readMaze(gridList, mazeFilePath);

            // Convert ArrayList to a 2D array for easy access
            Tile[][] grid = gridList.toArray(new Tile[gridList.size()][]);

            // Create Player to traverse maze
            Player player = new RHRPlayer(); // can create different players to add more advanced algorthims

            // Create Maze object
            maze = new MazeBuilder()
                    .setGrid(grid)
                    .setPlayer(player)
                    .build();

            // ***** DISPLAY MAZE OPTIONAL ***** //
            // maze.displayMaze(); // Display maze
            // ***** DISPLAY MAZE OPTIONAL ***** //

            // Check if the `-p` flag is provided and handle accordingly
            if (commandLine.hasOption("p")) {
                String path = commandLine.getOptionValue("p");
                maze.validPath(path); // Validate the provided path
            } else {
                maze.traverseMaze(); // Find a path in the maze
            }

        } catch (ParseException e) {
            logger.error("Failed to parse command-line arguments: " + e.getMessage());
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred: " + e.getMessage());
        }

        logger.info("** End of MazeRunner");
    }

    // Helper method

    // Read maze from file
    private static void readMaze(ArrayList<Tile[]> gridList, String mazeFilePath) {
        try {
            logger.trace("**** Reading the maze from file: " + mazeFilePath);
            BufferedReader reader = new BufferedReader(new FileReader(mazeFilePath));
            String line;

            while ((line = reader.readLine()) != null) {
                Tile[] tiles = new Tile[line.length()];

                for (int idx = 0; idx < line.length(); idx++) {
                    Tile tile = new Tile();

                    if (line.charAt(idx) == '#') {
                        tile.setIsWall(true);
                        logger.debug("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        tile.setIsWall(false);
                        logger.debug("PASS ");
                    }

                    tiles[idx] = tile;
                }
                gridList.add(tiles);
                logger.debug(System.lineSeparator());
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred while reading the maze: " + e.getMessage());
        }
    }
}
