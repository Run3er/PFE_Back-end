package projMngmntSaaS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import projMngmntSaaS.domain.entities.ProjectsEntity;
import projMngmntSaaS.domain.entities.enums.ActionStatus;
import projMngmntSaaS.domain.entities.enums.ProjectLevelStatus;
import projMngmntSaaS.domain.entities.enums.ResourceType;
import projMngmntSaaS.domain.entities.enums.RiskStatus;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.domain.entities.projectLevel.ProjectLevelUpdate;
import projMngmntSaaS.domain.entities.projectLevel.SubProject;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Action;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Milestone;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Resource;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Risk;
import projMngmntSaaS.repositories.ProjectRepository;
import projMngmntSaaS.repositories.ResourceRepository;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Configuration for Spring Boot.
 * It allows the application to be stand-alone (embedded TomCat server, ...).
 */
@SpringBootApplication
public class AppConfiguration
{
    @Autowired
    @Qualifier("transactionManager")
    private PlatformTransactionManager txManager;

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }

    @PostConstruct
    public void init() {
        TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                // Create project
                Project project = new Project();
                // name
                project.setName("Project-X");
                // update
                project.setStatus(ProjectLevelStatus.BAD);
                project.setAdvancement(0.9f);
                //  update milestones
                Milestone milestone = new Milestone();
                milestone.setName("Phase-I");
                milestone.setDueDate(new Date());
                project.getMilestones().add(milestone);
                //  update actions #1
                Action action = new Action();
                action.setDescription("important task #1");
                action.setPriority(2);
                action.setCreationDate(new Date());
                action.setStatus(ActionStatus.ONGOING);
                action.setClosurePlannedDate(new Date());
                Resource resource = new Resource();
                resource.setName("Ali");
                resource.setType(ResourceType.HUMAN);
                resourceRepository.save(resource);
                action.setSupervisor(resource);
                project.getResources().add(resource);
                project.getActions().add(action);
                //  update actions #2
                Action actionTwo = new Action();
                actionTwo.setDescription("heavy work #2");
                actionTwo.setPriority(2);
                actionTwo.setCreationDate(new Date());
                actionTwo.setStatus(ActionStatus.STANDBY);
                actionTwo.setClosurePlannedDate(new Date());
                Resource resourceTwo = new Resource();
                resourceTwo.setName("Fatma");
                resourceTwo.setType(ResourceType.HUMAN);
                resourceRepository.save(resourceTwo);
                project.getResources().add(resourceTwo);
                actionTwo.setSupervisor(resourceTwo);
                project.getActions().add(actionTwo);
                //  update risks
                Risk risk = new Risk();
                risk.setDescription("risky state #15");
                risk.setProbability(3);
                risk.setImpact(4);
                risk.setActionPlan("Lessen impact by doing sth.");
                risk.setStatus(RiskStatus.ACTION_PLAN_ONGOING);
                risk.setDetectionDate(new Date());
                risk.setQualificationDate(new Date());
                project.getRisks().add(risk);
                //  set archived updates
                ProjectLevelUpdate updateOld = new ProjectLevelUpdate(project, new Date());
                updateOld.setStatus(ProjectLevelStatus.GOOD);
                updateOld.setAdvancement(0.3f);
                project.getArchivedUpdates().add(updateOld);
                // sub-projects
                SubProject subProject = new SubProject();
                subProject.setName("Subby-Y");
                subProject.setStatus(ProjectLevelStatus.BAD);
                subProject.setAdvancement(0.8f);
                project.getSubProjects().add(subProject);
                // projectsEntity
                ProjectsEntity projectsEntity = new ProjectsEntity();
                projectsEntity.setName("Default-Entity");
//                projectsEntity = projectsEntityRepository.save(projectsEntity);
                project.setEntity(projectsEntity);
                projectRepository.save(project);
            }
        });
    }
}
