import java.io.IOException;
import java.util.Scanner;


public class TravProfInterface {

    private String fileName;            // Holds database file name
    private int selection;              // Variable to keep track of user menu selection
    private TravProfDB db;              // Database object to read / write to DB after execution
    private String curTravAgentID;      // Holds the ID of the travel agent interacting with the ITS

    // Constructor initialize filename
    public TravProfInterface(String fileName) {
        this.fileName = fileName;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Initialize an interface object, then initialize the database
        TravProfInterface travInterface = new TravProfInterface("TravelerDatabase.txt");
        travInterface.initDB();

        // Get the ITS user's ID,
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ITS, please enter your ITS ID:");
        travInterface.curTravAgentID = scanner.nextLine();

        // Main loop to allow the user to interact with the menu
        while (true){

            // After every iteration, get the user choice, and perform the corresponding action
            travInterface.getUserChoice();
            int choice = travInterface.getSelection();

            if (choice == 1) {
                travInterface.createNewTravProf();

            } else if (choice == 2) {
                travInterface.findTravProf();

            } else if (choice == 3) {
                travInterface.deleteTravProf();

            } else if (choice == 4) {
                travInterface.updateTravProf();

            } else if (choice == 5) {
                travInterface.displayAllTravProf();

            } else if (choice == 6) {
                System.out.println("Enter your Agent ID");
                String agent = scanner.nextLine();
                travInterface.setTravAgentID(agent);

            } else if (choice == 0) {
                travInterface.writeToDB();
                break;
            }
        }
    }

    // Function to print menu options and get a selection from the user
    public void getUserChoice(){

        // Print options
        System.out.println("");
        System.out.println("(1) Enter New Traveler Profile");
        System.out.println("(2) Find Profile");
        System.out.println("(3) Delete Profile");
        System.out.println("(4) Update Profile");
        System.out.println("(5) Display All Profiles");
        System.out.println("(6) Change ITS ID");
        System.out.println("(0) Exit");

        Scanner in = new Scanner(System.in);

        // Get the user selection
        try {
            this.selection = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e){
            this.selection = -1;
            System.out.println("Please enter a number as an option.");
        }
    }

    // Delete a TravProf give an last name and travProfID
    public void deleteTravProf(){
        boolean success = false;

        // Get input name and ID
        System.out.println("Enter the traveler's last name.");
        Scanner in = new Scanner(System.in);
        String lstName = in.nextLine();

        System.out.println("Enter the traveler's ID.");
        String travProfID = in.nextLine();

        // Use db object to attempt to find and delete the given profile
        TravProfDB db = getDB();
        TravProf toBeDeleted = db.findProfile(lstName, travProfID);

        if(toBeDeleted == null){
            System.out.println("Given profile not found.");
            return;
        }

        // Conditionally check to see if the profile was created by the user, if so deletion is possible, else it fails
        if(toBeDeleted.getCreatedByAgentID().equals(getTravAgentID())){
            success = db.deleteProfile(travProfID, lstName);

            if (success) {
                System.out.println("Profile has been successfully deleted.");
            } else {
                System.out.println("Unable to delete profile.");
            }
        } else {
            System.out.println("Sorry you can only delete profiles that you create.");
        }
    }

    // Finds and displays a traveler profile based on last name and travAgentID
    public void findTravProf() {
        // Get last name and ID as input for identification purposes
        System.out.println("Please enter the last name of the traveler.");
        Scanner in = new Scanner(System.in);
        String lstName = in.nextLine();

        System.out.println("Please enter their ID.");
        String travAgentID = in.nextLine();

        // Use the db object to attept to find the profile
        TravProfDB db = getDB();
        TravProf curTP = db.findProfile(lstName, travAgentID);

        if(curTP == null){
            System.out.println("Sorry the requested profile was not found.");
            return;
        }
        // If the profile was found, we can display it
        displayTravProf(curTP);
    }

