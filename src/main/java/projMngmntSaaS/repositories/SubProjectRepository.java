package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;

import java.util.UUID;

/**
 * {@link SubProject} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "sub-projects", path = "sub-projects")
public interface SubProjectRepository extends JpaRepository<SubProject, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
