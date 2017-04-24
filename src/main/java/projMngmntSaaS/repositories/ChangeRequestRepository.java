package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.ChangeRequest;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link ChangeRequest} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "change-requests", path = "change-requests")
public interface ChangeRequestRepository extends EntitiesRepository<ChangeRequest>
{
    // Registering repository implementation automatically via Spring Data Rest
}
