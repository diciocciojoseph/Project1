public class TravProfDB {

    private int numTravelers;           // Holds total # of profiles in travelerList
    private int currentTravelerIndex;   // Points to the next open spot in travelerList (null)
    private String fileName;            // Holds name of file the profiles are stored in
    private TravProf[] travelerList;    // Holds TravProf object instances

    // Constructor initialize variables
    public TravProfDB(String fileName){
        this.fileName = fileName;
        this.numTravelers = 0;
        this.travelerList = new TravProf[10];
        this.currentTravelerIndex = 0;
    }

    // Insert a TravProf object into the travelerList
    private void insertNewProfile(TravProf profile){

        // Resize travelerList if needed (create new with x2 size, copy over profiles)
        if (this.currentTravelerIndex >= this.travelerList.length){
            TravProf[] newTravelerList = new TravProf[this.numTravelers*2];
            for(int i = 0; i < this.travelerList.length; i++){
                newTravelerList[i] = this.travelerList[i];
            }
            this.travelerList = newTravelerList;
        }

        // Add the entry to travelerList, increment index and size
        this.travelerList[this.currentTravelerIndex] = profile;
        this.currentTravelerIndex++;
        this.numTravelers++;
    }

    // Delete a TravProf object from the travelerList
    // Return whether the deletion was successful
    private boolean deleteProfile(String travID, String lName){

        boolean deleted = false;

        // Search for the profile with the given ID and lName
        for(int i = 0; i < this.currentTravelerIndex; i++){
            TravProf currentProf = this.travelerList[i];
            if(currentProf.getTravAgentID().equals(travID) && currentProf.getLastName().equals(lName)){

                // Delete the desired entry by setting it to null
                this.travelerList[i] = null;

                // Shift entries over from the right
                for(int j = i; j < currentTravelerIndex; j++){
                    this.travelerList[j] = this.travelerList[j+1];
                }

                // Update index and size, mark the deletion as successful
                this.currentTravelerIndex--;
                this.numTravelers--;
                deleted = true;
            }
        }

        // Return success of deletion
        return deleted;
    }

    // Write the current contents of all traveler profiles into the file
    private void writeAllTravProf(String fName){
        // Plan to simply print each attribute of each element on it's own line
        // Can we use Java in-built object serialization interface?
    }

    // Fetch all current content of DB file into traveler array
    private void initializeDatabase(String fName){
        // Read from file / deserialize
    }
}
