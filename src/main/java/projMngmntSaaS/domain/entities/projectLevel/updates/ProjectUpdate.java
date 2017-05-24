package projMngmntSaaS.domain.entities.projectLevel.updates;

import projMngmntSaaS.domain.entities.projectLevel.archivableContents.ProjectArchivableContent;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * TODO
 * This is an aggregation of a project level properties for a specific update.
 * Note that the last update time for the most recent contents of a project level is temporarily referring to the update
 * time of the previous one. This is useful to avoid querying the previous update time from the DB when needing the info.
 */
@Entity
public class ProjectUpdate extends ProjectArchivableContent
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Column(nullable = false)
    private Date updateTime;

    public ProjectUpdate() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public ProjectUpdate(ProjectArchivableContent projectContents, Date updateTime) {
        super(projectContents);
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
