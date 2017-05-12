package projMngmntSaaS.domain.entities.projectLevel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * A sub-project is a sub group of {@link Project} activities.
 */
@Entity
public class SubProject extends ConstructionSite
{
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ConstructionSite> constructionSites = new HashSet<>();

    public SubProject() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public Set<ConstructionSite> getConstructionSites() {
        return constructionSites;
    }
}
