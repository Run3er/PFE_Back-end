package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.CommunicationPlan;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link CommunicationPlan} repository providing mainly CRUD methods.
 */
public interface CommunicationPlanRepository extends EntitiesRepository<CommunicationPlan>
{
    // Registering repository implementation automatically via Spring Data Rest
}
