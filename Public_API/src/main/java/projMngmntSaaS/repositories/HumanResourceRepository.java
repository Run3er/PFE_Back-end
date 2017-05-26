package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.HumanResource;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link HumanResource} repository providing mainly CRUD methods.
 */
public interface HumanResourceRepository extends EntitiesRepository<HumanResource>
{
    // Registering repository implementation automatically via Spring Data Rest
}
