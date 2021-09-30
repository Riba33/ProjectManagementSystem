package contoller;

import lombok.SneakyThrows;
import model.Project;

public class ProjectController extends BaseController {
    private static ProjectController service;

    @SneakyThrows
    public static synchronized ProjectController getInstance() {
        if (service == null) {
            service = new ProjectController();
        }
        return service;
    }

    @Override
    public Project makeModel(){
        Project project = new Project();
        System.out.println("Введите ID");
        project.setId(checkLong());
        System.out.println("Введите название проекта.");
        project.setName(sc.next());
        return project;
    }
}
