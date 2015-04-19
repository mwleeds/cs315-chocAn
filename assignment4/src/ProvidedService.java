package chocAn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Provided Service class that holds information on an individual service that were provided by a provider
 * @author  Ryan Miitchell
 */
public class ProvidedService {

    //The provided service property variables
    private String dateProvided;
    private String dateInput;
    private int memberId;
    private int providerId;
    private int serviceId;
    private String comment;

    /**
     * Creates a Provided Service instance
     * @param dateProvided  the date the service was provided
     * @param memberId      the id of the member who was provided the service
     * @param providerId    the id of the provider who provided the service
     * @param serviceId     the id of the provided service
     * @param comment
     */
    public ProvidedService(Date dateProvided, int memberId, int providerId, int serviceId, String comment){

        //Formats the dates into strings for storage
        DateFormat dateFormat = new SimpleDateFormat("MM-DD-YYYY HH:MM:SS");
        this.dateProvided = dateFormat.format(dateProvided);
        this.dateInput = dateFormat.format(new Date());

        this.memberId = memberId;
        this.serviceId = serviceId;
        this.comment = comment;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getDateProvided() {
        return dateProvided;
    }

    public void setDateProvided(String dateProvided) {
        this.dateProvided = dateProvided;
    }

    public String getDateInput() {
        return dateInput;
    }

    public void setDateInput(String dateInput) {
        this.dateInput = dateInput;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
