package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.Contribution;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Contribution} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "contributions", path = "contributions")
public interface ContributionRepository extends EntitiesRepository<Contribution>
{
    // Registering repository implementation automatically via Spring Data Rest
}
