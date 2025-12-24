package src.com.airtribe.learntrack.service;

import src.com.airtribe.learntrack.entity.*;
import src.com.airtribe.learntrack.exception.*;
import src.com.airtribe.learntrack.repository.EntityRepository;
import src.com.airtribe.learntrack.repository.LoginRepository;
import src.com.airtribe.learntrack.util.InputValidator;

import java.util.List;

public class  EnrollService {
    private Entity loginEntity;
    private static EntityRepository entityRepository;

    static {
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

    private static LoginRepository loginRepository = LoginRepository.getInstance();
    private BatchService batchService = new BatchService();
    private EntityService entityService = new EntityService();
    private CourseService courseService = new CourseService();

    private EnrollService(Entity entity){
        this.loginEntity = entity;
    }

    public static EnrollService getInstance(Entity entity) throws UnAuthorizedException, InvalidInput {
        Entity localEntity = null;
        if(entity != null){
            if(entityRepository.getEntityRepository().containsKey(entity.getId())){
                localEntity = entityRepository.getEntityRepository().get(entity.getId());
                Login login = loginRepository.getLoginRepository().get(localEntity.getId());
                if(login.isActive()) {
                    if (localEntity.getRole().equals(Role.ADMIN))
                        return new EnrollService(entity);
                    else throw new UnAuthorizedException();
                }else throw new InvalidInput("User is not logged in !");
            }else throw new InvalidInput("No such User Found !!");
        } else throw new InvalidInput();
    }
    public boolean enrollBatch(Batch batch) throws InvalidInput {
        return batchService.createBatch(batch);
    }

    public boolean enrollEntity(Entity entity) throws InvalidInput {
        return entityService.registerEntity(entity);
    }

    public boolean removeEntityFromBatch(String entity_id , String batch_id) throws InvalidInput {
        return batchService.removeEntityFromBatch(entity_id,batch_id);
    }

    public boolean enrollEntityToBatch(String entity_id , String batch_id) throws InvalidInput, BatchMaxSizeReachedException {
        if(InputValidator.isValidTextInput(entity_id) && InputValidator.isValidTextInput(batch_id)){
            if(batchService.isBatchLive(batch_id)){
                Entity entity = entityRepository.getEntityRepository().get(entity_id);
                if(entity == null)
                    throw new InvalidInput("No Such Entity found !");

                return batchService.enrollEntityToBatch(entity_id,batch_id);
            }
        }
        return true;
    }

    public boolean enrollCourse(Course course) throws InvalidInput {
        return courseService.introduceCourse(course);
    }

    public boolean removeCourse(String course_id) throws InvalidInput {
        Course course = courseService.getCourse(course_id);
        if(course != null)
         return courseService.removeCourse(course);

        return false;
    }

    public boolean deactivateBatch(String batch_id) throws InvalidInput {
        return batchService.deactivateBatch(batch_id);
    }

    public boolean removeBatch(String batch_id) throws InvalidInput {
        return batchService.removeBatch(batch_id);
    }
    public void getContentsOfCourse(Course course){
        if(courseService.isCourseAvailable(course.getCourseId())){
            course.getCourseContent();
        }
    }

    public boolean addTopicToCourse(String course_id , String topic) throws InvalidInput {


        if(courseService.isCourseAvailable(course_id)){
            return courseService.addTopicToCourse(course_id,topic);
        }
        throw new InvalidInput("No Such Course Found !");
    }

    public boolean removeTopicFromCourse(String course_id , String topic) throws InvalidInput {


        if(courseService.isCourseAvailable(course_id)){
            return courseService.removeTopicFromCourse(course_id,topic);
        }
        throw new InvalidInput("No Such Course Found !");
    }

    public boolean removeEntity(Entity entity) throws InvalidInput, NoSuchUserException {

        List<Batch> batches = null;
        if(entityRepository.getEntityRepository().containsKey(entity.getId())){
            batches = entity.getBatches();
            for (Batch batch : batches){
                batchService.removeEntityFromBatch(entity.getId(),batch.getBatchId());
            }
            entityRepository.getEntityRepository().remove(entity.getId());
            return true;
        }else throw new NoSuchUserException();
    }
}
