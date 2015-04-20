package database;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This is the database class used to save objects to files for use later.
 * All database members must inherit from DatabaseEntry in order to work in the database.
 * Entries can be added, returned, removed, and updated after their values are changed.
 * @author Ryan Mitchell
 */
public final class Database<T> {

    /**
     * The class all database members must inherit from so they can be given ids
     */
    public static class DatabaseEntry implements Serializable{

        //The id of the member in the database that can only be set once
        private int id = -1;

        /**
         * Gets the id of the database member
         * @return  the id
         */
        public int getId() throws Exception{
            if (id != -1){
                return id;}
            throw new Exception("The id cannot be relieved until the object is added to the database");
        }

        /**
         * Sets the id of the database member. It can only be set once when it is added to the database.
         * @param obj   the object calling set id
         * @param id    the id of the database object
         */
        public void setId(Object obj, int id) throws Exception{
            if (this.id == -1 && obj instanceof Database){
                this.id = id;
                return;
            }
            throw new Exception("The database member id has previously been set");
        }
    }

    //The name of the database file
    private String fileName;

    //An array list that holds the database entries
    private ArrayList<DatabaseEntry> entryList = new ArrayList<DatabaseEntry>();

    /**
     * Creates or loads the database if it already exists
     * @param fileName  the file name of the database
     */
    public Database(String fileName){

        //Load the database if it exists
        this.fileName = fileName;
        File databaseFile = new File(fileName);
        if (databaseFile.exists()) {
            load();
        } else {
            //Create a new database file if it does not
            try {
                databaseFile.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            entryList = new ArrayList<DatabaseEntry>();}
    }

    /**
     * Loads the database from the file
     */
    private void load(){
        try {
            //Read in the contents of the database file
            BufferedInputStream fileOut = new BufferedInputStream(new FileInputStream(fileName));
            byte[] fileBytes =  Files.readAllBytes(Paths.get(fileName));
            
            if (fileBytes.length == 0){
            	return;}

            //Convert the file contents into the object array
            ByteArrayInputStream byteStream = new ByteArrayInputStream(fileBytes);
            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
            entryList = (ArrayList<DatabaseEntry>) objectStream.readObject();

            //Close the files
            byteStream.close();
            objectStream.close();
        } catch (Exception e) {
            System.out.println(e.toString());  //Catch out any exceptions
        }
    }

    /**
     * Saves the database to the file
     */
    private void save() {
        try {
            //Write the bytes of the object into an output stream
            FileOutputStream byteStream = new FileOutputStream(fileName);
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

            objectStream.writeObject(entryList);

            //Close the files
            byteStream.close();
            objectStream.close();

        } catch (Exception e) {
            System.out.println(e.toString());  //Catch out any exceptions
        }
    }

    /***
     * Adds an object into the database and returns its id
     * @param entry The object to be entered into the database
     * @return      The id of the object in the database
     */
    public int addEntry(DatabaseEntry entry) {
        try {
            //Add the entry to the entry list
            int id = entryList.size();

            entry.setId(this, id);
            entryList.add(entry);

            save();  //Save the new database

            return id;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Cannot not add entry because the entry already exists");
        }

        return -1;
    }

    /**
     * Returns the object stored in the database with the specified id
     * @param id    The id of the object to be returned
     * @return      The object with the specified id or null if no object exists
     */
    public T getEntry(int id){
    	if (id >= 0 && id < entryList.size()){
    		return (T) entryList.get(id);}
    	return null;
    }

    /**
     * Returns an array list containing the database members
     * @return  the array list of the entries
     */
    public ArrayList<T> getEntryList() {
        ArrayList<T> outList = new ArrayList<T>();
        for (int i=0;i<entryList.size();i++){
            outList.add((T) entryList.get(i));
        }
        return outList;
    }
    /**
     * Remove the entry from the database
     * @param entry the entry to remove
     */
    public void removeEntry(DatabaseEntry entry){
        try {
            int id = entry.getId();
            entryList.set(id,null);
            save();
        } catch (Exception e) {
            System.out.println(e);
            System.out.print("The entry has not been added to the database so it cannot be removed");
        }
    }

    /**
     * Gets the number of entries in the database
     * @return  the number of entries in the database
     */
    public int size() {
        return entryList.size();
    }

    /**
     * Updates the object stores in the database with the specified id
     * @param entry The object to replace the old object
     */
    public void updateEntry(DatabaseEntry entry){
        try{
            int id = entry.getId();
            entryList.set(id,entry);
            save();}
        catch (Exception e){
            System.out.println(e);
        }
    }

}

