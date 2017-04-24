package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Project} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "projects", path = "projects")
public interface ProjectRepository extends EntitiesRepository<Project>
{
    // Registering repository implementation automatically via Spring Data Rest
}
