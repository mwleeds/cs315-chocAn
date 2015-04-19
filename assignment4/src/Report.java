package chocAn;

/**
 * Parent class for all Reports.
 * @author mleeds95
 */
public abstract class Report {
    public static abstract void generateReport();
    private static String fileName;
    public static String getFileName() {
        return fileName;
    }
    public static void setFilename(String f) {
        fileName = f;
    }
}
