package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.updates.ProjectUpdate;
import projMngmntSaaS.repositories.general.UpdateRepository;

/**
 * {@link ProjectUpdate} repository providing mainly CRUD methods.
 */
public interface ProjectUpdateRepository extends UpdateRepository<ProjectUpdate>
{
    // Registering repository implementation automatically via Spring Data Rest
}
