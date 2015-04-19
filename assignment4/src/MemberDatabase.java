package chocAn;

/**
 * Created by user on 4/15/2015.
 */
public class MemberDatabase{

    //The file name of the database
    private static final String fileName = "member_database.dat";

    //Instantiate the member database
    private static final Database mDatabase = new Database(fileName);

    /***
     * Adds a member who has not been added yet to the member database
     * @param member    the member instance to add
     * @return          the id of the member
     */
    public static int addMember(Member member){
        return mDatabase.addEntry(member);
    }

    /***
     * Gets the member with the specified id from the database
     * @param id    the id to request
     * @return      the member with the corresponding id or null if none exists
     */
    public static Member getMember(int id){
        return (Member) mDatabase.getEntry(id);
    }

    /***
     *  Updates the member in the database
     * @param member
     */
    public static void updateMember(Member member){
        mDatabase.updateEntry(member);
    }

}
