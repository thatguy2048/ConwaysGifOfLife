package com.company;


import GameOfLife.Board;

import java.io.IOException;

/**
 * A BoardWriter which prints directly to the system log.
 */
public class BoardPrintWriter implements BoardWriter {
    @Override
    public void write(Board board) throws IOException {
        System.out.println(board);
    }

    @Override
    public void close() throws IOException {

    }
}
