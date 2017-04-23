package projMngmntSaaS.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.api.controllers.utils.ProjectFullUpdateArchiver;
import projMngmntSaaS.repositories.ProjectRepository;

import java.util.Date;
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

    @Autowired
    public ApiController(ProjectRepository projectRepository, ProjectFullUpdateArchiver projectFullUpdateArchiver) {
        this.projectRepository = projectRepository;
        this.projectFullUpdateArchiver = projectFullUpdateArchiver;
    }

    @ResponseBody
    @RequestMapping(method = POST, value = "/**/projects/{projectId}/archivedUpdates")
    public ResponseEntity<?> archivePendingUpdate(@PathVariable UUID projectId) {
        Project project = projectRepository.findOne(projectId);

        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        projectFullUpdateArchiver.archive(project, new Date());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
