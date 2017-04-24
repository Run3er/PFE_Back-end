package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Action;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Action} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "actions", path = "actions")
public interface ActionRepository extends EntitiesRepository<Action>
{
    // Registering repository implementation automatically via Spring Data Rest
}
