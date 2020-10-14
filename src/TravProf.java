// This is a test
public class TravProf {

    // Instance Variables
    String travAgentID;
    String firstName;
    String lastName;
    String address;
    String phone;
    String travelType;
    String paymentType;
    float tripCost;
    MedCond medCondInfo;

    // Constructor
    public TravProf(String travAgentID, String firstName, String lastName, String address,
                    String phone, float tripCost, String travelType, String paymentType,
                    MedCond medCondInfo){

        this.travAgentID = travAgentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.travelType = travelType;
        this.paymentType = paymentType;
        this.tripCost = tripCost;
        this.medCondInfo = medCondInfo;
    }

    // Get Methods
    public String getTravAgentID() {
        return travAgentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getTravelType() {
        return travelType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public float getTripCost() {
        return tripCost;
    }

    public MedCond getMedCondInfo() {
        return medCondInfo;
    }

    // Update Methods

    public void updateFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void updateLastName(String lastName) {
        this.lastName = lastName;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }

    public void updateTravelType(String travelType) {
        this.travelType = travelType;
    }

    public void updatePaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void updateTripCost(float tripCost) {
        this.tripCost = tripCost;
    }

    public void updateMedCondInfo(MedCond medCondInfo) {
        this.medCondInfo = medCondInfo;
    }
}
