package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.ContributorOrganization;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link ContributorOrganization} repository providing mainly CRUD methods.
 */
public interface ContributorOrganizationRepository extends EntitiesRepository<ContributorOrganization>
{
    // Registering repository implementation automatically via Spring Data Rest
}
