package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.Contribution;

import java.util.UUID;

/**
 * {@link Contribution} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "contributions", path = "contributions")
public interface ContributionRepository extends JpaRepository<Contribution, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
