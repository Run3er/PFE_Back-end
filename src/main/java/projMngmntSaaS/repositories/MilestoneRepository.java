package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.Milestone;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Milestone} repository providing mainly CRUD methods.
 */
public interface MilestoneRepository extends EntitiesRepository<Milestone>
{
    // Registering repository implementation automatically via Spring Data Rest
}
