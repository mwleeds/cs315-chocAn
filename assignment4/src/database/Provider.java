package database;

/**
 * The Provider class that holds information on an individual provider
 * @author  Ryan Mitchell
 */
public class Provider extends Database.DatabaseEntry {

    //The provider property variables
    private String name;
    private String addressStreet;
    private String addressCity;
    private String addressZipCode;
    private String addressState;

    /***
     * Creates a Provider instance
     * @param name              the name of the provider
     * @param addressStreet     the street the provider is located on
     * @param addressCity       the city the provider is located in
     * @param addressZipCode    the zip code the provider is located in
     * @param addressState      the state the provider is located in
     */
    public Provider(String name, String addressStreet, String addressCity, String addressZipCode, String addressState){
        this.name = name;
        this.addressStreet = addressStreet;
        this.addressCity = addressCity;
        this.addressZipCode = addressZipCode;
        this.addressState = addressState;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddressStreet() {
        return addressStreet;
    }
    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }
    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getAddressState() {
        return addressState;
    }
    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }
}
