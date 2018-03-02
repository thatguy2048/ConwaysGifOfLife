package GameOfLife;


/**
 * The game board contains current cell information for Conway's game of life, and is used to get a board representing the next iteration.
 */
public interface Board {

    /** The board width.
     * @return Board width */
    int getWidth();

    /** The board height.
     * @return Board height */
    int getHeight();

    /**
     * Check if the cell is alive.
     * @param w x position on the board.
     * @param h y position on the board.
     * @return true if a cell is alive at a provided position.
     */
    boolean getCell(int w, int h);

    /**
     *  Set if the cell is dead or alive at a given position.
     * @param w x position on the board.
     * @param h y position on the board.
     * @param value if the cell is alive.
     */
    void setCell(int w, int h, boolean value);

    /**
     * Get the alive state of the surrounding cells.
     * @param w x position on the board.
     * @param h y position on the board.
     * @return an array of the cells alive state, clockwise from top left.
     * <P>0,1,2<br>
     * 3,x,4<br>
     * 5,6,7</P>
     */
    boolean[] getNeighbors(int w, int h);

    /**
     * Get the count of the number of neighbors who are alive.
     * @param w x position on the board.
     * @param h y position on the board.
     * @return tne number of alive neighbors
     */
    int countNeighbors(int w, int h);

    /**
     * Check if a cell will be alive the next iteration of the board.
     * @param w x position on the board.
     * @param h y position on the board.
     * @return true if a cell at the position will be alive the next iteration.
     */
    boolean aliveNextRound(int w, int h);

    /**
     * Clear the cells (kill them all).
     */
    void clearCells();

    /**
     * Create a new board which contains update cells from this board.
     * @return a board which is updated by one iteration.
     */
    Board getNext();

    /**
     * Create a copy of this board.
     * @return a clone of this board.
     */
    Board clone();
}
