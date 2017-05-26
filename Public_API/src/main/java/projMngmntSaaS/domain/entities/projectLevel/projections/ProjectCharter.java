package projMngmntSaaS.domain.entities.projectLevel.projections;

import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.projectLevel.Project;

/**
 * TODO: description
 */
@Projection(name = "charter", types = { Project.class })
public interface ProjectCharter extends SubProjectCharter
{
    String getMainContact();

    String getSponsors();

    String getFinalClient();

    String getHypotheses_constraints();

    String getHistory_decisions();
}