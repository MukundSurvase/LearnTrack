package src.com.airtribe.learntrack.util;


import src.com.airtribe.learntrack.exception.InvalidEmailException;

public class EmailValidator {

    public static boolean validiateEmail(String email) throws InvalidEmailException {

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if(email.matches(regex))
            return true;
        else
            throw new InvalidEmailException();
    }
;}
