package repository;

import lombok.SneakyThrows;
import model.Developer;
import model.DevelopersInProject;
import model.Project;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class QueryRepositoryImpl implements QueryRepository {

    private Connection connection;
    private static QueryRepositoryImpl queryRepository;

    @SneakyThrows
    public QueryRepositoryImpl() {

        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    @SneakyThrows
    public static synchronized QueryRepositoryImpl getInstance() {
        if (queryRepository == null) {
            queryRepository = new QueryRepositoryImpl();
        }
        return queryRepository;
    }

    @Override
    public Long getSumByProID(Long projectId) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT sum(salary) as cost,\n" +
                "\tprojects.name as projectName\n" +
                "\tFROM developers\n" +
                "\tINNER JOIN developers_projects\n" +
                "\tON developers.id = developers_projects.developers_id\n" +
                "\tINNER JOIN projects \n" +
                "\tON developers_projects.projects_id = projects.id\n" +
                "   \tWHERE projects.name='" + findProById(projectId) +"';");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Long> cost = new ArrayList<>();
        while (resultSet.next()){
            cost.add(resultSet.getLong("cost"));
        }
        return cost.get(0);
    }

    @Override
    public List getDevsByProID(Long projectId) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * \n" +
                "FROM developers\n" +
                "\tINNER JOIN developers_projects\n" +
                "\tON developers.id = developers_projects.developers_id\n" +
                "\tINNER JOIN projects \n" +
                "\tON developers_projects.projects_id = projects.id\n" +
                "    WHERE projects.name = '" + findProById(projectId) +"';");

        return parseDeveloppers(preparedStatement.executeQuery());
    }

    @Override
    public List listJava() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT developers.id, developers.name, " +
                "developers.surname, developers.age, developers.gender, developers.salary \n" +
                "FROM developers\n" +
                "\tINNER JOIN developers_skils\n" +
                "\tON developers.id = developers_skils.developers_id\n" +
                "\tINNER JOIN skils \n" +
                "\tON developers_skils.skils_id = skils.id\n" +
                "\tWHERE skils.name='Java';");
        return parseDeveloppers(preparedStatement.executeQuery());
    }

    @Override
    public List listMiddle() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT developers.id, developers.name, developers.surname,\n" +
                "\tdevelopers.age, developers.gender, developers.salary \n" +
                "FROM developers\n" +
                "\tINNER JOIN developers_skils\n" +
                "\tON developers.id = developers_skils.developers_id\n" +
                "\tINNER JOIN skils \n" +
                "\tON developers_skils.skils_id = skils.id\n" +
                "   \tWHERE skils.level='Middle'\n" +
                "    ORDER BY id;");
        return parseDeveloppers(preparedStatement.executeQuery());
    }

    @Override
    public List<DevelopersInProject> listProWithData() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT projects.name, projects.date," +
                " count(projects.name) Developers\n" +
                "FROM projects\n" +
                "\tINNER JOIN developers_projects\n" +
                "\tON projects.id = developers_projects.projects_id\n" +
                "\tINNER JOIN developers \n" +
                "\tON developers_projects.developers_id = developers.id\n" +
                "GROUP BY name;");
        final ResultSet resultSet = preparedStatement.executeQuery();
        List<DevelopersInProject> list = new ArrayList<>();
        while(resultSet.next()) {
            DevelopersInProject devInProject = DevelopersInProject.builder()
                    .name(resultSet.getString("name"))
                    .date(resultSet.getDate("date"))
                    .countDevelopers(resultSet.getLong("Developers"))
                    .build();
            list.add(devInProject);
        }

        return list;
    }
    public Long selectProject() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mydb.projects;");
        final ResultSet resultSet = preparedStatement.executeQuery();
        List<Project> list = new ArrayList<>();
        while(resultSet.next()) {
            Project project = Project.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .date(resultSet.getDate("date"))
                    .build();
            list.add(project);
        }
        Scanner sc = new Scanner(System.in);
        System.out.println(list);
        Long i = 0L;
        do {
            System.out.println("Выбирите проект указав его ID от 1 до "+ list.size() + "!");
            while (!sc.hasNextInt()){
                System.out.println("Введите число от 0 до " + list.size() + "!");
                sc.next();
            }
            i = sc.nextLong();
        } while (!isGood(i,list.size()));

        return i;

    }
    private List<Developer> parseDeveloppers(ResultSet resultSet) throws SQLException {
        List<Developer> developers = new ArrayList<>();
        while(resultSet.next()) {
            Developer developer = Developer.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .age(resultSet.getInt("age"))
                    .gender(resultSet.getString("gender"))
                    .salary(resultSet.getInt("salary"))
                    .build();
            developers.add(developer);
        }
        return developers;
    }
    private String findProById(Long projectId) {
        CrudRepository<Project, Long> projectRepository = RepositoryFactory.of(Project.class);
        Optional<Project> project = projectRepository.findById(projectId);
        return project.get().getName();
    }
    private boolean isGood(Long i, int size) {
        if (i > 0 && i <= size) return true;
        return false;
    }
}
