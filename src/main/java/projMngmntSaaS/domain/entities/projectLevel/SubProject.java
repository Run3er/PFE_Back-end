package projMngmntSaaS.domain.entities.projectLevel;

import projMngmntSaaS.domain.entities.projectLevel.archivableContents.SubProjectArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.updates.SubProjectUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * A sub-project is a sub group of {@link Project} activities.
 */
@Entity
public class SubProject extends ProjectLevel
{
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ConstructionSite> constructionSites = new HashSet<>();

    @Embedded
    private SubProjectArchivableContent archivableContent = new SubProjectArchivableContent();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubProjectUpdate> archivedUpdates = new HashSet<>();

    public SubProject() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public Set<ConstructionSite> getConstructionSites() {
        return constructionSites;
    }

    public SubProjectArchivableContent getArchivableContent() {
        return archivableContent;
    }

    public Set<SubProjectUpdate> getArchivedUpdates() {
        return archivedUpdates;
    }
}
