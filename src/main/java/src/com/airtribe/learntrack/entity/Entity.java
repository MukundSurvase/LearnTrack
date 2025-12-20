package src.com.airtribe.learntrack.entity;

import src.com.airtribe.learntrack.exception.InvalidContactNumber;
import src.com.airtribe.learntrack.exception.InvalidEmailException;
import src.com.airtribe.learntrack.exception.InvalidInput;
import src.com.airtribe.learntrack.util.IDGenerator;

import java.util.List;

public class Entity extends Person {

    private String id;
    private Role role;
    private long xpPoints;
    private List<Batch> batches;

    public Entity(String firstName, String middleName, String lastName, String gender, long age, String contact,
                  String emailId, String addressLine1, String addressLine2, String addressLine3, String city, String state, String country, Role role) throws InvalidContactNumber, InvalidEmailException, InvalidInput {

        super(firstName, middleName, lastName, gender, age, contact, emailId, addressLine1, addressLine2, addressLine3, city, state, country);
        xpPoints = 0;
        id = IDGenerator.generateID(this);
        this.role = role;
    }

    public String getId() {
        return id;
    }


    public Role getRole() {
        return role;
    }


    public long getXpPoints() {
        return xpPoints;
    }

    public void updateXpPoints(long xpPoints) {
        this.xpPoints += xpPoints;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }
}
