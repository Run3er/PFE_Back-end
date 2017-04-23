package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import projMngmntSaaS.domain.entities.projectLevel.ProjectLevelUpdate;

import java.util.UUID;

/**
 * {@link ProjectLevelUpdate} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "updates", path = "updates")
public interface ProjectLevelUpdateRepository extends JpaRepository<ProjectLevelUpdate, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest

    // Forbid POST, PUT & DELETE methods, allow only GET

    @Override
    @RestResource(exported = false)
    void delete(UUID uuid);

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends ProjectLevelUpdate> entities);

    @Override
    @RestResource(exported = false)
    <S extends ProjectLevelUpdate> S save(S entity);
}
