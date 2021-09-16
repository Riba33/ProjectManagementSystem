package service;

import lombok.SneakyThrows;
import model.Project;

public class ProjectService extends BaseService<Project,Long>{
    private static ProjectService service;

    @SneakyThrows
    public static synchronized ProjectService getInstance() {
        if (service == null) {
            service = new ProjectService();
        }
        return service;
    }

    @Override
    public Project init(){
        Project project = new Project();
        System.out.println("Введите ID");
        project.setId(checkLong());
        System.out.println("Введите название проекта.");
        project.setName(sc.next());
        return project;
    }
}
