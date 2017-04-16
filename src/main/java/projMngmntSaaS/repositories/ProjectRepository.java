package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.Project;

import java.util.UUID;

/**
 * {@link Project} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "projects", path = "projects")
public interface ProjectRepository extends JpaRepository<Project, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
