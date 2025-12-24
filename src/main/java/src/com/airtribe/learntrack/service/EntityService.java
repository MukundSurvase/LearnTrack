package src.com.airtribe.learntrack.service;

import src.com.airtribe.learntrack.entity.Entity;
import src.com.airtribe.learntrack.entity.Login;
import src.com.airtribe.learntrack.exception.InvalidContactNumber;
import src.com.airtribe.learntrack.exception.InvalidEmailException;
import src.com.airtribe.learntrack.exception.InvalidInput;
import src.com.airtribe.learntrack.repository.EntityRepository;
import src.com.airtribe.learntrack.repository.LoginRepository;
import src.com.airtribe.learntrack.util.InputValidator;
import src.com.airtribe.learntrack.util.Util;

import java.util.Scanner;

public class EntityService {

    private LoginRepository loginRepository = LoginRepository.getInstance();
    private EntityRepository entityRepository;

    {
        try {
            entityRepository = EntityRepository.getInstance();
        } catch (InvalidContactNumber e) {
            throw new RuntimeException(e);
        } catch (InvalidEmailException e) {
            throw new RuntimeException(e);
        } catch (InvalidInput e) {
            throw new RuntimeException(e);
        }
    }

    public boolean registerEntity(Entity entity) throws InvalidInput {
        boolean result = true;
        if (entity != null) {
            entityRepository.getEntityRepository().put(entity.getId(), entity);
        }else throw new InvalidInput();

        if(!entity.getId().equalsIgnoreCase("admin"))
             result = newRegistrationEntry(entity);

        return result;
    }

    public boolean removeEntity(Entity entity) {

        if (entityRepository.getEntityRepository().containsKey(entity.getId())) {
            loginRepository.getLoginRepository().remove(entity.getId());
            entityRepository.getEntityRepository().remove(entity.getId());
            return true;
        }
        return false;
    }

    public Entity getEntityById(String id) throws InvalidInput {
        if ((!InputValidator.isEmptyString(id)) && entityRepository.getEntityRepository().containsKey(id)) {
            return entityRepository.getEntityRepository().get(id);
        }
        return null;
    }


    private boolean newRegistrationEntry(Entity entity) {

        Util.titlePage("create Credential");
        Util.newLine();
        Util.printDashLine();
        int count = 0;

        do {
            count++;
            Scanner sc = new Scanner(System.in);
            Util.newLine();
            System.out.print("ENTER PASSWORD : ");
            String password = sc.nextLine();
            Util.printDashLine();
            Util.newLine();
            System.out.print("RE-ENTER PASSWORD : ");
            String rePassword = sc.nextLine();
            Util.printDashLine();
            Util.newLine();
            if (password.equals(rePassword)) {
                System.out.print("PASSWORD MATCHED !");
                Login login = new Login(entity.getId(), password, entity.getRole());
                loginRepository.getLoginRepository().put(entity.getId(), login);
                return true;
            } else {
                Util.newLine();
                System.out.print("PASSWORD NOT MATCHED !!");
                Util.printDashLine();
                Util.newLine();
                System.out.print("PLEASE TRY AGAIN [YOU WILL GET MAX 5 CHANCES ] ["+count+" out of 5 USED]!!!");
            }
        } while (count<=0);
        return false;
    }

    public boolean updatePassword(String login_id) {
        Login login = null;
        if (loginRepository.getLoginRepository().containsKey(login_id)) {
            login = loginRepository.getLoginRepository().get(login_id);
        }
        if (login != null && loginRepository.getLoginRepository().get(login_id).isActive()) {


            Util.titlePage("update Credential");

            do {
                Scanner sc = new Scanner(System.in);
                System.out.println("ENTER OLD PASSWORD: ");
                String password = sc.nextLine();
                Util.printDashLine();
                Util.newLine();
                System.out.println("ENTER NEW PASSWORD: ");
                String rePassword = sc.nextLine();
                Util.printDashLine();
                Util.newLine();
                System.out.println("CONFIRM PASSWORD :");
                String confirmPassword = sc.nextLine();
                Util.printDashLine();
                Util.newLine();

                if (login.getPassword().equals(password)) {
                    if (rePassword.equals(confirmPassword)) {
                        System.out.println("PASSWORD MATCHED");
                        login.setPassword(rePassword);
                        loginRepository.getLoginRepository().replace(login.getLoginId(), login);
                    }
                    return true;
                }

                System.out.println("PASSWORD MISS-MATCH");
                Util.printDashLine();
                System.out.println("DO YOU WANT TO TRY IT AGAIN ?[Y/N]:");
                String response = sc.nextLine().trim().toUpperCase();
                if (response.equals("Y"))
                    continue;
                else if (response.equals("N"))
                    break;
                else
                    System.out.println("INVALID INPUT ! PLEASE ENTER EITHER 'Y' OR 'N'.");

            } while (true);
        }
        return false;
    }

    public boolean updateXpPoints(Entity entity, long xpPoints) throws InvalidInput {
        if (entity != null && entityRepository.getEntityRepository().containsKey(entity.getId())) {
            Entity entity1 = entityRepository.getEntityRepository().get(entity.getId());
            if (xpPoints > 0)
                entity1.updateXpPoints(xpPoints);
            else
                throw new InvalidInput("Invalid XpPoints !!");
        }
        throw new InvalidInput("Entity Not Found !!");
    }

    public Entity showMyProfile(Entity entity){
        return entityRepository.getEntityRepository().get(entity.getId());
    }

}
