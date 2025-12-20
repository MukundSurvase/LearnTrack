package src.com.airtribe.learntrack.entity;

import src.com.airtribe.learntrack.util.IDGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Batch {

    private String batchId;
    private String courseId;
    private long batchMaxSize;
    private long batchSize;
    private List<String> enrolledStudents;
    private List<String> mentors;
    private String instructorId;
    private String coOridinatorId;

    public Batch(String courseId, long batchMaxSize,String instructorId,String coOridinatorId) {
        this.batchId = IDGenerator.generateID(this);
        this.courseId = courseId;
        this.batchMaxSize = batchMaxSize;
        this.batchSize = 0;
        this.instructorId=instructorId;
        this.coOridinatorId = coOridinatorId;
        this.enrolledStudents = new ArrayList<>();
        this.mentors = new ArrayList<>();
    }

    public String getBatchId() {
        return batchId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public long getBatchMaxSize() {
        return batchMaxSize;
    }

    public void updateBatchMaxSize(long batchMaxSize) {
        this.batchMaxSize += batchMaxSize;
    }

    public long getBatchSize() {
        return enrolledStudents.size();
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean enrollStudent(Entity entity) {
        if(entity != null) {
            enrolledStudents.add(entity.getId());
            return true;
        }
        return false;
    }



    public List<String> getMentors() {
        return mentors;
    }

    public void setMentors(List<String> mentors) {
        this.mentors = mentors;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getCoOridinatorId() {
        return coOridinatorId;
    }

    public void setCoOridinatorId(String coOridinatorId) {
        this.coOridinatorId = coOridinatorId;
    }
}
