package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import javax.persistence.*;
import java.util.UUID;

/**
 * This provides common project level artifact properties.
 * It is used to guarantee that each artifact entity has a reference identifying it, apart from its id. It is useful
 * for versioning artifacts (each version being an artifact entity in the DB).
 */
@MappedSuperclass
public abstract class ProjectLevelArtifact<T extends ProjectLevelArtifact<T>>
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Column(nullable = false)
    protected UUID reference = UUID.randomUUID();

    protected ProjectLevelArtifact() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public UUID getReference() {
        return reference;
    }

    public void setReference(UUID reference) {
        this.reference = reference;
    }
}
