package com.company;

import GameOfLife.Board;

import java.io.IOException;

//Output for a game of life board
public interface BoardWriter {
    void write(Board board) throws IOException;
    void close() throws IOException;
}
