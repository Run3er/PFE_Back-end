package projMngmntSaaS.domain.entities.projectLevel.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.enums.ProjectLevelStatus;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;

import java.math.BigInteger;
import java.util.Date;

/**
 * Projection of {@link SubProject} charter details data.
 */
@Projection(name = "charter", types = { SubProject.class })
public interface SubProjectCharter
{
    String getName();

    String getGoal();

    Date getStartDate();

    Date getEndDate();

    String getComment();

    BigInteger getBudgetInitial();

    BigInteger getChargePrevision();

    BigInteger getAdvancement();

    ProjectLevelStatus getStatus();
}
