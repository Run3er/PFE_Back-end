package projMngmntSaaS.domain.entities.projectLevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * This is an aggregation of a project level properties for a specific update.
 * Note that the last update time for the most recent contents of a project level is temporarily referring to the update
 * time of the previous one. This is useful to avoid querying the previous update time from the DB when needing the info.
 */
@Entity
public class ProjectLevelUpdate extends ProjectLevelContents
{
    @Column(nullable = false)
    private Date updateTime;

    public ProjectLevelUpdate() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public ProjectLevelUpdate(ProjectLevelContents projectLevelContents, Date updateTime) {
        super(projectLevelContents);
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
