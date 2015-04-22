import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.Iterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.DecimalFormat;
import database.*;

/**
 * The SummaryReport class generates report files for managers with totals for the week.
 * It can also generate Electronic Funds Transfer data files.
 * @author Matthew Leeds
 */
public class SummaryReport extends Report {

    private Map<Integer, Double> totalFees;
    private Map<Integer, Integer> numConsultations;

    public SummaryReport(File summaryFile) {
        this.file = summaryFile;
    }

    /**
     * For each provider who has provided services in the past week, this prints their 
     * total number of consultations and amount to be paid.
     */
    public void generateReport() throws IOException {
        FileWriter fW = new FileWriter(this.file);
        // Find all providers who provided services in the past week.
        ArrayList<ProvidedService> allServices = ChocAnMain.providedServiceDatabase.getEntryList();
        // List of providers who provided services in the past week.
        ArrayList<Provider> relevantProviders = new ArrayList<Provider>();
        // This maps Provider IDs to their total number of consultations.
        this.numConsultations = new HashMap<Integer, Integer>();
        // This maps Provider IDs to Fee totals.
        this.totalFees = new HashMap<Integer, Double>();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Date now = new Date();
        Date aWeekAgo = new Date(now.getTime() - 7 * 24 * 3600 * 1000);
        long overallFeeTotal = 0;
        for (int i = 0; i < allServices.size(); ++i) {
            ProvidedService p = allServices.get(i);
            int providerId;
            try { providerId = p.getProviderId(); }
            catch (Exception e) { providerId = -1 * i; }
            Date timeInput = null;
            try { timeInput = dateFormat.parse(p.getDateInput()); }
            catch (ParseException e) {System.out.println(e.getMessage());}
            if (timeInput.after(aWeekAgo)) {
                int serviceId = p.getServiceId();
                Service s = ChocAnMain.providerDirectoryDatabase.getEntry(serviceId);
                if (!this.numConsultations.containsKey(providerId)) {
                    this.numConsultations.put(providerId, 1);
                    this.totalFees.put(providerId, s.getFee());
                    relevantProviders.add(ChocAnMain.providerDatabase.getEntry(providerId));
                } else {
                    int prevNumConsultations = this.numConsultations.get(providerId);
                    this.numConsultations.put(providerId, ++prevNumConsultations);
                    double prevFeeTotal = this.totalFees.get(providerId);
                    this.totalFees.put(providerId, prevFeeTotal + s.getFee());
                }
                overallFeeTotal += s.getFee();
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        for (int i = 0; i < relevantProviders.size(); ++i) {
            Provider thisProvider = relevantProviders.get(i);
            int thisProviderId;
            try { thisProviderId = thisProvider.getId(); }
            catch (Exception e) { thisProviderId = -1; }
            fW.write("Provider name: " + thisProvider.getName() + newLine);
            fW.write("Provider " + i + " number: " + thisProviderId + newLine);
            fW.write("Number of consultations: " + this.numConsultations.get(thisProviderId) + newLine);
            fW.write("Total fee: " + df.format(this.totalFees.get(thisProviderId)) + newLine);
        }
        fW.write("\nTotal number of providers: " + relevantProviders.size() + newLine);
        fW.write("Total of all fees: " + df.format(overallFeeTotal) + newLine);
        fW.close();
    }
    
    /**
     * This writes to the disk the fee totals for each provider with services provided in the past week,
     * which will be read by Acme Accounting Services.
     */ 
    public void generateEFTReport(File eftFile) throws IOException {
        FileWriter fW = new FileWriter(eftFile);
        Iterator<Integer> itr = this.totalFees.keySet().iterator();
        while (itr.hasNext()) {
            int providerId = itr.next();
            double providerFee = this.totalFees.get(providerId);
            fW.write(providerId + "," + providerFee + newLine);
        }
        fW.close();
    }
}
