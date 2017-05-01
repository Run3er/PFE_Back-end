package projMngmntSaaS.repositories;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.Document;
import projMngmntSaaS.repositories.general.EntitiesRepository;

/**
 * {@link Document} repository providing mainly CRUD methods.
 */
public interface DocumentRepository extends EntitiesRepository<Document>
{
    // Registering repository implementation automatically via Spring Data Rest
}
