public class GameBoard {
    private final char[][] GAMEBOARD;

    public GameBoard(char[][] gameBoard) {
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[0].length; c++) {
                gameBoard[r][c] = '~';
            }
        }
        this.GAMEBOARD = gameBoard;
    }

    public char[][] getGAMEBOARD() {
        return GAMEBOARD;
    }

    protected void printGameBoard() {
        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        for (int r = 0; r < GAMEBOARD.length; r++) {
            System.out.print((char) ('@' + r + 1) + " ");
            for (int c = 0; c < GAMEBOARD[0].length; c++) {
                System.out.print(GAMEBOARD[r][c] + " ");
            }
            System.out.println();
        }
    }
}
