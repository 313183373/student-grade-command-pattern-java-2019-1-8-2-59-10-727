package com.tw;

public class Student {
    String name;
    String number;
    Subject chinese;
    Subject english;
    Subject math;
    Subject program;

    public Student(String name, String number, Subject chinese, Subject english, Subject math, Subject program) {
        this.name = name;
        this.number = number;
        this.chinese = chinese;
        this.english = english;
        this.math = math;
        this.program = program;
    }

    private double getAverage() {
        return getTotal() / 4;
    }

    double getTotal() {
        return chinese.score + english.score + math.score + program.score;
    }

    @Override
    public String toString() {
        return name + "|" + math.score + "|" + chinese.score + "|" + english.score + "|" + program.score + "|" + getAverage() + "|" + getTotal();
    }
}
