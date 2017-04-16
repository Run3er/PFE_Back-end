package projMngmntSaaS.domain.entities.projectLevel;


import projMngmntSaaS.domain.entities.enums.ProjectLevelStatus;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * This is an aggregation of a project level properties for a specific update.
 * Note that the last update time for the most recent contents of a project level is temporarily referring to the update
 * time of the previous one. This is useful to avoid querying the previous update time from the DB when needing the info.
 */
@Entity
public class ProjectLevelUpdate
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Date updateTime;

    private BigDecimal budgetTotal;

    private BigDecimal budgetPlanned;

    private BigDecimal budgetConsumed;

    private int chargeConsumed;

    @Column(nullable = false, precision = 3)
    private float advancement;

    @Column(nullable = false)
    private ProjectLevelStatus status;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Action> actions = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Risk> risks = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<PendingIssue> pendingIssues = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<ChangeRequest> changeRequests = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Resource> resources = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Document> documents = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Milestone> milestones = new HashSet<>();

    public ProjectLevelUpdate() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public ProjectLevelUpdate(ProjectLevelUpdate projectLevelUpdate) {
        this.updateTime = projectLevelUpdate.updateTime;
        this.budgetTotal = projectLevelUpdate.budgetTotal;
        this.budgetPlanned = projectLevelUpdate.budgetPlanned;
        this.budgetConsumed = projectLevelUpdate.budgetConsumed;
        this.chargeConsumed = projectLevelUpdate.chargeConsumed;
        this.advancement = projectLevelUpdate.advancement;
        this.status = projectLevelUpdate.status;
        this.actions = new HashSet<>(projectLevelUpdate.actions);
        this.risks = new HashSet<>(projectLevelUpdate.risks);
        this.pendingIssues = new HashSet<>(projectLevelUpdate.pendingIssues);
        this.changeRequests = new HashSet<>(projectLevelUpdate.changeRequests);
        this.resources = new HashSet<>(projectLevelUpdate.resources);
        this.documents = new HashSet<>(projectLevelUpdate.documents);
        this.milestones = new HashSet<>(projectLevelUpdate.milestones);
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
