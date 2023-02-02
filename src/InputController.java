import java.util.Scanner;

public class InputController {
    private static final String TOO_CLOSE = "Error! You placed it too close to another one. Try again:";
    private static final String WRONG_FORMAT = "Error! You entered the wrong coordinates! Try again:";
    private static final String WRONG_LOCATION = "Error! Wrong ship location. Try again:";
    private static final String WRONG_LENGTH = "Error! Wrong length of the %s! Try again:";
    private static final String OUT_OF_BOUNDS = "You can not place your ship outside the battlefield. Try again:";
    private static final String HIT = "You hit a ship!";
    private static final String MISS = "You missed!";
    private static final String SHIP_SANK = "You sank a ship!";
    private static final String GAME_END = "You sank the last ship. You won. Congratulations!";
    private static final String CHANGE_TURN = "Press Enter and pass the move to another player";
    private static Player player = new Player(2);
    private static Player enemy;

    public static void placeShips(GameBoard gameBoard, Ship ship, Scanner scanner) {
        boolean rightFormat;
        boolean inBounds;
        boolean isTooClose;
        boolean location;
        boolean rightLength;

        String shipCoordinates = scanner.nextLine().toUpperCase();

        rightFormat = validCoordinates(shipCoordinates);
        if (rightFormat) {
            int[] coordinates = getCoordinates(shipCoordinates);
            inBounds = coordinatesInBounds(coordinates);
            if (inBounds) {
                location = checkLocation(coordinates);
                if (location) {
                    rightLength = checkLength(coordinates, ship);
                    if (rightLength) {
                        isTooClose = checkSurroundingFields(coordinates, gameBoard);
                        if (!isTooClose) {
                            ship.setShip(coordinates, gameBoard);
                            gameBoard.printGameBoard();
                        } else {
                            System.out.print(TOO_CLOSE);
                            placeShips(gameBoard, ship, scanner);
                        }
                    } else {
                        System.out.printf(WRONG_LENGTH, ship.getNAME());
                        placeShips(gameBoard, ship, scanner);
                    }
                } else {
                    System.out.print(WRONG_LOCATION);
                    placeShips(gameBoard, ship, scanner);
                }
            } else {
                System.out.print(OUT_OF_BOUNDS);
                placeShips(gameBoard, ship, scanner);
            }
        } else {
            System.out.print(WRONG_FORMAT);
            placeShips(gameBoard, ship, scanner);
        }
    }

    public static void startGame(Player player1, Player player2, Scanner scanner) {
        shoot(player1, player2, scanner);
    }

    private static boolean validCoordinates(String shipCoordinates) {
        String[] c = shipCoordinates.trim().split("\\s+");
        if (c.length < 2) {
            return false;
        }
        return c[0].matches("^[A-J][0-9]{1,2}$") && c[1].matches("^[A-J][0-9]{1,2}$");
    }

    private static int[] getCoordinates(String shipCoordinates) {
        int[] coordinates = new int[4];
        coordinates[0] = shipCoordinates.split(" ")[0].charAt(0) - 'A';
        coordinates[1] = Integer.parseInt(shipCoordinates.split(" ")[0].substring(1)) - 1;
        coordinates[2] = shipCoordinates.split(" ")[1].charAt(0) - 'A';
        coordinates[3] = Integer.parseInt(shipCoordinates.split(" ")[1].substring(1)) - 1;
        return coordinates;
    }

    private static boolean coordinatesInBounds(int[] coordinates) {
        int rowMin = Math.min(coordinates[0], coordinates[2]);
        int rowMax = Math.max(coordinates[0], coordinates[2]);
        int colMin = Math.min(coordinates[1], coordinates[3]);
        int colMax = Math.max(coordinates[1], coordinates[3]);
        return rowMin > -1 && rowMin < 10 && colMin > -1 && colMin < 10 && rowMax < 10 && colMax < 10;
    }

    private static boolean checkLocation(int[] coordinates) {
        return (coordinates[0] == coordinates[2]) || (coordinates[1] == coordinates[3]);
    }

    private static boolean checkLength(int[] coordinates, Ship ship) {
        return (Math.abs((coordinates[0] - coordinates[2]) - (coordinates[1] - coordinates[3])) + 1) == ship.getLENGTH();
    }

