package projMngmntSaaS.api.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projMngmntSaaS.domain.entities.projectLevel.*;
import projMngmntSaaS.repositories.ProjectRepository;

import java.util.Date;

/**
 * Provides a method to archive a {@link Project} state.
 */
@Component
public class ProjectFullUpdateArchiver
{
    private static final String ARCHIVER_DESCRIPTION;

    static {
        ARCHIVER_DESCRIPTION = "Archives the current pending update (to archived updates resource, of URI: " +
                "./archived-updates, relatively to this pending update URI) and instantiates a new pending update " +
                "resource in its place.";
    }

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectFullUpdateArchiver(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public static String getDescription() {
        return ARCHIVER_DESCRIPTION;
    }

    /**
     * Archives the supplied <code>project</code> state.
     * It does this by appending the currently pending update of itself and each of its sub project levels, to their
     * respective archived updates set.
     * This is followed by resetting each of the currently pending update to a new instance.
     * @param project       project to archive the state for
     * @param updateTime    persisted update time for the archived update
     */
    public void archive(Project project, Date updateTime) {
        archiveProjectLevelUpdate(project, updateTime);
        for (SubProject subProject : project.getSubProjects()) {
            archiveProjectLevelUpdate(subProject, updateTime);
            for (ConstructionSite constructionSite : subProject.getSites()) {
                archiveProjectLevelUpdate(constructionSite, updateTime);
            }
        }
        projectRepository.save(project);
    }

    private void archiveProjectLevelUpdate(ProjectLevel projectLevel, Date updateTime) {
        // Archive current pending update
        ProjectLevelUpdate pendingUpdate = projectLevel.getPendingUpdate();
        pendingUpdate.setUpdateTime(updateTime);
        projectLevel.getArchivedUpdates().add(pendingUpdate);
        // Replace it with a new pending update instance
        ProjectLevelUpdate newPendingUpdate = new ProjectLevelUpdate(pendingUpdate);
        projectLevel.setPendingUpdate(newPendingUpdate);
    }
}
