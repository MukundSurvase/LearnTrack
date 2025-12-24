package src.com.airtribe.learntrack.util;

import src.com.airtribe.learntrack.exception.InvalidContactNumber;
import src.com.airtribe.learntrack.exception.InvalidInput;

public class InputValidator {
    public static boolean isValidTextInput(String input) throws InvalidInput {
        String regex = "^[A-Za-z ]+$";
        if(input.matches(regex))
            return true;
        else
            throw new InvalidInput();
    }

    public static boolean isEmptyString(String input) throws InvalidInput {
        if(!(input.isBlank() || input.equals("") || input == null))
            return false;

        return true;
    }

    public static boolean isValidContactNumber(String input) throws InvalidContactNumber {
        String regex = "^[6-9]\\d{9}$";
        if(input.matches(regex))
            return true;
        else
            throw new InvalidContactNumber();
    }

    public static boolean isValidPassword(String password) throws InvalidInput{
        String regEx = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$";
        if(password.matches(regEx))
            return true;
        else {
            System.out.println("[\n1.At least 1 letter\n2.At least 1 digit\n3.At least 1 special character\n4.Length between 8 and 16\n]");
            throw new InvalidInput("Password criteria miss-match !!");
        }
    }
}
