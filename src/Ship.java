import java.util.ArrayList;

public class Ship {
    private final String NAME;
    private final int LENGTH;
    private final ArrayList<Integer> COORDINATES_ROW = new ArrayList<>();
    private final ArrayList<Integer> COORDINATES_COL = new ArrayList<>();
    private int[] startEnd;

    public Ship(String name, int length) {
        this.NAME = name;
        this.LENGTH = length;
    }

    public String getNAME() {
        return NAME;
    }

    public int getLENGTH() {
        return LENGTH;
    }

    private int[] getStartEnd() {
        return startEnd;
    }

    private ArrayList<Integer> getCOORDINATES_ROW() {
        return COORDINATES_ROW;
    }

    private ArrayList<Integer> getCOORDINATES_COL() {
        return COORDINATES_COL;
    }

    protected void setShip(int[] coordinates, GameBoard gameBoard) {
        int rowMin = Math.min(coordinates[0], coordinates[2]);
        int rowMax = Math.max(coordinates[0], coordinates[2]);
        int colMin = Math.min(coordinates[1], coordinates[3]);
        int colMax = Math.max(coordinates[1], coordinates[3]);
        for (int r = rowMin; r <= rowMax; r++) {
            this.COORDINATES_ROW.add(r);
            for (int c = colMin; c <= colMax; c++) {
                this.COORDINATES_COL.add(c);
                gameBoard.getGAMEBOARD()[r][c] = 'O';
            }
        }
        startEnd = new int[]{rowMin, colMin, rowMax, colMax};
    }

    protected static Ship searchShip(ArrayList<Ship> ships, int row, int col) {
        for (Ship s : ships) {
            for (int r = 0; r < s.getCOORDINATES_ROW().size(); r++) {
                for (int c = 0; c < s.getCOORDINATES_COL().size(); c++) {
                    if (s.getCOORDINATES_ROW().get(r) == row && s.getCOORDINATES_COL().get(c) == col) {
                        return s;
                    }
                }
            }
        }
        return null;
    }

    protected static boolean sunkShip(Ship ship, GameBoard gameBoard, ArrayList<Ship> ships) {
        int counter = 0;
        for (int r = ship.getStartEnd()[0]; r <= ship.getStartEnd()[2]; r++) {
            for (int c = ship.getStartEnd()[1]; c <= ship.getStartEnd()[3]; c++) {
                if (gameBoard.getGAMEBOARD()[r][c] == 'X') {
                    counter++;
                    if (counter == ship.getLENGTH()) {
                        ships.remove(ship);
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }
}
