package projMngmntSaaS.domain.entities.projectLevel;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * This provides common project level properties.
 */
@MappedSuperclass
public class ProjectLevel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private ProjectLevelUpdate currentUpdate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectLevelUpdate> updates;

    public ProjectLevel() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProjectLevelUpdate> getUpdates() {
        return updates;
    }

    public void setUpdates(Set<ProjectLevelUpdate> updates) {
        this.updates = updates;
    }

    public ProjectLevelUpdate getCurrentUpdate() {
        return currentUpdate;
    }

    public void setCurrentUpdate(ProjectLevelUpdate currentUpdate) {
        this.currentUpdate = currentUpdate;
    }
}
