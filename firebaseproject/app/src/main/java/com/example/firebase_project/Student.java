package com.example.firebase_project;

public class Student {
    String name, studentId, id;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
    }

    public Student(String name, String studentId, String id) {
        this.name = name;
        this.studentId = studentId;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
