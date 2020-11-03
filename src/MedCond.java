import java.io.Serializable;

public class MedCond implements Serializable {

    // Instance variables
    private String mdContact;
    private String mdPhone;
    private String algType;
    private String illType;

    // Constructor
    public MedCond(String mdContact, String mdPhone, String algType, String illType) {
       this.mdContact = mdContact;
       this.mdPhone = mdPhone;
       // In the future use ENUMS for these options
       this.algType = algType; // options: (1) None, (2) Food, (3) Medication, (4) Other
       this.illType = illType; // options (1) None, (2) Heart, (3) Diabetes, (4) Asthma, (5) Other

    }

    // Get Methods
    public String getMdContact() {
        return this.mdContact;
    }

    public String getMdPhone() {
        return this.mdPhone;
    }

    public String getAlgType() {
        return this.algType;
    }

    public String getIllType() {
        return this.illType;
    }

    // Update Methods
    public void updateMdContact(String newMdContact) {
        this.mdContact = newMdContact;
    }

    public void updateMdPhone(String newMdPhone) {
        this.mdPhone = newMdPhone;
    }

    public void updateAlgType(String newAlgType) {
        this.algType = newAlgType;
    }

    public void updateIllType(String newIllType) {
        this.illType = newIllType;
    }
}
