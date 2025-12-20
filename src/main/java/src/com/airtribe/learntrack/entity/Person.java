package src.com.airtribe.learntrack.entity;


import src.com.airtribe.learntrack.exception.InvalidContactNumber;
import src.com.airtribe.learntrack.exception.InvalidEmailException;
import src.com.airtribe.learntrack.exception.InvalidInput;
import src.com.airtribe.learntrack.util.EmailValidator;
import src.com.airtribe.learntrack.util.InputValidator;

public class Person {
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private long age;
    private String contact;
    private String emailId;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;
    private String country;

    public Person(String firstName, String middleName, String lastName, String gender, long age, String contact, String emailId, String addressLine1, String addressLine2, String addressLine3, String city, String state, String country) throws InvalidEmailException, InvalidInput, InvalidContactNumber {

        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setContact(contact);
        setEmailId(emailId);
        this.gender = gender;
        this.age = age;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) throws InvalidContactNumber {
        if (InputValidator.isValidContactNumber(contact))
            this.contact = contact;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws InvalidInput {
        if (InputValidator.isValidTextInput(firstName))
            this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) throws InvalidInput {
        if (InputValidator.isValidTextInput(middleName))
            this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws InvalidInput {
        if (InputValidator.isValidTextInput(lastName))
            this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) throws InvalidEmailException {

        if (EmailValidator.validiateEmail(emailId))
            this.emailId = emailId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
