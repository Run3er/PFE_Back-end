package projMngmntSaaS.domain.entities.projectLevel.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.enums.ActionStatus;
import projMngmntSaaS.domain.entities.enums.RiskStatus;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.domain.entities.projectLevel.ProjectLevelUpdate;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Milestone;

import java.util.Collection;
import java.util.Map;

/**
 * Projection of {@link Project} dashboard needed data.
 * Optimized for the dashboard as all its needed data is going to be inline.
 */
@Projection(name = "dashboard", types = { Project.class })
interface ProjectProjectionDashboard
{
    String getName();
    ProjectLevelUpdate getPendingUpdate();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.DashboardHelpers).getSubProjectsAdvancement(target.subProjects)}")
    Collection<Map<String, String>> getSubProjectsAdvancement();

    @Value("#{target.pendingUpdate.milestones}")
    Collection<Milestone> getMilestones();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.DashboardHelpers).getActionStatusesCount(target.pendingUpdate.actions)}")
    Map<ActionStatus, Long> getActionStatusesCount();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.DashboardHelpers).getRiskStatusesCount(target.pendingUpdate.risks)}")
    Map<RiskStatus, Long> getRiskStatusesCount();
}