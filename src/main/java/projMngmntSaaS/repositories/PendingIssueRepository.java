package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.PendingIssue;

import java.util.UUID;

/**
 * {@link PendingIssue} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "pending-issues", path = "pending-issues")
public interface PendingIssueRepository extends JpaRepository<PendingIssue, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
