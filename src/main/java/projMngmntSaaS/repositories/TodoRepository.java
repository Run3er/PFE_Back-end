package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Todo;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Todo} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "todos", path = "todos")
public interface TodoRepository extends EntitiesRepository<Todo>
{
    // Registering repository implementation automatically via Spring Data Rest
}
