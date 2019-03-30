package com.tw;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BuildGrade implements Service {
    private Service mainMenu;

    public BuildGrade(Service mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public String buildInputPrompt(boolean isFirst) {
        return isFirst ? "请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：" : "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：";
    }

    @Override
    public Object getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        if (scanner.hasNextLine()) {
            command = scanner.nextLine();
        }
        return command;
    }

    @Override
    public boolean isValidCommand(Object command) {
        String regex = "^(\\d{3})(, \\d{3})*$";
        return Pattern.matches(regex, (CharSequence) command);
    }

    @Override
    public void handleCommand(Object command) {
        String c = (String) command;
        HashSet<String> studentNumbers = new HashSet<>(Arrays.asList(c.split(", ")));
        List<Student> filteredStudent = StudentDatabase.getStudents().stream().filter(student -> studentNumbers.contains(student.number)).collect(Collectors.toList());
        String scoreString = filteredStudent.stream().map(Student::toString).collect(Collectors.joining("\n"));
        double totalScore = filteredStudent.stream().mapToDouble(Student::getTotal).average().orElse(0);
        List<Double> sorted = filteredStudent.stream().mapToDouble(Student::getTotal).sorted().boxed().collect(Collectors.toList());
        double middle = (sorted.size() - 1) / 2.0;
        int leftIndex = (int) middle;
        int rightIndex = (int) Math.ceil(middle);
        double middleScore = (sorted.get(leftIndex) + sorted.get(rightIndex)) / 2.0;
        String scoreSheet = "成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" + scoreString + "\n" +
                "========================\n" +
                "全班总分平均数：" + totalScore + "\n" +
                "全班总分中位数：" + middleScore;
        System.out.println(scoreSheet);
        mainMenu.main();
    }
}
