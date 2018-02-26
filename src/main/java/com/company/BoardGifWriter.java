package com.company;

import GameOfLife.Board;
import GameOfLife.BoardUtils;

import javax.inject.Inject;
import java.io.IOException;

//Adapter for GifSequenceWriter to BoardWriter
public class BoardGifWriter implements BoardWriter {
    @Inject
    com.aaronco.GifSequenceWriter gifWriter;

    @Inject
    public BoardGifWriter(com.aaronco.GifSequenceWriter gifWriter){
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
