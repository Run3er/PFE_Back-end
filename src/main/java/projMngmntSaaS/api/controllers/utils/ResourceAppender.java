package projMngmntSaaS.api.controllers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import projMngmntSaaS.domain.entities.ProjectsEntity;
import projMngmntSaaS.domain.entities.projectLevel.ConstructionSite;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.domain.entities.projectLevel.ProjectLevel;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.*;
import projMngmntSaaS.domain.utils.UuidIdentifiable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Appends sub-resources to their parent parent resource in batch.
 */
@Component
public class ResourceAppender
{
    private static final String URI_NESTED_RESRC_INVALID_MSG = "URI incorrect: Nested resource invalid.";
    private static final String URI_RESRC_INVALID_MSG = "URI incorrect: Hierarchy head resource invalid.";

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public List<UUID> append(String parentResourcePath, UUID parentResrcId, String subResourcePath,
                                 Collection<?> subResources) throws IllegalArgumentException {
        List<UUID> appendedResourcesIDs = new ArrayList<>();

        switch (parentResourcePath) {
            case "entities":
                ProjectsEntity entity = entityManager.find(ProjectsEntity.class, parentResrcId);
                if (entity == null) {
                    throw new IllegalArgumentException(URI_RESRC_INVALID_MSG);
                }

                if ("projects".equals(subResourcePath)) {
                    appendResources(ProjectsEntity.class, Project.class, entity.getProjects(),
                            subResources, appendedResourcesIDs);
                }
                else {
                    throw new IllegalArgumentException(URI_NESTED_RESRC_INVALID_MSG);
                }
                break;
            case "projects":
                Project project = entityManager.find(Project.class, parentResrcId);
                if (project == null) {
                    throw new IllegalArgumentException(URI_RESRC_INVALID_MSG);
                }

                if ("subProjects".equals(subResourcePath)) {
                    appendResources(project, SubProject.class, project.getSubProjects(),
                            subResources, appendedResourcesIDs);
                }
                else appendArtifacts(project, subResourcePath, subResources, appendedResourcesIDs);
                break;
            case "subProjects":
                SubProject subProject = entityManager.find(SubProject.class, parentResrcId);
                if (subProject == null) {
                    throw new IllegalArgumentException(URI_RESRC_INVALID_MSG);
                }

                if ("constructionSites".equals(subResourcePath)) {
                    appendResources(subProject, ConstructionSite.class, subProject.getConstructionSites(),
                                subResources, appendedResourcesIDs);
                }
                else appendArtifacts(subProject, subResourcePath, subResources, appendedResourcesIDs);
                break;
            case "constructionSites":
                ConstructionSite constructionSite = entityManager.find(ConstructionSite.class, parentResrcId);
                if (constructionSite == null) {
                    throw new IllegalArgumentException(URI_RESRC_INVALID_MSG);
                }

                appendArtifacts(constructionSite, subResourcePath, subResources, appendedResourcesIDs);
                break;
            default:
                throw new IllegalArgumentException(URI_RESRC_INVALID_MSG);
        }
        return appendedResourcesIDs;
    }

    private void appendArtifacts(ProjectLevel parentResrc, String subResourcePath,
                                 Collection<?> subResources, Collection<UUID> appendedResourcesIDs) {
        switch (subResourcePath) {
            case "actions":
                appendResources(parentResrc, Action.class, parentResrc.getActions(),
                        subResources, appendedResourcesIDs);
                break;
            case "risks":
                appendResources(parentResrc, Risk.class, parentResrc.getRisks(),
                        subResources, appendedResourcesIDs);
                break;
            case "changeRequests":
                appendResources(parentResrc, ChangeRequest.class, parentResrc.getChangeRequests(),
                        subResources, appendedResourcesIDs);
                break;
            case "pendingIssues":
                appendResources(parentResrc, PendingIssue.class, parentResrc.getPendingIssues(),
                        subResources, appendedResourcesIDs);
                break;
            case "resources":
                appendResources(parentResrc, Resource.class, parentResrc.getResources(),
                        subResources, appendedResourcesIDs);
                break;
            case "documents":
                appendResources(parentResrc, Document.class, parentResrc.getDocuments(),
                        subResources, appendedResourcesIDs);
                break;
            case "milestones":
                appendResources(parentResrc, Milestone.class, parentResrc.getMilestones(),
                        subResources, appendedResourcesIDs);
                break;
            default:
                throw new IllegalArgumentException(URI_NESTED_RESRC_INVALID_MSG);
        }
    }

    private <T, U extends UuidIdentifiable> void appendResources(T parentResource, Class<U> subResourcesType,
             Collection<U> appendableCollection, Collection<?> subResources, Collection<UUID> appendedResourcesIDs) {
        ObjectMapper mapper = new ObjectMapper();
        for (Object subResource : subResources) {
            U concreteSubResource = null;
            try {
                concreteSubResource = mapper.convertValue(subResource, subResourcesType);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            entityManager.persist(concreteSubResource);
            appendableCollection.add(concreteSubResource);
            appendedResourcesIDs.add(concreteSubResource.getId());
        }
        entityManager.merge(parentResource);
    }
}