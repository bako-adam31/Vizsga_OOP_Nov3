package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


public class ReportGenerator {


    private ObjectMapper dataConverter;


    public ReportGenerator() {
        this.dataConverter = new ObjectMapper();
    }


    public void printConsoleReport(RootData data) {

        System.out.println("\n--- Repo ---");
        System.out.println("=========================================");
        System.out.println("Term: " + data.getTerm());
        System.out.println("Departmant: " + data.getDepartment().getName() + " (" + data.getDepartment().getCode() + ")");

        System.out.println("\n--- (" + data.getCourses().size() + " db) ---");

        for (Course course : data.getCourses()) {

            if (course.getTitle() == null) {
                System.out.println("\n  * " + course.getCode() + " - Error Data");
                continue;
            }

            System.out.println("\n  * " + course.getCode() + ": " + course.getTitle() + " (" + course.getCredits() + " credit)");

            if (course.getInstructor() != null && course.getInstructor().getName() != null) {
                System.out.println("    Teacher: " + course.getInstructor().getName());
            }

            if (course.getSchedule() != null) {
                System.out.println("    Date: " + course.getSchedule().getDay() + " " +
                        course.getSchedule().getStart() + "-" + course.getSchedule().getEnd() +
                        " (Class: " + course.getSchedule().getRoom() + ")");
            }

            Object studentsObj = course.getStudents();

            if (studentsObj instanceof List) {

                List<?> rawList = (List<?>) studentsObj;

                if (rawList.isEmpty()) {
                    System.out.println("    Student (empty):");
                } else {
                    List<Student> students;
                    try {
                        students = dataConverter.convertValue(
                                rawList,
                                new TypeReference<List<Student>>() {}
                        );
                    } catch (Exception e) {
                        System.out.println("    Student: error!");
                        continue;
                    }

                    System.out.println("    Student (" + students.size() + " size):");
                    for (Student student : students) {
                        System.out.println("      - " + student.toString());
                    }
                }
            }
            else if (studentsObj instanceof String) {
                System.out.println("    Student: " + studentsObj.toString());
            }
            else {
                System.out.println("    Student: not fynd");
            }
        }

        System.out.println("\n--- Advisors (" + data.getAdvisors().size() + " ) ---");
        for (Advisor advisor : data.getAdvisors()) {
            System.out.println("  * " + advisor.getName() + " (Group: " + advisor.getGroups().toString() + ")");
        }
        System.out.println("=========================================");
    }
}