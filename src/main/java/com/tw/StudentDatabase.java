package com.tw;

import java.util.ArrayList;

public class StudentDatabase {
    static ArrayList<Student> students = new ArrayList<>();

    static void addStudent(Student student) {
        students.add(student);
    }

    public static ArrayList<Student> getStudents() {
        return students;
    }
}