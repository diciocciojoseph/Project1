import java.util.Scanner;

public class TravProfInterface {

    private String fileName;

    public TravProfInterface(String fileName){
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        TravProfInterface tpi = new TravProfInterface("TravelerDatabase.txt");
        TravProfDB db = new TravProfDB(tpi.getFileName());
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Welcome to ITS, please enter your ITS ID:");
            String id = scanner.nextLine();

            // If ID is valid

            System.out.println("(1) Enter New TravProf:");
        }
    }


    public void getUserChoice(){

    }

    public void deleteTravProf(){

    }

    public void findTravProf(){

    }

    public void updateTravProf(){

    }

    public void displayTravProf(TravProf profile){

    }

    public void displayAllTravProf(){

    }

    public void writeToDB(){

    }

    public void initDB(){

    }

//    public TravProf createNewTravProf(){
//
//    }
//
//    public MedCond createNewMedCond(){
//
//    }

    public String getFileName() {
        return fileName;
    }
}
