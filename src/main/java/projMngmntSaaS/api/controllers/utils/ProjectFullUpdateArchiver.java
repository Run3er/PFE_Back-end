package projMngmntSaaS.api.controllers.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import projMngmntSaaS.domain.entities.projectLevel.ConstructionSite;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.ConstructionSiteArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.ProjectArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.ProjectLevelArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.SubProjectArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Action;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.CommunicationPlan;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.ProjectLevelArtifact;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Resource;
import projMngmntSaaS.domain.entities.projectLevel.updates.ConstructionSiteUpdate;
import projMngmntSaaS.domain.entities.projectLevel.updates.ProjectUpdate;
import projMngmntSaaS.domain.entities.projectLevel.updates.SubProjectUpdate;
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

    private void archiveProjectLevelUpdate(ProjectLevelArchivableContent projectLevel, Date updateTime) {
        if (projectLevel instanceof Project) {
            Project project = (Project) projectLevel;
            ProjectUpdate update = new ProjectUpdate(project, updateTime);
            archiveProjectUpdate(project, update);
            project.getArchivedUpdates().add(update);
        }
        else if (projectLevel instanceof SubProject) {
            SubProject subProject = (SubProject) projectLevel;
            SubProjectUpdate update = new SubProjectUpdate(subProject, updateTime);
            archiveSubProjectUpdate(subProject, update);
            subProject.getArchivedUpdates().add(update);
        }
        else if (projectLevel instanceof ConstructionSite) {
            ConstructionSite constructionSite = (ConstructionSite) projectLevel;
            ConstructionSiteUpdate update = new ConstructionSiteUpdate(constructionSite, updateTime);
            archiveConstructionSiteUpdate(constructionSite, update);
            constructionSite.getArchivedUpdates().add(update);
        }
    }

    private void archiveProjectUpdate(ProjectArchivableContent projectContents, ProjectArchivableContent update) {
        archiveSubProjectUpdate(projectContents, update);
        
        // Artifacts archiving
        archiveArtifactCollection(update.getCommunicationPlans(), projectContents.getCommunicationPlans(), communicationPlanRepository);
        archiveArtifactCollection(update.getReunionPlannings(), projectContents.getReunionPlannings(), reunionPlanningRepository);
        archiveArtifactCollection(update.getWriteups(), projectContents.getWriteups(), writeupRepository);

        // Setting each current artifact's dependency to its unarchived version
        // CommunicationPlan-resource dependency
        for (CommunicationPlan communicationPlan : projectContents.getCommunicationPlans()) {
            for (Resource resource : projectContents.getResources()) {
                if (communicationPlan.getSupervisor().getReference().equals(resource.getReference())) {
                    communicationPlan.setSupervisor(resource);
                    communicationPlanRepository.save(communicationPlan);
                    break;
                }
            }
        }
    }

    private void archiveSubProjectUpdate(SubProjectArchivableContent subProjectContents, SubProjectArchivableContent update) {
        archiveConstructionSiteUpdate(subProjectContents, update);
    }

    private void archiveConstructionSiteUpdate(ConstructionSiteArchivableContent constructionSiteContents, ConstructionSiteArchivableContent update) {

        // Artifacts archiving
        archiveArtifactCollection(update.getChangeRequests(), constructionSiteContents.getChangeRequests(), changeRequestRepository);
        archiveArtifactCollection(update.getDocuments(), constructionSiteContents.getDocuments(), documentRepository);
        archiveArtifactCollection(update.getMilestones(), constructionSiteContents.getMilestones(), milestoneRepository);
        archiveArtifactCollection(update.getPendingIssues(), constructionSiteContents.getPendingIssues(), pendingIssueRepository);
        archiveArtifactCollection(update.getRisks(), constructionSiteContents.getRisks(), riskRepository);
        archiveArtifactCollection(update.getResources(), constructionSiteContents.getResources(), resourceRepository);
        archiveArtifactCollection(update.getHumanResources(), constructionSiteContents.getHumanResources(), humanResourceRepository);
        archiveArtifactCollection(update.getTodos(), constructionSiteContents.getTodos(), todoRepository);
        archiveArtifactCollection(update.getActions(), constructionSiteContents.getActions(), actionRepository);

        // Setting each current artifact's dependency to its unarchived version
        // Action-resource dependency
        for (Action currentAction : constructionSiteContents.getActions()) {
            for (Resource resource : constructionSiteContents.getResources()) {
                if (currentAction.getSupervisor().getReference().equals(resource.getReference())) {
                    currentAction.setSupervisor(resource);
                    actionRepository.save(currentAction);
                    break;
                }
            }
        }
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
