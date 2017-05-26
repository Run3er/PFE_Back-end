package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * TODO: description
 */
@Entity
public class HumanResource extends ProjectLevelArtifact<HumanResource>
{
    @Column(nullable = false)
    private String name;

    public HumanResource() {
        // no-arg constructor for ORM (due to reflection use)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        HumanResource that = (HumanResource) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public HumanResource shallowClone() {
        HumanResource clone = new HumanResource();

        shallowCloneRootsInto(clone);
        clone.name = name;

        return clone;
    }
}
