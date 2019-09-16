package ru.otus.boot.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CommandLineServiceTest {

    private static final String ANY_INPUT_MESSAGE = "any input";
    private static final String ANY_OUTPUT_MESSAGE = "any output";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private CommandLineService commandLineService;

    @Before
    public void before() {
        System.setIn(new ByteArrayInputStream(ANY_INPUT_MESSAGE.getBytes()));
        System.setOut(new PrintStream(outContent));
        commandLineService = new CommandLineService();
    }

    @Test
    public void askQuestion() {
        String userInput = commandLineService.askQuestion(ANY_OUTPUT_MESSAGE);
        assertEquals(ANY_OUTPUT_MESSAGE + System.lineSeparator(), outContent.toString());
        assertEquals(userInput, ANY_INPUT_MESSAGE);
    }

    @Test
    public void printMessage() {
        commandLineService.printMessage(ANY_OUTPUT_MESSAGE);
        assertEquals(ANY_OUTPUT_MESSAGE + System.lineSeparator(), outContent.toString());
    }
}