package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Risk;

import java.util.UUID;

/**
 * {@link Risk} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "risks", path = "risks")
public interface RiskRepository extends JpaRepository<Risk, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