    // Update a traveler profile
    public void updateTravProf(){
        // Get last name and ID as input
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the traveler's last name.");
        String lstName = in.nextLine();

        System.out.println("Enter their ID.");
        String travAgentID = in.nextLine();

        // Attempt to find the profile
        TravProfDB dB = getDB();
        TravProf traveler = dB.findProfile(lstName, travAgentID);

        if (traveler == null){
            System.out.println("Sorry the requested profile could not be found");
            return;
        }

        MedCond travMed = traveler.getMedCondInfo();

        // Display menu options
        System.out.println("What would you like to update?");
        System.out.println("(1) Update address.");
        System.out.println("(2) Update phone number.");
        System.out.println("(3) Update travel type.");
        System.out.println("(4) Update trip cost.");
        System.out.println("(5) Update payment type.");
        System.out.println("(6) Update physician contact.");
        System.out.println("(7) Update physician phone number.");
        System.out.println("(8) Update allergy type.");
        System.out.println("(9) Update illness type.");
        System.out.println("Enter the option number.");

        // Get user choice and update fields accordingly
        int choice = Integer.parseInt(in.nextLine());

        if (choice == 1) {
            System.out.println("Please enter the new address.");
            String address = in.nextLine();
            traveler.updateAddress(address);
            System.out.println("Address updated successfully.");

        } else if (choice == 2) {
            System.out.println("Please enter the new phone number");
            String phone = in.nextLine();
            traveler.updatePhone(phone);
            System.out.println("Phone number updated successfully.");

        } else if (choice == 3) {
            System.out.println("Please enter the new travel type: (1) Pleasure, (2) Business");
            int trav_choice = Integer.parseInt(in.nextLine());
            String travType = traveler.getTravelType();

            if (trav_choice == 1){
                travType = "Pleasure";
            } else if (trav_choice == 2){
                travType = "Business";
            } else {
                System.out.println("Invalid choice for trip type, update failed please try again.");
            }
            System.out.println("Travel type updated successfully.");
            traveler.updateTravelType(travType);

        } else if (choice == 4) {
            System.out.println("Please enter the new trip cost.");
            float cost = in.nextFloat();
            traveler.updateTripCost(cost);
            System.out.println("Trip cost updated successfully.");

        } else if (choice == 5) {
            System.out.println("Please enter the new payment type: (1) Credit, (2) Check, (3) Debit, (4) Invoice");
            int pay_choice = Integer.parseInt(in.nextLine());
            String payType = traveler.getPaymentType();
            if (pay_choice == 1){
                payType = "Credit";
            } else if (pay_choice == 2){
                payType = "Check";
            } else if (pay_choice == 3){
                payType = "Debit";
            } else  if (pay_choice == 4){
                payType = "Invoice";
            } else {
                System.out.println("Invalid choice for payment type, update failed please try again.");
            }
            System.out.println("Payment type updated successfully.");
            traveler.updatePaymentType(payType);

        } else if (choice == 6) {
            System.out.println("Please enter the new physician contact.");
            String physContact = in.nextLine();
            travMed.updateMdContact(physContact);
            System.out.println("Physician contact updated successfully.");

        } else if (choice == 7) {
            System.out.println("Please enter the new physician phone number.");
            String physNum = in.nextLine();
            travMed.updateMdPhone(physNum);
            System.out.println("Physician phone updated successfully.");

        } else if (choice == 8) {
            System.out.println("Please enter the new allergy type: (1) None, (2) Food, (3) Medication, (4) Other, please specify in next line");
            String allergy = traveler.getMedCondInfo().getAlgType();
            int allergy_choice = Integer.parseInt(in.nextLine());
            if(allergy_choice == 1){
                allergy = "None";
            } else if (allergy_choice == 2){
                allergy = "Food";
            } else if (allergy_choice == 3){
                allergy = "Medication";
            } else if (allergy_choice == 4){
                System.out.println("Specify your allergy type here:");
                allergy = in.nextLine();
            } else {
                System.out.println("Invalid option for allergy, update failed please try again.");
            }
            System.out.println("Allergy type updated successfully.");
            travMed.updateAlgType(allergy);

        } else if (choice == 9) {
            System.out.println("Please enter the new illness type: (1) None, (2) Heart, (3) Diabetes, (4) Asthma, (5) Other, please specify in next line");
            String illness = traveler.getMedCondInfo().getIllType();
            int illness_choice = Integer.parseInt(in.nextLine());
            if(illness_choice == 1){
                illness = "None";
            } else if (illness_choice == 2){
                illness = "Heart";
            } else if (illness_choice == 3){
                illness = "Diabetes";
            } else if (illness_choice == 4){
                illness = "Asthma";
            } else if (illness_choice == 5) {
                System.out.println("Specify your illness type here:");
                illness = in.nextLine();
            } else {
                System.out.println("Invalid option for illness, field left blank, please update later.");
            }
            System.out.println("Illness type updated successfully.");
            travMed.updateIllType(illness);
        }
        System.out.println("");
    }

    public void displayTravProf(TravProf profile){

        // Info from TravProf
        String agentID = profile.getTravAgentID();
        String FName = profile.getFirstName();
        String LName = profile.getLastName();
        String address = profile.getAddress();
        String phoneNumber = profile.getPhone();
        String travType = profile.getTravelType();
        String payType = profile.getPaymentType();
        float tripCost = profile.getTripCost();
        MedCond medInfo = profile.getMedCondInfo();

        // MedCond
        String mdContact = medInfo.getMdContact();
        String mdPhone = medInfo.getMdPhone();
        String allergy = medInfo.getAlgType();
        String illness = medInfo.getIllType();

        // Format and display fields
        System.out.println("Travel Agent ID: " + agentID);
        System.out.println("Traveler name: " + FName + " " + LName);
        System.out.println("Traveler's address: " + address);
        System.out.println("Traveler's phone number: " + phoneNumber);
        System.out.println("Travel Type: " + travType);
        System.out.println("Payment Type: " + payType);
        System.out.println("Trip cost: " + String.valueOf(tripCost));
        System.out.println("Physician's name: " + mdContact);
        System.out.println("Physician's phone number: " + mdPhone);
        System.out.println("Traveler's allergy: " + allergy);
        System.out.println("Traveler's illness: " + illness);
        System.out.println("");
    }

