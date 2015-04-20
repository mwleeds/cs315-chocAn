import org.junit.*;
import static org.junit.Assert.*;
import database.*;

public class ChocAnTest {
  private Provider p;
  private Member m;
  private Service s;
  //private ChocAnMain c;
  private String memberDatabaseFile = "member_database.dat";
  public Database<Member> memberDatabase = new Database<Member>(memberDatabaseFile);
  
  @Before
  public void setUp() {
    p = new Provider("Rachel Smith", "456 Carol Street", "Sutton", "56789", "MA");
    m = new Member("Jacob Farmer", "Suspended", "123 Maple Street", "Farmington", "12345", "WI");
    s = new Service("talk for a while", 4200);
    //c = new ChocAnMain();
    memberDatabase.clear();
  }
  
  @Test
  public void testProvider() {
    
    assertEquals("Rachel Smith", p.getName());
  }
  
  @Test
  public void testMember() {
    
    assertEquals("123 Maple Street", m.getAddressStreet());
  }
  
  @Test
  public void testService() {
    double fee = 99.99;
    s.setFee(fee);
    
    assertEquals(99.99,fee, s.getFee());
  }
    
  @Test
  public void testDatabase() {
    Member mem1 = new Member ("Josh Turner", "OK", "625 Adams Street", "Orlando", "34443", "CA");
    int memberId = memberDatabase.addEntry(mem1);
    
    memberDatabase = new Database<Member>(memberDatabaseFile);
    
    Member mem2 = memberDatabase.getEntry(0);
    try{
      assertEquals(0, mem2.getId());
    }
    catch (Exception e){
      System.out.println(e);
    }
  }
  
  @Test
  public void testUpdate() {
	Member mem1 = new Member ("Turner Bob", "OK", "343 fulton street", "Tacoma", "34657", "MI");
	int memberId = memberDatabase.addEntry(mem1);
	memberDatabase = new Database<Member>(memberDatabaseFile);
    Member mem3 = memberDatabase.getEntry(0);
    mem3.setName("Adam Clark");
    memberDatabase.updateEntry(mem3);
    
    Member mem4 = memberDatabase.getEntry(0);
    try{
      assertEquals(0, mem4.getId());
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }
}
