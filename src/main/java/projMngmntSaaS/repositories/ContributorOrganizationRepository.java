package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.ContributorOrganization;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link ContributorOrganization} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "contributorOrganizations", path = "contributorOrganizations")
public interface ContributorOrganizationRepository extends EntitiesRepository<ContributorOrganization>
{
    // Registering repository implementation automatically via Spring Data Rest
}
