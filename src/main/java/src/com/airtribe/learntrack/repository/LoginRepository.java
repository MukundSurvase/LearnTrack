package src.com.airtribe.learntrack.repository;

import src.com.airtribe.learntrack.entity.Login;
import src.com.airtribe.learntrack.entity.Role;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginRepository {

    private static Map<String, Login> loginRepository ;

    private static LoginRepository repository=null;

    private LoginRepository(){


    }

    static {
        loginRepository = new ConcurrentHashMap<>();
        Login login = new Login("admin","admin", Role.ADMIN);
        loginRepository.put("admin",login);
    }

    public static LoginRepository getInstance(){
        if(repository == null)
            repository = new LoginRepository();

        return repository;
    }
    public Map<String, Login> getLoginRepository() {
        return loginRepository;
    }
}
