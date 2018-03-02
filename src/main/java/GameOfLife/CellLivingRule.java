package GameOfLife;

/**
 * Determines if a cell on a board should be alive the next iteration of the simulation.
 */
public interface CellLivingRule {

    /**
     * Determine the cells next alive state.
     * @param board the game board.
     * @param cellx the x position of the cell on the board.
     * @param celly the y position of the cell on the board.
     * @return true if the cell wil be alive the next iteration.
     */
    boolean aliveNextRound(Board board, int cellx, int celly);
}
