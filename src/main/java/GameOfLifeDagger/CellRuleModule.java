package GameOfLifeDagger;

import GameOfLife.CellLivingRule;
import GameOfLife.ConwaysCellRules;
import dagger.Module;
import dagger.Provides;

/**
 * Generates a CellLivingRule.
 */
@Module
public class CellRuleModule {
    @Provides
    CellLivingRule provideCellLivingRule(){
        return new ConwaysCellRules();
    }
}
