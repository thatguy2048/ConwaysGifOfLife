package com.company;

import GameOfLife.Board;
import GameOfLife.BoardUtils;

import java.io.IOException;

//Adapter for GifSequenceWriter to BoardWriter
public class BoardGifWriter implements BoardWriter {
    com.aaronco.GifSequenceWriter gifWriter;

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
