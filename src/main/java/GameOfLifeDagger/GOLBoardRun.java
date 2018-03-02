package GameOfLifeDagger;

import GameOfLife.Board;
import GameOfLife.BoardUtils;
import com.company.BoardWriter;
import com.company.StartingParameterValues;
import dagger.Component;

import javax.inject.Singleton;
import java.io.IOException;

/**
 * GOLBoardRun uses the DAGGER II dependency injection framework to run an instance of Conway's game of life, and create a GIF of the result.
 */
public class GOLBoardRun {
    /**
     * This interface is used to create starting parameters, a game board, and board writer.
     */
    @Singleton @Component(modules = {CellRuleModule.class, BoardModule.class, BoardWriterModule.class, StartingParameterModule.class})
    public interface BoardRun{
        StartingParameterValues startingParameters();
        Board board();
        BoardWriter writer();
    }

    public static void main(String[] args) throws IOException{
        BoardRun currentRun = GameOfLifeDagger.DaggerGOLBoardRun_BoardRun.create();
        BoardUtils.outputBoardRun(
            currentRun.writer(),
                currentRun.board(),
                currentRun.startingParameters().numberOfRuns
        );
    }
}
