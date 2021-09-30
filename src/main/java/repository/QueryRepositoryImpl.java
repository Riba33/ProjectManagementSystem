package repository;

import lombok.SneakyThrows;
import model.Developer;
import model.ProjectWithCountDevelopers;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public Long getSumSalaryByProject(Long projectId) throws SQLException {

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
    public List getDevelopersByProject(Long projectId) throws SQLException {

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
    @SneakyThrows
    public List listDevelopersOfSkill(String skill){
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT developers.id, developers.name, " +
                "developers.surname, developers.age, developers.gender, developers.salary " +
                "FROM developers " +
                " INNER JOIN developers_skils " +
                " ON developers.id = developers_skils.developers_id " +
                " INNER JOIN skils " +
                " ON developers_skils.skils_id = skils.id " +
                " WHERE skils.name= ?;");
        preparedStatement.setString(1,skill);
        ResultSet resultSet = preparedStatement.executeQuery();
        return parseDevelopers(resultSet);
    }

    @Override
    @SneakyThrows
    public List listDevelopersOfLevelSkill(String level) {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT developers.id, developers.name, developers.surname,\n" +
                " developers.age, developers.gender, developers.salary " +
                "FROM developers " +
                " INNER JOIN developers_skils " +
                " ON developers.id = developers_skils.developers_id " +
                " INNER JOIN skils " +
                " ON developers_skils.skils_id = skils.id " +
                "   WHERE skils.level = ? " +
                "    ORDER BY id;");
        preparedStatement.setString(1,level);
        ResultSet resultSet = preparedStatement.executeQuery();
        return parseDevelopers(resultSet);
    }

    @Override
    @SneakyThrows
    public List<ProjectWithCountDevelopers> listProjectWithCountDevelopers() {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT projects.name, projects.date," +
                " count(projects.name) Developers " +
                "FROM projects " +
                " INNER JOIN developers_projects " +
                " ON projects.id = developers_projects.projects_id " +
                " INNER JOIN developers " +
                " ON developers_projects.developers_id = developers.id " +
                "GROUP BY name;");
        final ResultSet resultSet = preparedStatement.executeQuery();
        List<ProjectWithCountDevelopers> list = new ArrayList<>();
        while(resultSet.next()) {
            ProjectWithCountDevelopers devInProject = ProjectWithCountDevelopers.builder()
                    .name(resultSet.getString("name"))
                    .date(resultSet.getDate("date"))
                    .countDevelopers(resultSet.getLong("Developers"))
                    .build();
            list.add(devInProject);
        }

        return list;
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
}
