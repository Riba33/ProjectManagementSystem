package repository;

import model.BaseEntity;

import java.sql.SQLException;
import java.util.List;

public interface QueryRepository <T extends BaseEntity<ID>,ID>{

    Long getSumSalaryByProject(Long projectId) throws SQLException;

    List<T> getDevelopersByProject(Long projectId) throws SQLException;

    List<T> listDevelopersOfSkill(String skill) throws SQLException;

    List<T> listDevelopersOfLevelSkill(String level) throws SQLException;

    List listProjectWithCountDevelopers() throws SQLException;
}
