package projMngmntSaaS.api.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import projMngmntSaaS.domain.entities.projectLevel.*;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Action;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.CommunicationPlan;
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
    private final CommunicationPlanRepository communicationPlanRepository;
    private final HumanResourceRepository humanResourceRepository;
    private final ReunionPlanningRepository reunionPlanningRepository;
    private final TodoRepository todoRepository;
    private final WriteupRepository writeupRepository;

    @Autowired
    public ProjectFullUpdateArchiver(ProjectRepository projectRepository, RiskRepository riskRepository,
                                     ActionRepository actionRepository, ResourceRepository resourceRepository,
                                     MilestoneRepository milestoneRepository, PendingIssueRepository pendingIssueRepository,
                                     DocumentRepository documentRepository, ChangeRequestRepository changeRequestRepository, CommunicationPlanRepository communicationPlanRepository, HumanResourceRepository humanResourceRepository, ReunionPlanningRepository reunionPlanningRepository, TodoRepository todoRepository, WriteupRepository writeupRepository) {
        this.projectRepository = projectRepository;
        this.riskRepository = riskRepository;
        this.actionRepository = actionRepository;
        this.resourceRepository = resourceRepository;
        this.milestoneRepository = milestoneRepository;
        this.pendingIssueRepository = pendingIssueRepository;
        this.documentRepository = documentRepository;
        this.changeRequestRepository = changeRequestRepository;
        this.communicationPlanRepository = communicationPlanRepository;
        this.humanResourceRepository = humanResourceRepository;
        this.reunionPlanningRepository = reunionPlanningRepository;
        this.todoRepository = todoRepository;
        this.writeupRepository = writeupRepository;
    }

    public static String getDescription() {
        return ARCHIVER_DESCRIPTION;
    }

    /**
     * Archives the supplied <code>project</code> state under a new update, appended to its archived updates.
     *
     * @param project       project to archive the state of
     * @param updateTime    persisted update time for the archived update
     */
    @Transactional
    public void archive(Project project, Date updateTime) {
        archiveProjectLevelUpdate(project, updateTime);
        for (SubProject subProject : project.getSubProjects()) {
            archiveProjectLevelUpdate(subProject, updateTime);
            for (ConstructionSite constructionSite : subProject.getConstructionSites()) {
                archiveProjectLevelUpdate(constructionSite, updateTime);
            }
        }
        for (ConstructionSite constructionSite : project.getConstructionSites()) {
            archiveProjectLevelUpdate(constructionSite, updateTime);
        }
        projectRepository.save(project);
    }

    private void archiveProjectLevelUpdate(ProjectLevel projectLevel, Date updateTime) {
        ProjectLevelUpdate update = new ProjectLevelUpdate(projectLevel, updateTime);

        // Artifacts archiving
        archiveArtifactCollection(update.getChangeRequests(), projectLevel.getChangeRequests(), changeRequestRepository);
        archiveArtifactCollection(update.getDocuments(), projectLevel.getDocuments(), documentRepository);
        archiveArtifactCollection(update.getMilestones(), projectLevel.getMilestones(), milestoneRepository);
        archiveArtifactCollection(update.getPendingIssues(), projectLevel.getPendingIssues(), pendingIssueRepository);
        archiveArtifactCollection(update.getRisks(), projectLevel.getRisks(), riskRepository);
        archiveArtifactCollection(update.getResources(), projectLevel.getResources(), resourceRepository);
        archiveArtifactCollection(update.getHumanResources(), projectLevel.getHumanResources(), humanResourceRepository);
        archiveArtifactCollection(update.getTodos(), projectLevel.getTodos(), todoRepository);
        archiveArtifactCollection(update.getCommunicationPlans(), projectLevel.getCommunicationPlans(), communicationPlanRepository);
        archiveArtifactCollection(update.getReunionPlannings(), projectLevel.getReunionPlannings(), reunionPlanningRepository);
        archiveArtifactCollection(update.getWriteups(), projectLevel.getWriteups(), writeupRepository);
        archiveArtifactCollection(update.getActions(), projectLevel.getActions(), actionRepository);

        // Setting each current artifact's dependency to its unarchived version

        // Action-resource dependency
        for (Action currentAction : projectLevel.getActions()) {
            for (Resource resource : projectLevel.getResources()) {
                if (currentAction.getSupervisor().getReference().equals(resource.getReference())) {
                    currentAction.setSupervisor(resource);
                    actionRepository.save(currentAction);
                    break;
                }
            }
        }
        // CommunicationPlan-resource dependency
        for (CommunicationPlan communicationPlan : projectLevel.getCommunicationPlans()) {
            for (Resource resource : projectLevel.getResources()) {
                if (communicationPlan.getSupervisor().getReference().equals(resource.getReference())) {
                    communicationPlan.setSupervisor(resource);
                    communicationPlanRepository.save(communicationPlan);
                    break;
                }
            }
        }

        // Archive update
        projectLevel.getArchivedUpdates().add(update);
    }

    private  <T extends ProjectLevelArtifact<T>> void archiveArtifactCollection(Set<T> updateArtifactCollection,
            Set<T> currentArtifactCollection, CrudRepository<T, UUID> artifactRepository) {
        // Temporary collection to avoid ConcurrentModificationException
        Set<T> artifactCollection = new HashSet<>(updateArtifactCollection);

        for (T currentArtifact : artifactCollection) {
            if (currentArtifact.getLastVersion() != null) {
                T originalRisk = currentArtifact.getLastVersion();
                if (currentArtifact.equals(originalRisk)) {
                    // Point update's artifact version to original entry if no nothing to update
                    updateArtifactCollection.remove(currentArtifact);
                    updateArtifactCollection.add(originalRisk);
                    continue;
                }
            }
            // Create a new current artifact instance & archive current artifact (by default)
            currentArtifactCollection.remove(currentArtifact);
            T newCurrentArtifact = currentArtifact.shallowClone();
            artifactRepository.save(newCurrentArtifact);
            newCurrentArtifact.setLastVersion(currentArtifact);
            currentArtifactCollection.add(newCurrentArtifact);
        }
    }
}
