package GameOfLifeDagger;

import GameOfLife.Board;
import GameOfLife.BoardUtils;
import com.company.BoardWriter;
import com.company.StartingParameterValues;
import dagger.Component;

import javax.inject.Singleton;
import java.io.IOException;

public class GOLBoardRun {
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
