package projMngmntSaaS.domain.entities.projectLevel.projections;

import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;

import java.math.BigDecimal;

/**
 * TODO: description
 */
@Projection(name = "budget", types = { Project.class, SubProject.class})
public interface ProjectLevelBudget
{
    BigDecimal getBudgetInitial();

    BigDecimal getBudgetToConsume();

    BigDecimal getBudgetConsumed();
}