/**
 * Created by user on 4/16/2015.
public class TestMain {

    public static void main(String []args) {

        Member m1 = new Member("Ryan", "Address", "City", "Zip", "State");
        Member m2 = new Member("Matthew", "Address2", "City2", "Zip2", "State2");
        Member m3 = new Member("Bre", "Address3", "City3", "Zip3", "State3");

        //MemberDatabase.addMember(m1);
        //MemberDatabase.addMember(m2);
        //MemberDatabase.addMember(m3);

        Member m4 = MemberDatabase.getMember(2);

        m4.name = "New NAme";

        MemberDatabase.updateMember(m4);

        System.out.println(m4.name);


    }
}
 */
