package src.com.airtribe.learntrack.repository;

import src.com.airtribe.learntrack.entity.Course;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CourseRepository {
    private static Map<String, Course> courseRepository;

    private static CourseRepository repository=null;

    public  Map<String, Course> getCourseRepository() {
        return courseRepository;
    }

    private CourseRepository(){
        courseRepository = new ConcurrentHashMap<>();
    }

    public static CourseRepository getInstance(){
        if(repository == null)
            repository = new CourseRepository();

        return repository;
    }

}
