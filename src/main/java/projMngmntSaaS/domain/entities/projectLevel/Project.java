package projMngmntSaaS.domain.entities.projectLevel;

import projMngmntSaaS.domain.entities.ProjectsEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * A project is the main element for a project's management.
 * A project always belongs one to {@link projMngmntSaaS.domain.entities.ProjectsEntity}.
 */
@Entity
public class Project extends ProjectLevel
{
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // Exact field name ('entity') mappedBy ProjectsEntity association
    private ProjectsEntity entity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubProject> subProjects = new HashSet<>();

    public Project() {
        // no-arg constructor for ORM (due to reflection use)
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
}
