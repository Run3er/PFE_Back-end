package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.Tenant;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Tenant} repository providing mainly CRUD methods.
 */
public interface TenantRepository extends EntitiesRepository<Tenant>
{
    // Registering repository implementation automatically via Spring Data Rest
}
