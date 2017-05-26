package projMngmntSaaS.domain.entities.projectLevel.projections.utils;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.Todo;

import java.math.BigInteger;
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

    BigInteger getBudgetInitial();

    BigInteger getBudgetConsumed();

    BigInteger getBudgetToConsume();

    BigInteger getChargePrevision();

    BigInteger getChargeConsumed();

    Set<Todo> getTodos();
}
