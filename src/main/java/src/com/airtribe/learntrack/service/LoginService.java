package src.com.airtribe.learntrack.service;

import src.com.airtribe.learntrack.entity.Entity;
import src.com.airtribe.learntrack.entity.Login;
import src.com.airtribe.learntrack.exception.InvalidContactNumber;
import src.com.airtribe.learntrack.exception.InvalidEmailException;
import src.com.airtribe.learntrack.exception.InvalidInput;
import src.com.airtribe.learntrack.exception.UnAuthorizedException;
import src.com.airtribe.learntrack.repository.EntityRepository;
import src.com.airtribe.learntrack.repository.LoginRepository;

public class LoginService {
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

    public boolean login(String login_id , String password) throws UnAuthorizedException{
        if(entityRepository.getEntityRepository().containsKey(login_id)){
            Login login = loginRepository.getLoginRepository().get(login_id);
            if(login.getPassword().equals(password)){
                login.setActive(true);
                loginRepository.getLoginRepository().put(login_id,login);
                return true;
            }else
                throw new UnAuthorizedException();
        }
       else throw new UnAuthorizedException("No Such User Found !");
    }

    public boolean logout(String login_id) throws UnAuthorizedException{
        if(entityRepository.getEntityRepository().containsKey(login_id)){
            Login login = loginRepository.getLoginRepository().get(login_id);
            if(login != null && login.isActive()){
                login.setActive(false);
                loginRepository.getLoginRepository().replace(login_id,login);
                return true;
            }else
                throw new UnAuthorizedException();
        }
        else throw new UnAuthorizedException("No Such User Found !");
    }
}
