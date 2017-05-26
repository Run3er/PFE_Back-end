package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.ChangeRequest;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link ChangeRequest} repository providing mainly CRUD methods.
 */
public interface ChangeRequestRepository extends EntitiesRepository<ChangeRequest>
{
    // Registering repository implementation automatically via Spring Data Rest
}
