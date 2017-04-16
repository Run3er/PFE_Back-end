package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.ContributorOrganization;

import java.util.UUID;

/**
 * {@link ContributorOrganization} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "contributorOrganizations", path = "contributorOrganizations")
public interface ContributorOrganizationRepository extends JpaRepository<ContributorOrganization, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
