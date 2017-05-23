package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

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

    @Column(nullable = false)
    private Date estimationDate;

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
        if (description != null ? !description.equals(todo.description) : todo.description != null) return false;
        return estimationDate != null ? estimationDate.equals(todo.estimationDate) : todo.estimationDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + charge;
        result = 31 * result + (estimationDate != null ? estimationDate.hashCode() : 0);
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

    public Date getEstimationDate() {
        return estimationDate;
    }

    public void setEstimationDate(Date estimationDate) {
        this.estimationDate = estimationDate;
    }

    @Override
    public Todo shallowClone() {
        Todo clone = new Todo();

        shallowCloneRootsInto(clone);
        clone.description = description;
        clone.charge = charge;
        clone.estimationDate = estimationDate;

        return clone;
    }
}
