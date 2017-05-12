package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * TODO: description
 */
@Entity
public class Writeup extends ProjectLevelArtifact<Writeup>
{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    public Writeup() {
        // no-arg constructor for ORM (due to reflection use)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Writeup writeup = (Writeup) o;

        if (name != null ? !name.equals(writeup.name) : writeup.name != null) return false;
        return description != null ? description.equals(writeup.description) : writeup.description == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Writeup shallowClone() {
        Writeup clone = new Writeup();

        shallowCloneRootsInto(clone);
        clone.description = description;
        clone.name = name;

        return clone;
    }
}
