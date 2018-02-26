package GameOfLifeDagger;

import GameOfLife.*;
import com.company.StartingParameterValues;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class BoardModule {
    @Provides
    Board provideBoard(CellLivingRule livingRule, StartingParameterValues params){
        /*
        int width = 256;
        int height = 256;
        Board output = new WrappedBoard(width, height, livingRule);
        int w2 = width/2;
        for (int i = 0; i < height; i++) {
            output.setCell(w2,i,true);
        }
        return output;
        */

        return BoardUtils.populateBoardWithBitset(
                new WrappedBoard(params.width, params.height, livingRule),
                params.startingBoard, params.startingBoardWidth, params.startingBoardHeight);
    }
}