    private static boolean checkSurroundingFields(int[] coordinates, GameBoard gameBoard) {
        int rowMin = Math.min(coordinates[0], coordinates[2]);
        int rowMax = Math.max(coordinates[0], coordinates[2]);
        int colMin = Math.min(coordinates[1], coordinates[3]);
        int colMax = Math.max(coordinates[1], coordinates[3]);
        for (int r = rowMin; r <= rowMax; r++) {
            for (int c = colMin; c <= colMax; c++) {
                if (gameBoard.getGAMEBOARD()[r][c] == 'O') {
                    return true;
                }
                if (c > 0) {
                    if (gameBoard.getGAMEBOARD()[r][c - 1] == 'O') {
                        return true;
                    }
                    if (r > 0) {
                        if (gameBoard.getGAMEBOARD()[r - 1][c] == 'O' || gameBoard.getGAMEBOARD()[r - 1][c - 1] == 'O') {
                            return true;
                        }
                    }
                    if (r < 9) {
                        if (gameBoard.getGAMEBOARD()[r + 1][c] == 'O' || gameBoard.getGAMEBOARD()[r + 1][c - 1] == 'O') {
                            return true;
                        }
                    }
                }
                if (c < 9) {
                    if (gameBoard.getGAMEBOARD()[r][c + 1] == 'O') {
                        return true;
                    }
                    if (r > 0) {
                        if (gameBoard.getGAMEBOARD()[r - 1][c] == 'O' || gameBoard.getGAMEBOARD()[r - 1][c + 1] == 'O') {
                            return true;
                        }
                    }
                    if (r < 9) {
                        if (gameBoard.getGAMEBOARD()[r + 1][c] == 'O' || gameBoard.getGAMEBOARD()[r + 1][c + 1] == 'O') {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /////////////////// start the Game ////////////////////////

    private static boolean checkHitCoordinate(String coordinate) {
        return coordinate.matches("^[A-J][0-9]{1,2}$");
    }

    private static boolean coordinateInBounds(int row, int col) {
        return row > -1 && row < 10 && col > -1 && col < 10;
    }

    private static char hitOrMiss(int row, int col, GameBoard gameBoard) {
        if (gameBoard.getGAMEBOARD()[row][col] == 'O') {
            return 'X';
        } else if (gameBoard.getGAMEBOARD()[row][col] == 'X') {
            return 'X';
        } else if (gameBoard.getGAMEBOARD()[row][col] == 'M') {
            return 'M';
        }
        return 'M';
    }

    private static Player oneIsPlaying(Player player1, Player player2) {
        if (player.getId() == 1) {
            player = player2;
        } else {
            player = player1;
        }
        return player;
    }

    private static Player routine(Player player1, Player player2) {
        player = oneIsPlaying(player1, player2);
        enemy = enemy(player1, player2);
        enemy.getRawBoard().printGameBoard();
        System.out.println("---------------------");
        player.getGameBoard().printGameBoard();
        System.out.println("Player " + player.getId() + ", it's your turn: ");
        return player;
    }

    private static Player enemy(Player player1, Player player2) {
        if (player == player1) {
            enemy = player2;
        } else {
            enemy = player1;
        }
        return enemy;
    }

    private static void shoot(Player player1, Player player2, Scanner scanner) {
        player = routine(player1, player2);
        while (player.getShips().size() > 0 && enemy.getShips().size() > 0) {
            String coordinate = scanner.nextLine().toUpperCase();
            boolean rightFormat = checkHitCoordinate(coordinate);
            if (rightFormat) {
                int row = coordinate.charAt(0) - 'A';
                int col = Integer.parseInt(coordinate.substring(1)) - 1;
                boolean inBounds = coordinateInBounds(row, col);
                if (inBounds) {
                    char sign = hitOrMiss(row, col, enemy.getGameBoard());
                    enemy.getGameBoard().getGAMEBOARD()[row][col] = sign;
                    if (sign == 'X') {
                        Ship ship = Ship.searchShip(enemy.getShips(), row, col);
                        if (ship == null) { // it was already removed from list
                            System.out.println(HIT);
                            System.out.print(CHANGE_TURN);
                            scanner.nextLine();
                            player = routine(player1, player2);
                        } else {
                            boolean isSunken = Ship.sunkShip(ship, enemy.getGameBoard(), enemy.getShips());
                            if (isSunken && enemy.getShips().size() > 0) {
                                System.out.println(SHIP_SANK);
                                System.out.print(CHANGE_TURN);
                                scanner.nextLine();
                                player = routine(player1, player2);
                            } else if (isSunken && enemy.getShips().size() == 0) {
                                break;
                            } else if (!isSunken) {
                                System.out.println(HIT);
                                System.out.print(CHANGE_TURN);
                                scanner.nextLine();
                                player = routine(player1, player2);
                            }
                        }
                    } else {
                        System.out.println(MISS);
                        System.out.print(CHANGE_TURN);
                        scanner.nextLine();
                        player = routine(player1, player2);
                    }
                } else {
                    System.out.println(OUT_OF_BOUNDS);
                }
            } else {
                System.out.println(WRONG_FORMAT);
            }
        }
        System.out.print(GAME_END);
    }
}