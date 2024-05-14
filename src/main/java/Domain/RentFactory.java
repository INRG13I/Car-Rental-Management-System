package Domain;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class RentFactory implements IEntityFactory<Rent> {

    private SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String entityToString(Rent entity) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String string = new String();
        string += entity.getId() + ",";
        string += entity.getIdCar() + ",";
        string += simpleDateFormat.format(entity.getStartDate()) + ",";
        string += simpleDateFormat.format(entity.getEndDate()) + "\n";
        return string;
    }

    @Override
    public Rent stringToEntity(String line) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String[] args = line.split(",");
        try {
            Integer id = Integer.parseInt(args[0]);
            Integer carId = Integer.parseInt(args[1]);
            java.sql.Date startDate = (java.sql.Date) simpleDateFormat.parse(args[2]);
            java.sql.Date endDate = (java.sql.Date) simpleDateFormat.parse(args[3]);
            return new Rent(id, carId, startDate, endDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public String createTable() {

        String command = "CREATE TABLE IF NOT EXISTS rent(id int,carId int,startDate date,endDate date);";

        return command;
    }

    @Override
    public PreparedStatement insertToDB(Rent entity, Connection connection) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO rent VALUES (?, ?, ?, ?);");
        statement.setInt(1, entity.getId());
        statement.setInt(2, entity.getIdCar());
        statement.setDate(3, entity.getStartDate());
        statement.setDate(4, entity.getEndDate());
        return statement;
    }

    @Override
    public PreparedStatement updateToDB(Integer id, Rent entity, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE car SET col=(?, ?, ?, ?) WHERE id=(?);");
        statement.setInt(1, entity.getId());
        statement.setInt(2, entity.getIdCar());
        statement.setDate(3, (java.sql.Date) entity.getStartDate());
        statement.setDate(4, (java.sql.Date) entity.getEndDate());
        statement.setInt(5, id);
        return statement;
    }

    @Override
    public PreparedStatement deleteToDB(Integer id, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM car WHERE id=(?);");
        statement.setInt(1, id);
        return statement;
    }

    @Override
    public ArrayList<Rent> initFromDB(Connection connection) throws SQLException {
        Statement stm;
        stm = connection.createStatement();
        String sql = "SELECT * FROM rent";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ArrayList<Rent> rentList = new ArrayList<>();
        while (rst.next()) {
            Rent rent = new Rent(rst.getInt("id"), rst.getInt("carId"), rst.getDate("startDate"), rst.getDate("endDate"));
            rentList.add(rent);
        }
        return rentList;
    }
}
