package repository;

import model.BaseEntity;

import java.sql.SQLException;
import java.util.List;

public interface QueryRepository <T extends BaseEntity<ID>,ID>{

    Long getSumByProID(Long projectId) throws SQLException;

    List<T> getDevsByProID(Long projectId) throws SQLException;

    List<T> listJava() throws SQLException;

    List<T> listMiddle() throws SQLException;

    List listProWithData() throws SQLException;
}
