package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Risk;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Risk} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "risks", path = "risks")
public interface RiskRepository extends EntitiesRepository<Risk>
{
    // Registering repository implementation automatically via Spring Data Rest
}
