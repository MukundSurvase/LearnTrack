package src.com.airtribe.learntrack.entity;

import src.com.airtribe.learntrack.exception.InvalidInput;
import src.com.airtribe.learntrack.util.IDGenerator;
import src.com.airtribe.learntrack.util.InputValidator;
import src.com.airtribe.learntrack.util.Util;

import java.util.HashMap;
import java.util.Map;

public class Course {

    private final String courseId;
    private String courseName;
    private String technology;
    private long durationInWeeks;
    private Map<Integer, String> courseContent;
    private double courseFees;

    public Course(String courseNme , double courseFees ,String technology, long durationInWeeks) {
        this.courseId = IDGenerator.generateID(this);
        this.technology = technology;
        this.durationInWeeks = durationInWeeks;
        this.courseContent = new HashMap<>();
        this.courseName = courseNme;
        this.courseFees = courseFees;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getCourseFees() {
        return courseFees;
    }

    public void setCourseFees(double courseFees) {
        this.courseFees = courseFees;
    }

    public String getCourseId() {
        return courseId;
    }


    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public long getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(long durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public void getCourseContent() {
        Util.titlePage("course content");
        int i =1;
        for (Integer topicNumber : courseContent.keySet()){
            Util.newLine();
            System.out.print(i+"--"+courseContent.get(topicNumber));
            i++;
            Util.printDashLine();
        }
    }

    public boolean addContent(String topicName) throws InvalidInput {
        if (!InputValidator.isEmptyString(topicName)) {
            if (courseContent.isEmpty()) {
                courseContent.put(1, topicName);
                return true;
            } else {
                courseContent.put(courseContent.size() + 1, topicName);
                return true;
            }
        }else throw new InvalidInput();
    }

    public boolean removeContentFromCourse(String topicName) throws InvalidInput {
        if (!InputValidator.isEmptyString(topicName)) {
            if(courseContent.isEmpty())
                throw new InvalidInput("NO SUCH CONTENT AVAILABLE");
            else {
                return courseContent.entrySet().removeIf(e->e.getValue() != null &&
                        e.getValue().toLowerCase().contains(topicName.toLowerCase()));
            }
        }else throw new InvalidInput();
    }

    public String toString(){
        return courseId+"\t║\t"+courseName+"\t║\t"+technology+"\t║\t"+durationInWeeks+"\t║\t"+courseFees+"\t║\t";
    }



}
