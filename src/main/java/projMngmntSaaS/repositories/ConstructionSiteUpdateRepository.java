package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.updates.ConstructionSiteUpdate;
import projMngmntSaaS.repositories.general.UpdateRepository;

/**
 * {@link ConstructionSiteUpdate} repository providing mainly CRUD methods.
 */
public interface ConstructionSiteUpdateRepository extends UpdateRepository<ConstructionSiteUpdate>
{
    // Registering repository implementation automatically via Spring Data Rest
}
