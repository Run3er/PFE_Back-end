package projMngmntSaaS.repositories;

import org.springframework.data.repository.query.Param;
import projMngmntSaaS.domain.entities.ContributorOrganization;
import projMngmntSaaS.domain.entities.enums.ContributorRole;
import projMngmntSaaS.repositories.general.EntitiesRepository;

import java.util.List;

/**
 * {@link ContributorOrganization} repository providing mainly CRUD methods.
 */
public interface ContributorOrganizationRepository extends EntitiesRepository<ContributorOrganization>
{
    // Registering repository implementation automatically via Spring Data Rest

    List<ContributorOrganization> findByRole(@Param("role") ContributorRole role);
}
