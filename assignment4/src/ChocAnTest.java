import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChocAnTest {

  private Provider p;
  private Member m;
  private Service s;
  private double fee;
  private Report r;
  
  @Before
  public void setUp() {
    p = new Provider("Rachel Smith, "456 Carol Street", "Sutton", "56789", "MA");
    m = new Member("Jacob Farmer", "Suspended", "123 Maple Street", "Farmington", "12345", "WI");
    s = new Service();
    r = new Report();
  }
  
  @Test
  public void testProvider() {
    p.setName("Rachel Smith");
    
    p.getName();
  }
  
  @Test
  public void testMember() {
    m.setName("Jacob Farmer");
    
    m.getname();
  }
  
  @Test
  public void testService() {
    double fee = 99.99;
    s.setFee(fee);
    
    s.getFee();
  }
    
  @Test
  public void testReport() {
    
  }
}
