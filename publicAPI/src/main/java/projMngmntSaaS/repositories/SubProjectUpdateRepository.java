package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.updates.SubProjectUpdate;
import projMngmntSaaS.repositories.general.UpdateRepository;

/**
 * {@link SubProjectUpdate} repository providing mainly CRUD methods.
 */
public interface SubProjectUpdateRepository extends UpdateRepository<SubProjectUpdate>
{
    // Registering repository implementation automatically via Spring Data Rest
}
