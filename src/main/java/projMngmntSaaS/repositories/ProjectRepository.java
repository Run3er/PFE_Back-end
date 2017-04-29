package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Project} repository providing mainly CRUD methods.
 */
public interface ProjectRepository extends EntitiesRepository<Project>
{
    // Registering repository implementation automatically via Spring Data Rest
}
