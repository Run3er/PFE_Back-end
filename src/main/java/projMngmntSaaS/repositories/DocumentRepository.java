package projMngmntSaaS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Document;

import java.util.UUID;

/**
 * {@link Document} repository providing mainly CRUD methods.
 */
@RepositoryRestResource(collectionResourceRel = "documents", path = "documents")
public interface DocumentRepository extends JpaRepository<Document, UUID>
{
    // Registering repository implementation automatically via Spring Data Rest
}
