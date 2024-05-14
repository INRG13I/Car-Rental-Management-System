package Domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IEntityFactory<T extends IEntity> {

    String entityToString(T entity);

    T stringToEntity(String line);

    String createTable();

    PreparedStatement insertToDB(T entity, Connection connection) throws SQLException;

    PreparedStatement updateToDB(Integer id, T entity, Connection connection) throws SQLException;

    PreparedStatement deleteToDB(Integer id, Connection connection) throws SQLException;


    ArrayList<T> initFromDB(Connection connection) throws SQLException;

}
