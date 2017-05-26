package projMngmntSaaS.domain.entities.projectLevel.artifacts.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import projMngmntSaaS.domain.entities.enums.ActionStatus;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Action;

import java.util.Date;

/**
 * Excerpt inlining directly useful nested property (supervisor) values for {@link Action}.
 */
@Projection(name = "inline", types = { Action.class })
public interface InlineAction
{
    String getDescription();

    ActionStatus getStatus();

    @Value("#{'resources/' + target.supervisor.id}")
    String getSupervisor();

    @Value("#{target.supervisor.name}")
    String getSupervisorName();

    int getPriority();

    int getAdvancement();

    Date getCreationDate();

    Date getClosurePlannedDate();

    Date getClosureDate();

    String getComment();
}