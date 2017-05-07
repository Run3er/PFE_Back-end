package projMngmntSaaS.domain.entities.projectLevel.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;

import java.util.Collection;
import java.util.Map;

/**
 * Projection of {@link SubProject} dashboard needed data.
 * Optimized for the dashboard as all its needed data is going to be inline.
 */
@Projection(name = "dashboard", types = { SubProject.class })
public interface SubProjectDashboard extends ConstructionSiteDashboard
{
    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.DashboardHelpers).getProjectLevelsAdvancement(target.constructionSites)}")
    Collection<Map<String, String>> getConstructionSitesAdvancement();
}