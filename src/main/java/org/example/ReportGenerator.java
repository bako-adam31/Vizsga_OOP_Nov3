package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

// 1. ÚJ IMPORT: Szükségünk van a PrintWriter-re
import java.io.PrintWriter;
import java.util.List;


public class ReportGenerator {

    private ObjectMapper dataConverter;

    public ReportGenerator() {
        this.dataConverter = new ObjectMapper();
    }

    // 2. MÓDOSÍTOTT METÓDUS:
    // A neve 'generateReport' lett, és kap egy 'PrintWriter writer' paramétert.
    // Ez a 'writer' lesz a kimenet (vagy a konzol, vagy egy fájl).
    public void generateReport(RootData data, PrintWriter writer) {

        // 3. CSERE: Az összes 'System.out.println'
        // 'writer.println'-re lett cserélve!

        writer.println("\n--- Riport ---"); // 'Repo' javítva 'Riport'-ra
        writer.println("=========================================");
        writer.println("Term: " + data.getTerm());
        writer.println("Department: " + data.getDepartment().getName() + " (" + data.getDepartment().getCode() + ")"); // 'Departmant' javítva

        writer.println("\n--- (" + data.getCourses().size() + " db) ---");

        for (Course course : data.getCourses()) {

            if (course.getTitle() == null) {
                writer.println("\n  * " + course.getCode() + " - Error Data");
                continue;
            }

            writer.println("\n  * " + course.getCode() + ": " + course.getTitle() + " (" + course.getCredits() + " credit)");

            if (course.getInstructor() != null && course.getInstructor().getName() != null) {
                writer.println("    Teacher: " + course.getInstructor().getName());
            }

            if (course.getSchedule() != null) {
                writer.println("    Date: " + course.getSchedule().getDay() + " " +
                        course.getSchedule().getStart() + "-" + course.getSchedule().getEnd() +
                        " (Class: " + course.getSchedule().getRoom() + ")");
            }

            Object studentsObj = course.getStudents();

            if (studentsObj instanceof List) {

                List<?> rawList = (List<?>) studentsObj;

                if (rawList.isEmpty()) {
                    writer.println("    Student (empty):");
                } else {
                    List<Student> students;
                    try {
                        students = dataConverter.convertValue(
                                rawList,
                                new TypeReference<List<Student>>() {}
                        );
                    } catch (Exception e) {
                        writer.println("    Student: error!");
                        continue;
                    }

                    writer.println("    Student (" + students.size() + " size):");
                    for (Student student : students) {
                        writer.println("      - " + student.toString());
                    }
                }
            }
            else if (studentsObj instanceof String) {
                writer.println("    Student: " + studentsObj.toString());
            }
            else {
                writer.println("    Student: Not found"); // 'not fynd' javítva
            }
        }

        writer.println("\n--- Advisors (" + data.getAdvisors().size() + " ) ---");
        for (Advisor advisor : data.getAdvisors()) {
            writer.println("  * " + advisor.getName() + " (Group: " + advisor.getGroups().toString() + ")");
        }
        writer.println("=========================================");
    }
}