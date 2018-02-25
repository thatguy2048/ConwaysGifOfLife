package GameOfLife;

import java.util.BitSet;

public class Board {
    protected BitSet cells;
    protected int width;
    protected int height;
    protected int widthm1;
    protected int heightm1;
    protected CellLivingRule livingCheck;

    public Board(int width, int height, CellLivingRule livingRule, BitSet cells) {
        this.width = width;
        this.height = height;
        this.livingCheck = livingRule;

        widthm1 = width-1;
        heightm1 = height-1;

        this.cells = cells;
    }

    public Board(int width, int height, CellLivingRule livingRule){
        this(width, height, livingRule, new BitSet(width*height));
    }

    public Board(final Board other) {
        this.width = other.getWidth();
        this.height = other.getHeight();
        this.livingCheck = other.livingCheck;

        widthm1 = width-1;
        heightm1 = height-1;

        cells = (BitSet)(other.cells.clone());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getCell(int w, int h){
        return cells.get(h*width + w);
    }

    public void setCell(int w, int h, boolean value){
        cells.set(h * width + w, value);
    }

    public void clearCells(){
        cells.clear();
    }

    /*
        Returns an array of 8 boolean values representing the cells around the position provided, with the positions in the array corresponding to the following, where x is the position provided.
        0,1,2
        3,x,4
        5,6,7
     */
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

    public int countNeighbors(int w, int h){
        int output = 0;
        for(boolean b : getNeighbors(w,h)){
            if(b) ++output;
        }
        return output;
    }

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
    public Board clone() {
        return new Board(this);
    }

    @Override
    public String toString() {
        String output = "Board "+this.width+","+this.height+" alive: "+aliveCellCount();
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
