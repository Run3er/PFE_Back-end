package projMngmntSaaS.domain.entities.projectLevel.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.enums.ProjectLevelStatus;
import projMngmntSaaS.domain.entities.enums.RiskStatus;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Milestone;

import java.math.BigDecimal;
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

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.DashboardHelpers).getBudgetConsumedPercentage(target.archivableContent.budgetConsumed, target.archivableContent.budgetToConsume)}")
    BigDecimal getBudgetConsumed();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.DashboardHelpers).getBudgetPrevisionGapPercentage(target.budgetInitial, target.archivableContent.budgetConsumed, target.archivableContent.budgetToConsume)}")
    BigDecimal getBudgetPrevisionGap();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.DashboardHelpers).getChargeConsumedPercentage(target.archivableContent.chargeConsumed, target.archivableContent.todos)}")
    BigDecimal getChargeConsumed();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.DashboardHelpers).getChargePrevisionGapPercentage(target.chargePrevision, target.archivableContent.chargeConsumed, target.archivableContent.todos)}")
    BigDecimal getChargePrevisionGap();

    @Value("#{target.archivableContent.advancement}")
    int getAdvancement();

    @Value("#{target.archivableContent.status}")
    ProjectLevelStatus getStatus();

    @Value("#{target.archivableContent.milestones}")
    Collection<Milestone> getMilestones();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.DashboardHelpers).getActionsOngoingInTimeOrNotCount(target.archivableContent.actions)}")
    Map<Boolean, Long> getActionsOngoingInTimeOrNotCount();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.DashboardHelpers).getRiskStatusesCount(target.archivableContent.risks)}")
    Map<RiskStatus, Long> getRiskStatusesCount();

    @Value("#{T(projMngmntSaaS.domain.entities.projectLevel.projections.utils.DashboardHelpers).getProjectLevelsAdvancement(target.constructionSites)}")
    Collection<Map<String, String>> getConstructionSitesAdvancement();
}