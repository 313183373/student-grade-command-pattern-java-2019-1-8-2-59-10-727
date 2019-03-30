package com.tw;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BuildGradeTest {
    private BuildGrade buildGrade;

    @Before
    public void beforeEach() {
        buildGrade = new BuildGrade(new MainMenu());
    }

    @Test
    public void should_return_true_when_input_is_right() {
        assertTrue(buildGrade.isValidCommand("123, 132"));
    }
}
