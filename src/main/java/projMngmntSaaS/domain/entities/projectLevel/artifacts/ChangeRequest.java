package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import projMngmntSaaS.domain.entities.enums.ChangeRequestStatus;
import projMngmntSaaS.domain.entities.enums.PendingIssueStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * TODO: describe a change request.
 */
@Entity
public class ChangeRequest extends ProjectLevelArtifact<ChangeRequest>
{
    private static final int PRIORITY_LOWER_BOUND = 0;
    private static final int PRIORITY_UPPER_BOUND = 3;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private ChangeRequestStatus status;

    @Column(nullable = false)
    private String requester;

    @Column(nullable = false)
    private int priority;

    @Column(nullable = false)
    private Date requestDate;

    @Column(nullable = false)
    private Date decisionPlannedDate;

    private Date decisionDate;

    private String impacts;

    public ChangeRequest() {
        // no-arg constructor for ORM (due to reflection use)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ChangeRequest that = (ChangeRequest) o;

        if (priority != that.priority) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (status != that.status) return false;
        if (requester != null ? !requester.equals(that.requester) : that.requester != null) return false;
        if (requestDate != null ? !requestDate.equals(that.requestDate) : that.requestDate != null) return false;
        if (decisionPlannedDate != null ? !decisionPlannedDate.equals(that.decisionPlannedDate) : that.decisionPlannedDate != null)
            return false;
        if (decisionDate != null ? !decisionDate.equals(that.decisionDate) : that.decisionDate != null) return false;
        return impacts != null ? impacts.equals(that.impacts) : that.impacts == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (requester != null ? requester.hashCode() : 0);
        result = 31 * result + priority;
        result = 31 * result + (requestDate != null ? requestDate.hashCode() : 0);
        result = 31 * result + (decisionPlannedDate != null ? decisionPlannedDate.hashCode() : 0);
        result = 31 * result + (decisionDate != null ? decisionDate.hashCode() : 0);
        result = 31 * result + (impacts != null ? impacts.hashCode() : 0);
        return result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChangeRequestStatus getStatus() {
        return status;
    }

    public void setStatus(ChangeRequestStatus status) {
        this.status = status;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        if (decisionPlannedDate != null && requestDate.after(decisionPlannedDate)) {
            throw new IllegalArgumentException("Request date can't be set to later than decision planned date:" +
                    "\trequestDate=" + requestDate +
                    "\n\tdecisionPlannedDate=" + decisionPlannedDate);
        }
        if (decisionDate != null && requestDate.after(decisionDate)) {
            throw new IllegalArgumentException("Request date can't be set to later than decision date:" +
                    "\trequestDate=" + requestDate +
                    "\n\tresolutionDate=" + decisionDate);
        }
        this.requestDate = requestDate;
    }

    public Date getDecisionPlannedDate() {
        return decisionPlannedDate;
    }

    public void setDecisionPlannedDate(Date decisionPlannedDate) {
        if (requestDate != null && decisionPlannedDate.after(requestDate)) {
            throw new IllegalArgumentException("Planned decision date can't be set to earlier than request date:" +
                    "\tdecisionPlannedDate=" + decisionPlannedDate +
                    "\n\trequestDate=" + requestDate);
        }
        this.decisionPlannedDate = decisionPlannedDate;
    }

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        if (requestDate != null && decisionDate.after(requestDate)) {
            throw new IllegalArgumentException("Decision date can't be set to earlier than request date:" +
                    "\tdecisionDate=" + decisionDate +
                    "\n\trequestDate=" + requestDate);
        }
        this.decisionDate = decisionDate;
    }

    public String getImpacts() {
        return impacts;
    }

    public void setImpacts(String impacts) {
        this.impacts = impacts;
    }

    @Override
    public ChangeRequest shallowClone() {
        ChangeRequest clone = new ChangeRequest();

        shallowCloneRootsInto(clone);
        clone.description = description;
        clone.status = status;
        clone.requester = requester;
        clone.priority = priority;
        clone.requestDate = requestDate;
        clone.decisionPlannedDate = decisionPlannedDate;
        clone.decisionDate = decisionDate;
        clone.impacts = impacts;

        return clone;
    }
}
