package src.com.airtribe.learntrack.util;

import src.com.airtribe.learntrack.entity.*;
import src.com.airtribe.learntrack.exception.InvalidContactNumber;
import src.com.airtribe.learntrack.exception.InvalidEmailException;
import src.com.airtribe.learntrack.exception.InvalidInput;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class Util {




    public static void titlePage(String title) {
        newLine();
        printDashLine();printDashLine();
        newLine();
        System.out.format("════════════════ %s ════════════════", title.toUpperCase());
        newLine();
        printDashLine();printDashLine();
        newLine();
        newLine();
    }

    public static void printDashLine() {
        System.out.print("════════════════════════════════════════════════════════");
    }

    public static void newLine() {
        System.out.print("\n");
    }

    public static void newTab() {
        System.out.print("\t");
    }

    public static void servicesPage(Map<Integer,String> services) {
        titlePage("Services");
        mainMenu(services);
    }


    public static int getChoice(){
        int choice =0;
        Scanner scanner = new Scanner(System.in);
        newLine();
        System.out.print("PLEASE ENTER YOUR CHOICE :");
        choice = scanner.nextInt();
        return choice;
    }

    public static void mainMenu(Map<Integer,String> menu){
        printDashLine();
        newLine();
        for(Integer key : menu.keySet()){
            newLine();
            System.out.print(key+"\t------------->\t"+menu.get(key));
            newLine();
            printDashLine();
        }
        newLine();
        newLine();
        printDashLine();
    }

    public static void getHeader(Object object) {
        List<Field> headers = getHeaderTitles(object);

        for (int i=0; i < headers.size()/3;i++)
             printDashLine();

        newLine();
        newTab();
        for (Field field : headers) {
            if (field.getName().equals("enrolledStudents") || field.getName().equals("mentors") || field.getName().equals("courseContent") || field.getName().equals("batches"))
                continue;
            else {
                System.out.print(field.getName());
                newTab();
                System.out.print("║");
                newTab();
            }
        }
        newLine();
        for (int i=0; i < headers.size()/3;i++)
            printDashLine();
    }

    public static void printAllBatchDetails(List<Batch> batches) {
        for (Batch batch : batches) {
            printEntityDetails(batch);
        }
    }


    public static void printEntityDetails(Object entity) {

        newLine();
        printDashLine();
        printDashLine();
        printDashLine();
        newLine();
        newTab();
        System.out.print("" + entity);
        newLine();
        printDashLine();
        printDashLine();
        printDashLine();
        newLine();

    }

    private static List<Field> getHeaderTitles(Object object) {

        List<Field> fields;
        Class<?> clazz = null;
        if (object instanceof Batch)
            clazz = Batch.class;

        if (object instanceof Course)
            clazz = Course.class;

        if (object instanceof Entity)
            clazz = Entity.class;

        return getAllFields(clazz);
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();

        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                fields.add(field);
            }
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    public static void showMyXpPoints(Entity entity){
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("AIRTRIBE-ID"+"\t║\t"+"FIRST-NAME"+"\t║\t"+"LAST-NAME"+"\t║\t"+"XP-POINTS"+"\t║\t");
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print(entity.getId()+"\t║\t"+entity.getFirstName()+"\t║\t"+entity.getLastName()+"\t║\t"+entity.getXpPoints()+"\t║\t");
        Util.newLine();
        Util.printDashLine();
    }
    static void main() throws InvalidContactNumber, InvalidEmailException, InvalidInput, IOException {
//        titlePage("Branch Details");
//        Batch b = new Batch("AIRCORS1001", 100, "AIRINS1001", "AIRCOR1001", BatchType.OFFLINE);
//        Util.getHeader(b);
//        Util.printEntityDetails(b);

        //servicesPage();

//        Course c = new Course("Java",32);
//        Util.getHeader(c);
//        printCourseDetails(c);

//        Entity entity = new Entity("Mukund","Vithal","Surwase","Male",35,"9145338028","surwasemukund080289@gmail.com","shelar mala","Salpe sankul society","near utkarsha society","Pune","Maharashtra","India", Role.ADMIN);
//        Util.getHeader(entity);
//        Util.printEntityDetails(entity);
    }

}
