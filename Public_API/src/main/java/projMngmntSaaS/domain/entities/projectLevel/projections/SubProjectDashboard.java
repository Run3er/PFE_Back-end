package projMngmntSaaS.domain.entities.projectLevel.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.enums.ProjectLevelStatus;
import projMngmntSaaS.domain.entities.enums.RiskStatus;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Milestone;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

/**
 * Projection of {@link SubProject} dashboard needed data.
 * Optimized for the dashboard as all its needed data is going to be inline.
 */
@Projection(name = "dashboard", types = { SubProject.class })
public interface SubProjectDashboard
{
    String getName();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.ProjectionsHelper).getBudgetConsumedPercentage(target)}")
    BigInteger getBudgetConsumed();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.ProjectionsHelper).getBudgetPrevisionGapPercentage(target)}")
    BigInteger getBudgetPrevisionGap();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.ProjectionsHelper).getChargeConsumedPercentage(target)}")
    BigInteger getChargeConsumed();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.ProjectionsHelper).getChargePrevisionGapPercentage(target)}")
    BigInteger getChargePrevisionGap();

    @Value("#{target.advancement}")
    int getAdvancement();

    @Value("#{target.status}")
    ProjectLevelStatus getStatus();

    @Value("#{target.milestones}")
    Collection<Milestone> getMilestones();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.ProjectionsHelper).getActionsOngoingInTimeOrNotCount(target.actions)}")
    Map<Boolean, Long> getActionsOngoingInTimeOrNotCount();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.ProjectionsHelper).getRiskStatusesCount(target.risks)}")
    Map<RiskStatus, Long> getRiskStatusesCount();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.ProjectionsHelper).getProjectLevelsAdvancement(target.constructionSites)}")
    Collection<Map<String, String>> getConstructionSitesAdvancement();
}