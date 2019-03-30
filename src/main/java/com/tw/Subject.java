package com.tw;

import java.util.Arrays;

public class Subject {
    enum SubjectName {
        CHINESE("语文"), ENGLISH("英语"), PROGRAM("编程"), MATH("数学");
        private final String name;

        SubjectName(String name) {
            this.name = name;
        }

        static boolean isValidSubjectName(String name) {
            return Arrays.stream(SubjectName.values()).anyMatch(subjectName -> subjectName.name.equals(name));
        }

        static SubjectName getSubjectName(String name) {
            for (SubjectName subjectName : SubjectName.values()) {
                if (subjectName.name.equals(name)) {
                    return subjectName;
                }
            }
            return null;
        }
    }

    SubjectName name;
    double score;

    Subject(String name, double score) {
        this.name = SubjectName.getSubjectName(name);
        this.score = score;
    }

    static boolean isValidSubject(String name, double score) {
        return SubjectName.isValidSubjectName(name) && score >= 0 && score <= 100;
    }
}
