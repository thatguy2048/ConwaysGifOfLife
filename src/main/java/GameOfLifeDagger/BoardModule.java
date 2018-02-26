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
        return BoardUtils.populateBoardWithBitset(
                new WrappedBoard(params.width, params.height, livingRule),
                params.startingBoard, params.startingBoardWidth, params.startingBoardHeight);
    }
}
