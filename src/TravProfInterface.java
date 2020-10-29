import java.io.IOException;
import java.util.Scanner;


public class TravProfInterface {

    private String fileName;
    private int selection;
    private TravProfDB db;
    private String travAgentID;

    public TravProfInterface(String fileName) {
        this.fileName = fileName;
        this.db = null;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TravProfInterface tpi = new TravProfInterface("TravelerDatabase.txt");
        tpi.initDB();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ITS, please enter your ITS ID:");
        String id = scanner.nextLine();

        // Stores the current agent ID
        tpi.travAgentID = id;

        while (true){

            tpi.getUserChoice();
            int choice = tpi.getSelection();

            if (choice == 1) {
                tpi.createNewTravProf();

            } else if (choice == 2) {
                tpi.findTravProf();

            } else if (choice == 3) {
                tpi.deleteTravProf();

            } else if (choice == 4) {
                tpi.updateTravProf();

            } else if (choice == 5) {
                tpi.displayAllTravProf();

            } else if (choice == 6) {
                System.out.println("Enter your Agent ID");
                String agent = scanner.nextLine();
                tpi.setTravAgentID(agent);

            } else if (choice == 0) {
                tpi.writeToDB();
                break;
            }
        }
    }


    public void getUserChoice(){

        // Print options
        System.out.println("(1) Enter New Traveler Profile");
        System.out.println("(2) Find Profile");
        System.out.println("(3) Delete Profile");
        System.out.println("(4) Update Profile");
        System.out.println("(5) Display All Profiles");
        System.out.println("(6) Change ITS ID");
        System.out.println("(0) Exit");

        Scanner in = new Scanner(System.in);
        this.selection = in.nextInt();
    }

    public void deleteTravProf(){
        boolean success = false;

        System.out.println("Enter the traveler's last name.");

        Scanner in = new Scanner(System.in);
        String lstName = in.nextLine();

        String agentID = getTravAgentID();
        TravProfDB db = getDB();

        success = db.deleteProfile(agentID, lstName);

        if (success) {
            System.out.println("Profile has been successfully deleted.");
        } else {
            System.out.println("Unable to delete profile.");
        }
    }

    public void findTravProf() {
        System.out.println("Please enter the last name of the traveler");
        Scanner in = new Scanner(System.in);
        String lstName = in.nextLine();

        TravProfDB db = getDB();
        String agID = getTravAgentID();
        TravProf currTP = db.findProfile(lstName, agID);
        displayTravProf(currTP);
    }

    public void updateTravProf(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the traveler's last name.");
        String lstName = in.nextLine();

        System.out.println("What would you like to update?");
        System.out.println("(1) Update address");
        System.out.println("(2) Update phone number");
        System.out.println("(3) Update Travel Type");
        System.out.println("(4) Update trip cost");
        System.out.println("(5) Update payment type");
        System.out.println("(6) Update physician contact");
        System.out.println("(7) Update physician phone number");
        System.out.println("(8) Update allergy type");
        System.out.println("(9) Update illness type");
        System.out.println("Enter the option number");

        int choice = in.nextInt();
        TravProfDB dB = getDB();
        String agentID = getTravAgentID();
        TravProf traveler = dB.findProfile(lstName, agentID);
        MedCond travMed = traveler.getMedCondInfo();

        if (choice == 1) {
            String address = in.nextLine();
            traveler.updateAddress(address);

        } else if (choice == 2) {
            String phone = in.nextLine();
            traveler.updatePhone(phone);

        } else if (choice == 3) {
            String travType = in.nextLine();
            traveler.updateTravelType(travType);

        } else if (choice == 4) {
            float cost = in.nextFloat();
            traveler.updateTripCost(cost);

        } else if (choice == 5) {
            String payType = in.nextLine();
            traveler.updatePaymentType(payType);

        } else if (choice == 6) {
            String physContact = in.nextLine();
            travMed.updateMdContact(physContact);

        } else if (choice == 7) {
            String physNum = in.nextLine();
            travMed.updateMdPhone(physNum);

        } else if (choice == 8) {
            String allergy = in.nextLine();
            travMed.updateAlgType(allergy);

        } else if (choice == 9) {
            String illness = in.nextLine();
            travMed.updateIllType(illness);
        }
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
        TravProfDB dB = getDB();
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
        cost = in.nextFloat();

        MedCond medical = createNewMedCond();
        TravProf traveler = new TravProf(agent, fName, lName, address, phoneNum, cost, travType, payType, medical);
        dB.insertNewProfile(traveler);

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
        return travAgentID;
    }

    public void setTravAgentID(String agent) {
        travAgentID = agent;
    }
}
