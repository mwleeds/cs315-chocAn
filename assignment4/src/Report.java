package chocAn;

import java.io.*;

/**
 * Parent class for all Reports.
 * @author mleeds95
 */
public abstract class Report {
    public abstract void generateReport() throws IOException;
    public File file;
}
