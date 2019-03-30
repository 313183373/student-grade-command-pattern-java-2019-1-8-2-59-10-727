package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;

public class MainMenuTest {
    private MainMenu mainMenu;

    @Before
    public void beforeEach() {
        mainMenu = new MainMenu();
    }

    @Test
    public void should_return_a_input_prompt() {
        assertEquals("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出请输入你的选择（1～3）：", mainMenu.buildInputPrompt(true));
    }

    @Test
    public void should_return_another_input_prompt() {
        assertEquals("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出请输入你的选择（1～3）：", mainMenu.buildInputPrompt(false));
    }

    @Test
    public void should_return_a_user_input_command() {
        String command = "1";
        ByteArrayInputStream in = new ByteArrayInputStream(command.getBytes());
        System.setIn(in);
        Object c = mainMenu.getUserInput();
        assertEquals(Integer.parseInt(command), c);
        assertTrue(mainMenu.isValidCommand(c));
        System.setIn(System.in);
    }

    @Test
    public void should_return_true_when_user_input_a_valid_command() {
        assertTrue(mainMenu.isValidCommand(1));
        assertTrue(mainMenu.isValidCommand(2));
        assertTrue(mainMenu.isValidCommand(3));
    }

    @Test
    public void should_return_false_when_user_input_a_valid_command() {
        assertFalse(mainMenu.isValidCommand(0));
        assertFalse(mainMenu.isValidCommand(4));
    }
}