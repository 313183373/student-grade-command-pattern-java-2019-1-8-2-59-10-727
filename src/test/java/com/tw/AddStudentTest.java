package com.tw;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddStudentTest {
    private AddStudent addStudent;

    @Before
    public void beforeEach() {
        addStudent = new AddStudent(new MainMenu());
    }

    @Test
    public void should_return_a_input_prompt_first_time() {
        assertEquals("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：", addStudent.buildInputPrompt(true));
    }

    @Test
    public void should_return_another_input_prompt() {
        assertEquals("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：", addStudent.buildInputPrompt(false));
    }

    @Test
    public void should_return_true_when_input_is_right() {
        String command = "张三, 100, 语文: 0, 英语: 0.20, 编程: 30, 数学: 100";
        assertTrue(addStudent.isValidCommand(command));
        command = "李四四, 241, 语文: 10.3, 英语: 20.5, 编程: 35.2341, 数学: 100.00";
        assertTrue(addStudent.isValidCommand(command));
        command = "王五, 543, 英语: 23.4, 语文: 100, 编程: 0, 数学: 12";
        assertTrue(addStudent.isValidCommand(command));
    }

    @Test
    public void should_return_false_when_input_is_wrong() {
        // student number is wrong \d{3}
        String command = "张三, 1003, 语文: 10, 英语: 20, 编程: 30, 数学: 100";
        assertFalse(addStudent.isValidCommand(command));
        // no name
        command = "241, 语文: 10.3, 英语: 20.5, 编程: 35.2341, 数学: 100.00";
        assertFalse(addStudent.isValidCommand(command));
        // no english grade
        command = "王五, 543, 语文: 100, 编程: 0, 数学: 12";
        assertFalse(addStudent.isValidCommand(command));
        // english grade is error
        command = "王五, 543, 英语: 200 语文: 100, 编程: 0, 数学: 12";
        assertFalse(addStudent.isValidCommand(command));
        // wrong format
        command = "王五, 英语: 33, 语文: 100, 编程: 0, 数学: 12, 543";
        assertFalse(addStudent.isValidCommand(command));
        // wrong format
        command = "王五, 123, 英语: 23, 语文:100, 编程: 0, 数学: 12";
        assertFalse(addStudent.isValidCommand(command));
    }
}
