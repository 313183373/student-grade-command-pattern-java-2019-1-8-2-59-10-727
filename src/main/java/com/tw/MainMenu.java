package com.tw;

import java.util.Arrays;
import java.util.Scanner;

public class MainMenu implements Service {

    private Service addStudent = new AddStudent(this);
    private Service buildGrade = new BuildGrade(this);

    enum Command {
        ADD_STUDENT_INFO(1), BUILD_GRADE(2), EXIT(3);

        private int code;

        Command(int code) {
            this.code = code;
        }

        static boolean isValidCommand(int i) {
            return Arrays.stream(Command.values()).anyMatch(command -> command.code == i);
        }

        static Command getCommand(int i) {
            for (Command command : Command.values()) {
                if (command.code == i) {
                    return command;
                }
            }
            return null;
        }
    }

    @Override
    public String buildInputPrompt(boolean isFirst) {
        return "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出请输入你的选择（1～3）：";
    }

    @Override
    public Object getUserInput() {
        Scanner scanner = new Scanner(System.in);
        int command = 0;
        if (scanner.hasNextInt()) {
            command = scanner.nextInt();
        }
        return command;
    }

    @Override
    public boolean isValidCommand(Object command) {
        return Command.isValidCommand((Integer) command);
    }

    @Override
    public void handleCommand(Object command) {
        Command c = Command.getCommand((Integer) command);
        switch (c) {
            case ADD_STUDENT_INFO: {
                addStudent.main();
                break;
            }
            case BUILD_GRADE: {
                buildGrade.main();
                break;
            }
            case EXIT: {
                return;
            }
            default: {
                main();
                break;
            }
        }
    }

    public static void main(String[] args) {
        new MainMenu().main();
    }
}
