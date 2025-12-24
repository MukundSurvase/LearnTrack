package src.com.airtribe.learntrack.entity;

import src.com.airtribe.learntrack.exception.InvalidContactNumber;
import src.com.airtribe.learntrack.exception.InvalidEmailException;
import src.com.airtribe.learntrack.exception.InvalidInput;
import src.com.airtribe.learntrack.util.IDGenerator;
import src.com.airtribe.learntrack.util.Util;

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
        this.role = role;
        id = IDGenerator.generateID(this);
        notifyCreation(id);
    }

    private void notifyCreation(String id){
        Util.newLine();
        Util.newLine();
        Util.printDashLine();
        if(!id.equalsIgnoreCase("admin")) {
            System.out.print("ENTITY CREATED WITH ID:\t" + id);
        }

        Util.printDashLine();
        Util.newLine();
        Util.newLine();
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

    public boolean enrollToNewBatch(Batch batch){

        if(batches.contains(batch)){
            return false;
        }else{
            batches.add(batch);
        }
        return true;
    }

    public String toString(){
        return id+"\t║\t"+role+"\t║\t"+xpPoints+"\t║\t"+getFirstName()+"\t║\t"+getMiddleName()+"\t║\t"+
                getLastName()+"\t║\t"+getGender()+"\t║\t"+getAge()+"\t║\t"+getContact()+"\t║\t"+
                getEmailId()+"\t║\t"+getAddressLine1()+"\t║\t"+getAddressLine2()+"\t║\t"+
                getAddressLine3()+"\t║\t"+getCity()+"\t║\t"+getState()+"\t║\t"+getCountry();
    }

}
