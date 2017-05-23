package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import projMngmntSaaS.domain.entities.enums.ActionStatus;
import projMngmntSaaS.domain.entities.enums.ResourceType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * An action represents a task to be performed to advance a project level completion.
 * Once all actions have been completed for a project level, it is itself considered completed.
 */
@Entity
public class Action extends ProjectLevelArtifact<Action>
{
    private static final int PRIORITY_LOWER_BOUND = 0;
    private static final int PRIORITY_UPPER_BOUND = 3;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private ActionStatus status;

    @Column(nullable = false, precision = 3)
    private int advancement = 0;

    @ManyToOne(optional = false)
    private Resource supervisor;

    @Column(nullable = false)
    private int priority;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Date closurePlannedDate;

    private Date closureDate;

    private String comment;

    public Action() {
        // no-arg constructor for ORM (due to reflection use)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Action action = (Action) o;

        if (advancement != action.advancement) return false;
        if (priority != action.priority) return false;
        if (description != null ? !description.equals(action.description) : action.description != null) return false;
        if (status != action.status) return false;
        if (supervisor != null ? !supervisor.equals(action.supervisor) : action.supervisor != null) return false;
        if (creationDate != null ? !creationDate.equals(action.creationDate) : action.creationDate != null)
            return false;
        if (closurePlannedDate != null ? !closurePlannedDate.equals(action.closurePlannedDate) : action.closurePlannedDate != null)
            return false;
        if (closureDate != null ? !closureDate.equals(action.closureDate) : action.closureDate != null) return false;
        return comment != null ? comment.equals(action.comment) : action.comment == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + advancement;
        result = 31 * result + (supervisor != null ? supervisor.hashCode() : 0);
        result = 31 * result + priority;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (closurePlannedDate != null ? closurePlannedDate.hashCode() : 0);
        result = 31 * result + (closureDate != null ? closureDate.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActionStatus getStatus() {
        return status;
    }

    public void setStatus(ActionStatus status) {
        this.status = status;
    }

    public int getAdvancement() {
        return advancement;
    }

    public void setAdvancement(int advancement) {
        if (advancement > 100) {
            throw new IllegalArgumentException("Advancement can't be higher than 100 %");
        }
        this.advancement = advancement;
    }

    public Resource getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Resource supervisor) {
        if (supervisor.getType() != ResourceType.HUMAN) {
            throw new IllegalArgumentException("Supervisor can only be a resource of type " + ResourceType.HUMAN + ".");
        }
        this.supervisor = supervisor;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority < PRIORITY_LOWER_BOUND || priority > PRIORITY_UPPER_BOUND) {
            throw new IllegalArgumentException("Priority can't be set outside of its defined bounds: ["
                    + PRIORITY_LOWER_BOUND + ", " + PRIORITY_UPPER_BOUND + "].");
        }
        this.priority = priority;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getClosurePlannedDate() {
        return closurePlannedDate;
    }

    public void setClosurePlannedDate(Date closurePlannedDate) {
        this.closurePlannedDate = closurePlannedDate;
    }

    public Date getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(Date closureDate) {
        this.closureDate = closureDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public Action shallowClone() {
        Action clone = new Action();

        shallowCloneRootsInto(clone);
        clone.description = description;
        clone.status = status;
        clone.advancement = advancement;
        clone.supervisor = supervisor;
        clone.priority = priority;
        clone.creationDate = creationDate;
        clone.closurePlannedDate = closurePlannedDate;
        clone.closureDate = closureDate;
        clone.comment = comment;

        return clone;
    }
}
