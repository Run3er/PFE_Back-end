package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.PendingIssue;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link PendingIssue} repository providing mainly CRUD methods.
 */
public interface PendingIssueRepository extends EntitiesRepository<PendingIssue>
{
    // Registering repository implementation automatically via Spring Data Rest
}