    // Function it iterate over all travelers and display them
    public void displayAllTravProf(){
        TravProfDB db = getDB();
        for(TravProf t : db.getTravelerList()){
            displayTravProf(t);
        }
    }

    // Write the current contents of the traveler list into the database file
    public void writeToDB() throws IOException {
        TravProfDB dB = getDB();
        String file = getFileName();
        dB.writeAllTravProf(file);
    }

    // Initialize the database, write the contents of the database file into the travelers list
    public void initDB() throws IOException, ClassNotFoundException {
        this.db = new TravProfDB(this.fileName);
        db.initializeDatabase(this.fileName);
    }

    // Function to create a new traveler profile
    public TravProf createNewTravProf(){
        Scanner in = new Scanner(System.in);
        TravProfDB db = getDB();
        // initialize variables
        String agent, fName, lName, address, phoneNum, travType, payType = "";
        float cost = 0;

        // Accept input for each profile field
        System.out.println("Enter traveler agent ID");
        agent = in.nextLine();

        System.out.println("Enter first name");
        fName = in.nextLine();

        System.out.println("Enter last name");
        lName = in.nextLine();

        System.out.println("Enter traveler's address");
        address = in.nextLine();

        System.out.println("Enter phone number");
        phoneNum = in.nextLine();

        System.out.println("Enter trip type: (1) Pleasure, (2) Business");
        int trav_choice = Integer.parseInt(in.nextLine());
        if (trav_choice == 1){
            travType = "Pleasure";
        } else if (trav_choice == 2){
            travType = "Business";
        } else {
            System.out.println("Invalid choice for trip type, field left blank please update later.");
            travType = "";
        }

        System.out.println("Enter payment type: (1) Credit, (2) Check, (3) Debit, (4) Invoice");
        int pay_choice = Integer.parseInt(in.nextLine());

        if (pay_choice == 1){
            payType = "Credit";
        } else if (pay_choice == 2){
            payType = "Check";
        } else if (pay_choice == 3){
            payType = "Debit";
        } else if (pay_choice == 4){
            payType = "Invoice";
        } else {
            System.out.println("Invalid choice for payment type, field left blank please update later.");
            payType = "";
        }

        System.out.println("Enter the cost of the trip");
        cost = Float.parseFloat(in.nextLine());

        // Call the createNewMedCond() function to get the medcond portion
        MedCond medical = createNewMedCond();

        // Finally, create the new TravProf object and insert it into the travelers list using the db object
        TravProf traveler = new TravProf(agent, fName, lName, address, phoneNum,
                cost, travType, payType, medical, getTravAgentID());
        db.insertNewProfile(traveler);

        return traveler;
    }

    // Function to accept user input to create a MedCond object
    public MedCond createNewMedCond() {
        Scanner in = new Scanner(System.in);

        // Get user input for medcond fields
        System.out.println("Enter physician name");
        String contact = in.nextLine();

        System.out.println("Enter physician's phone number");
        String phone = in.nextLine();

        System.out.println("Enter allergy type: (1) None, (2) Food, (3) Medication, (4) Other, please specify in next line");
        String allergy = "";
        int allergy_choice = Integer.parseInt(in.nextLine());
        if(allergy_choice == 1){
            allergy = "None";
        } else if (allergy_choice == 2){
            allergy = "Food";
        } else if (allergy_choice == 3){
            allergy = "Medication";
        } else if (allergy_choice == 4){
            System.out.println("Specify your allergy type here:");
            allergy = in.nextLine();
        } else {
            System.out.println("Invalid option for allergy, field left blank please update later.");
        }

        System.out.println("Enter illness type: (1) None, (2) Heart, (3) Diabetes, (4) Asthma, (5) Other, please specify in next line");
        String illness = "";
        int illness_choice = Integer.parseInt(in.nextLine());
        if(illness_choice == 1){
            illness = "None";
        } else if (illness_choice == 2){
            illness = "Heart";
        } else if (illness_choice == 3){
            illness = "Diabetes";
        } else if (illness_choice == 4){
            illness = "Asthma";
        } else if (illness_choice == 5) {
            System.out.println("Specify your illness type here:");
            illness = in.nextLine();
        } else {
            System.out.println("Invalid option for illness, field left blank, please update later.");
        }

        // Create the new medcond object and return it
        return new MedCond(contact, phone, allergy, illness);
    }

    // Get methods
    public String getFileName() {
        return fileName;
    }

    public int getSelection() {
        return selection;
    }

    public TravProfDB getDB() {
        return db;
    }

    public String getTravAgentID() {
        return curTravAgentID;
    }

    // Set methods
    public void setTravAgentID(String agent) {
        curTravAgentID = agent;
    }
}
