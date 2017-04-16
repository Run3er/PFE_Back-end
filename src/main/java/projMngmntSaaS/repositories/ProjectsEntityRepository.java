package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.ProjectsEntity;

import java.util.UUID;

/**
 * {@link ProjectsEntity} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "entities", path = "entities")
public interface ProjectsEntityRepository extends JpaRepository<ProjectsEntity, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
