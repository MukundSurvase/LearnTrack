package src.com.airtribe.learntrack.repository;

import src.com.airtribe.learntrack.entity.Login;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginRepository {

    private static Map<String, Login> loginRepository = new ConcurrentHashMap<>();

    public Map<String, Login> getLoginRepository() {
        return loginRepository;
    }
}
