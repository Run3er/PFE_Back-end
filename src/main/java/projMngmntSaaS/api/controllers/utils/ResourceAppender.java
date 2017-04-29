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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.UUID;

/**
 * Appends sub-resources to their parent parent resource in batch.
 */
@Component
public class ResourceAppender
{
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Transactional
    public boolean append(String parentResourcePath, UUID parentResrcId, String subResourcePath,
                                 Collection<?> subResources) {
        switch (parentResourcePath) {
            case "entities":
                ProjectsEntity entity = entityManager.find(ProjectsEntity.class, parentResrcId);
                if (entity != null) {
                    if ("projects".equals(subResourcePath)) {
                        return appendResources(ProjectsEntity.class, Project.class, entity.getProjects(), subResources);
                    }
                    else return false;
                }
            case "projects":
                Project project = entityManager.find(Project.class, parentResrcId);
                if (project != null) {
                    if ("subProjects".equals(subResourcePath)) {
                        return appendResources(Project.class, SubProject.class, project.getSubProjects(), subResources);
                    }
                    else return appendArtifacts(subResourcePath, project, subResources);
                }
            case "subProjects":
                SubProject subProject = entityManager.find(SubProject.class, parentResrcId);
                if (subProject != null) {
                    if ("constructionSites".equals(subResourcePath)) {
                        return appendResources(subProject, ConstructionSite.class, subProject.getConstructionSites(), subResources);
                    }
                    else return appendArtifacts(subResourcePath, subProject, subResources);
                }
            case "constructionSites":
                ConstructionSite constructionSite = entityManager.find(ConstructionSite.class, parentResrcId);
                if (constructionSite != null) {
                    return appendArtifacts(subResourcePath, constructionSite, subResources);
                }
        }
        // Unless parentResourceId incorrect, should never even be reached (since resource paths should be valid)
        return false;
    }

    private boolean appendArtifacts(String subResourcePath, ProjectLevel parentResrc, Collection<?> subResources) {
        switch (subResourcePath) {
            case "actions":
                return appendResources(parentResrc, Action.class, parentResrc.getActions(), subResources);
            case "risks":
                return appendResources(parentResrc, Risk.class, parentResrc.getRisks(), subResources);
            case "changeRequests":
                return appendResources(parentResrc, ChangeRequest.class, parentResrc.getChangeRequests(), subResources);
            case "pendingIssues":
                return appendResources(parentResrc, PendingIssue.class, parentResrc.getPendingIssues(), subResources);
            case "resources":
                return appendResources(parentResrc, Resource.class, parentResrc.getResources(), subResources);
            case "documents":
                return appendResources(parentResrc, Document.class, parentResrc.getDocuments(), subResources);
            case "milestones":
                return appendResources(parentResrc, Milestone.class, parentResrc.getMilestones(), subResources);
        }
        return false;
    }

    private <T, U> boolean appendResources(T parentResource, Class<U> subResourcesType,
                                           Collection<U> appendableCollection, Collection<?> subResources) {
        ObjectMapper mapper = new ObjectMapper();
        for (Object subResource : subResources) {
            U concreteSubResource;
            try {
                concreteSubResource = mapper.convertValue(subResource, subResourcesType);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return false;
            }
            entityManager.persist(concreteSubResource);
            appendableCollection.add(concreteSubResource);
        }
        entityManager.merge(parentResource);
        return true;
    }
}