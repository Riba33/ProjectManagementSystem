package service;

import lombok.SneakyThrows;
import model.ProjectWithCountDevelopers;
import repository.QueryRepositoryImpl;

import java.util.List;

public class QueryService {
    QueryRepositoryImpl repo = QueryRepositoryImpl.getInstance();
    private static QueryService service;
    @SneakyThrows
    public static synchronized QueryService getInstance() {
        if (service == null) {
            service = new QueryService();
        }
        return service;
    }
    @SneakyThrows
    public Long getSumSalaryByProject(Long projectId){
        return repo.getSumSalaryByProject(projectId);
    }
    @SneakyThrows
    public List getDevelopersByProject(Long projectId) {
        return repo.getDevelopersByProject(projectId);
    }
    @SneakyThrows
    public List listDevelopersOfSkill(String skill){
        return repo.listDevelopersOfSkill(skill);
    }
    @SneakyThrows
    public List listDevelopersOfLevelSkill(String level){
        return repo.listDevelopersOfLevelSkill(level);
    }
    @SneakyThrows
    public List<ProjectWithCountDevelopers> listProjectsWithCountOfDevelopers() {
        return repo.listProjectWithCountDevelopers();
    }
}
