package projMngmntSaaS.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.Tenant;

import java.util.UUID;

/**
 * Tenant repository providing CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "tenants", path = "tenants")
public interface TenantRepository extends CrudRepository<Tenant, UUID> {
    // Registering repository implementation automatically via Spring Data Rest
}
