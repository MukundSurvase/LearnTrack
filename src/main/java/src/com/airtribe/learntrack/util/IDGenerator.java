package src.com.airtribe.learntrack.util;

import src.com.airtribe.learntrack.entity.Batch;
import src.com.airtribe.learntrack.entity.Course;
import src.com.airtribe.learntrack.entity.Entity;
import src.com.airtribe.learntrack.entity.Message;

public class IDGenerator {

    public static String generateID(Object entityType) {

        long randomId = System.currentTimeMillis();

       if (entityType instanceof Batch)
            return "ATBAT" + randomId;
        else if (entityType instanceof Course)
            return "ATCOR" + randomId;
        else if (entityType instanceof Message)
            return "NOTIF" + randomId;
        else if (entityType instanceof Entity)
            return ((Entity) entityType).getRole()+""+ randomId;

        return "";
    }
}
