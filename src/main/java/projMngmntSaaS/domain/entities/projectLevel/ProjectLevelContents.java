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
    
    protected BigDecimal budgetInitial = new BigDecimal(0);

    protected BigDecimal budgetToConsume = new BigDecimal(0);

    protected BigDecimal budgetConsumed = new BigDecimal(0);

    protected BigDecimal chargePrevision = new BigDecimal(0);

    protected BigDecimal chargeConsumed = new BigDecimal(0);

    @Column(nullable = false, precision = 3)
    protected float advancement = 0;

    @Column(nullable = false)
    protected ProjectLevelStatus status = ProjectLevelStatus.GREEN;

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

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<Todo> todos = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<HumanResource> humanResources = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<CommunicationPlan> communicationPlans = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<Writeup> writeups = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<ReunionPlanning> reunionPlannings = new HashSet<>();

    public ProjectLevelContents() {
        // explicit, public, no-arg constructor (making it always available to derived classes)
    }

    public ProjectLevelContents(ProjectLevelContents projectLevelContents) {
        this.budgetInitial = projectLevelContents.budgetInitial;
        this.budgetToConsume = projectLevelContents.budgetToConsume;
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
        this.todos = new HashSet<>(projectLevelContents.todos);
        this.humanResources = new HashSet<>(projectLevelContents.humanResources);
        this.communicationPlans = new HashSet<>(projectLevelContents.communicationPlans);
        this.writeups = new HashSet<>(projectLevelContents.writeups);
        this.reunionPlannings = new HashSet<>(projectLevelContents.reunionPlannings);
    }

    public BigDecimal getBudgetInitial() {
        return budgetInitial;
    }

    public void setBudgetInitial(BigDecimal budgetInitial) {
        this.budgetInitial = budgetInitial;
    }

    public BigDecimal getBudgetToConsume() {
        return budgetToConsume;
    }

    public void setBudgetToConsume(BigDecimal budgetToConsume) {
        this.budgetToConsume = budgetToConsume;
    }

    public BigDecimal getBudgetConsumed() {
        return budgetConsumed;
    }

    public void setBudgetConsumed(BigDecimal budgetConsumed) {
        this.budgetConsumed = budgetConsumed;
    }

    public BigDecimal getChargePrevision() {
        return chargePrevision;
    }

    public void setChargePrevision(BigDecimal chargePrevision) {
        this.chargePrevision = chargePrevision;
    }

    public BigDecimal getChargeConsumed() {
        return chargeConsumed;
    }

    public void setChargeConsumed(BigDecimal chargeConsumed) {
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

    public Set<Todo> getTodos() {
        return todos;
    }

    public Set<HumanResource> getHumanResources() {
        return humanResources;
    }

    public Set<CommunicationPlan> getCommunicationPlans() {
        return communicationPlans;
    }

    public Set<Writeup> getWriteups() {
        return writeups;
    }

    public Set<ReunionPlanning> getReunionPlannings() {
        return reunionPlannings;
    }
}
