package projMngmntSaaS.domain.entities.projectLevel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.ConstructionSiteArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.fixedDetails.ConstructionSiteDetails;
import projMngmntSaaS.domain.entities.projectLevel.fixedDetails.interfaces.IConstructionSiteDetails;
import projMngmntSaaS.domain.entities.projectLevel.projections.utils.ProjectLevel;
import projMngmntSaaS.domain.entities.projectLevel.updates.ConstructionSiteUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A construction site is a sub group of {@link SubProject} activities.
 */
@Entity
public class ConstructionSite extends ConstructionSiteArchivableContent implements IConstructionSiteDetails, ProjectLevel
{
    @Embedded
    @JsonIgnore
    protected ConstructionSiteDetails fixedDetails = new ConstructionSiteDetails();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<ConstructionSiteUpdate> archivedUpdates = new HashSet<>();

    public ConstructionSite() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public ConstructionSiteDetails getFixedDetails() {
        return fixedDetails;
    }

    public Set<ConstructionSiteUpdate> getArchivedUpdates() {
        return archivedUpdates;
    }


    public String getName() {
        return fixedDetails.getName();
    }

    public void setName(String name) {
        this.fixedDetails.setName(name);
    }

    public String getGoal() {
        return fixedDetails.getGoal();
    }

    public void setGoal(String goal) {
        this.fixedDetails.setGoal(goal);
    }

    public Date getStartDate() {
        return fixedDetails.getStartDate();
    }

    public void setStartDate(Date startDate) {
        this.fixedDetails.setStartDate(startDate);
    }

    public Date getEndDate() {
        return fixedDetails.getEndDate();
    }

    public void setEndDate(Date endDate) {
        this.fixedDetails.setEndDate(endDate);
    }

    public String getComment() {
        return fixedDetails.getComment();
    }

    public void setComment(String comment) {
        this.fixedDetails.setComment(comment);
    }

    public BigDecimal getBudgetInitial() {
        return fixedDetails.getBudgetInitial();
    }

    public void setBudgetInitial(BigDecimal budgetInitial) {
        this.fixedDetails.setBudgetInitial(budgetInitial);
    }

    public BigDecimal getChargePrevision() {
        return fixedDetails.getChargePrevision();
    }

    public void setChargePrevision(BigDecimal chargePrevision) {
        this.fixedDetails.setChargePrevision(chargePrevision);
    }
}
