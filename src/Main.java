import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static final Ship AIRCRAFT_CARRIER1 = new Ship("Aircraft Carrier", 5);
    private static final Ship BATTLESHIP1 = new Ship("Battleship", 4);
    private static final Ship SUBMARINE1 = new Ship("Submarine", 3);
    private static final Ship CRUISER1 = new Ship("Cruiser", 3);
    private static final Ship DESTROYER1 = new Ship("Destroyer", 2);
    private static final Player PLAYER1 = new Player(1, new ArrayList<>(Arrays.asList(AIRCRAFT_CARRIER1, BATTLESHIP1, SUBMARINE1, CRUISER1, DESTROYER1)),
            new GameBoard(new char[10][10]), new GameBoard(new char[10][10]));

    private static final Ship AIRCRAFT_CARRIER2 = new Ship("Aircraft Carrier", 5);
    private static final Ship BATTLESHIP2 = new Ship("Battleship", 4);
    private static final Ship SUBMARINE2 = new Ship("Submarine", 3);
    private static final Ship CRUISER2 = new Ship("Cruiser", 3);
    private static final Ship DESTROYER2 = new Ship("Destroyer", 2);
    private static final Player PLAYER2 = new Player(2, new ArrayList<>(Arrays.asList(AIRCRAFT_CARRIER2, BATTLESHIP2, SUBMARINE2, CRUISER2, DESTROYER2)),
            new GameBoard(new char[10][10]), new GameBoard(new char[10][10]));

    private static final Player[] PLAYER = {PLAYER1, PLAYER2};

    public static void main(String[] args) {
        BattleShip battleShip = new BattleShip();
        battleShip.start(PLAYER);
    }
}
