package GameOfLife;

import javax.inject.Inject;
import java.util.BitSet;

/**
 * A game board whose edges wrap around so that the far sides are next to one another.
 */
public class WrappedBoard extends StandardBoard {

    public WrappedBoard(int width, int height, CellLivingRule livingRule) {
        super(width, height, livingRule);
    }

    @Override
    public boolean[] getNeighbors(int w, int h) {
        boolean[] output = new boolean[8];
        int _left = (w > 0)?w-1:widthm1;
        int _right = (w < widthm1)?w+1:0;
        int _top = (h > 0)?h-1:heightm1;
        int _bottom = (h < heightm1)?h+1:0;

        output[0] = getCell(_left, _top);
        output[1] = getCell(w,_top);
        output[2] = getCell(_right,_top);
        output[3] = getCell(_left, h);
        output[4] = getCell(_right, h);
        output[5] = getCell(_left, _bottom);
        output[6] = getCell(w, _bottom);
        output[7] = getCell(_right, _bottom);

        return output;
    }

    @Override
    public WrappedBoard clone() {
        return new WrappedBoard(width, height, livingCheck);
    }

    @Override
    public String toString() {
        return "Wrapped"+super.toString();
    }
}
