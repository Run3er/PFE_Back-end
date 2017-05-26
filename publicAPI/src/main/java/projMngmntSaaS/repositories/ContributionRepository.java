package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.Contribution;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Contribution} repository providing mainly CRUD methods.
 */
public interface ContributionRepository extends EntitiesRepository<Contribution>
{
    // Registering repository implementation automatically via Spring Data Rest
}
