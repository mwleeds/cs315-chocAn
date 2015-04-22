package database;

/**
 * The Member class that holds information on an individual member
 * @author  Ryan Mitchell
 */
public class Member extends Database.DatabaseEntry{

    //The member property variables
    private String name, status, addressStreet, addressCity, addressZipCode, addressState;

    /***
     * Creates a Member instance
     * @param name              the name of the member
     * @param status			the status of the membership of the member
     * @param addressStreet     the street the member lives on
     * @param addressCity       the city the member lives in
     * @param addressZipCode    the zip code the member lives in
     * @param addressState      the state the member lives in
     */
    public Member(String name, String status, String addressStreet, String addressCity, String addressZipCode, String addressState){
        this.name = name;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
