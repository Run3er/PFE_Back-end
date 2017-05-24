package projMngmntSaaS.domain.entities.projectLevel;

import projMngmntSaaS.domain.entities.projectLevel.archivableContents.ConstructionSiteArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.updates.ConstructionSiteUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * A construction site is a sub group of {@link SubProject} activities.
 */
@Entity
public class ConstructionSite extends ProjectLevel
{
    @Embedded
    protected ConstructionSiteArchivableContent archivableContent = new ConstructionSiteArchivableContent();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<ConstructionSiteUpdate> archivedUpdates = new HashSet<>();

    public ConstructionSite() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public ConstructionSiteArchivableContent getArchivableContent() {
        return archivableContent;
    }

    public Set<ConstructionSiteUpdate> getArchivedUpdates() {
        return archivedUpdates;
    }
}
