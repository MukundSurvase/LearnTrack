package src.com.airtribe.learntrack.ui;

import src.com.airtribe.learntrack.entity.*;
import src.com.airtribe.learntrack.exception.*;
import src.com.airtribe.learntrack.service.*;
import src.com.airtribe.learntrack.util.InputValidator;
import src.com.airtribe.learntrack.util.Util;

import java.util.*;

public class Airtribe {
    private Entity loggedInEntity = null;
    private Map<Integer, String> services;
    private static Airtribe airtribe = null;
    private Scanner scanner = new Scanner(System.in);
    private EntityService entityService;
    private BatchService batchService;
    private CourseService courseService;
    private LoginService loginService;
    private EnrollService enrollService;

    private Airtribe() {

        services = new HashMap<>();
        services.put(1, "ABOUT AIRTRIBE");
        services.put(2, "ENTITY ON-BOARDING");
        services.put(3, "NEW BATCH");
        services.put(4, "NEW COURSE");
        services.put(5, "REMOVE STUDENT");
        services.put(6, "DE-ACTIVATE BATCH");
        services.put(7, "DELETE BATCH");
        services.put(8, "DELETE COURSE");
        services.put(9, "SHOW PROFILE");
        services.put(10, "UPDATE PROFILE");
        services.put(11, "ENROLL STUDENT TO BATCH");
        services.put(12, "SHOW XP-POINT");
        services.put(13, "ADD XP-POINTS");
        services.put(14, "ADD TOPIC TO COURSE");
        services.put(15, "REMOVE TOPIC FROM COURSE");
        services.put(16, "DISPLAY ENTITY DETAIL");
        services.put(17, "DISPLAY BATCH DETAIL");
        services.put(18, "DISPLAY ALL ENTITY FROM BATCH");
        services.put(19, "DISPLAY ALL BATCH DETAILS");
        services.put(20, "DISPLAY ALL COURSES");
        services.put(21, "LOG-IN");
        services.put(22, "LOG-OUT");
        services.put(23, "COURSE CONTENT");
        services.put(24, "CONTACT-US");//contactUs
        services.put(25, "CLOSE TAB ");


        entityService = new EntityService();
        batchService = new BatchService();
        loginService = new LoginService();
        courseService = new CourseService();
    }

    private void initiateEnrollService() {
        try {
            this.enrollService = EnrollService.getInstance(this.loggedInEntity);
        } catch (UnAuthorizedException e) {
            throw new RuntimeException(e);
        } catch (InvalidInput e) {
            throw new RuntimeException(e);
        }
    }

    public static Airtribe getInstance() {
        if (airtribe == null) {
            airtribe = new Airtribe();
        }
        return airtribe;
    }

    private boolean logout() throws UnAuthorizedException, InvalidInput {

        if (loggedInEntity == null)
            throw new InvalidInput("NOT LOGGED IN !");
        loginService.logout(loggedInEntity.getId());
        loggedInEntity = null;
        return true;
    }

    private boolean login() throws UnAuthorizedException, InvalidInput {

        if (loggedInEntity != null) {
            throw new InvalidInput("ALREADY LOGGED IN !");
        }
        boolean flag = false;
        Util.titlePage("Login");
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("ENTER AIRTRIBE-ID: ");
        String id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("ENTER PASSOWORD: ");
        String password = scanner.next();
        Util.newLine();
        Util.printDashLine();
        try {
            flag = loginService.login(id, password);
        } catch (UnAuthorizedException e) {
            Util.newLine();
            System.out.print(e.getMessage());
            Util.newLine();
            Util.printDashLine();

        }

        if (flag) {
            try {
                this.loggedInEntity = entityService.getEntityById(id);
                if (this.loggedInEntity.getRole().equals(Role.ADMIN))
                    initiateEnrollService();

            } catch (InvalidInput e) {
                Util.newLine();
                System.out.print("INVALID ENTITY ID !!");
                Util.printDashLine();
            }
        }
        return flag;
    }

