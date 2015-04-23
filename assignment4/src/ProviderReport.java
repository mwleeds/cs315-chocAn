import java.io.*;
import java.util.ArrayList;

import database.*;

/**
 * The ProviderReport class generates report files for providers with their info, info for 
 * all the services they provided, and totals for number of consultations and fees.
 * @author Matthew Leeds
 */
public class ProviderReport extends Report {
    
	private Provider provider;
    private File reportFile;
    
    public ProviderReport(File f, Provider provider) {
        this.reportFile = f;
        this.provider = provider;
    }
    
    public void generateReport() throws IOException {
    	
        //Write the provider properties to the report
    	FileWriter fW = new FileWriter(reportFile);
        int providerId = -1;
        try {
        	providerId = provider.getId();
        	fW.write("Provider name: " + provider.getName() + newLine);
            fW.write("Provider number: " + String.format("%09d",providerId) + newLine); 
	        fW.write("Provider address: " + provider.getAddressStreet() + newLine);
	        fW.write("Provider city: " + provider.getAddressCity() + newLine);
	        fW.write("Provider state: " + provider.getAddressState() + newLine);
	        fW.write("Provider ZIP code: " + provider.getAddressZipCode() + newLine + newLine);
	        
	        //Declare the variables for counting the sum
	        int numServicesProvided = 0;
	        double totalFees = 0;
	        
	        ArrayList<ProvidedService> allServices = ChocAnMain.providedServiceDatabase.getEntryList();
	        for (int i = 0; i < allServices.size(); ++i) {
	        	//Find all the services provided by the provider
	            ProvidedService s = allServices.get(i);
	            if (s.getProviderId() == providerId) {
	                
	                //Log the provided service details
	                fW.write("Date provided: " + s.getDateProvided() + newLine);
	                fW.write("Date input: " + s.getDateInput() + newLine);
	                
	                //Get the member the service was provided to
	                Member m = ChocAnMain.memberDatabase.getEntry(s.getMemberId());
	                fW.write("Member name: " + m.getName() + newLine);
	                fW.write("Member number: " + String.format("%09d",m.getId()) + newLine);
	                
	                //Get the service that was provided
	                Service service = ChocAnMain.providerDirectoryDatabase.getEntry(s.getServiceId());
	                fW.write("Service code: " + String.format("%09d",service.getId()) + newLine);
	                fW.write("Service fee: " + service.getFee() + newLine + newLine);
	                
	                numServicesProvided++;
	                totalFees += service.getFee();
	            }
	        }
	        fW.write("Total number of consultations: " + numServicesProvided + newLine);
	        fW.write("Total fees: " + totalFees + newLine);
	        fW.flush();
	        fW.close();}
     catch (Exception e) {
    	System.out.println(e);
     	}
    }
}
