import java.util.ArrayList;

public class Player {
    private ArrayList<Ship> ships;
    private GameBoard gameBoard;
    private GameBoard rawBoard;
    private final int ID;

    public Player(int id, ArrayList<Ship> ships, GameBoard gameBoard, GameBoard rawBoard) {
        this.ID = id;
        this.ships = ships;
        this.gameBoard = gameBoard;
        this.rawBoard = rawBoard;
    }

    public Player(int ID) {
        this.ID = ID;

    }

    public int getId() {
        return ID;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public GameBoard getRawBoard() {
        return rawBoard;
    }
}
