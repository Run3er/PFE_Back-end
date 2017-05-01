package projMngmntSaaS.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import projMngmntSaaS.api.controllers.utils.ProjectFullUpdateArchiver;
import projMngmntSaaS.api.controllers.utils.ResourceAppender;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.repositories.ProjectRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * API controller for customizing Spring Data REST endpoints.
 */
@RepositoryRestController
public class ApiController
{
    private final ProjectRepository projectRepository;
    private final ProjectFullUpdateArchiver projectFullUpdateArchiver;
    private final ResourceAppender resourceAppender;

    @Autowired
    public ApiController(ProjectRepository projectRepository, ProjectFullUpdateArchiver projectFullUpdateArchiver,
                         ResourceAppender resourceAppender) {
        this.projectRepository = projectRepository;
        this.projectFullUpdateArchiver = projectFullUpdateArchiver;
        this.resourceAppender = resourceAppender;
    }

    @RequestMapping(method = POST, value = "/**/projects/{projectId}/archivedUpdates")
    public ResponseEntity<?> archivePendingUpdate(@PathVariable UUID projectId) {
        Project project = projectRepository.findOne(projectId);

        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        projectFullUpdateArchiver.archive(project, new Date());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Seems Spring Data REST @RepositoryRestController can't work on overriding behavior with regex path,
    // since conflicts are raised instead. Thus, each path literal is specifies explicitly in the mappings
    @RequestMapping(method = POST, consumes = "application/json", value = {
            "**/entities/{parentResourceId}/projects",
            "**/projects/{parentResourceId}/subProjects",
            "**/subProjects/{parentResourceId}/constructionSites",

            "**/projects/{parentResourceId}/actions",
            "**/projects/{parentResourceId}/risks",
            "**/projects/{parentResourceId}/changeRequests",
            "**/projects/{parentResourceId}/pendingIssues",
            "**/projects/{parentResourceId}/resources",
            "**/projects/{parentResourceId}/documents",
            "**/projects/{parentResourceId}/milestones",

            "**/subProjects/{parentResourceId}/actions",
            "**/subProjects/{parentResourceId}/risks",
            "**/subProjects/{parentResourceId}/changeRequests",
            "**/subProjects/{parentResourceId}/pendingIssues",
            "**/subProjects/{parentResourceId}/resources",
            "**/subProjects/{parentResourceId}/documents",
            "**/subProjects/{parentResourceId}/milestones",

            "**/constructionSites/{parentResourceId}/actions",
            "**/constructionSites/{parentResourceId}/risks",
            "**/constructionSites/{parentResourceId}/changeRequests",
            "**/constructionSites/{parentResourceId}/pendingIssues",
            "**/constructionSites/{parentResourceId}/resources",
            "**/constructionSites/{parentResourceId}/documents",
            "**/constructionSites/{parentResourceId}/milestones"
    })
    public ResponseEntity<?> appendSubResources(@PathVariable UUID parentResourceId, HttpServletRequest request,
                                                @RequestBody Collection<Object> subResources) {
        String restOfTheUri = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String[] UriParts = restOfTheUri.split("/");
        String subResourcePath = UriParts[UriParts.length - 1];
        String parentResourcePath = UriParts[UriParts.length - 3];

        List<UUID> appendedUuids;
        try {
            appendedUuids = resourceAppender.append(parentResourcePath, parentResourceId, subResourcePath, subResources);
            return new ResponseEntity<>(appendedUuids,  HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
