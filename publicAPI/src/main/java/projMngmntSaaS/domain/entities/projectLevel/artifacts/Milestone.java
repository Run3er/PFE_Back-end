package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * A milestone is akin to a soft deadline. it has characterized by a name and a due date.
 */
@Entity
public class Milestone extends ProjectLevelArtifact<Milestone>
{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date dueDate;

    private String description;

    public Milestone() {
        // no-arg constructor for ORM (due to reflection use)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Milestone milestone = (Milestone) o;

        if (name != null ? !name.equals(milestone.name) : milestone.name != null) return false;
        if (dueDate != null ? !dueDate.equals(milestone.dueDate) : milestone.dueDate != null) return false;
        return description != null ? description.equals(milestone.description) : milestone.description == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Milestone shallowClone() {
        Milestone clone = new Milestone();

        shallowCloneRootsInto(clone);
        clone.name = name;
        clone.dueDate = dueDate;

        return clone;
    }
}
