package projMngmntSaaS.api.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import projMngmntSaaS.domain.entities.projectLevel.*;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Action;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.ProjectLevelArtifact;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Resource;
import projMngmntSaaS.repositories.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Provides a method to archive a {@link Project} state.
 */
@Component
public class ProjectFullUpdateArchiver
{
    // TODO: make use of the following description
    private static final String ARCHIVER_DESCRIPTION;

    static {
        ARCHIVER_DESCRIPTION = "Archives the current pending update (to archived updates resource, of URI: " +
                "./archived-updates, relatively to this pending update URI) and instantiates a new pending update " +
                "resource in its place.";
    }

    private final ProjectRepository projectRepository;
    private final RiskRepository riskRepository;
    private final ActionRepository actionRepository;
    private final ResourceRepository resourceRepository;
    private final MilestoneRepository milestoneRepository;
    private final PendingIssueRepository pendingIssueRepository;
    private final DocumentRepository documentRepository;
    private final ChangeRequestRepository changeRequestRepository;

    @Autowired
    public ProjectFullUpdateArchiver(ProjectRepository projectRepository, RiskRepository riskRepository,
                                     ActionRepository actionRepository, ResourceRepository resourceRepository,
                                     MilestoneRepository milestoneRepository, PendingIssueRepository pendingIssueRepository,
                                     DocumentRepository documentRepository, ChangeRequestRepository changeRequestRepository) {
        this.projectRepository = projectRepository;
        this.riskRepository = riskRepository;
        this.actionRepository = actionRepository;
        this.resourceRepository = resourceRepository;
        this.milestoneRepository = milestoneRepository;
        this.pendingIssueRepository = pendingIssueRepository;
        this.documentRepository = documentRepository;
        this.changeRequestRepository = changeRequestRepository;
    }

    public static String getDescription() {
        return ARCHIVER_DESCRIPTION;
    }

    /**
     * Archives the supplied <code>project</code> state.
     * It does this by appending the currently pending update of itself and each of its sub project levels, to their
     * respective archived updates set.
     * This is followed by resetting each of the currently pending update to a new instance.
     *
     * @param project       project to archive the state of
     * @param updateTime    persisted update time for the archived update
     */
    @Transactional
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
        ProjectLevelUpdate pendingUpdate = projectLevel.getPendingUpdate();
        pendingUpdate.setUpdateTime(updateTime);
        // Going to replace it with a new pending update instance
        ProjectLevelUpdate newPendingUpdate = new ProjectLevelUpdate(pendingUpdate);

        // Artifacts archiving
        archiveArtifactCollection(pendingUpdate.getChangeRequests(), newPendingUpdate.getChangeRequests(), changeRequestRepository);
        archiveArtifactCollection(pendingUpdate.getDocuments(), newPendingUpdate.getDocuments(), documentRepository);
        archiveArtifactCollection(pendingUpdate.getMilestones(), newPendingUpdate.getMilestones(), milestoneRepository);
        archiveArtifactCollection(pendingUpdate.getPendingIssues(), newPendingUpdate.getPendingIssues(), pendingIssueRepository);
        archiveArtifactCollection(pendingUpdate.getRisks(), newPendingUpdate.getRisks(), riskRepository);
        archiveArtifactCollection(pendingUpdate.getResources(), newPendingUpdate.getResources(), resourceRepository);
        archiveArtifactCollection(pendingUpdate.getActions(), newPendingUpdate.getActions(), actionRepository);

        // Setting each newPendingUpdate artifact's dependency to its unarchived (new pending) version
        // Action-resource dependency
        for (Action newPendingAction : newPendingUpdate.getActions()) {
            for (Resource resource : newPendingUpdate.getResources()) {
                if (newPendingAction.getSupervisor().getReference().equals(resource.getReference())) {
                    newPendingAction.setSupervisor(resource);
                    actionRepository.save(newPendingAction);
                    break;
                }
            }
        }

        // Archive current pending update & set up newPendingUpdate as the new current
        projectLevel.getArchivedUpdates().add(pendingUpdate);
        projectLevel.setPendingUpdate(newPendingUpdate);
    }

    private  <T extends ProjectLevelArtifact<T>> void archiveArtifactCollection(Set<T> pendingArtifactCollection,
                                                                                Set<T> newPendingArtifactCollection,
                                                                                CrudRepository<T, UUID> artifactRepository) {
        // Temporary collection to avoid ConcurrentModificationException
        Set<T> artifactCollection = new HashSet<>(pendingArtifactCollection);

        for (T pendingArtifact : artifactCollection) {
            if (pendingArtifact.getLastVersion() != null) {
                T originalRisk = pendingArtifact.getLastVersion();
                if (pendingArtifact.equals(originalRisk)) {
                    pendingArtifactCollection.remove(pendingArtifact);
                    pendingArtifactCollection.add(originalRisk);
                    continue;
                }
            }
            newPendingArtifactCollection.remove(pendingArtifact);
            T newPendingArtifact = pendingArtifact.shallowClone();
            artifactRepository.save(newPendingArtifact);
            newPendingArtifact.setLastVersion(pendingArtifact);
            newPendingArtifactCollection.add(newPendingArtifact);
        }
    }
}
