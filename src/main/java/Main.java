import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Developer;
import model.DeveloperProject;
import model.Project;
import repository.CrudRepository;
import repository.QueryRepositoryImpl;
import repository.RepositoryFactory;
import service.Console;

import java.sql.SQLException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {

        //CrudRepository<Project, Long> projectRepository = RepositoryFactory.of(Project.class);
        //List<Company> findAll=companyRepository.findALL();
        //Optional<Project> c = projectRepository.findById(3L);
        //System.out.println(c.get().getName());
        //Company company = Company.builder().id(4l).name("Asd").safer("Software").build();
        //Company create = companyRepository.save(company);
        //ScriptExecutor.start();
        //System.out.println(new QueryRepositoryImpl().getSumByProID(2l));
        //System.out.println(new QueryRepositoryImpl().listJava());
        //System.out.println(new QueryRepositoryImpl().listMiddle());
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //String json = gson.toJson(QueryRepositoryImpl.getInstance().listProWithData());
        //String json = gson.toJson(QueryRepositoryImpl.getInstance().getDevsByProID(2L));
        //System.out.println(json);
        new Console().selectMenu();
    }
}
