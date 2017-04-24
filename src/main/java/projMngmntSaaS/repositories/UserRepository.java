package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.User;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link User} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends EntitiesRepository<User>
{
    // Registering repository implementation automatically via Spring Data Rest
}
