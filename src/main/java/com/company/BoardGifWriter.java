package com.company;

import GameOfLife.Board;
import GameOfLife.BoardUtils;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Utilizes a GifSequenceWriter to output a game board to a GIF
 */
public class BoardGifWriter implements BoardWriter {
    @Inject
    com.company.GifSequenceWriter gifWriter;

    @Inject
    public BoardGifWriter(com.company.GifSequenceWriter gifWriter){
        this.gifWriter = gifWriter;
    }

    @Override
    public void write(Board board) throws IOException{
        gifWriter.writeToSequence(BoardUtils.boardToImage(board));
    }

    @Override
    public void close() throws IOException{
        gifWriter.close();
    }
}
