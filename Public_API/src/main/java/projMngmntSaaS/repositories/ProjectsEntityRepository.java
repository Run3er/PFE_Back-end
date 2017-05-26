package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.ProjectsEntity;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link ProjectsEntity} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "entities", path = "entities")
public interface ProjectsEntityRepository extends EntitiesRepository<ProjectsEntity>
{
    // Registering repository implementation automatically via Spring Data Rest
}
