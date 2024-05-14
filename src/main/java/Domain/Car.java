package Domain;


public class Car implements IEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String make;

    private String model;

    public Car(Integer id, String make, String model) {

        this.id = id;
        this.make = make;
        this.model = model;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", make: " + make +
                ", model: " + model;
    }
}
