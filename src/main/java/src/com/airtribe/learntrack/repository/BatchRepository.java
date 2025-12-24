package src.com.airtribe.learntrack.repository;

import src.com.airtribe.learntrack.entity.Batch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BatchRepository {

    private static Map<String, Batch> batchRepository = new ConcurrentHashMap<>();

    private static BatchRepository repository = null;

    public Map<String, Batch> getBatchRepository() {
        return batchRepository;
    }

    private BatchRepository(){

    }

    public static BatchRepository getInstance(){
        if(repository == null){
            repository = new BatchRepository();
        }
        return repository;
    }
}
