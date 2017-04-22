package pl.devdioniz.regextester;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author TheDioniz, created on 21.04.2017.
 */
public class RegexTesterTest {

    private static List<String> mockFileDataLines = new ArrayList<>();
    private static PrintStream out;

    @BeforeClass
    public static void getReferenceToDefaultOutput() {
        PrintStream out = System.out;
    }

    @BeforeClass
    public static void setUp() throws Exception {
        mockFileDataLines.add("10 abc 10:20:30");
        mockFileDataLines.add("24/07/2017 [DEBUG] GenericException, message: \"This is test\"");
        mockFileDataLines.add("Email: thedioniz@gmail.com");
        mockFileDataLines.add("User id: 102030");
    }

    @Test()
    public void createRegexTesterInstanceWithFile() throws IOException {
        RegexTester regexTester = new RegexTester(new File("mock"), true);

        assertNotNull(regexTester);
        assertNotNull(regexTester.getFile());
        assertNotNull(regexTester.getScanner());

        assertTrue(regexTester.isVerbose());

        assertNull(regexTester.getInputFileLines());
    }

    @Test
    public void createRegexTesterInstanceWithListOfStrings() throws IOException {
        RegexTester regexTester = new RegexTester(mockFileDataLines, true);

        assertNotNull(regexTester);
        assertNotNull(regexTester.getInputFileLines());
        assertNotNull(regexTester.getScanner());

        assertTrue(regexTester.isVerbose());

        assertNull(regexTester.getFile());
    }

    /*
        That test method prints result only via native System.out when testing regexp
        TODO implement the test for console output validation
     */

    @Test
    public void checkIfUserInputIsCorrectlyProcessed() throws IOException {

        String fakeRegex = "^Email.*\n.*Email.*\nq";
        ByteArrayInputStream mockInput = new ByteArrayInputStream(fakeRegex.getBytes());

        RegexTester regexTester = new RegexTester(mockFileDataLines, false, mockInput);
        regexTester.runInteractive();



    }

}