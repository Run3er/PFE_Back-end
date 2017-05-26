package projMngmntSaaS.domain.entities.projectLevel.artifacts.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.enums.PendingIssueStatus;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.PendingIssue;

import java.util.Date;

/**
 * Excerpt inlining directly useful nested property (supervisor) values for {@link PendingIssue}.
 */
@Projection(name = "inline", types = { PendingIssue.class })
public interface InlinePendingIssue
{
    String getDescription();

    PendingIssueStatus getStatus();

    @Value("#{'resources/' + target.supervisor.id}")
    String getSupervisor();

    @Value("#{target.supervisor.name}")
    String getSupervisorName();

    int getPriority();

    Date getCreationDate();

    Date getResolutionPlannedDate();

    Date getResolutionDate();

    String getImpacts();

    String getDecisions();
}