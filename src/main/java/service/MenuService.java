package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import contoller.Console;
import contoller.CrudController;
import lombok.SneakyThrows;
import org.apache.maven.model.Developer;
import repository.QueryRepositoryImpl;

import java.util.List;

public class MenuService {
    private static MenuService service;
    private final Console console = Console.getInstance();
    final QueryRepositoryImpl repo = QueryRepositoryImpl.getInstance();
    final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @SneakyThrows
    public static synchronized MenuService getInstance() {
        if (service == null) {
            service = new MenuService();
        }
        return service;
    }
    @SneakyThrows
    public void switchSelected(int i) {
        switch (i) {
            case 1:
                Long id = repo.selectProject();
                System.out.println(repo.getSumByProID(id));
                console.exitOrNot();
                break;
            case 2:
                Long id1 = repo.selectProject();
                List<Developer> developers = repo.getDevsByProID(id1);
                String json = gson.toJson(developers);
                System.out.println(json);
                console.exitOrNot();
                break;
            case 3:
                System.out.println(gson.toJson(repo.listDevelopersOfSkill()));
                console.exitOrNot();
                break;
            case 4:
                System.out.println(gson.toJson(repo.listDevelopersOfLevelSkill()));
                console.exitOrNot();
                break;
            case 5:
                System.out.println(gson.toJson(repo.listProWithData()));
                console.exitOrNot();
                break;
            case 6:
                CrudController.getInstance().selectModel();

                break;
            default:
                break;
        }
    }
}
