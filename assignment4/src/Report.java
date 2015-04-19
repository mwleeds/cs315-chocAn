import java.io.*;

/**
 * Parent class for all Reports.
 * @author Matthew Leeds
 */
public abstract class Report {
    public abstract void generateReport() throws IOException;
    public File file;
}
