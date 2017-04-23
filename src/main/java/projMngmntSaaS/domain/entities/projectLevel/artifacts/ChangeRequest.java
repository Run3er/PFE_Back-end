package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * TODO: describe a change request & extend its properties.
 */
@Entity
public class ChangeRequest extends ProjectLevelArtifact<ChangeRequest>
{
    @Column(nullable = false)
    private String description;

    public ChangeRequest() {
        // no-arg constructor for ORM (due to reflection use)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ChangeRequest that = (ChangeRequest) o;

        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public ChangeRequest shallowClone() {
        ChangeRequest clone = new ChangeRequest();

        shallowCloneRootsInto(clone);
        clone.description = description;

        return clone;
    }
}
