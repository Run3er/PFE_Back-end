package projMngmntSaaS.api.controllers.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import projMngmntSaaS.domain.entities.projectLevel.ConstructionSite;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.ConstructionSiteArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.ProjectArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.SubProjectArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.UUID;


/**
 * Deletes sub-resources completely, by removing them from their parent resource first.
 */
@Component
@Scope("prototype")
public class ResourceDeleter
{
    private static final String URI_NESTED_RESRC_INVALID_MSG = "URI incorrect: Nested resource invalid.";
    private static final String URI_RESRC_INVALID_MSG = "URI incorrect: Hierarchy head resource invalid.";

    @PersistenceContext
    private EntityManager entityManager;

    private String subResourcePath;
    private UUID subResrcId;


    @Transactional
    public void delete(String parentResourcePath, UUID parentResrcId, String subResourcePath, UUID subResrcId)
            throws IllegalArgumentException {
        this.subResourcePath = subResourcePath;
        this.subResrcId = subResrcId;

        switch (parentResourcePath) {
            case "projects":
                Project project = entityManager.find(Project.class, parentResrcId);
                if (project != null) {
                    if ("subProjects".equals(subResourcePath)) {
                        SubProject subProject = entityManager.find(SubProject.class, subResrcId);
                        deleteResource(SubProject.class, project.getSubProjects(), subProject);
                    }
                    else deleteProjectArtifact(project);
                    break;
                }
            case "subProjects":
                SubProject subProject = entityManager.find(SubProject.class, parentResrcId);
                if (subProject != null) {
                    if ("constructionSites".equals(subResourcePath)) {
                        ConstructionSite constructionSite = entityManager.find(ConstructionSite.class, subResrcId);
                        deleteResource(subProject, subProject.getConstructionSites(), constructionSite);
                    }
                    else deleteSubProjectArtifact(subProject);
                    break;
                }
            case "constructionSites":
                ConstructionSite constructionSite = entityManager.find(ConstructionSite.class, parentResrcId);
                if (constructionSite != null) {
                    deleteConstructionSiteArtifact(constructionSite);
                }
                break;
            default:
                throw new IllegalArgumentException(URI_RESRC_INVALID_MSG);
        }
    }

    private void deleteProjectArtifact(ProjectArchivableContent parentResrc) {
        switch (subResourcePath) {
            case "reunionPlannings":
                ReunionPlanning reunionPlanning = entityManager.find(ReunionPlanning.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getReunionPlannings(), reunionPlanning);
                return;
            case "communicationPlans":
                CommunicationPlan communicationPlan = entityManager.find(CommunicationPlan.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getCommunicationPlans(), communicationPlan);
                return;
            case "writeups":
                Writeup writeup = entityManager.find(Writeup.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getWriteups(), writeup);
                return;
        }
        // case default must be handled by this method
        deleteSubProjectArtifact(parentResrc);
    }

    private void deleteSubProjectArtifact(SubProjectArchivableContent parentResrc) {
        // case default must be handled by this method
        deleteConstructionSiteArtifact(parentResrc);
    }

    private void deleteConstructionSiteArtifact(ConstructionSiteArchivableContent parentResrc) {
        switch (subResourcePath) {
            case "actions":
                Action action = entityManager.find(Action.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getActions(), action);
                return;
            case "risks":
                Risk risk = entityManager.find(Risk.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getRisks(), risk);
                return;
            case "changeRequests":
                ChangeRequest changeRequest = entityManager.find(ChangeRequest.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getChangeRequests(), changeRequest);
                return;
            case "pendingIssues":
                PendingIssue pendingIssue = entityManager.find(PendingIssue.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getPendingIssues(), pendingIssue);
                return;
            case "resources":
                Resource resource = entityManager.find(Resource.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getResources(), resource);
                return;
            case "documents":
                Document document = entityManager.find(Document.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getDocuments(), document);
                return;
            case "milestones":
                Milestone milestone = entityManager.find(Milestone.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getMilestones(), milestone);
                return;
            case "todos":
                Todo todo = entityManager.find(Todo.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getTodos(), todo);
                return;
            case "humanResources":
                HumanResource humanResource = entityManager.find(HumanResource.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getHumanResources(), humanResource);
                return;
            default:
                throw new IllegalArgumentException(URI_NESTED_RESRC_INVALID_MSG);
        }
    }

    private <T, U> void deleteResource(T parentResource, Collection<U> removableResrcsCollection, U subResrc) {
        if (subResrc == null) {
            throw new IllegalArgumentException(URI_NESTED_RESRC_INVALID_MSG);
        }
        removableResrcsCollection.remove(subResrc);
        entityManager.merge(parentResource);
        entityManager.remove(subResrc);
    }
}