package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Resource;

import java.util.UUID;

/**
 * {@link Resource} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "resources", path = "resources")
public interface ResourceRepository extends JpaRepository<Resource, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
