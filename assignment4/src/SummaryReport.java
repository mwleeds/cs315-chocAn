import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import database.*;

/**
 * The SummaryReport class generates report files for managers with totals for each 
 * providers (# of services and fees), and overall totals for the week.
 * @author Matthew Leeds
 */
public class SummaryReport extends Report {
    public SummaryReport(File f) {
        this.file = f;
    }
    public void generateReport() throws IOException {
        FileWriter fW = new FileWriter(this.file);
        // Find all providers who provided services in the past week.
        ArrayList<ProvidedService> allServices = ChocAnMain.providedServiceDatabase.getEntryList();
        // List of providers who provided services in the past week.
        ArrayList<Provider> relevantProviders = new ArrayList<Provider>();
        // This maps Provider IDs to their total number of consultations.
        Map<Integer, Integer> numConsultations = new HashMap<Integer, Integer>();
        // This maps Provider IDs to Fee totals.
        Map<Integer, Double> totalFees = new HashMap<Integer, Double>();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-YYYY HH:mm:ss");
        Date now = new Date();
        Date aWeekAgo = new Date(now.getTime() - 7 * 24 * 3600 * 1000);
        long overallFeeTotal = 0;
        for (int i = 0; i < allServices.size(); ++i) {
            ProvidedService p = allServices.get(i);
            int providerId;
            try { providerId = p.getProviderId(); }
            catch (Exception e) { providerId = -1 * i; }
            Date timeInput = now;
            try { timeInput = dateFormat.parse(p.getDateInput()); }
            catch (ParseException e) {}
            if (timeInput.after(aWeekAgo)) {
                int serviceId = p.getServiceId();
                Service s = ChocAnMain.providerDirectoryDatabase.getEntry(serviceId);
                if (!numConsultations.containsKey(providerId)) {
                    numConsultations.put(providerId, 1);
                    totalFees.put(providerId, s.getFee());
                    relevantProviders.add(ChocAnMain.providerDatabase.getEntry(providerId));
                } else {
                    int prevNumConsultations = numConsultations.get(providerId);
                    numConsultations.put(providerId, ++prevNumConsultations);
                    double prevFeeTotal = totalFees.get(providerId);
                    totalFees.put(providerId, prevFeeTotal + s.getFee());
                }
                overallFeeTotal += s.getFee();
            }
        }
        for (int i = 0; i < relevantProviders.size(); ++i) {
            Provider thisProvider = relevantProviders.get(i);
            int thisProviderId;
            try { thisProviderId = thisProvider.getId(); }
            catch (Exception e) { thisProviderId = -1; }
            fW.write(thisProviderId + "\n");
            fW.write(numConsultations.get(thisProviderId) + "\n");
            fW.write(totalFees.get(thisProviderId) + "\n");
        }
        fW.write(relevantProviders.size() + "\n");
        fW.write(overallFeeTotal + "\n");
        fW.close();
    }
}
