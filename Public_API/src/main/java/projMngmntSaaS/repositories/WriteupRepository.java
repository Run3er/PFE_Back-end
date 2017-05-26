package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.Writeup;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Writeup} repository providing mainly CRUD methods.
 */
public interface WriteupRepository extends EntitiesRepository<Writeup>
{
    // Registering repository implementation automatically via Spring Data Rest
}
