package projMngmntSaaS.domain.entities.projectLevel;

import projMngmntSaaS.domain.entities.enums.ProjectLevelStatus;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * This is an aggregation of a project level properties for a specific update.
 * Note that the last update time for the most recent contents of a project level is temporarily referring to the update
 * time of the previous one. This is useful to avoid querying the previous update time from the DB when needing the info.
 */
@MappedSuperclass
public abstract class ProjectLevelContents
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;
    
    protected BigDecimal budgetTotal;

    protected BigDecimal budgetPlanned;

    protected BigDecimal budgetConsumed;

    protected int chargeConsumed;

    @Column(nullable = false, precision = 3)
    protected float advancement;

    @Column(nullable = false)
    protected ProjectLevelStatus status;

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<Action> actions = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<Risk> risks = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<PendingIssue> pendingIssues = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<ChangeRequest> changeRequests = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<Resource> resources = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<Document> documents = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<Milestone> milestones = new HashSet<>();

    public ProjectLevelContents() {
        // explicit, public, no-arg constructor (making it always available to derived classes)
    }

    public ProjectLevelContents(ProjectLevelContents projectLevelContents) {
        this.budgetTotal = projectLevelContents.budgetTotal;
        this.budgetPlanned = projectLevelContents.budgetPlanned;
        this.budgetConsumed = projectLevelContents.budgetConsumed;
        this.chargeConsumed = projectLevelContents.chargeConsumed;
        this.advancement = projectLevelContents.advancement;
        this.status = projectLevelContents.status;
        this.actions = new HashSet<>(projectLevelContents.actions);
        this.risks = new HashSet<>(projectLevelContents.risks);
        this.pendingIssues = new HashSet<>(projectLevelContents.pendingIssues);
        this.changeRequests = new HashSet<>(projectLevelContents.changeRequests);
        this.resources = new HashSet<>(projectLevelContents.resources);
        this.documents = new HashSet<>(projectLevelContents.documents);
        this.milestones = new HashSet<>(projectLevelContents.milestones);
    }

    public BigDecimal getBudgetTotal() {
        return budgetTotal;
    }

    public void setBudgetTotal(BigDecimal budgetTotal) {
        this.budgetTotal = budgetTotal;
    }

    public BigDecimal getBudgetPlanned() {
        return budgetPlanned;
    }

    public void setBudgetPlanned(BigDecimal budgetPlanned) {
        this.budgetPlanned = budgetPlanned;
    }

    public BigDecimal getBudgetConsumed() {
        return budgetConsumed;
    }

    public void setBudgetConsumed(BigDecimal budgetConsumed) {
        this.budgetConsumed = budgetConsumed;
    }

    public int getChargeConsumed() {
        return chargeConsumed;
    }

    public void setChargeConsumed(int chargeConsumed) {
        this.chargeConsumed = chargeConsumed;
    }

    public float getAdvancement() {
        return advancement;
    }

    public void setAdvancement(float advancement) {
        this.advancement = advancement;
    }

    public ProjectLevelStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectLevelStatus status) {
        this.status = status;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public Set<Risk> getRisks() {
        return risks;
    }

    public Set<PendingIssue> getPendingIssues() {
        return pendingIssues;
    }

    public Set<ChangeRequest> getChangeRequests() {
        return changeRequests;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public Set<Milestone> getMilestones() {
        return milestones;
    }
}
