package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link SubProject} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "sub-projects", path = "sub-projects")
public interface SubProjectRepository extends EntitiesRepository<SubProject>
{
    // Registering repository implementation automatically via Spring Data Rest
}
