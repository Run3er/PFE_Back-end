package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.PendingIssue;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.projections.InlinePendingIssue;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link PendingIssue} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(excerptProjection = InlinePendingIssue.class)
public interface PendingIssueRepository extends EntitiesRepository<PendingIssue>
{
    // Registering repository implementation automatically via Spring Data Rest
}
