package org.example;

import java.util.Date;
import java.util.List;

public class Course extends BaseEntity implements Schedulable, Identifiable {
    String code;
    String title;
    int credits;
    Object type;
    Schedule schedule;
    Instructor instructor;
    Object students;
    List<Assessment> assessments;

    public Course() {
        super();
    }

    @Override
    public String businessKey() {
        return code;}

    @Override
    public String getIdentifier() {
        return code;
    }

    @Override
    public String toString() {
        return "Course{code='" + code + "', title='" + title + "'}";
    }

    @Override
    public Date getStartTime() {
        return (schedule != null) ? schedule.getStartDate() : null;
    }

    @Override
    public Date getEndTime() {
        return (schedule != null) ? schedule.getEndDate() : null;
    }

    @Override
    public boolean overlapsWith(Schedulable other) {
        if (getStartTime() == null || getEndTime() == null || other.getStartTime() == null || other.getEndTime() == null) {
            return false;
        }

        return getStartTime().before(other.getEndTime()) && getEndTime().after(other.getStartTime());
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public Object getType() { return type; }
    public void setType(Object type) { this.type = type; }
    public Schedule getSchedule() { return schedule; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule; }
    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }
    public Object getStudents() {
        return students;
    }
    public void setStudents(Object students) {
        this.students = students;
    }
    public List<Assessment> getAssessments() { return assessments; }
    public void setAssessments(List<Assessment> assessments) { this.assessments = assessments; }
}