package projMngmntSaaS.domain.entities.projectLevel.archivableContents;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * This is an aggregation of any {@link projMngmntSaaS.domain.entities.projectLevel.ProjectLevel} archivable content.
 */
@MappedSuperclass
@Embeddable
public abstract class ProjectLevelArchivableContent
{
    protected BigDecimal budgetToConsume = new BigDecimal(0);

    protected BigDecimal budgetConsumed = new BigDecimal(0);

    @Column(precision = 10)
    protected BigDecimal chargeConsumed = new BigDecimal(0);

    @Column(nullable = false, precision = 3)
    protected int advancement = 0;

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

    public ProjectLevelArchivableContent() {
        // explicit, public, no-arg constructor (making it available for derived classes)
    }

    public ProjectLevelArchivableContent(ProjectLevelArchivableContent projectLevelArchivableContent) {
        this.budgetToConsume = projectLevelArchivableContent.budgetToConsume;
        this.budgetConsumed = projectLevelArchivableContent.budgetConsumed;
        this.chargeConsumed = projectLevelArchivableContent.chargeConsumed;
        this.advancement = projectLevelArchivableContent.advancement;
        this.actions = new HashSet<>(projectLevelArchivableContent.actions);
        this.risks = new HashSet<>(projectLevelArchivableContent.risks);
        this.pendingIssues = new HashSet<>(projectLevelArchivableContent.pendingIssues);
        this.changeRequests = new HashSet<>(projectLevelArchivableContent.changeRequests);
        this.resources = new HashSet<>(projectLevelArchivableContent.resources);
        this.documents = new HashSet<>(projectLevelArchivableContent.documents);
        this.milestones = new HashSet<>(projectLevelArchivableContent.milestones);
        this.todos = new HashSet<>(projectLevelArchivableContent.todos);
        this.humanResources = new HashSet<>(projectLevelArchivableContent.humanResources);
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

    public BigDecimal getChargeConsumed() {
        return chargeConsumed;
    }

    public void setChargeConsumed(BigDecimal chargeConsumed) {
        this.chargeConsumed = chargeConsumed;
    }

    public int getAdvancement() {
        return advancement;
    }

    public void setAdvancement(int advancement) {
        if (advancement > 100) {
            throw new IllegalArgumentException("Advancement can't be higher than 100 %");
        }
        this.advancement = advancement;
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
}
