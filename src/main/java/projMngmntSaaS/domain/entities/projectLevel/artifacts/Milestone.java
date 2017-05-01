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
        return dueDate != null ? dueDate.equals(milestone.dueDate) : milestone.dueDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
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

    @Override
    public Milestone shallowClone() {
        Milestone clone = new Milestone();

        shallowCloneRootsInto(clone);
        clone.name = name;
        clone.dueDate = dueDate;

        return clone;
    }
}
