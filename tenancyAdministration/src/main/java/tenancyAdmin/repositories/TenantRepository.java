package tenancyAdmin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projMngmntSaaS.multiTenancy.domain.Tenant;

import java.util.UUID;

/**
 * {@link Tenant} repository providing mainly CRUD methods.
 */
public interface TenantRepository extends JpaRepository<Tenant, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