    public void runAirtribe() throws UnAuthorizedException {
        Util.newLine();
        Util.titlePage("AirTribe : EdTech & Upskilling");
        Util.newLine();
        try {
            login();
        } catch (InvalidInput e) {
            Util.newLine();
            System.out.print(e.getMessage());
            Util.newLine();
            Util.printDashLine();
        }
        Util.newLine();

        boolean keepRunning = true;
        do {

            Util.servicesPage(this.services);
            int serviceChoice = Util.getChoice();
            switch (serviceChoice) {

                case 1:
                    about();
                    break;
                case 2:
                    try {
                        newEntityOnBoarding();
                    } catch (UnAuthorizedException e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();

                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    break;
                case 3:
                    try {
                        newBatch();
                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    break;
                case 4:
                    try {
                        newCourse();
                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }

                    break;
                case 5:
                    try {
                        removeEntity();
                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    } catch (NoSuchUserException e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    break;
                case 6:
                    try {
                        deactivateBatch();
                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    break;
                case 7:
                    try {
                        removeBatch();
                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    break;
                case 8:
                    try {
                        removeCourse();
                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    break;
                case 9:
                    Util.getHeader(loggedInEntity);
                    showMyProfile();
                    break;
                case 10:

                    break;
                case 11:
                    try {
                        enrollEntityToBatch();
                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    } catch (BatchMaxSizeReachedException e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    break;
                case 12:
                    showMyXpPoints();
                    break;
                case 13:
                    try {
                        updateXpPoints();
                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    } catch (NoSuchUserException e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    break;
                case 14:
                    try {
                        addTopicToCourse();
                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    break;
                case 15:
                    try {
                        removeTopicFromCourse();
                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    break;
                case 21:
                    try {

                        login();
                    } catch (UnAuthorizedException e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    } catch (Exception e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    break;
                case 22:
                    try {
                        logout();
                    } catch (UnAuthorizedException e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    } catch (Exception e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }

                    break;
                case 23:
                    showContentsOFCourse();
                    break;
                case 24:
                    contactUs();
                    break;
                case 25:
                    keepRunning = false;
                    break;
                case 16:
                    Entity entity = null;
                    try {
                        entity = displayEntityDetails();
                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    Util.getHeader(entity);
                    if (entity != null) {
                        Util.printEntityDetails(entity);
                    }
                    break;
                case 17:
                    Batch batch = null;
                    try {
                        batch = displayBatchDetail();
                    } catch (InvalidInput e) {
                        Util.newLine();
                        System.out.print(e.getMessage());
                        Util.newLine();
                        Util.printDashLine();
                    }
                    Util.getHeader(batch);
                    if (batch != null) {
                        Util.printEntityDetails(batch);
                    }
                    break;
                case 18:
                    break;
                case 19:
                    break;
                case 20:
                    List<Course> courses = displayAllCourses();
                    if (!courses.isEmpty())
                        Util.getHeader(courses.getFirst());
                    else
                        Util.printEntityDetails(new Course("dummy",32000,"java",52));

                    for (Course course : courses)
                        if (course != null) {
                            Util.printEntityDetails(course);
                        }
                    break;

            }

        } while (keepRunning);
    }

    private void about() {
        Util.newLine();
        Util.printDashLine();
        System.out.println("" + this);
    }

    public String toString() {
        return "\n\nAIRTRIBE : (EdTech and UpSkilling Platform)\n\n\t\t Airtribe is a technology-focused EdTech start-up and social learning platform that offers cohort-based updkilling pograms designed to help professional.\n\n Features :\n\t1.Live Interactions\n\t2.Real-projects and CAPSTONE assignments\n\t3.Community engagement\n\t4.Career Support";
    }


    private boolean newEntityOnBoarding() throws UnAuthorizedException, InvalidInput {

        if (enrollService == null) {
            throw new InvalidInput("PLEASE LOGIN FIRST !");
        } else {

            Util.newLine();
            Util.titlePage("on-boarding");
            Util.newLine();
            System.out.print("SELECT ROLE :\n\n1.STUDENT\n2.INSTUCTOR\n3.COORIDINATOR\n");
            int choice = Util.getChoice();
            Role role = Role.fromInput(choice);
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("FIRST-NAME :");
            String firstName = scanner.next();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("MIDDLE-NAME :");
            String middleName = scanner.next();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("LAST-NAME :");
            String lastName = scanner.next();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("GENDER [MALE/FEMALE] :");
            String gender = scanner.next();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("AGE :");
            long age = scanner.nextLong();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("CONTACT NO :");
            String contact = scanner.next();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            scanner.nextLine();
            System.out.print("EMAIL-ID :");
            String email = scanner.nextLine();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("ADDRESS LINE 1 :");
            String address1 = scanner.nextLine();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("ADDRESS LINE 2 :");
            String address2 = scanner.nextLine();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("ADDRESS LINE 3 :");
            String address3 = scanner.nextLine();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("CITY :");
            String city = scanner.next();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("STATE :");
            String state = scanner.next();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("COUNTRY :");
            String country = scanner.next();
            Util.newLine();
            Util.printDashLine();
            try {
                Entity entity = entity = new Entity(firstName, middleName, lastName, gender, age, contact, email, address1, address2, address3, city, state, country, role);
                enrollService.enrollEntity(entity);
            } catch (InvalidContactNumber e) {
                Util.newLine();
                System.out.print(e.getMessage());
                Util.printDashLine();
            } catch (InvalidEmailException e) {
                Util.newLine();
                System.out.print(e.getMessage());
                Util.printDashLine();
            } catch (InvalidInput e) {
                Util.newLine();
                System.out.print(e.getMessage());
                Util.printDashLine();
            }
        }
        return true;
    }

    private void getEntityDetails() throws UnAuthorizedException {
        if (enrollService == null)
            throw new UnAuthorizedException();
        else {
            String entity_id = "";
            Util.newLine();
            System.out.print("PLEASE ENTER ENTITY AIRTRIBE-ID:");
            entity_id = scanner.next();
            Entity entity = null;
            try {
                entity = entityService.getEntityById(entity_id);
            } catch (InvalidInput e) {
                Util.newLine();
                System.out.print(e.getMessage());
                Util.printDashLine();
            }
            if (entity != null) {
                Util.getHeader(entity);
                Util.newLine();
                System.out.print("" + entity);
                Util.newLine();
                Util.printDashLine();
            }
        }
    }

    private void updatePassword() {
        Util.newLine();
        if (loggedInEntity != null) {
            if (entityService.updatePassword(loggedInEntity.getId())) {
                Util.newLine();
                System.out.print("PASSWORD UPDATED SUCCESSFULLY !");
                Util.newLine();
                Util.printDashLine();
            } else {
                Util.newLine();
                System.out.print("SOMETHING WENT WRONG ! TRY AFTER SOMETIME !!");
                Util.newLine();
                Util.printDashLine();
            }
        }
    }

    private boolean updateXpPoints() throws InvalidInput, NoSuchUserException, UnAuthorizedException {
        if (loggedInEntity.getRole().equals(Role.ADMIN)) {
            Util.newLine();
            System.out.print("ENTER STUDENT'S AIRTRIBE-ID:");
            String id = scanner.next();
            Util.printDashLine();

            if (!InputValidator.isEmptyString(id)) {
                Entity entity = entityService.getEntityById(id);
                if (entity != null) {
                    Util.newLine();
                    System.out.print("ENTER XP-POINTS:");
                    long points = scanner.nextLong();
                    Util.printDashLine();
                    return entityService.updateXpPoints(entity, points);
                } else
                    throw new NoSuchUserException();
            } else
                throw new InvalidInput();

        } else {
            throw new UnAuthorizedException();
        }
    }

    private boolean newBatch() throws UnAuthorizedException, InvalidInput {

        Batch batch = null;
        if (loggedInEntity == null)
            throw new InvalidInput("PLEASE LOG-IN FIRST !");

        if (!loggedInEntity.getRole().equals(Role.ADMIN))
            throw new UnAuthorizedException();


        Util.newLine();
        Util.titlePage("New Batch");
        Util.newLine();
        System.out.print("PLEASE ENTER COURSE ID:");
        String course_id = scanner.next();


        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER BATCH CAPACITY:");
        long batch_max_size = scanner.nextLong();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER INSTRUCTOR ID:");
        String instructor_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER CO-ORDINATOR ID:");
        String ordinator_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("BATCH TYPE : \n {\n1. ONLINE\n2. OFFLINE\n}");
        int batchType = Util.getChoice();
        Util.newLine();
        Util.printDashLine();

        batch = new Batch(course_id, batch_max_size, instructor_id, ordinator_id, BatchType.fromInput(batchType));
        return enrollService.enrollBatch(batch);
    }

    private String newCourse() throws UnAuthorizedException, InvalidInput {
        if (loggedInEntity == null)
            throw new InvalidInput("PLEASE LOG-IN FIRST !");

        if (!loggedInEntity.getRole().equals(Role.ADMIN))
            throw new UnAuthorizedException();

        Course course = null;
        Util.newLine();
        Util.titlePage("New Course");
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        scanner.nextLine();
        System.out.print("PLEASE ENTER COURSE NAME:");
        String course_name = scanner.nextLine();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE COURSE FEES:");
        double fees = scanner.nextDouble();
        scanner.nextLine();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER TECHNOLOGY/STREAM [JAVA / NODEJS / ETC]:");
        String technolgy = scanner.nextLine();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER DURATION IN WEEKS:");
        long duration = scanner.nextLong();
        Util.newLine();
        Util.printDashLine();
        course = new Course(course_name,fees,technolgy, duration);
        boolean result = enrollService.enrollCourse(course);
        if (result)
            return course.getCourseId();
        else
            throw new InvalidInput();
    }

    private Entity getEntityFromBatch() throws InvalidInput, UnAuthorizedException {

        Entity entity = null;
        if (loggedInEntity == null)
            throw new InvalidInput("PLEASE LOG-IN FIRST !");

        if (!loggedInEntity.getRole().equals(Role.ADMIN))
            throw new UnAuthorizedException();

        Util.newLine();
        System.out.print("PLEASE ENTER BACTH ID:");
        String batch_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER BATCH ID:");
        String entity_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        return batchService.getEntityFromBatch(entity_id, batch_id);

    }

    private boolean deactivateBatch() throws InvalidInput, UnAuthorizedException {

        if (loggedInEntity == null)
            throw new InvalidInput("PLEASE LOG-IN FIRST !");

        if (!loggedInEntity.getRole().equals(Role.ADMIN))
            throw new UnAuthorizedException();

        Util.newLine();
        System.out.print("PLEASE ENTER BATCH ID:");
        String batch_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();

        return enrollService.deactivateBatch(batch_id);
    }

    private boolean enrollEntityToBatch() throws InvalidInput, UnAuthorizedException, BatchMaxSizeReachedException {
        Entity entity = null;
        if (loggedInEntity == null)
            throw new InvalidInput("PLEASE LOG-IN FIRST !");

        if (!loggedInEntity.getRole().equals(Role.ADMIN))
            throw new UnAuthorizedException();

        Util.newLine();
        System.out.print("PLEASE ENTER BACTH ID:");
        String batch_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER BATCH ID:");
        String entity_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();

        return enrollService.enrollEntityToBatch(entity_id, batch_id);
    }

    private boolean removeEntity() throws InvalidInput, UnAuthorizedException, NoSuchUserException {

        Entity entity = null;
        if (loggedInEntity == null)
            throw new InvalidInput("PLEASE LOG-IN FIRST !");

        if (!loggedInEntity.getRole().equals(Role.ADMIN))
            throw new UnAuthorizedException();

        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER STUDENT ID:");
        String entity_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();

        entity = entityService.getEntityById(entity_id);
        return enrollService.removeEntity(entity);

    }

    private boolean removeBatch() throws InvalidInput {
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER BATCH ID:");
        String batch_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        return enrollService.removeBatch(batch_id);
    }

    private void showMyProfile() {
        Util.newLine();
        Util.printEntityDetails(this.loggedInEntity);
        Util.newLine();
        Util.printDashLine();
    }

    private boolean removeCourse() throws InvalidInput {
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER COURSE ID:");
        String course_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        return enrollService.removeCourse(course_id);
    }

    private void showMyXpPoints() {
        Util.showMyXpPoints(this.loggedInEntity);
    }

    private boolean addTopicToCourse() throws UnAuthorizedException, InvalidInput {

        if (enrollService != null) {
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("PLEASE ENTER COURSE ID:");
            String course_id = scanner.next();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            scanner.nextLine();
            System.out.print("PLEASE ENTER TOPIC TITLE:");
            String topic = scanner.nextLine();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            return enrollService.addTopicToCourse(course_id, topic);
        } else throw new UnAuthorizedException();
    }

    private boolean removeTopicFromCourse() throws InvalidInput, UnAuthorizedException {
        if (enrollService != null) {
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("PLEASE ENTER COURSE ID:");
            String course_id = scanner.next();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("PLEASE ENTER TOPIC TITLE:");
            String topic = scanner.nextLine();
            Util.newLine();
            Util.printDashLine();
            Util.newLine();
            return enrollService.removeTopicFromCourse(course_id, topic);
        } else throw new UnAuthorizedException();
    }


    private Entity displayEntityDetails() throws InvalidInput {


        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER ENTITY ID:");
        String entity_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        return entityService.getEntityById(entity_id);
    }

    private void contactUs() {
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        Util.titlePage("Contact Us");
        System.out.print("Feel free to reach out if you have any queries\nEmail us at support@airtribe.live\n\nAddress: #1858, 1st Sector, HSR Layout, Bangalore - 560102, Karnataka, India\n");
    }

    private Batch displayBatchDetail() throws InvalidInput {
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER BATCH ID:");
        String batch_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        return batchService.getBatchDetails(batch_id);
    }

    private List<Course> displayAllCourses() {
        return courseService.getAllCourses();
    }

    private void showContentsOFCourse(){
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        System.out.print("PLEASE ENTER COURSE ID:");
        String course_id = scanner.next();
        Util.newLine();
        Util.printDashLine();
        Util.newLine();
        courseService.showCourseContents(course_id);
    }
}
