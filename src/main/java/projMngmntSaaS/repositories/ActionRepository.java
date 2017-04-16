package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Action;

import java.util.UUID;

/**
 * {@link Action} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "actions", path = "actions")
public interface ActionRepository extends JpaRepository<Action, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
