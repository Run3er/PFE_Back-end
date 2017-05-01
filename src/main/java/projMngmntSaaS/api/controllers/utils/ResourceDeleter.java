package projMngmntSaaS.api.controllers.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import projMngmntSaaS.domain.entities.ProjectsEntity;
import projMngmntSaaS.domain.entities.projectLevel.ConstructionSite;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.domain.entities.projectLevel.ProjectLevel;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;
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
            case "entities":
                ProjectsEntity entity = entityManager.find(ProjectsEntity.class, parentResrcId);
                if (entity != null) {
                    if ("projects".equals(subResourcePath)) {
                        Project project = entityManager.find(Project.class, subResrcId);
                        deleteResource(ProjectsEntity.class, entity.getProjects(), project);
                    }
                    else {
                        throw new IllegalArgumentException(URI_NESTED_RESRC_INVALID_MSG);
                    }
                    break;
                }
            case "projects":
                Project project = entityManager.find(Project.class, parentResrcId);
                if (project != null) {
                    if ("subProjects".equals(subResourcePath)) {
                        SubProject subProject = entityManager.find(SubProject.class, subResrcId);
                        deleteResource(SubProject.class, project.getSubProjects(), subProject);
                    }
                    else deleteArtifact(project);
                    break;
                }
            case "subProjects":
                SubProject subProject = entityManager.find(SubProject.class, parentResrcId);
                if (subProject != null) {
                    if ("constructionSites".equals(subResourcePath)) {
                        ConstructionSite constructionSite = entityManager.find(ConstructionSite.class, subResrcId);
                        deleteResource(subProject, subProject.getConstructionSites(), constructionSite);
                    }
                    else deleteArtifact(subProject);
                    break;
                }
            case "constructionSites":
                ConstructionSite constructionSite = entityManager.find(ConstructionSite.class, parentResrcId);
                if (constructionSite != null) {
                    deleteArtifact(constructionSite);
                }
                break;
            default:
                throw new IllegalArgumentException(URI_RESRC_INVALID_MSG);
        }
    }

    private void deleteArtifact(ProjectLevel parentResrc) {
        switch (subResourcePath) {
            case "actions":
                Action action = entityManager.find(Action.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getActions(), action);
                break;
            case "risks":
                Risk risk = entityManager.find(Risk.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getRisks(), risk);
                break;
            case "changeRequests":
                ChangeRequest changeRequest = entityManager.find(ChangeRequest.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getChangeRequests(), changeRequest);
                break;
            case "pendingIssues":
                PendingIssue pendingIssue = entityManager.find(PendingIssue.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getPendingIssues(), pendingIssue);
                break;
            case "resources":
                Resource resource = entityManager.find(Resource.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getResources(), resource);
                break;
            case "documents":
                Document document = entityManager.find(Document.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getDocuments(), document);
                break;
            case "milestones":
                Milestone milestone = entityManager.find(Milestone.class, subResrcId);
                deleteResource(parentResrc, parentResrc.getMilestones(), milestone);
                break;
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