package projMngmntSaaS.domain.entities.projectLevel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * A sub-project is a sub group of {@link Project} activities.
 */
@Entity
public class SubProject extends ProjectLevel
{
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ConstructionSite> sites;

    public SubProject() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public Set<ConstructionSite> getSites() {
        return sites;
    }

    public void setSites(Set<ConstructionSite> sites) {
        this.sites = sites;
    }
}
