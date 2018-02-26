package GameOfLifeDagger;

import GameOfLife.CellLivingRule;
import GameOfLife.ConwaysCellRules;
import dagger.Module;
import dagger.Provides;

@Module
public class CellRuleModule {
    @Provides
    CellLivingRule provideCellLivingRule(){
        return new ConwaysCellRules();
    }
}
