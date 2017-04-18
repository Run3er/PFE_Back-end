package projMngmntSaaS.domain.entities.projectLevel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * A sub-project is a sub group of {@link Project} activities.
 */
@Entity
public class SubProject extends ProjectLevel
{
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ConstructionSite> sites = new HashSet<>();

    public SubProject() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public Set<ConstructionSite> getSites() {
        return sites;
    }
}
