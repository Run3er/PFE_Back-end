package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Milestone;

import java.util.UUID;

/**
 * {@link Milestone} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "milestones", path = "milestones")
public interface MilestoneRepository extends JpaRepository<Milestone, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
