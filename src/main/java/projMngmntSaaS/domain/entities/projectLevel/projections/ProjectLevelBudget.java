package projMngmntSaaS.domain.entities.projectLevel.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;

import java.math.BigInteger;

/**
 * TODO: description
 */
@Projection(name = "budget", types = { Project.class, SubProject.class})
public interface ProjectLevelBudget
{
    BigInteger getBudgetInitial();

    @Value("#{target.budgetToConsume}")
    BigInteger getBudgetToConsume();

    @Value("#{target.budgetConsumed}")
    BigInteger getBudgetConsumed();
}