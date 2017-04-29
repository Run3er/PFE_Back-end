package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.SubProject;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link SubProject} repository providing mainly CRUD methods.
 */
public interface SubProjectRepository extends EntitiesRepository<SubProject>
{
    // Registering repository implementation automatically via Spring Data Rest
}
