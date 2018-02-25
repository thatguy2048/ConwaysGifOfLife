package GameOfLife;

import com.aaronco.GifSequenceWriter;
import com.company.BoardGifWriter;
import com.company.BoardWriter;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.BitSet;

public class BoardUtils {
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

    public static BufferedImage boardToImage(final Board board){
        BufferedImage output = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_USHORT_555_RGB);
        return drawBoard(board, output);
    }

    public static Board outputBoardRun(BoardWriter outWriter, Board initialBoard, int numberOfRuns) throws IOException{
        outWriter.write(initialBoard);
        for (int i = 0; i < numberOfRuns; i++) {
            initialBoard = initialBoard.getNext();
            outWriter.write(initialBoard);
        }
        return initialBoard;
    }

    public static Board makeGifFromRun(String filename, Board board, int runs, BitSet initial, int bitsetWidth, int bitsetHeight) throws IOException{
        //initialize board
        populateBoardWithBitset(board, initial, bitsetWidth, bitsetHeight);

        ImageOutputStream ios = new FileImageOutputStream(new File(filename));

        BoardGifWriter bgw = new BoardGifWriter(new GifSequenceWriter(ios, BufferedImage.TYPE_USHORT_555_RGB, 100, false));

        board = outputBoardRun(bgw, board, runs);

        bgw.close();
        ios.close();

        return board;
    }
}
