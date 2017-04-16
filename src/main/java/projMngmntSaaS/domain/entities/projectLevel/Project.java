package projMngmntSaaS.domain.entities.projectLevel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * A project is the main element for a project's management.
 * A project always belongs one to {@link projMngmntSaaS.domain.entities.ProjectsEntity}.
 */
@Entity
public class Project extends ProjectLevel
{
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubProject> subProjects;

    public Project() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public Set<SubProject> getSubProjects() {
        return subProjects;
    }

    public void setSubProjects(Set<SubProject> subProjects) {
        this.subProjects = subProjects;
    }
}
