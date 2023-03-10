package lk.ijse.teafactory.dao;

import lk.ijse.teafactory.dao.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO {
    public boolean save(T entity) throws SQLException , ClassNotFoundException;
    public ArrayList <T> getAll () throws SQLException , ClassNotFoundException;
    public T search (String id) throws SQLException , ClassNotFoundException;
    public boolean update(T entity) throws  SQLException , ClassNotFoundException;
    public boolean delete(String id) throws  SQLException , ClassNotFoundException;


}
