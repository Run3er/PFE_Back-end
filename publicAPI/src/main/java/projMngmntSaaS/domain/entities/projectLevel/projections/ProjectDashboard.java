package projMngmntSaaS.domain.entities.projectLevel.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.projectLevel.Project;

import java.util.Collection;
import java.util.Map;

/**
 * Projection of {@link Project} charter details data.
 */
@Projection(name = "dashboard", types = { Project.class })
public interface ProjectDashboard extends SubProjectDashboard
{
    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.ProjectionsHelper).getProjectLevelsAdvancement(target.subProjects)}")
    Collection<Map<String, String>> getSubProjectsAdvancement();
}