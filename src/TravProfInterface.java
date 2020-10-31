import java.io.IOException;
import java.util.Scanner;


public class TravProfInterface {

    private String fileName;            // Holds database file name
    private int selection;              // Variable to keep track of user menu selection
    private TravProfDB db;              // Database object to read / write to DB after execution
    private String curTravAgentID;      // Holds the ID of the travel agent interacting with the ITS

    public TravProfInterface(String fileName) {
        this.fileName = fileName;
        this.db = null;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Initialize an interface object, then initialize the database
        TravProfInterface travInterface = new TravProfInterface("TravelerDatabase.txt");
        travInterface.initDB();

        // Get the ITS user's ID,
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ITS, please enter your ITS ID:");
        travInterface.curTravAgentID = scanner.nextLine();;

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

        // Add this for all inputs
        try {
            this.selection = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e){
            this.selection = -1;
            System.out.println("Please enter a number as an option.");
        }
    }

    // Delete a TravProf give an last name and travProfID
    // TODO: The user cannot delete a profile unless they created it
    public void deleteTravProf(){
        boolean success = false;

        System.out.println("Enter the traveler's last name.");
        Scanner in = new Scanner(System.in);
        String lstName = in.nextLine();

        System.out.println("Enter the traveler's ID.");
        String travProfID = in.nextLine();

        TravProfDB db = getDB();
        success = db.deleteProfile(travProfID, lstName);

        if (success) {
            System.out.println("Profile has been successfully deleted.");
        } else {
            System.out.println("Unable to delete profile.");
        }
    }

    // Finds and displays a traveler profile based on last name and travAgentID
    public void findTravProf() {
        System.out.println("Please enter the last name of the traveler.");
        Scanner in = new Scanner(System.in);
        String lstName = in.nextLine();

        System.out.println("Please enter their ID.");
        String travAgentID = in.nextLine();

        TravProfDB db = getDB();

        TravProf curTP = db.findProfile(lstName, travAgentID);

        if(curTP == null){
            System.out.println("Sorry the requested profile was not found.");
            return;
        }

        displayTravProf(curTP);
    }

    // Update a traveler profile
    public void updateTravProf(){
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


        System.out.println("What would you like to update?");
        System.out.println("(1) Update address");
        System.out.println("(2) Update phone number");
        System.out.println("(3) Update travel type");
        System.out.println("(4) Update trip cost");
        System.out.println("(5) Update payment type");
        System.out.println("(6) Update physician contact");
        System.out.println("(7) Update physician phone number");
        System.out.println("(8) Update allergy type");
        System.out.println("(9) Update illness type");
        System.out.println("Enter the option number");

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

        } else if (choice == 3) {
            System.out.println("Please enter the new travel type");
            String travType = in.nextLine();
            traveler.updateTravelType(travType);

        } else if (choice == 4) {
            System.out.println("Please enter the new trip cost.");
            float cost = in.nextFloat();
            traveler.updateTripCost(cost);

        } else if (choice == 5) {
            System.out.println("Please enter the new payment type.");
            String payType = in.nextLine();
            traveler.updatePaymentType(payType);

        } else if (choice == 6) {
            System.out.println("Please enter the new physician contact.");
            String physContact = in.nextLine();
            travMed.updateMdContact(physContact);

        } else if (choice == 7) {
            System.out.println("Please enter the new physician phone number.");
            String physNum = in.nextLine();
            travMed.updateMdPhone(physNum);

        } else if (choice == 8) {
            System.out.println("Please enter the new allergy type.");
            String allergy = in.nextLine();
            travMed.updateAlgType(allergy);

        } else if (choice == 9) {
            System.out.println("Please enter the new illness type.");
            String illness = in.nextLine();
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

    public void displayAllTravProf(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Agent ID");
        String agentID = in.nextLine();
        TravProfDB db = getDB();

        for(TravProf t : db.getTravelerList()){
            displayTravProf(t);
        }
    }

    public void writeToDB() throws IOException {
        TravProfDB dB = getDB();
        String file = getFileName();
        dB.writeAllTravProf(file);
    }

    public void initDB() throws IOException, ClassNotFoundException {
        this.db = new TravProfDB(this.fileName);
        db.initializeDatabase(this.fileName);
    }

    public TravProf createNewTravProf(){
        Scanner in = new Scanner(System.in);
        TravProfDB db = getDB();
        String agent, fName, lName, address, phoneNum, travType, payType = "";
        float cost = 0;

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

        System.out.println("Enter trip type");
        travType = in.nextLine();

        System.out.println("Enter payment type");
        payType = in.nextLine();

        System.out.println("Enter the cost of the trip");
        cost = Float.parseFloat(in.nextLine());

        MedCond medical = createNewMedCond();
        TravProf traveler = new TravProf(agent, fName, lName, address, phoneNum, cost, travType, payType, medical);
        db.insertNewProfile(traveler);

        return traveler;
    }

    public MedCond createNewMedCond() {
        Scanner in = new Scanner(System.in);
        String contact, phone, allergy, illness = "";

        System.out.println("Enter physician name");
        contact = in.nextLine();

        System.out.println("Enter physician's phone number");
        phone = in.nextLine();

        System.out.println("Enter allergy if you have one, else write none");
        allergy = in.nextLine();

        System.out.println("Enter illness if you have one, else write none");
        illness = in.nextLine();

        return new MedCond(contact, phone, allergy, illness);
    }

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

    public void setTravAgentID(String agent) {
        curTravAgentID = agent;
    }
}
