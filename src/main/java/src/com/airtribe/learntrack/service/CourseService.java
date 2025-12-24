package src.com.airtribe.learntrack.service;

import src.com.airtribe.learntrack.entity.Course;
import src.com.airtribe.learntrack.exception.InvalidInput;
import src.com.airtribe.learntrack.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private CourseRepository courseRepository = CourseRepository.getInstance();

    public boolean introduceCourse(Course course) throws InvalidInput {
        if (courseRepository.getCourseRepository().containsKey(course.getCourseId())) {
            throw new InvalidInput("Course already available");
        } else {
            courseRepository.getCourseRepository().put(course.getCourseId(), course);
        }
        return true;
    }

    public boolean updateCourse(Course course){
        if(courseRepository.getCourseRepository().containsKey(course.getCourseId())){
            courseRepository.getCourseRepository().replace(course.getCourseId(),course);
            return true;
        }
        return false;
    }

    public boolean removeCourse(Course course) throws InvalidInput {
        if (courseRepository.getCourseRepository().containsKey(course.getCourseId())) {
            courseRepository.getCourseRepository().remove(course.getCourseId());
            return true;
        } else throw new InvalidInput("No Such Course Found !");
    }

    public boolean isCourseAvailable(String course_id){
        if(courseRepository.getCourseRepository().containsKey(course_id))
            return true;

        return false;
    }

    public Course getCourse(String course_id){
        if(courseRepository.getCourseRepository().containsKey(course_id)){
            return courseRepository.getCourseRepository().get(course_id);
        }
        return null;
    }

    public boolean addTopicToCourse(String course_id , String topic) throws InvalidInput {
        Course course = courseRepository.getCourseRepository().get(course_id);
        if(course != null){
            return course.addContent(topic);
        }throw new InvalidInput("INVALID COURSE DETAILS");
    }

    public boolean removeTopicFromCourse(String course_id , String topic) throws InvalidInput {
        Course course = courseRepository.getCourseRepository().get(course_id);
        if(course != null){
            return course.removeContentFromCourse(topic);
        }throw new InvalidInput("INVALID COURSE DETAILS");
    }

    public List<Course> getAllCourses(){
        return new ArrayList<>(courseRepository.getCourseRepository().values());
    }

    public void showCourseContents(String course_id){
        Course course = courseRepository.getCourseRepository().get(course_id);
        if(course != null){
            course.getCourseContent();
        }
    }
}
