package projMngmntSaaS.domain.entities.projectLevel.projections.utils;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.Todo;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

/**
 * Regroups some project level shared traits, for use in {@link ProjectionsHelper}.
 */
public interface ProjectLevel
{
    UUID getId();

    String getName();

    int getAdvancement();

    BigDecimal getBudgetInitial();

    BigDecimal getBudgetConsumed();

    BigDecimal getBudgetToConsume();

    BigDecimal getChargePrevision();

    BigDecimal getChargeConsumed();

    Set<Todo> getTodos();
}
