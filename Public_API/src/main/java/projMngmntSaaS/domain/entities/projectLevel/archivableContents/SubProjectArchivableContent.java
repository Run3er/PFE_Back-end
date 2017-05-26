package projMngmntSaaS.domain.entities.projectLevel.archivableContents;

import projMngmntSaaS.domain.entities.enums.ProjectLevelStatus;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * This is an aggregation of a {@link projMngmntSaaS.domain.entities.projectLevel.SubProject} archivable content.
 */
@MappedSuperclass
public class SubProjectArchivableContent extends ConstructionSiteArchivableContent
{
    @Column(nullable = false)
    protected ProjectLevelStatus status = ProjectLevelStatus.GREEN;

    public SubProjectArchivableContent() {
        // explicit, public, no-arg constructor
    }

    public SubProjectArchivableContent(SubProjectArchivableContent subProjectContents) {
        super(subProjectContents);
        this.status = subProjectContents.status;
    }

    public ProjectLevelStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectLevelStatus status) {
        this.status = status;
    }
}
