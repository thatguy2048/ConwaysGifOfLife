package GameOfLife;

import javax.inject.Inject;
import java.util.BitSet;

/**
 * The standard implementation of the Conway's game of life board.
 */
public class StandardBoard implements Board{
    protected BitSet cells;
    protected int width;
    protected int height;
    protected int widthm1;
    protected int heightm1;

    @Inject
    protected CellLivingRule livingCheck;


    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
        widthm1 = width-1;
        heightm1 = height-1;

        cells = new BitSet(width * height);
    }

    @Inject
    public StandardBoard(int width, int height, CellLivingRule livingRule) {
        setSize(width,height);
        this.livingCheck = livingRule;
    }


    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean getCell(int w, int h){
        return cells.get(h*width + w);
    }

    @Override
    public void setCell(int w, int h, boolean value){
        cells.set(h * width + w, value);
    }

    @Override
    public void clearCells(){
        cells.clear();
    }

    /*
        Returns an array of 8 boolean values representing the cells around the position provided, with the positions in the array corresponding to the following, where x is the position provided.
        0,1,2
        3,x,4
        5,6,7
     */
    @Override
    public boolean[] getNeighbors(int w, int h){
        boolean[] output = new boolean[8];
        if(h > 0){ //the top row
            if(w > 0)  output[0] = getCell(w-1,h-1);
            output[1] = getCell(w,h-1);
            if(w < widthm1)  output[2] = getCell(w+1,h-1);
        }
        //the middle row
        if(w > 0) output[3] = getCell(w-1,h);
        if(w < widthm1) output[4] = getCell(w+1,h);
        if(h < heightm1){ //the bottom row
            if(w > 0)  output[0] = getCell(w-1,h+1);
            output[1] = getCell(w,h+1);
            if(w < widthm1)  output[2] = getCell(w+1,h+1);
        }
        return output;
    }

    @Override
    public int countNeighbors(int w, int h){
        int output = 0;
        for(boolean b : getNeighbors(w,h)){
            if(b) ++output;
        }
        return output;
    }

    @Override
    public boolean aliveNextRound(int w, int h){
        return livingCheck.aliveNextRound(this, w, h);
    }

    public Board populateNextBoard(Board board){
        for(int h = 0; h < height; ++h){
            for(int w = 0; w < height; ++w){
                board.setCell(w, h, aliveNextRound(w, h));
            }
        }
        return board;
    }

    public Board getNext(){
        return populateNextBoard(this.clone());
    }

    public int aliveCellCount(){
        return cells.cardinality();
    }

    @Override
    public StandardBoard clone() {
        return new StandardBoard(width, height, livingCheck);
    }

    @Override
    public String toString() {
        String output = this.getClass().getSimpleName()+" LivingRule:"+livingCheck.getClass().getSimpleName()+" size:"+this.width+","+this.height+" alive: "+aliveCellCount();
        for(int h = 0; h < this.height; ++h){
            String line = "";
            for(int w = 0; w < this.width; ++w){
                line += getCell(w,h)?'1':'0';
            }
            output += '\n' + line;
        }
        return output;
    }
}
