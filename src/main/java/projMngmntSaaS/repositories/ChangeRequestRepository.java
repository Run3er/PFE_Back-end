package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.ChangeRequest;

import java.util.UUID;

/**
 * {@link ChangeRequest} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "change-requests", path = "change-requests")
public interface ChangeRequestRepository extends JpaRepository<ChangeRequest, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
