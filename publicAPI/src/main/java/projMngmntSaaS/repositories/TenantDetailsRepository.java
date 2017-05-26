package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.TenantDetails;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link TenantDetails} repository providing mainly CRUD methods.
 */
public interface TenantDetailsRepository extends EntitiesRepository<TenantDetails>
{
    // Registering repository implementation automatically via Spring Data Rest
}
