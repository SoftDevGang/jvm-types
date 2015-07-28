package com.softdevgang.types;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AnswerTest {
    @Test
    public void get_the_answer() {
        int expected = 42;

        Answer answer = new Answer();

        int actual = answer.getTheAnswer();

        assertThat(actual, is(expected));
    }
}
