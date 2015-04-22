import java.io.*;
import java.util.ArrayList;
import database.*;

/**
 * The MemberReport class generates report files for members with their info,
 * and info for all the services provided to them.
 * @author Matthew Leeds
 */
public class MemberReport extends Report {
    
	private File reportFile;
	private Member member;
    
    public MemberReport(File f, Member member) {
        this.reportFile = f;
        this.member = member;
    }
    
    public void generateReport() throws IOException {
        FileWriter fW = new FileWriter(reportFile);
        
        try{
        	System.out.println(reportFile);
        	
        	//Write the member properties
        	int thisMemberId = member.getId();
        	
        	fW.write("Member name: " + member.getName() + newLine);
            fW.write("Member number: " + thisMemberId + newLine); 
	        fW.write("Member address: " + member.getAddressStreet() + newLine);
	        fW.write("Member city: " + member.getAddressCity() + newLine);
	        fW.write("Member state: " + member.getAddressState() + newLine);
	        fW.write("Member ZIP code: " + member.getAddressZipCode() + newLine + newLine);
	        
	        ArrayList<ProvidedService> allServices = ChocAnMain.providedServiceDatabase.getEntryList();
	        for (int i = 0; i < allServices.size(); ++i) {
	        	//Get all the services provided to the member
	            ProvidedService s = allServices.get(i);
	            if (s.getMemberId() == thisMemberId) {
	            	
	                fW.write("Date provided: " + s.getDateProvided() + newLine);
	                
	                //Get the provider of the service
	                Provider provider = ChocAnMain.providerDatabase.getEntry(s.getProviderId());
	                fW.write("Provider name: " + provider.getName() + newLine);
	                
	                //Get the service provided
	                Service service = ChocAnMain.providerDirectoryDatabase.getEntry(s.getServiceId());
	                fW.write("Service name: " + service.getName() + newLine + newLine);
	            }
	        }
        }
        catch (Exception e){
        	System.out.println(e);}
    fW.flush();
    fW.close();
    }
}
