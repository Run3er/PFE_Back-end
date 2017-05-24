package projMngmntSaaS.domain.entities.projectLevel.archivableContents;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

/**
 * This is an aggregation of a {@link projMngmntSaaS.domain.entities.projectLevel.ConstructionSite} archivable content.
 */
@MappedSuperclass
@Embeddable
public class ConstructionSiteArchivableContent extends ProjectLevelArchivableContent
{
    public ConstructionSiteArchivableContent() {
        // explicit, public, no-arg constructor
    }

    public ConstructionSiteArchivableContent(ConstructionSiteArchivableContent constructionSiteContents) {
        super(constructionSiteContents);
    }
}
