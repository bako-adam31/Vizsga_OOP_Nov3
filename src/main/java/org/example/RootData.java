package org.example;
import java.util.List;
import java.util.Map;

public class RootData {
    private String term;
    private Department department;
    private List<Course> courses;
    private List<Advisor> advisors;
    private Map<String, Object> meta;

    public RootData() {}

    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public List<Course> getCourses() { return courses; }
    public void setCourses(List<Course> courses) { this.courses = courses; }
    public List<Advisor> getAdvisors() { return advisors; }
    public void setAdvisors(List<Advisor> advisors) { this.advisors = advisors; }
    public Map<String, Object> getMeta() { return meta; }
    public void setMeta(Map<String, Object> meta) { this.meta = meta; }
}