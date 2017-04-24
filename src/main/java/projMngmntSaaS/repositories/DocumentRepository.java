package projMngmntSaaS.repositories;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Document;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Document} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "documents", path = "documents")
public interface DocumentRepository extends EntitiesRepository<Document>
{
    // Registering repository implementation automatically via Spring Data Rest
}
