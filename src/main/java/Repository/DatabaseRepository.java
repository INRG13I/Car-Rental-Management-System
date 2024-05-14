package Repository;


import Domain.IEntity;
import Domain.IEntityFactory;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseRepository<T extends IEntity> extends MemoryRepository<T> {

    private String database;
    private Connection connection;

    private IEntityFactory<T> factory;

    public DatabaseRepository(String dbLocation, IEntityFactory factory) {
        this.database = dbLocation;
        this.factory = factory;
        openConnection();
        createTable();
        init();
    }


    private void init() {
        try {
            entityList = factory.initFromDB(connection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void openConnection() {

        try {
            SQLiteDataSource dataSource = new SQLiteDataSource();
            dataSource.setUrl(database);
            if (connection == null || connection.isClosed()) {
                connection = dataSource.getConnection();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
        }

    }

    private void createTable() {
        try (final Statement statement = connection.createStatement()) {
            statement.execute(factory.createTable());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insert(T entity) {
        try (PreparedStatement statement = factory.insertToDB(entity, connection)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void remove(Integer id) {
        try (PreparedStatement statement = factory.deleteToDB(id, connection)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void renew(Integer id, T entity) {
        try (PreparedStatement statement = factory.updateToDB(id, entity, connection)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(T entity) {
        super.add(entity);
        insert(entity);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
        remove(id);
    }

    @Override
    public void update(Integer id, T entity) {
        super.update(id, entity);
        renew(id, entity);
    }
}
