import java.io.Serializable;

public class TravProf implements Serializable {

    // Instance Variables
    private String travAgentID;
    private String createdByAgentID;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String travelType;      // (1) Pleasure, (2) Business
    private String paymentType;     // (1) Credit, (2) Check, (3) Debit, (4) Invoice
    float tripCost;
    MedCond medCondInfo;

    // Constructor
    public TravProf(String travAgentID, String firstName, String lastName, String address,
                    String phone, float tripCost, String travelType, String paymentType,
                    MedCond medCondInfo, String createdByAgentID){

        this.travAgentID = travAgentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.travelType = travelType;
        this.paymentType = paymentType;
        this.tripCost = tripCost;
        this.medCondInfo = medCondInfo;
        this.createdByAgentID = createdByAgentID;
    }

    // Get Methods
    public String getTravAgentID() {
        return this.travAgentID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getTravelType() { return this.travelType; }

    public String getPaymentType() {
        return this.paymentType;
    }

    public float getTripCost() {
        return this.tripCost;
    }

    public MedCond getMedCondInfo() {
        return this.medCondInfo;
    }

    public String getCreatedByAgentID() {
        return createdByAgentID;
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

    public void updateCreatedByAgentID(String id){
        this.createdByAgentID = id;
    }
}
