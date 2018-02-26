package GameOfLife;

import java.util.BitSet;

public interface Board {
    int getWidth();
    int getHeight();

    boolean getCell(int w, int h);
    void setCell(int w, int h, boolean value);
    boolean[] getNeighbors(int w, int h);
    int countNeighbors(int w, int h);
    boolean aliveNextRound(int w, int h);

    void clearCells();

    Board getNext();

    Board clone();
}
