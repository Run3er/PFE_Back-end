package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.ConstructionSite;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link ConstructionSite} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "construction-sites", path = "construction-sites")
public interface ConstructionSiteRepository extends EntitiesRepository<ConstructionSite>
{
    // Registering repository implementation automatically via Spring Data Rest
}
