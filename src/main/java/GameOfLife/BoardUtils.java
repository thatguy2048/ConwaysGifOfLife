package GameOfLife;


import com.company.BoardGifWriter;
import com.company.BoardWriter;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.BitSet;

/**
 * Class to contain utility methods for the game board.
 */
public class BoardUtils {

    /**
     * Populate the cells of a board with bit values.
     * @param board the board to populate.
     * @param bitset the cell's living state in a set.
     * @param bitsetWidth the width of the living cells.
     * @param bitsetHeight the height of the living cells.
     * @return the board which was populated.
     */
    public static Board populateBoardWithBitset(Board board, BitSet bitset, int bitsetWidth, int bitsetHeight){
        Board output = null;
        if(board.getWidth() < bitsetWidth || board.getHeight() < bitsetHeight){

        }else{
            int i_start = board.getWidth()/2 - bitsetWidth/2;
            int j_start = board.getHeight()/2 - bitsetHeight/2;
            for (int i = 0; i < bitsetWidth; i++) {
                for (int j = 0; j < bitsetHeight; j++) {
                    board.setCell(i+i_start, j+j_start, bitset.get(i*bitsetWidth+j));
                }
            }
            output = board;
        }
        return output;
    }

    /**
     * Draw a board onto an image.
     * @param board the board to draw.
     * @param image the image to draw on.
     * @return the image with the board drawn on it.
     */
    public static BufferedImage drawBoard(final Board board, BufferedImage image){
        for(int w = 0; w < board.getWidth(); ++w){
            for(int h = 0; h < board.getHeight(); ++h){
                if(board.getCell(w,h)) {
                    image.setRGB(w, h, 255);
                }
            }
        }
        return image;
    }

    /**
     * Create a new image from a board.
     * @param board the board to draw.
     * @return a new image with board drawn on it.
     */
    public static BufferedImage boardToImage(final Board board){
        return drawBoard(
                board,
                new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_USHORT_555_RGB)
        );
    }

    /**
     * Iterate a game board for a number of runs, and write the result each iteration.
     * @param outWriter the board writer to output to.
     * @param initialBoard the board to run.
     * @param numberOfRuns the number of iterations to run.
     * @return the last instance of the game board.
     * @throws IOException
     */
    public static Board outputBoardRun(BoardWriter outWriter, Board initialBoard, int numberOfRuns) throws IOException{
        outWriter.write(initialBoard);
        for (int i = 0; i < numberOfRuns; i++) {
            initialBoard = initialBoard.getNext();
            outWriter.write(initialBoard);
        }
        return initialBoard;
    }

    /**
     * Run a game board a number of iterations and write the output to a GIF file.
     * @param filename the output file to write to.
     * @param board the board to run.
     * @param runs the number of iterations to run.
     * @param initial the cell's living state in a set.
     * @param bitsetWidth the width of the living cells.
     * @param bitsetHeight the height of the living cells.
     * @return the last instance of the game board.
     * @throws IOException
     */
    public static Board makeGifFromRun(String filename, Board board, int runs, BitSet initial, int bitsetWidth, int bitsetHeight) throws IOException{
        //initialize board
        populateBoardWithBitset(board, initial, bitsetWidth, bitsetHeight);

        ImageOutputStream ios = new FileImageOutputStream(new File(filename));

        BoardGifWriter bgw = new BoardGifWriter(new com.company.GifSequenceWriter(ios, BufferedImage.TYPE_USHORT_555_RGB, 100, false));

        board = outputBoardRun(bgw, board, runs);

        bgw.close();
        ios.close();

        return board;
    }
}
