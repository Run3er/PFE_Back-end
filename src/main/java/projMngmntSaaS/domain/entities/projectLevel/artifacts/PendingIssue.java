package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import projMngmntSaaS.domain.entities.enums.PendingIssueStatus;
import projMngmntSaaS.domain.entities.enums.ResourceType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * TODO: describe a pending issue.
 */
@Entity
public class PendingIssue extends ProjectLevelArtifact<PendingIssue>
{
    private static final int PRIORITY_LOWER_BOUND = 0;
    private static final int PRIORITY_UPPER_BOUND = 4;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private PendingIssueStatus status;

    @ManyToOne(optional = false)
    private Resource supervisor;

    @Column(nullable = false)
    private int priority;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Date resolutionPlannedDate;

    private Date resolutionDate;

    private String impacts;

    private String decisions;

    public PendingIssue() {
        // no-arg constructor for ORM (due to reflection use)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PendingIssue that = (PendingIssue) o;

        if (priority != that.priority) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (status != that.status) return false;
        if (supervisor != null ? !supervisor.equals(that.supervisor) : that.supervisor != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (resolutionPlannedDate != null ? !resolutionPlannedDate.equals(that.resolutionPlannedDate) : that.resolutionPlannedDate != null)
            return false;
        if (resolutionDate != null ? !resolutionDate.equals(that.resolutionDate) : that.resolutionDate != null)
            return false;
        if (impacts != null ? !impacts.equals(that.impacts) : that.impacts != null) return false;
        return decisions != null ? decisions.equals(that.decisions) : that.decisions == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (supervisor != null ? supervisor.hashCode() : 0);
        result = 31 * result + priority;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (resolutionPlannedDate != null ? resolutionPlannedDate.hashCode() : 0);
        result = 31 * result + (resolutionDate != null ? resolutionDate.hashCode() : 0);
        result = 31 * result + (impacts != null ? impacts.hashCode() : 0);
        result = 31 * result + (decisions != null ? decisions.hashCode() : 0);
        return result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PendingIssueStatus getStatus() {
        return status;
    }

    public void setStatus(PendingIssueStatus status) {
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
        this.creationDate = creationDate;
    }

    public Date getResolutionPlannedDate() {
        return resolutionPlannedDate;
    }

    public void setResolutionPlannedDate(Date resolutionPlannedDate) {
        this.resolutionPlannedDate = resolutionPlannedDate;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public String getImpacts() {
        return impacts;
    }

    public void setImpacts(String impacts) {
        this.impacts = impacts;
    }

    public String getDecisions() {
        return decisions;
    }

    public void setDecisions(String decisions) {
        this.decisions = decisions;
    }

    @Override
    public PendingIssue shallowClone() {
        PendingIssue clone = new PendingIssue();

        shallowCloneRootsInto(clone);
        clone.description = description;
        clone.status = status;
        clone.supervisor = supervisor;
        clone.priority = priority;
        clone.creationDate = creationDate;
        clone.resolutionPlannedDate = resolutionPlannedDate;
        clone.resolutionDate = resolutionDate;
        clone.impacts = impacts;
        clone.decisions = decisions;

        return clone;
    }
}
