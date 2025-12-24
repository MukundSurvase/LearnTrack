package src.com.airtribe.learntrack.util;

import src.com.airtribe.learntrack.entity.*;

public class IDGenerator {

    public static String generateID(Object entityType) {

        long randomId = System.currentTimeMillis();

       if (entityType instanceof Batch)
            return "ATBAT" + randomId;
        else if (entityType instanceof Course)
            return "ATCOR" + randomId;
        else if (entityType instanceof Message)
            return "NOTIF" + randomId;
        else if (entityType instanceof Entity && ((Entity) entityType).getRole().equals(Role.ADMIN))
            return "admin";
        else if (entityType instanceof Entity)
            return ((Entity) entityType).getRole().toString().substring(0,4)+"-"+ randomId;

        return "";
    }
}
