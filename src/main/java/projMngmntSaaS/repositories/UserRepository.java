package projMngmntSaaS.repositories;

import org.springframework.data.repository.query.Param;
import projMngmntSaaS.domain.entities.User;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link User} repository providing mainly CRUD methods.
 */
public interface UserRepository extends EntitiesRepository<User>
{
    // Registering repository implementation automatically via Spring Data Rest

    User findByLogin(@Param("login") String login);
}
