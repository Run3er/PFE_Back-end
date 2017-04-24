package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Resource;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Resource} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "resources", path = "resources")
public interface ResourceRepository extends EntitiesRepository<Resource>
{
    // Registering repository implementation automatically via Spring Data Rest
}
