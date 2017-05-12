package projMngmntSaaS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Configuration for Spring Boot.
 * It allows the application to be stand-alone (embedded TomCat server, ...).
 */
@SpringBootApplication
public class AppConfiguration
{
//    @Autowired
//    @Qualifier("transactionManager")
//    private PlatformTransactionManager txManager;
//
//    @Autowired
//    private ProjectRepository projectRepository;
//    @Autowired
//    private ResourceRepository resourceRepository;
//
    public static void main(String[] args) {
        SpringApplication.run(AppConfiguration.class, args);
    }
//
//    @PostConstruct
//    public void init() {
//        TransactionTemplate tmpl = new TransactionTemplate(txManager);
//        tmpl.execute(new TransactionCallbackWithoutResult() {
//            @Override
//            protected void doInTransactionWithoutResult(TransactionStatus status) {
//                // Create project
//                Project project = new Project();
//                // name
//                project.setName("Project-X");
//                // update
//                project.setStatus(ProjectLevelStatus.RED);
//                project.setAdvancement(0.9f);
//                //  update milestones
//                Milestone milestone = new Milestone();
//                milestone.setName("Phase-I");
//                milestone.setDueDate(new Date());
//                project.getMilestones().add(milestone);
//                //  update actions #1
//                Action action = new Action();
//                action.setName("important task #1");
//                action.setPriority(2);
//                action.setCreationDate(new Date());
//                action.setStatus(ActionStatus.ONGOING);
//                action.setClosurePlannedDate(new Date());
//                Resource resource = new Resource();
//                resource.setName("Ali");
//                resource.setType(ResourceType.HUMAN);
//                resourceRepository.save(resource);
//                action.setSupervisor(resource);
//                project.getResources().add(resource);
//                project.getActions().add(action);
//                //  set archived updates
//                ProjectLevelUpdate updateOld = new ProjectLevelUpdate(project, new Date());
//                updateOld.setStatus(ProjectLevelStatus.GREEN);
//                updateOld.setAdvancement(0.3f);
//                project.getArchivedUpdates().add(updateOld);
//                //  update actions #2
//                Action actionTwo = new Action();
//                actionTwo.setName("heavy work #2");
//                actionTwo.setPriority(2);
//                actionTwo.setCreationDate(new Date());
//                actionTwo.setStatus(ActionStatus.STANDBY);
//                actionTwo.setClosurePlannedDate(new Date());
//                Resource resourceTwo = new Resource();
//                resourceTwo.setName("Fatma");
//                resourceTwo.setType(ResourceType.HUMAN);
//                resourceRepository.save(resourceTwo);
//                project.getResources().add(resourceTwo);
//                actionTwo.setSupervisor(resourceTwo);
//                project.getActions().add(actionTwo);
//                //  update risks
//                Risk risk = new Risk();
//                risk.setName("risky state #15");
//                risk.setProbability(3);
//                risk.setImpact(4);
//                risk.setActionPlan("Lessen impact by doing sth.");
//                risk.setStatus(RiskStatus.ACTION_PLAN_ONGOING);
//                risk.setDate(new Date());
//                risk.setQualificationDate(new Date());
//                project.getRisks().add(risk);
//                // sub-projects
//                SubProject subProject = new SubProject();
//                subProject.setName("Subby-Y");
//                subProject.setStatus(ProjectLevelStatus.RED);
//                subProject.setAdvancement(0.8f);
//                project.getSubProjects().add(subProject);
//                // projectsEntity
//                ProjectsEntity projectsEntity = new ProjectsEntity();
//                projectsEntity.setName("Default-Entity");
////                projectsEntity = projectsEntityRepository.save(projectsEntity);
//                project.setEntity(projectsEntity);
//                projectRepository.save(project);
//            }
//        });
//    }
}
