package projMngmntSaaS.domain.entities;

import projMngmntSaaS.domain.entities.projectLevel.Project;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Entity for classifying a set of {@link Project}. Any {@link Project} belongs to one entity.
 */
@Entity
public class ProjectsEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Project> projects = new HashSet<>();

    public ProjectsEntity() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
