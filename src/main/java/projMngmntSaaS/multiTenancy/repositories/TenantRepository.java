package projMngmntSaaS.multiTenancy.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.multiTenancy.domain.Tenant;

import java.util.UUID;

/**
 * {@link Tenant} repository providing mainly CRUD methods.
 */
// Cancelling automatic repository registration via Spring Data REST
@RepositoryRestResource(exported = false)
public interface TenantRepository extends CrudRepository<Tenant, UUID>
{
    Tenant findTenantByPseudo(String pseudo);
}
