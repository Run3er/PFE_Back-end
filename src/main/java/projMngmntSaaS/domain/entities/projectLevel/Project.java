package projMngmntSaaS.domain.entities.projectLevel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import projMngmntSaaS.domain.entities.ProjectsEntity;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.ProjectArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.fixedDetails.ProjectDetails;
import projMngmntSaaS.domain.entities.projectLevel.fixedDetails.interfaces.IProjectDetails;
import projMngmntSaaS.domain.entities.projectLevel.projections.utils.ProjectLevel;
import projMngmntSaaS.domain.entities.projectLevel.updates.ProjectUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A project is the main element for a project's management.
 * A project always belongs one to {@link projMngmntSaaS.domain.entities.ProjectsEntity}.
 */
@Entity
public class Project extends ProjectArchivableContent implements IProjectDetails, ProjectLevel
{
    @Embedded
    @JsonIgnore
    private ProjectDetails fixedDetails = new ProjectDetails();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // Exact field name ('entity') mappedBy ProjectsEntity association
    private ProjectsEntity entity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubProject> subProjects = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ConstructionSite> constructionSites = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectUpdate> archivedUpdates = new HashSet<>();

    public Project() {
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

    public String getMainContact() {
        return fixedDetails.getMainContact();
    }

    public void setMainContact(String mainContact) {
        this.fixedDetails.setMainContact(mainContact);
    }

    public String getSponsors() {
        return fixedDetails.getSponsors();
    }

    public void setSponsors(String sponsors) {
        this.fixedDetails.setSponsors(sponsors);
    }

    public String getFinalClient() {
        return fixedDetails.getFinalClient();
    }

    public void setFinalClient(String finalClient) {
        this.fixedDetails.setFinalClient(finalClient);
    }

    public String getHypotheses_constraints() {
        return fixedDetails.getHypotheses_constraints();
    }

    public void setHypotheses_constraints(String hypotheses_constraints) {
        this.fixedDetails.setHypotheses_constraints(hypotheses_constraints);
    }

    public String getHistory_decisions() {
        return fixedDetails.getHistory_decisions();
    }

    public void setHistory_decisions(String history_decisions) {
        this.fixedDetails.setHistory_decisions(history_decisions);
    }

    public ProjectsEntity getEntity() {
        return entity;
    }

    public void setEntity(ProjectsEntity entity) {
        this.entity = entity;
    }

    public Set<SubProject> getSubProjects() {
        return subProjects;
    }

    public Set<ConstructionSite> getConstructionSites() {
        return constructionSites;
    }

    public ProjectDetails getFixedDetails() {
        return fixedDetails;
    }

    public Set<ProjectUpdate> getArchivedUpdates() {
        return archivedUpdates;
    }
}
