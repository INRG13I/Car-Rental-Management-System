package Domain;

import java.sql.*;
import java.util.ArrayList;

public class CarFactory implements IEntityFactory<Car> {

    @Override
    public String entityToString(Car entity) {
        String string = new String();
        string += entity.getId() + ",";
        string += entity.getMake() + ",";
        string += entity.getModel() + "\n";
        return string;
    }

    @Override
    public Car stringToEntity(String line) {
        Integer id = Integer.parseInt(line.split(",")[0]);
        String make = line.split(",")[1];
        String model = line.split(",")[2];
        return new Car(id, make, model);
    }

    @Override
    public String createTable() {

        String command = "CREATE TABLE IF NOT EXISTS car(id int,make nvarchar(20),model nvarchar(20));";

        return command;
    }

    @Override
    public PreparedStatement insertToDB(Car entity, Connection connection) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO car VALUES (?,?,?);");
        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getMake());
        statement.setString(3, entity.getModel());
        return statement;
    }

    @Override
    public PreparedStatement updateToDB(Integer id, Car entity, Connection connection) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("UPDATE car SET col=(?,?,?) WHERE id=(?);");
        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getMake());
        statement.setString(3, entity.getModel());
        statement.setInt(4, id);
        return statement;
    }

    @Override
    public PreparedStatement deleteToDB(Integer id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM car WHERE id=(?);");
        statement.setInt(1, id);
        return statement;
    }

    @Override
    public ArrayList<Car> initFromDB(Connection connection) throws SQLException {
        Statement stm;
        stm = connection.createStatement();
        String sql = "SELECT * FROM car";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ArrayList<Car> rentList = new ArrayList<>();
        while (rst.next()) {
            Car rent = new Car(rst.getInt("id"), rst.getString("make"), rst.getString("model"));
            rentList.add(rent);
        }
        return rentList;
    }
}
