package com.tw;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddStudent implements Service {
    private Service mainMenu;

    private StudentInfo studentInfo;

    public AddStudent(Service mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public String buildInputPrompt(boolean isFirst) {
        return isFirst ? "请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：" : "请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：";
    }

    @Override
    public Object getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String command = null;
        if (scanner.hasNextLine()) {
            command = scanner.nextLine();
        }
        return command;
    }

    @Override
    public boolean isValidCommand(Object command) {
        String info = (String) command;
        studentInfo = new StudentInfo(info);
        return studentInfo.isValidStudentInfo();
    }

    @Override
    public void handleCommand(Object command) {
        StudentDatabase.addStudent(studentInfo.getStudent());
        System.out.println(buildAddStudentSuccessPrompt());
        mainMenu.main();
    }

    private String buildAddStudentSuccessPrompt() {
        return "学生" + studentInfo.getName() + "的成绩被添加";
    }
}

class StudentInfo {
    private String info;
    private String name;
    private String number;
    private Subject chinese;
    private Subject math;
    private Subject english;
    private Subject program;

    StudentInfo(String info) {
        this.info = info;
    }

    boolean isValidStudentInfo() {
        String regex = "^(.+)\\b,\\s+(\\d{3}),\\s+\\b(.+)\\b:\\s+([-+]?[0-9]*\\.?[0-9]+),\\s+\\b(.+)\\b:\\s+([-+]?[0-9]*\\.?[0-9]+),\\s+\\b(.+)\\b:\\s+([-+]?[0-9]*\\.?[0-9]+),\\s+\\b(.+)\\b:\\s+([-+]?[0-9]*\\.?[0-9]+)$";
        Matcher matcher = Pattern.compile(regex).matcher(info);
        if (matcher.matches()) {
            this.name = matcher.group(1);
            this.number = matcher.group(2);
            String subjectOneName = matcher.group(3);
            double subjectOneScore = Double.parseDouble(matcher.group(4));
            String subjectTwoName = matcher.group(5);
            double subjectTwoScore = Double.parseDouble(matcher.group(6));
            String subjectThreeName = matcher.group(7);
            double subjectThreeScore = Double.parseDouble(matcher.group(8));
            String subjectFourName = matcher.group(9);
            double subjectFourScore = Double.parseDouble(matcher.group(10));
            HashMap<String, Double> subjectMap = new HashMap<>();
            subjectMap.put(subjectOneName, subjectOneScore);
            subjectMap.put(subjectTwoName, subjectTwoScore);
            subjectMap.put(subjectThreeName, subjectThreeScore);
            subjectMap.put(subjectFourName, subjectFourScore);
            if (Subject.isValidSubject(subjectOneName, subjectOneScore) && Subject.isValidSubject(subjectTwoName, subjectTwoScore) && Subject.isValidSubject(subjectThreeName, subjectThreeScore) && Subject.isValidSubject(subjectFourName, subjectFourScore) && subjectMap.size() == 4) {
                subjectMap.forEach((subjectName, subjectScore) -> {
                    switch (Subject.SubjectName.getSubjectName(subjectName)) {
                        case CHINESE: {
                            this.chinese = new Subject(subjectName, subjectScore);
                            break;
                        }
                        case MATH: {
                            this.math = new Subject(subjectName, subjectScore);
                            break;
                        }
                        case ENGLISH: {
                            this.english = new Subject(subjectName, subjectScore);
                            break;
                        }
                        case PROGRAM: {
                            this.program = new Subject(subjectName, subjectScore);
                            break;
                        }
                    }
                });
                return true;
            }
            return false;
        }
        return false;
    }

    String getName() {
        return name;
    }

    Student getStudent() {
        return new Student(name, number, chinese, english, math, program);
    }
}