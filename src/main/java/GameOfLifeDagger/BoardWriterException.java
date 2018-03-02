package GameOfLifeDagger;

import GameOfLife.Board;
import com.company.BoardWriter;

import java.io.IOException;

/**
 * A special board writer which is returned in place of another BoardWriter in the event an IOException is thrown by that BoardWriter.
 */
public class BoardWriterException implements BoardWriter {
    IOException exception;

    public BoardWriterException(IOException exception) {
        this.exception = exception;
    }

    @Override
    public void write(Board board) throws IOException {
        throw exception;
    }

    @Override
    public void close() throws IOException {
        throw exception;
    }
}
