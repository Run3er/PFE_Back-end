package projMngmntSaaS.domain.entities.projectLevel.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.enums.ProjectLevelStatus;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;
import projMngmntSaaS.domain.entities.projectLevel.projections.utils.ProjectLevel;

import java.math.BigInteger;

/**
 * TODO: description
 */
@Projection(name = "advancementState", types = { Project.class, SubProject.class })
public interface ProjectLevelAdvancementState
{
    int getAdvancement();

    ProjectLevelStatus getStatus();

    BigInteger getChargeConsumed();
}