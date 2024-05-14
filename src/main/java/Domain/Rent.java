package Domain;

import java.text.SimpleDateFormat;

public class Rent implements IEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer idCar;

    private java.sql.Date startDate;

    private java.sql.Date endDate;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public Rent(Integer id, Integer idCar, java.sql.Date startDate, java.sql.Date endDate) {
        this.id = id;
        this.idCar = idCar;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCar() {
        return idCar;
    }

    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }

    public java.sql.Date getStartDate() {
        return (java.sql.Date) startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
        return (java.sql.Date) endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "id:" + id +
                ", idCar: " + idCar +
                ", startDate: " + format.format(startDate) +
                ", endDate: " + format.format(endDate);
    }
}
