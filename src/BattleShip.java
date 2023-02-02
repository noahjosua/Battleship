import java.util.Scanner;

public class BattleShip {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MSG = "Enter the coordinates of the %s (%d cells):\n";

    public void start(Player[] player) {
        for (Player value : player) {
            System.out.println("\nPlayer " + value.getId() + ", place your ships on the game field");
            value.getGameBoard().printGameBoard();
            for (Ship ship : value.getShips()) {
                System.out.printf(MSG, ship.getNAME(), ship.getLENGTH());
                InputController.placeShips(value.getGameBoard(), ship, SCANNER);
            }
            System.out.print("\nPress Enter and pass the move to another player");
            SCANNER.nextLine();
        }
        InputController.startGame(player[0], player[1], SCANNER);
    }
}