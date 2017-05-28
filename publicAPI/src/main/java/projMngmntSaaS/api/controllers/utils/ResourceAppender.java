package projMngmntSaaS.api.controllers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import projMngmntSaaS.domain.entities.projectLevel.ConstructionSite;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.ConstructionSiteArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.ProjectArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.archivableContents.SubProjectArchivableContent;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.*;
import projMngmntSaaS.domain.utils.UuidIdentifiable;
import projMngmntSaaS.repositories.HumanResourceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

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

    // TODO: remove after correcting behavior for transactional complex entry appending
    @Autowired
    HumanResourceRepository humanResourceRepository;

    @Transactional
    public List<UUID> append(String parentResourcePath, UUID parentResrcId, String subResourcePath, Collection<?> subResources) throws IllegalArgumentException {
        List<UUID> appendedResourcesIDs = new ArrayList<>();

        switch (parentResourcePath) {
            case "projects":
                Project project = entityManager.find(Project.class, parentResrcId);
                if (project == null) {
                    throw new IllegalArgumentException(URI_RESRC_INVALID_MSG);
                }

                if ("subProjects".equals(subResourcePath)) {
                    appendResources(project, SubProject.class, project.getSubProjects(),
                            subResources, appendedResourcesIDs);
                }
                else if ("constructionSites".equals(subResourcePath)) {
                    appendResources(project, ConstructionSite.class, project.getConstructionSites(),
                            subResources, appendedResourcesIDs);
                }
                else appendProjectArtifacts(project, subResourcePath, subResources, appendedResourcesIDs);
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
                else appendSubProjectArtifacts(subProject, subResourcePath, subResources, appendedResourcesIDs);
                break;
            case "constructionSites":
                ConstructionSite constructionSite = entityManager.find(ConstructionSite.class, parentResrcId);
                if (constructionSite == null) {
                    throw new IllegalArgumentException(URI_RESRC_INVALID_MSG);
                }

                appendConstructionSiteArtifacts(constructionSite, subResourcePath, subResources, appendedResourcesIDs);
                break;
            default:
                throw new IllegalArgumentException(URI_RESRC_INVALID_MSG);
        }
        return appendedResourcesIDs;
    }

    private void appendProjectArtifacts(ProjectArchivableContent parentResrc, String subResourcePath,
                                        Collection<?> subResources, Collection<UUID> appendedResourcesIDs) {
        switch (subResourcePath) {
            case "reunionPlannings":
                appendResources(parentResrc, ReunionPlanning.class, parentResrc.getReunionPlannings(),
                        subResources, appendedResourcesIDs);
                break;
            case "communicationPlans":
                appendResources(parentResrc, CommunicationPlan.class, parentResrc.getCommunicationPlans(),
                        subResources, appendedResourcesIDs);
                break;
            case "writeups":
                appendResources(parentResrc, Writeup.class, parentResrc.getWriteups(),
                        subResources, appendedResourcesIDs);
                break;
        }
        // case default must be handled by this method
        appendSubProjectArtifacts(parentResrc, subResourcePath, subResources, appendedResourcesIDs);
    }

    private void appendSubProjectArtifacts(SubProjectArchivableContent parentResrc, String subResourcePath,
                                           Collection<?> subResources, Collection<UUID> appendedResourcesIDs) {
        // case default must be handled by this method
        appendConstructionSiteArtifacts(parentResrc, subResourcePath, subResources, appendedResourcesIDs);
    }

    private void appendConstructionSiteArtifacts(ConstructionSiteArchivableContent parentResrc, String subResourcePath,
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
            case "todos":
                appendResources(parentResrc, Todo.class, parentResrc.getTodos(),
                        subResources, appendedResourcesIDs);
                break;
            case "humanResources":
                appendResources(parentResrc, HumanResource.class, parentResrc.getHumanResources(),
                        subResources, appendedResourcesIDs);
                break;
            default:
                throw new IllegalArgumentException(URI_NESTED_RESRC_INVALID_MSG);
        }
    }

    private <T, U extends UuidIdentifiable> void appendResources(T parentResource, Class<U> subResourcesType, Collection<U> appendableCollection, Collection<?> subResources, Collection<UUID> appendedResourcesIDs) {
        ObjectMapper mapper = new ObjectMapper();
        for (Object subResource : subResources) {
            // TODO: remove & correct behavior for transactional complex entry appending
            LinkedHashMap<String, String> linkedHashMap = (LinkedHashMap<String, String>) subResource;
            if (linkedHashMap.get("supervisor") != null) {
                linkedHashMap.remove("supervisor");
            }

            U concreteSubResource = null;
            try {
                concreteSubResource = mapper.convertValue(subResource, subResourcesType);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

            // TODO: remove & correct behavior for transactional complex entry appending
            if (concreteSubResource instanceof Action || concreteSubResource instanceof PendingIssue || concreteSubResource instanceof CommunicationPlan) {
                HumanResource humanResource;
                List<HumanResource> humanResources = humanResourceRepository.findAll();
                if (humanResources.size() > 0) {
                    humanResource = humanResources.get(0);
                } else {
                    humanResource = new HumanResource();
                    humanResource.setName("Ali");
                    humanResourceRepository.save(humanResource);
                }
                if (concreteSubResource instanceof Action) {
                    ((Action) concreteSubResource).setSupervisor(humanResource);
                }
                if (concreteSubResource instanceof PendingIssue) {
                    ((PendingIssue) concreteSubResource).setSupervisor(humanResource);
                }
                if (concreteSubResource instanceof CommunicationPlan) {
                    ((CommunicationPlan) concreteSubResource).setSupervisor(humanResource);
                }
            }

            entityManager.persist(concreteSubResource);
            appendableCollection.add(concreteSubResource);
            appendedResourcesIDs.add(concreteSubResource.getId());
        }
        entityManager.merge(parentResource);
    }
}