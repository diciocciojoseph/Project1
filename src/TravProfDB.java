import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TravProfDB {

    private int numTravelers;                    // Holds total # of profiles in travelerList
    private int currentTravelerIndex;            // Points to the current profile
    private String fileName;                     // Holds name of file the profiles are stored in
    private ArrayList<TravProf> travelerList;    // Holds TravProf object instances

    // Constructor initialize variables
    public TravProfDB(String fileName){
        this.fileName = fileName;
        this.numTravelers = 0;
        this.travelerList = new ArrayList<>();
        this.currentTravelerIndex = 0;
    }

    // Insert a TravProf object into the travelerList
    private void insertNewProfile(TravProf profile){
        // Add the entry to travelerList, increment index and size
        this.travelerList.add(profile);
        this.currentTravelerIndex++;
        this.numTravelers++;
    }

    // Delete a TravProf object from the travelerList
    // Return whether the deletion was successful
    private boolean deleteProfile(String travID, String lName){
        boolean deleted = false;

        // Search for the profile with the given ID and lName
        for(int i = 0; i < this.currentTravelerIndex; i++){
            TravProf currentProf = this.travelerList.get(i);
            if(currentProf.getTravAgentID().equals(travID) && currentProf.getLastName().equals(lName)){

                // Delete the desired entry
                this.travelerList.remove(currentProf);

                // Update index and size, mark the deletion as successful
                this.currentTravelerIndex--;
                this.numTravelers--;
                deleted = true;
            }
        }
        // Return success of deletion
        return deleted;
    }

    private TravProf findFirstProfile(){
        return this.travelerList.get(0);
    }

    private TravProf findNextProfile(){
        return this.travelerList.get(this.currentTravelerIndex);
    }

    // Write the current contents of all traveler profiles into the file
    private void writeAllTravProf(String fName) throws IOException{

        // Create an object output stream, and serialize the travelers list
            FileOutputStream f = new FileOutputStream(fileName);
            ObjectOutputStream s = new ObjectOutputStream(f);
            s.writeObject(this.travelerList);
            s.close();
    }

    // Fetch all current content of DB file into traveler array
    private void initializeDatabase(String fName) throws IOException, ClassNotFoundException{

        // Create an object input stream, read from the database into the travelers list
            FileInputStream f = new FileInputStream(fName);
            ObjectInputStream s = new ObjectInputStream(f);
            this.travelerList = (ArrayList<TravProf>) s.readObject();
    }
}
