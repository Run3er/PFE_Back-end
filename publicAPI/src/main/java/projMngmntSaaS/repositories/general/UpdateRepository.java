package projMngmntSaaS.repositories.general;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;
import projMngmntSaaS.domain.entities.projectLevel.updates.ConstructionSiteUpdate;

import java.util.UUID;

/**
 * {@link ConstructionSiteUpdate} repository providing mainly CRUD methods.
 */
@NoRepositoryBean
public interface UpdateRepository<T> extends EntitiesRepository<T>
{
    // Registering repository implementation automatically via Spring Data Rest

    // Forbid POST, PUT & DELETE methods, allow only GET

    @Override
    @RestResource(exported = false)
    void delete(UUID uuid);

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends T> entities);

    @Override
    @RestResource(exported = false)
    <S extends T> S save(S entity);
}
