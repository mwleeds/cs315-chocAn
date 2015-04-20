import java.io.*;

import database.Member;

public class MemberReport extends Report {
    private static Member member;
    public MemberReport(File f, Member member) {
        this.file = f;
        this.member = member;
    }
    public void generateReport() throws IOException {
        FileWriter fW = new FileWriter(this.file);
        fW.write("test");  
        fW.close();
    }
}
