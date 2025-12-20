package src.com.airtribe.learntrack.repository;

import src.com.airtribe.learntrack.entity.Entity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EntityRepository {
    private static Map<String, Entity> entityRepository = new ConcurrentHashMap<>();

    public Map<String, Entity> getEntityRepository() {
        return entityRepository;
    }
}
