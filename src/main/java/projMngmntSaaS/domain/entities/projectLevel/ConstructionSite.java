package projMngmntSaaS.domain.entities.projectLevel;

import javax.persistence.Entity;

/**
 * A construction site is a sub group of {@link SubProject} activities.
 */
@Entity
public class ConstructionSite extends ProjectLevel
{
    public ConstructionSite() {
        // no-arg constructor for ORM (due to reflection use)
    }
}
