package projMngmntSaaS.domain.entities.projectLevel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.SubProjectArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.fixedDetails.SubProjectDetails;
import projMngmntSaaS.domain.entities.projectLevel.fixedDetails.interfaces.ISubProjectDetails;
import projMngmntSaaS.domain.entities.projectLevel.projections.utils.ProjectLevel;
import projMngmntSaaS.domain.entities.projectLevel.updates.SubProjectUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A sub-project is a sub group of {@link Project} activities.
 */
@Entity
public class SubProject extends SubProjectArchivableContent implements ISubProjectDetails, ProjectLevel
{
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ConstructionSite> constructionSites = new HashSet<>();

    @Embedded
    @JsonIgnore
    private SubProjectDetails fixedDetails = new SubProjectDetails();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubProjectUpdate> archivedUpdates = new HashSet<>();

    public SubProject() {
        // no-arg constructor for ORM (due to reflection use)
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

    public Set<ConstructionSite> getConstructionSites() {
        return constructionSites;
    }

    public SubProjectDetails getFixedDetails() {
        return fixedDetails;
    }

    public Set<SubProjectUpdate> getArchivedUpdates() {
        return archivedUpdates;
    }
}
