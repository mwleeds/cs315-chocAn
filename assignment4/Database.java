

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Database {

    /***
     * The class all database members must inherit from so they can be given ids
     */
    public static class DatabaseMember implements Serializable{

        //The id of the member in the database that can only be set once
        private int id = -1;

        /***
         * Gets the id of the database member
         * @return  the id
         */
        public int getId() throws Exception{
            if (id != -1){
                return id;}
            throw new Exception("The id cannot be relieved until the object is added to the database");
        }

        /***
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

    private String fileName;  //The name of the database file
    private ArrayList<DatabaseMember> entryList;  //An array list that holds the database entries

    /***
     * Creates or loads the database if it already exists
     * @param fileName  the file name of the database
     */
    public Database(String fileName){

        //Load the database if it exists
        this.fileName = fileName;
        File databaseFile = new File(fileName);
        if (databaseFile.exists()){
            load();}
        else{
            //Create a new database file if it does not
            try{
                databaseFile.createNewFile();
            } catch (Exception e) {}
            entryList = new ArrayList<DatabaseMember>();}
    }

    /***
     * Takes a string representation and deserializes it into an object
     * @return      The object representation of the string
     */
    private void load(){
        try {
            //Read in the contents of the database file
            BufferedInputStream fileOut = new BufferedInputStream(new FileInputStream(fileName));
            byte[] fileBytes =  Files.readAllBytes(Paths.get(fileName));

            //Convert the file contents into the object array
            ByteArrayInputStream byteStream = new ByteArrayInputStream(fileBytes);
            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
            entryList = (ArrayList<DatabaseMember>) objectStream.readObject();

            //Close the files
            byteStream.close();
            objectStream.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());  //Catch out any exceptions
        }
    }

    /***
     * Takes an object and serializes it into a string representation
     * @return      The string representation of the object
     */
    private void save(){
        try {
            //Write the bytes of the object into an output stream

            FileOutputStream byteStream = new FileOutputStream(fileName);
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);

            objectStream.writeObject(entryList);

            //Close the files
            byteStream.close();
            objectStream.close();

        }
        catch (Exception e) {
            System.out.println(e.toString());  //Catch out any exceptions
        }
    }

    /***
     *  Adds an object into the database and returns its id
     * @param entry The object to be entered into the database
     * @return      The id of the object in the database
     */
    public int addEntry(DatabaseMember entry){
        try{

            //Add the entry to the entry list
            int id = entryList.size();

            entry.setId(this, id);
            entryList.add(entry);

            save();  //Save the new database

            return id;}
        catch (Exception e){
            System.out.println(e);
            System.out.println("Cannot not add entry because the entry already exists");
        }

        return -1;
    }

    /***
     * Returns the object stored in the database with the specified id
     * @param id    The id of the object to be returned
     * @return      The object with the specified id or null if no object exists
     */
    public DatabaseMember getEntry(int id){
        return entryList.get(id);
    }

    /***
     * Returns an array list containing the database members
     * @return  the array list of the entries
     */
    public ArrayList<DatabaseMember> getEntryList(){
        return (ArrayList<DatabaseMember>) entryList.clone();
    }

    /***
     *
     * @param entry
     */
    public void removeEntry(DatabaseMember entry){
        try{
            int id = entry.getId();
            entryList.set(id,null);
            save();}
        catch (Exception e){
            System.out.println(e);
            System.out.print("The entry has not been added to the database so it cannot be removed");
        }
    }

    /***
     * Updates the object stores in the database with the specified id
     * @param entry The object to replace the old object
     */
    public void updateEntry(DatabaseMember entry){
        try{
            int id = entry.getId();
            entryList.set(id,entry);
            save();}
        catch (Exception e){
            System.out.println(e);
        }
    }

}

