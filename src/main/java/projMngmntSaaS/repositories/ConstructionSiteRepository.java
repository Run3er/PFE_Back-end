package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.ConstructionSite;

import java.util.UUID;

/**
 * {@link ConstructionSite} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "construction-sites", path = "construction-sites")
public interface ConstructionSiteRepository extends JpaRepository<ConstructionSite, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
