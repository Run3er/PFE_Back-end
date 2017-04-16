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
public class Action extends ProjectLevelArtifact
{
    private static final int PRIORITY_LOWER_BOUND = 0;
    private static final int PRIORITY_UPPER_BOUND = 3;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private ActionStatus status;

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
        if (closurePlannedDate != null && creationDate.after(closurePlannedDate)) {
            throw new IllegalArgumentException("Creation date can't be set to later than closure planned date:" +
                    "\tcreationDate=" + creationDate +
                    "\n\tclosurePlannedDate=" + closurePlannedDate);
        }
        if (closureDate != null && creationDate.after(closureDate)) {
            throw new IllegalArgumentException("Creation date can't be set to later than closure date:" +
                    "\tcreationDate=" + creationDate +
                    "\n\tclosureDate=" + closureDate);
        }
        this.creationDate = creationDate;
    }

    public Date getClosurePlannedDate() {
        return closurePlannedDate;
    }

    public void setClosurePlannedDate(Date closurePlannedDate) {
        if (creationDate != null && closurePlannedDate.after(creationDate)) {
            throw new IllegalArgumentException("Planned closure date can't be set to earlier than creation date:" +
                    "\tclosurePlannedDate=" + closurePlannedDate +
                    "\n\tcreationDate=" + creationDate);
        }
        this.closurePlannedDate = closurePlannedDate;
    }

    public Date getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(Date closureDate) {
        if (creationDate != null && closureDate.after(creationDate)) {
            throw new IllegalArgumentException("Closure date can't be set to earlier than creation date:" +
                    "\tclosureDate=" + closureDate +
                    "\n\tcreationDate=" + creationDate);
        }
        this.closureDate = closureDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
