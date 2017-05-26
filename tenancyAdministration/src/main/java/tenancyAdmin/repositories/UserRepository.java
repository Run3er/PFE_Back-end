package tenancyAdmin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import projMngmntSaaS.multiTenancy.domain.Tenant;
import tenancyAdmin.domain.User;

import java.util.UUID;

/**
 * {@link User} repository providing mainly CRUD methods.
 */
public interface UserRepository extends JpaRepository<User, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest

    User findByLogin(@Param("login") String login);
}
