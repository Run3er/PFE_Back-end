package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.Risk;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Risk} repository providing mainly CRUD methods.
 */
public interface RiskRepository extends EntitiesRepository<Risk>
{
    // Registering repository implementation automatically via Spring Data Rest
}
