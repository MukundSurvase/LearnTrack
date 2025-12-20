package src.com.airtribe.learntrack.service;

import src.com.airtribe.learntrack.entity.Entity;
import src.com.airtribe.learntrack.entity.Login;
import src.com.airtribe.learntrack.repository.EntityRepository;
import src.com.airtribe.learntrack.repository.LoginRepository;

import java.io.Console;
import java.util.Scanner;

public class EntityService {

    private LoginRepository loginRepository = new LoginRepository();
    private EntityRepository entityRepository = new EntityRepository();

    public boolean registerEntity(Entity entity){
        if(entity != null){
            entityRepository.getEntityRepository().put(entity.getId(),entity);
        }
        newRegistrationEntry(entity);
        return true;
    }

    public boolean removeEntity(Entity entity){

        if(entityRepository.getEntityRepository().containsKey(entity.getId())){
            loginRepository.getLoginRepository().remove(entity.getId());
            entityRepository.getEntityRepository().remove(entity.getId());
            return true;
        }
        return false;
    }

    public Entity getEntityById(String id){
        if(entityRepository.getEntityRepository().containsKey(id)){
            return entityRepository.getEntityRepository().get(id);
        }
        return null;
    }

    private boolean newRegistrationEntry(Entity entity){

        System.out.println("**********************");
        System.out.println("* CREATE CREDENTIAL *");
        System.out.println("**********************\n");

        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter password : ");
            String password = sc.nextLine();
            System.out.println("**********************");
            System.out.println("Re-Enter password : ");
            String rePassword = sc.nextLine();
            System.out.println("**********************");
            if (password.equals(rePassword)) {
                System.out.println("Password Matched");
                Login login = new Login(entity.getId(),password,entity.getRole());
                loginRepository.getLoginRepository().put(entity.getId(),login);
                return true;
            }
            else {
                System.out.println("Password Not Matched");
                System.out.println("**********************");
                System.out.println("Do you want to Try it again ?[Y/N] :");
                String response = sc.nextLine().trim().toUpperCase();
                if (response.equals("Y"))
                    continue;
                else if (response.equals("N"))
                    break;
                else
                    System.out.println("Invalid input! Please enter Y or N.");
            }
        }while (true);

        return false;

    }

    public boolean updatePassword(String login_id){
        Login login = null;
        if(loginRepository.getLoginRepository().containsKey(login_id)) {
             login = loginRepository.getLoginRepository().get(login_id);
        }
        if(login!=null && loginRepository.getLoginRepository().get(login_id).isActive()){
            System.out.println("**********************");
            System.out.println("* UPDATE CREDENTIAL *");
            System.out.println("**********************\n");

            do {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter old password : ");
                String password = sc.nextLine();
                System.out.println("**********************");
                System.out.println("Enter new password : ");
                String rePassword = sc.nextLine();
                System.out.println("**********************");
                System.out.println("confirm password : ");
                String confirmPassword = sc.nextLine();
                System.out.println("**********************");

                if (login.getPassword().equals(password)) {
                    if(rePassword.equals(confirmPassword)) {
                        System.out.println("Password Matched");
                        login.setPassword(password);
                        loginRepository.getLoginRepository().put(login.getLoginId(), login);
                    }
                    return true;
                }

                    System.out.println("Password Not Matched");
                    System.out.println("**********************");
                    System.out.println("Do you want to Try it again ?[Y/N] :");
                    String response = sc.nextLine().trim().toUpperCase();
                    if (response.equals("Y"))
                        continue;
                    else if (response.equals("N"))
                        break;
                    else
                        System.out.println("Invalid input! Please enter Y or N.");

            }while (true);
        }
        return false;
    }

    public static void main(String [] args){
        //EntityService.newRegistrationEntry(null);
    }
}
