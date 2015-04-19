package chocAn;

import java.security.InvalidParameterException;

/**
 * @author Ryan Mitchell
 */
public class MemberDatabase{

    //The file name of the database
    private static final String fileName = "member_database.dat";

    //Instantiate the member database
    private static final Database mDatabase = new Database(fileName);

    /**
     * Adds a member who has not been added yet to the member database
     * @param member    the member instance to add
     * @return          the id of the member
     */
    public static int addMember(Member member){
        return mDatabase.addEntry(member);
    }

    /**
     * Gets the member with the specified id from the database
     * @param id    the id to request
     * @return      the member with the corresponding id or null if none exists
     */
    public static Member getMember(int id) throws InvalidParameterException {
        try {
            return (Member) mDatabase.getEntry(id);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidParameterException();
        }
    }

    /**
     * Updates the member in the database
     * @param member
     */
    public static void updateMember(Member member){
        mDatabase.updateEntry(member);
    }

}
