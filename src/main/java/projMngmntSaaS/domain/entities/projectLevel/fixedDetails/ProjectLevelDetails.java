package projMngmntSaaS.domain.entities.projectLevel.fixedDetails;

import projMngmntSaaS.domain.entities.projectLevel.fixedDetails.interfaces.IProjectLevelDetails;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.util.Date;

/**
 * This provides project level common fixed properties.
 */
@MappedSuperclass
@Embeddable
public abstract class ProjectLevelDetails implements IProjectLevelDetails
{
    @Column(nullable = false)
    protected String name;

    protected String goal;

    @Column(nullable = false)
    protected Date startDate;

    @Column(nullable = false)
    protected Date endDate;

    protected String comment;

    protected BigDecimal budgetInitial = new BigDecimal(0);

    @Column(precision = 10)
    protected BigDecimal chargePrevision = new BigDecimal(0);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getBudgetInitial() {
        return budgetInitial;
    }

    public void setBudgetInitial(BigDecimal budgetInitial) {
        this.budgetInitial = budgetInitial;
    }

    public BigDecimal getChargePrevision() {
        return chargePrevision;
    }

    public void setChargePrevision(BigDecimal chargePrevision) {
        this.chargePrevision = chargePrevision;
    }
}
