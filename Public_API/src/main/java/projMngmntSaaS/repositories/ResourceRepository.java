package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.Resource;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Resource} repository providing mainly CRUD methods.
 */
public interface ResourceRepository extends EntitiesRepository<Resource>
{
    // Registering repository implementation automatically via Spring Data Rest
}
