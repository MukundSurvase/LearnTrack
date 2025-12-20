package src.com.airtribe.learntrack.repository;

import src.com.airtribe.learntrack.entity.Course;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CourseRepository {
    private static Map<String, Course> courseRepository = new ConcurrentHashMap<>();

    public  Map<String, Course> getCourseRepository() {
        return courseRepository;
    }
}
