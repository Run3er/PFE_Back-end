package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.Tenant;

import java.util.UUID;

/**
 * {@link Tenant} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "tenants", path = "tenants")
public interface TenantRepository extends JpaRepository<Tenant, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
