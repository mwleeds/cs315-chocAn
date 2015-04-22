package database;

/**
 * The Service class that holds information on an individual service that a provider can provide
 * @author  Ryan Mitchell
 */
public class Service extends Database.DatabaseEntry {

    //The service property variables
    private String name;
    private double fee;

    /**
     * Creates a Service instance
     * @param name  the name of the service
     * @param fee   how much the is to be paid
     */
    public Service(String name, double fee){
        this.name = name;
        this.fee = fee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
