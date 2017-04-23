package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import projMngmntSaaS.domain.utils.CloneableEntity;

import javax.persistence.*;
import java.util.UUID;

/**
 * Provides common project level artifact properties.
 */
@MappedSuperclass
public abstract class ProjectLevelArtifact<T extends ProjectLevelArtifact<T>> implements CloneableEntity<T>
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    /**
     * This is used to guarantee that each artifact entity has a reference identifying it, apart from its id.
     * It is useful for versioning artifacts (each version being an artifact entity in the DB with its own id).
     */
    @Column(nullable = false)
    protected UUID reference = UUID.randomUUID();

    /**
     * A pointer to the last updated artifact version. It is null for the original/root artifact.
     * This is to leverage accessing an artifact's version history, avoiding multiple update-action joins.
     */
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected T lastVersion;

    public ProjectLevelArtifact() {
        // no-arg constructor for ORM (due to reflection use)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectLevelArtifact<?> that = (ProjectLevelArtifact<?>) o;

        return reference != null ? reference.equals(that.reference) : that.reference == null;
    }

    @Override
    public int hashCode() {
        return reference != null ? reference.hashCode() : 0;
    }

    public UUID getReference() {
        return reference;
    }

    public void setReference(UUID reference) {
        this.reference = reference;
    }

    public T getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(T lastVersion) {
        this.lastVersion = lastVersion;
    }

    public void shallowCloneRootsInto(T clone) {
        clone.reference = reference;
        clone.lastVersion = lastVersion;
    }
}
