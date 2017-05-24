package projMngmntSaaS.domain.entities.projectLevel.archivableContents;

import projMngmntSaaS.domain.entities.projectLevel.artifacts.CommunicationPlan;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.ReunionPlanning;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Writeup;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import java.util.HashSet;
import java.util.Set;

/**
 * This is an aggregation of {@link projMngmntSaaS.domain.entities.projectLevel.Project} archivable content.
 */
@MappedSuperclass
@Embeddable
public class ProjectArchivableContent extends SubProjectArchivableContent
{
    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<CommunicationPlan> communicationPlans = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<Writeup> writeups = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    protected Set<ReunionPlanning> reunionPlannings = new HashSet<>();

    public ProjectArchivableContent() {
        // explicit, public, no-arg constructor
    }

    public ProjectArchivableContent(ProjectArchivableContent projectContents) {
        super(projectContents);
        this.communicationPlans = new HashSet<>(projectContents.communicationPlans);
        this.writeups = new HashSet<>(projectContents.writeups);
        this.reunionPlannings = new HashSet<>(projectContents.reunionPlannings);
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
