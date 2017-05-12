package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * TODO: description
 */
@Entity
public class Todo extends ProjectLevelArtifact<Todo>
{
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int charge;

    public Todo() {
        // no-arg constructor for ORM (due to reflection use)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Todo todo = (Todo) o;

        if (charge != todo.charge) return false;
        return description != null ? description.equals(todo.description) : todo.description == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + charge;
        return result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    @Override
    public Todo shallowClone() {
        Todo clone = new Todo();

        shallowCloneRootsInto(clone);
        clone.description = description;
        clone.charge = charge;

        return clone;
    }
}
