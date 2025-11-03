package org.example;

import java.util.List;


public class Student {
    private String id;
    private String name;
    private int year;
    private String group;
    private List<Integer> grades; // A JSON-ban ez [8, 9, 10]

    public Student() {
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }
    public List<Integer> getGrades() { return grades; }
    public void setGrades(List<Integer> grades) { this.grades = grades; }

    @Override
    public String toString() {
        return name + " (ID: " + id + ", Csoport: " + group + ")";
    }
}