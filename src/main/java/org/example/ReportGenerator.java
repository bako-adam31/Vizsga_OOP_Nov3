package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.PrintWriter;
import java.util.List;

public class ReportGenerator {

    private ObjectMapper dataConverter;

    public ReportGenerator() {
        this.dataConverter = new ObjectMapper();
    }

    // --- 1. METÓDUS: RÉSZLETES RIPORT (A FÁJLNAK) ---
    // Ez a te régi metódusod, átnevezve 'generateDetailedReport'-ra
    // A tartalma VÁLTOZATLAN, továbbra is kiírja a súlyozott átlagot, stb.
    public void generateDetailedReport(RootData data, PrintWriter writer) {

        writer.println("\n--- RÉSZLETES RIPORT ---");
        writer.println("=========================================");
        writer.println("Term: " + data.getTerm());
        writer.println("Department: " + data.getDepartment().getName() + " (" + data.getDepartment().getCode() + ")");

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
                        students = dataConverter.convertValue(rawList, new TypeReference<List<Student>>() {});
                    } catch (Exception e) {
                        writer.println("    Student: error!");
                        continue;
                    }
                    writer.println("    Student (" + students.size() + " size):");
                    for (Student student : students) {
                        writer.println("      - " + student.toString());

                        List<Integer> grades = student.getGrades();
                        List<Assessment> assessments = course.getAssessments();
                        if (grades == null || assessments == null || grades.size() != assessments.size()) {
                            writer.println("        -> Átlag: Nem számítható.");
                        } else {
                            double weightedSum = 0.0;
                            double totalWeight = 0.0;
                            for (int i = 0; i < grades.size(); i++) {
                                weightedSum += grades.get(i) * assessments.get(i).getWeight();
                                totalWeight += assessments.get(i).getWeight();
                            }
                            if (Math.abs(totalWeight - 1.0) > 0.01) {
                                writer.println("        -> Átlag: Nem számítható (súlyok összege nem 100%)");
                            } else {
                                writer.println(String.format("        -> SÚLYOZOTT ÁTLAG: %.2f", weightedSum));
                            }
                        }
                    }
                }
            } else if (studentsObj instanceof String) {
                writer.println("    Student: " + studentsObj.toString());
            } else {
                writer.println("    Student: Not found");
            }
        }
        writer.println("\n--- Advisors (" + data.getAdvisors().size() + " ) ---");
        for (Advisor advisor : data.getAdvisors()) {
            writer.println("  * " + advisor.getName() + " (Group: " + advisor.getGroups().toString() + ")");
        }
        writer.println("=========================================");
    }



    public void generateSummaryReport(RootData data, PrintWriter writer) {

        writer.println("\n--- TÖMÖR ÖSSZEFOGLALÓ ---");
        writer.println("==========================");
        writer.println("Félév: " + data.getTerm());
        writer.println("Tanszék: " + data.getDepartment().getName());
        writer.println("Beolvasott kurzusok száma: " + data.getCourses().size());
        writer.println("Beolvasott témavezetők száma: " + data.getAdvisors().size());

        writer.println("\n--- Kurzusok Listája (Névsor) ---");
        for (Course course : data.getCourses()) {
            if (course.getTitle() != null) {
                writer.println("  * " + course.getCode() + ": " + course.getTitle());
            } else {
                writer.println("  * " + course.getCode() + ": ÉRVÉNYTELEN CÍM");
            }
        }
        writer.println("==========================");
        writer.println("\n(A részletes riport a 'fajl.txt' fájlban található.)");
    }
}