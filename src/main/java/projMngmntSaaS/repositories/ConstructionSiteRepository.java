package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.ConstructionSite;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link ConstructionSite} repository providing mainly CRUD methods.
 */
public interface ConstructionSiteRepository extends EntitiesRepository<ConstructionSite>
{
    // Registering repository implementation automatically via Spring Data Rest
}
