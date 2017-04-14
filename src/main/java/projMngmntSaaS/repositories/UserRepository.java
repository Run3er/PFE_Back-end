package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.User;

import java.util.UUID;

/**
 * {@link User} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
