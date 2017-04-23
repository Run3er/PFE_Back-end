package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import projMngmntSaaS.domain.entities.enums.RiskStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * A risk raises an issue to take into account relative to a project level.
 * It is dealt with by deciding on appropriate measures to take, before ultimately setting them up.
 */
@Entity
public class Risk extends ProjectLevelArtifact<Risk>
{
    private static final int PROBABILITY_LOWER_BOUND = 0;
    private static final int PROBABILITY_UPPER_BOUND = 3;
    private static final int IMPACT_LOWER_BOUND = 0;
    private static final int IMPACT_UPPER_BOUND = 5;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int probability;

    @Column(nullable = false)
    private int impact;

    @Column(nullable = false)
    private String actionPlan;

    @Column(nullable = false)
    private RiskStatus status;

    private String decision;

    @Column(nullable = false)
    private Date detectionDate;

    @Column(nullable = false)
    private Date qualificationDate;

    private Date closureDate;

    private String comment;

    public Risk() {
        // no-arg constructor for ORM (due to reflection use)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Risk risk = (Risk) o;

        if (probability != risk.probability) return false;
        if (impact != risk.impact) return false;
        if (description != null ? !description.equals(risk.description) : risk.description != null) return false;
        if (actionPlan != null ? !actionPlan.equals(risk.actionPlan) : risk.actionPlan != null) return false;
        if (status != risk.status) return false;
        if (decision != null ? !decision.equals(risk.decision) : risk.decision != null) return false;
        if (detectionDate != null ? !detectionDate.equals(risk.detectionDate) : risk.detectionDate != null)
            return false;
        if (qualificationDate != null ? !qualificationDate.equals(risk.qualificationDate) : risk.qualificationDate != null)
            return false;
        if (closureDate != null ? !closureDate.equals(risk.closureDate) : risk.closureDate != null) return false;
        return comment != null ? comment.equals(risk.comment) : risk.comment == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + probability;
        result = 31 * result + impact;
        result = 31 * result + (actionPlan != null ? actionPlan.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (decision != null ? decision.hashCode() : 0);
        result = 31 * result + (detectionDate != null ? detectionDate.hashCode() : 0);
        result = 31 * result + (qualificationDate != null ? qualificationDate.hashCode() : 0);
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

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        if (probability < PROBABILITY_LOWER_BOUND || probability > PROBABILITY_UPPER_BOUND) {
            throw new IllegalArgumentException("Priority can't be set outside of its defined bounds: ["
                    + PROBABILITY_LOWER_BOUND + ", " + PROBABILITY_UPPER_BOUND + "].");
        }
        this.probability = probability;
    }

    public int getImpact() {
        return impact;
    }

    public void setImpact(int impact) {
        if (impact < IMPACT_LOWER_BOUND || impact > IMPACT_UPPER_BOUND) {
            throw new IllegalArgumentException("Impact can't be set outside of its defined bounds: ["
                    + IMPACT_LOWER_BOUND + ", " + IMPACT_UPPER_BOUND + "].");
        }
        this.impact = impact;
    }

    public String getActionPlan() {
        return actionPlan;
    }

    public void setActionPlan(String actionPlan) {
        this.actionPlan = actionPlan;
    }

    public RiskStatus getStatus() {
        return status;
    }

    public void setStatus(RiskStatus status) {
        this.status = status;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public Date getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(Date detectionDate) {
        if (qualificationDate != null && detectionDate.after(qualificationDate)) {
            throw new IllegalArgumentException("Detection date can't be set to later than qualification date:" +
                    "\tdetectionDate=" + detectionDate +
                    "\n\tqualificationDate=" + qualificationDate);
        }
        if (closureDate != null && detectionDate.after(closureDate)) {
            throw new IllegalArgumentException("Detection date can't be set to later than closure date:" +
                    "\tdetectionDate=" + detectionDate +
                    "\n\tclosureDate=" + closureDate);
        }
        this.detectionDate = detectionDate;
    }

    public Date getQualificationDate() {
        return qualificationDate;
    }

    public void setQualificationDate(Date qualificationDate) {
        if (detectionDate != null && qualificationDate.before(detectionDate)) {
            throw new IllegalArgumentException("Qualification date can't be set to earlier than detection date:" +
                    "\tqualificationDate=" + qualificationDate +
                    "\n\tdetectionDate=" + detectionDate);
        }
        if (closureDate != null && qualificationDate.after(closureDate)) {
            throw new IllegalArgumentException("Qualification date can't be set to later than closure date:" +
                    "\tqualificationDate=" + qualificationDate +
                    "\n\tclosureDate=" + closureDate);
        }
        this.qualificationDate = qualificationDate;
    }

    public Date getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(Date closureDate) {
        if (detectionDate != null && closureDate.before(detectionDate)) {
            throw new IllegalArgumentException("Closure date can't be set to earlier than detection date:" +
                    "\tclosureDate=" + closureDate +
                    "\n\tdetectionDate=" + detectionDate);
        }
        if (qualificationDate != null && closureDate.before(qualificationDate)) {
            throw new IllegalArgumentException("Closure date can't be set to earlier than qualification date:" +
                    "\tclosureDate=" + closureDate +
                    "\n\tqualificationDate=" + qualificationDate);
        }
        this.closureDate = closureDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public Risk shallowClone() {
        Risk clone = new Risk();

        shallowCloneRootsInto(clone);
        clone.description = description;
        clone.probability = probability;
        clone.impact = impact;
        clone.actionPlan = actionPlan;
        clone.status = status;
        clone.decision = decision;
        clone.detectionDate = detectionDate;
        clone.qualificationDate = qualificationDate;
        clone.closureDate = closureDate;
        clone.comment = comment;

        return clone;
    }
}
