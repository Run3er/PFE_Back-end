package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.Tenant;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Tenant} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "tenants", path = "tenants")
public interface TenantRepository extends EntitiesRepository<Tenant>
{
    // Registering repository implementation automatically via Spring Data Rest
}
