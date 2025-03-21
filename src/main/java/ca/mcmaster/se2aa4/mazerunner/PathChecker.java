package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.RHRPlayer;

public class PathChecker {
    // Attributes
    private String path;
    private int[] endLeft;
    private int[] endRight;

    public PathChecker(String path, int endLeft[], int endRight[]) {
        this.path = factorizedToCanonical(path);
        this.endLeft = endLeft;
        this.endRight = endRight;
    }

    // Methods

    public boolean isValidPath() {
        return startingEast() || startingWest();
    }

    private String factorizedToCanonical(String givenPath) {
        if (givenPath.matches(".*\\d.*")) {
            // there are numbers in the string, it is in factorized form
            StringBuilder canPath = new StringBuilder(); // start new string

            try {
                for (int i = 0; i < givenPath.length(); i++) {
                    if (String.valueOf(givenPath.charAt(i)).matches("\\d")) {
                        // it is a number

                        for (int j = 0; j < Integer.parseInt(String.valueOf(givenPath.charAt(i))); j++) {
                            // add that many of the next character
                            canPath.append("" + givenPath.charAt(i + 1));
                        }
                        i++; // go to the next letter
                    } else {
                        // it has no number
                        canPath.append("" + givenPath.charAt(i));
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                return givenPath; // return the original path
            }

            return canPath.toString();

        } else {
            // there are no numbers in the string, it is in canonical form
            return givenPath;
        }
    }

    // check path starting at west end
    private boolean startingWest() {
        Player player = new RHRPlayer(endLeft[1], endLeft[0]); // set player starting position to Left end

        // Traverse the path
        for (int i = 0; i < path.length(); i++) {
            char move = path.charAt(i);

            // move the palyer according ot the given path
            if (move == 'F') {
                player.moveForward();
            } else if (move == 'R') {
                player.turnRight();
            } else if (move == 'L') {
                player.turnLeft();
            } else {
                // not a valid move
                return false;
            }
        }

        // after the last moves, check if player is at left WEST end
        if (player.getX() == endRight[1] && player.getY() == endRight[0]) {
            return true; // player reached other end
        } else {
            return false; // player is not at other end
        }
    }

    // check path starting at east end
    private boolean startingEast() {
        Player player = new RHRPlayer(endRight[1], endRight[0]); // set player starting position to Right end
        player.setDirection(Direction.WEST); // set starting postiont to WEST

        // Traverse the path
        for (int i = 0; i < path.length(); i++) {
            char move = path.charAt(i);

            // move the palyer according ot the given path
            if (move == 'F') {
                player.moveForward();
            } else if (move == 'R') {
                player.turnRight();
            } else if (move == 'L') {
                player.turnLeft();
            } else {
                // not a valid move
                return false;
            }
        }

        // after the last moves, check if player is at left WEST end
        if (player.getX() == endLeft[1] && player.getY() == endLeft[0]) {
            return true; // player reached other end
        } else {
            return false; // player is not at other end
        }
    }
}