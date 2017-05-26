package projMngmntSaaS.domain.entities.projectLevel.archivableContents;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.*;
import projMngmntSaaS.domain.entities.projectLevel.fixedDetails.ProjectLevelDetails;
import projMngmntSaaS.domain.utils.UuidIdentifiable;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * This is an aggregation of any {@link ProjectLevelDetails} archivable content.
 */
@MappedSuperclass
public abstract class ProjectLevelArchivableContent implements UuidIdentifiable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    protected BigInteger budgetToConsume = BigInteger.ZERO;

    protected BigInteger budgetConsumed = BigInteger.ZERO;

    @Column(precision = 10)
    protected BigInteger chargeConsumed = BigInteger.ZERO;

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

    public UUID getId() {
        return id;
    }

    public BigInteger getBudgetToConsume() {
        return budgetToConsume;
    }

    public void setBudgetToConsume(BigInteger budgetToConsume) {
        this.budgetToConsume = budgetToConsume;
    }

    public BigInteger getBudgetConsumed() {
        return budgetConsumed;
    }

    public void setBudgetConsumed(BigInteger budgetConsumed) {
        this.budgetConsumed = budgetConsumed;
    }

    public BigInteger getChargeConsumed() {
        return chargeConsumed;
    }

    public void setChargeConsumed(BigInteger chargeConsumed) {
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
