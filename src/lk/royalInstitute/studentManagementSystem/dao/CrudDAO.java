package lk.royalInstitute.studentManagementSystem.dao;

import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO {
     boolean add(T entity) throws Exception;
     boolean update(T entity) throws Exception;
     boolean delete(T entity) throws Exception;
     List<T> getAll() throws Exception;
     T search(T entity) throws Exception;
}
