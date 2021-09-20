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

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT sum(salary) as cost, " +
                " projects.name as projectName " +
                " FROM developers " +
                " INNER JOIN developers_projects " +
                " ON developers.id = developers_projects.developers_id " +
                " INNER JOIN projects " +
                " ON developers_projects.projects_id = projects.id " +
                "   WHERE projects.id='" + projectId +"';");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Long> cost = new ArrayList<>();
        while (resultSet.next()){
            cost.add(resultSet.getLong("cost"));
        }
        return cost.get(0);
    }

    @Override
    public List getDevsByProID(Long projectId) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * " +
                "FROM developers " +
                " INNER JOIN developers_projects " +
                " ON developers.id = developers_projects.developers_id " +
                " INNER JOIN projects " +
                " ON developers_projects.projects_id = projects.id " +
                "    WHERE projects.id = '" + projectId +"';");

        return parseDevelopers(preparedStatement.executeQuery());
    }

    @Override
    public List listJava() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT developers.id, developers.name, " +
                "developers.surname, developers.age, developers.gender, developers.salary " +
                "FROM developers " +
                " INNER JOIN developers_skils " +
                " ON developers.id = developers_skils.developers_id " +
                " INNER JOIN skils " +
                " ON developers_skils.skils_id = skils.id " +
                " WHERE skils.name='Java';");
        return parseDevelopers(preparedStatement.executeQuery());
    }

    @Override
    public List listMiddle() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT developers.id, developers.name, developers.surname,\n" +
                " developers.age, developers.gender, developers.salary " +
                "FROM developers " +
                " INNER JOIN developers_skils " +
                " ON developers.id = developers_skils.developers_id " +
                " INNER JOIN skils " +
                " ON developers_skils.skils_id = skils.id " +
                "   WHERE skils.level='Middle' " +
                "    ORDER BY id;");
        return parseDevelopers(preparedStatement.executeQuery());
    }

    @Override
    public List<DevelopersInProject> listProWithData() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT projects.name, projects.date," +
                " count(projects.name) Developers " +
                "FROM projects " +
                " INNER JOIN developers_projects " +
                " ON projects.id = developers_projects.projects_id " +
                " INNER JOIN developers " +
                " ON developers_projects.developers_id = developers.id " +
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
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM projects;");
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
    private List<Developer> parseDevelopers(ResultSet resultSet) throws SQLException {
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

    private boolean isGood(Long i, int size) {
        if (i > 0 && i <= size) return true;
        return false;
    }
}
