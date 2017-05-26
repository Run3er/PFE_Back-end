package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.ReunionPlanning;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link ReunionPlanning} repository providing mainly CRUD methods.
 */
public interface ReunionPlanningRepository extends EntitiesRepository<ReunionPlanning>
{
    // Registering repository implementation automatically via Spring Data Rest
}
