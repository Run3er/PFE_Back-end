package projMngmntSaaS.domain.entities.projectLevel.projections;

import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.enums.ProjectLevelStatus;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;

import java.util.Date;

/**
 * TODO: description
 */
@Projection(name = "charter", types = { Project.class, SubProject.class})
public interface ProjectLevelCharter
{
    String getName();

    String getMainContact();

    String getSponsors();

    String getFinalClient();

    String getGoal();

    Date getStartDate();

    Date getEndDate();

    String getHypotheses_constraints();

    String getHistory_decisions();

    String getComment();

    float getAdvancement();

    ProjectLevelStatus getStatus();
}