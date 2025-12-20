package src.com.airtribe.learntrack.service;

import src.com.airtribe.learntrack.entity.Entity;
import src.com.airtribe.learntrack.entity.Login;
import src.com.airtribe.learntrack.exception.UnAuthorizedException;
import src.com.airtribe.learntrack.repository.EntityRepository;
import src.com.airtribe.learntrack.repository.LoginRepository;

public class LoginService {
    private LoginRepository loginRepository = new LoginRepository();
    private EntityRepository entityRepository = new EntityRepository();
    private String test;

    public boolean login(String login_id , String password) throws UnAuthorizedException{
        if(entityRepository.getEntityRepository().containsKey(login_id)){
            Login login = loginRepository.getLoginRepository().get(login_id);
            if(login.getPassword().equals(password)){
                login.setActive(true);
                loginRepository.getLoginRepository().replace(login_id,login);
                return true;
            }else
                throw new UnAuthorizedException();
        }
       else throw new UnAuthorizedException("No Such User Found !");
    }

    public boolean logout(String login_id , String password) throws UnAuthorizedException{
        if(entityRepository.getEntityRepository().containsKey(login_id)){
            Login login = loginRepository.getLoginRepository().get(login_id);
            if(login.getPassword().equals(password)){
                login.setActive(false);
                loginRepository.getLoginRepository().replace(login_id,login);
                return true;
            }else
                throw new UnAuthorizedException();
        }
        else throw new UnAuthorizedException("No Such User Found !");
    }
}
