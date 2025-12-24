package src.com.airtribe.learntrack;

import src.com.airtribe.learntrack.exception.UnAuthorizedException;
import src.com.airtribe.learntrack.ui.Airtribe;

public class AirtribeHomePage {
    static void main() throws UnAuthorizedException {
        Airtribe airtribe = Airtribe.getInstance();
        airtribe.runAirtribe();
    }
}
