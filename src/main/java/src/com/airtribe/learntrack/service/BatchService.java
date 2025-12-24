package src.com.airtribe.learntrack.service;

import src.com.airtribe.learntrack.entity.Batch;
import src.com.airtribe.learntrack.entity.Entity;
import src.com.airtribe.learntrack.exception.BatchMaxSizeReachedException;
import src.com.airtribe.learntrack.exception.InvalidContactNumber;
import src.com.airtribe.learntrack.exception.InvalidEmailException;
import src.com.airtribe.learntrack.exception.InvalidInput;
import src.com.airtribe.learntrack.repository.BatchRepository;
import src.com.airtribe.learntrack.repository.EntityRepository;
import src.com.airtribe.learntrack.util.InputValidator;

import java.util.List;

public class BatchService {

    private BatchRepository batchRepository = BatchRepository.getInstance();
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

    public boolean createBatch(Batch batch) throws InvalidInput {

        if (!batchRepository.getBatchRepository().containsKey(batch.getBatchId())) {
            batchRepository.getBatchRepository().put(batch.getBatchId(), batch);
            return true;
        } else
            throw new InvalidInput("Batch is already Live !");
    }

    public Entity getEntityFromBatch(String entity_id, String batch_id) throws InvalidInput {
        Batch batch = null;
        if (InputValidator.isValidTextInput(entity_id) && InputValidator.isValidTextInput(batch_id)) {
            if (batchRepository.getBatchRepository().containsKey(batch_id)) {
                batch = batchRepository.getBatchRepository().get(batch_id);
                if (batch.getEnrolledStudents().contains(entity_id)) {
                    return entityRepository.getEntityRepository().get(entity_id);
                } else throw new InvalidInput("Entity has not been enrolled for Batch !");
            } else throw new InvalidInput("Batch is not Live yet !");
        } else throw new InvalidInput();
    }

    public boolean deactivateBatch(String batch_id) throws InvalidInput {
        Batch batch = null;
        if (InputValidator.isValidTextInput(batch_id)) {

            if (batchRepository.getBatchRepository().containsKey(batch_id)) {
                batch = batchRepository.getBatchRepository().get(batch_id);
                batch.setLive(false);
                return true;
            } else throw new InvalidInput("No Such Batch available !");

        } else throw new InvalidInput();
    }

    public boolean removeBatch(String batch_id) throws InvalidInput {

        Batch batch = batchRepository.getBatchRepository().get(batch_id);
        Entity entity = null;
        List<String> enrolledStudents = null;
        if(batch != null && !batch.isLive()){
            enrolledStudents = batch.getEnrolledStudents();
            for(String entity_id : enrolledStudents){
                entity = entityRepository.getEntityRepository().get(entity_id);
                entity.getBatches().remove(batch.getBatchId());
                entityRepository.getEntityRepository().replace(entity_id,entity);
            }
            return true;
        }else throw new InvalidInput("CAN NOT PERFORM OPERATION ! [EITHER BATCH IS LIVE OR NOT AVAILABLE]");

    }


    public boolean enrollEntityToBatch(String entity_id , String batch_id) throws InvalidInput, BatchMaxSizeReachedException {
        Batch batch = batchRepository.getBatchRepository().get(batch_id);
        if(batch == null || !isBatchLive(batch_id))
            throw new InvalidInput("INVALID BATCH !!");

        if(batch.getBatchSize()==batch.getBatchMaxSize())
            throw new BatchMaxSizeReachedException();

        boolean result = batch.enrollStudent(entityRepository.getEntityRepository().get(entity_id));
        if (result) {
            batchRepository.getBatchRepository().replace(batch_id, batch);
            return true;
        }else
            throw new InvalidInput("INVALID ENTITY !!");
    }

    public boolean isBatchLive(String batch_id){
        if(batchRepository.getBatchRepository().containsKey(batch_id)){
            return batchRepository.getBatchRepository().get(batch_id).isLive();
        }
        return false;
    }

    public boolean removeEntityFromBatch(String entity_id , String batch_id) throws InvalidInput {
        Batch batch = null;
        if(batchRepository.getBatchRepository().containsKey(batch_id)){
            batch = batchRepository.getBatchRepository().get(batch_id);
            if(batch.getEnrolledStudents().contains(entity_id)){
                batch.getEnrolledStudents().remove(entity_id);
                return true;
            }else throw new InvalidInput("No Such enrollment found !");
        }else throw new InvalidInput("No Such Batch Found !");
    }

    public Batch getBatchDetails(String batch_id) throws InvalidInput {
        if(!InputValidator.isEmptyString(batch_id)){
            if(batchRepository.getBatchRepository().containsKey(batch_id)){
                return batchRepository.getBatchRepository().get(batch_id);
            }else throw new InvalidInput("No Such Batch available !");
        } else throw new InvalidInput();
    }
}

