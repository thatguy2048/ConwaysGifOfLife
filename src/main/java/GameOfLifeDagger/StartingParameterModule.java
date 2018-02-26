package GameOfLifeDagger;

import com.company.StartingParameterValues;
import dagger.Module;
import dagger.Provides;

import java.util.BitSet;

@Module
public class StartingParameterModule {
    @Provides
    StartingParameterValues provideStartingParameters(){
        StartingParameterValues output = new StartingParameterValues();
        output.width = 256;
        output.height = 256;
        output.numberOfRuns = 128;
        output.startingBoardWidth = 1;
        output.startingBoardHeight = 256;
        output.startingBoard = new BitSet(256);
        output.startingBoard.set(0,256,true);
        output.outputFilename = "output.gif";
        return output;
    }
}
