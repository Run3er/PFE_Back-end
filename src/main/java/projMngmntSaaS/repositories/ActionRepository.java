package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.Action;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Action} repository providing mainly CRUD methods.
 */
public interface ActionRepository extends EntitiesRepository<Action>
{
    // Registering repository implementation automatically via Spring Data Rest
}
