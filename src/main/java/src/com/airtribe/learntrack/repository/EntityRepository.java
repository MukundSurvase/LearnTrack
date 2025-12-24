package src.com.airtribe.learntrack.repository;

import src.com.airtribe.learntrack.entity.Entity;
import src.com.airtribe.learntrack.entity.Role;
import src.com.airtribe.learntrack.exception.InvalidContactNumber;
import src.com.airtribe.learntrack.exception.InvalidEmailException;
import src.com.airtribe.learntrack.exception.InvalidInput;
import src.com.airtribe.learntrack.service.EntityService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EntityRepository {
    private static Map<String, Entity> entityRepository;
    private static EntityService service ;
    private static EntityRepository repository;

    private EntityRepository() throws InvalidContactNumber, InvalidEmailException, InvalidInput {



    }
    static {
        entityRepository = new ConcurrentHashMap<>();
        Entity entity = null;
        try {
            entity = new Entity("Mukund","Vithal","Surwase","Male",35,"9145338028","surwasemukund080289@gmail.com","shelar mala","Salpe sankul society","near utkarsha society","Pune","Maharashtra","India", Role.ADMIN);
        } catch (InvalidContactNumber e) {
            throw new RuntimeException(e);
        } catch (InvalidEmailException e) {
            throw new RuntimeException(e);
        } catch (InvalidInput e) {
            throw new RuntimeException(e);
        }
        entityRepository.put(entity.getId(),entity);
        service = new EntityService();
        try {
            service.registerEntity(entity);
        } catch (InvalidInput e) {
            throw new RuntimeException(e);
        }
    }
    public static EntityRepository getInstance() throws InvalidContactNumber, InvalidEmailException, InvalidInput {
        if(repository==null) {
            repository = new EntityRepository();
        }

        return repository;
    }
    public Map<String, Entity> getEntityRepository() {
        return entityRepository;
    }
}
