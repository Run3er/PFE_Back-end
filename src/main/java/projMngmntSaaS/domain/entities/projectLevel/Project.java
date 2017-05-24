package projMngmntSaaS.domain.entities.projectLevel;

import projMngmntSaaS.domain.entities.ProjectsEntity;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.ProjectArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.updates.ProjectUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A project is the main element for a project's management.
 * A project always belongs one to {@link projMngmntSaaS.domain.entities.ProjectsEntity}.
 */
@Entity
public class Project extends ProjectLevel
{
    private String mainContact;

    private String sponsors;

    private String finalClient;

    private String hypotheses_constraints;

    private String history_decisions;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // Exact field name ('entity') mappedBy ProjectsEntity association
    private ProjectsEntity entity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubProject> subProjects = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ConstructionSite> constructionSites = new HashSet<>();

    @Embedded
    private ProjectArchivableContent archivableContent = new ProjectArchivableContent();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectUpdate> archivedUpdates = new HashSet<>();

    public Project() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public String getMainContact() {
        return mainContact;
    }

    public void setMainContact(String mainContact) {
        this.mainContact = mainContact;
    }

    public String getSponsors() {
        return sponsors;
    }

    public void setSponsors(String sponsors) {
        this.sponsors = sponsors;
    }

    public String getFinalClient() {
        return finalClient;
    }

    public void setFinalClient(String finalClient) {
        this.finalClient = finalClient;
    }

    public String getHypotheses_constraints() {
        return hypotheses_constraints;
    }

    public void setHypotheses_constraints(String hypotheses_constraints) {
        this.hypotheses_constraints = hypotheses_constraints;
    }

    public String getHistory_decisions() {
        return history_decisions;
    }

    public void setHistory_decisions(String history_decisions) {
        this.history_decisions = history_decisions;
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

    public ProjectArchivableContent getArchivableContent() {
        return archivableContent;
    }

    public Set<ProjectUpdate> getArchivedUpdates() {
        return archivedUpdates;
    }
}
