package pl.devdioniz.regextester;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This program represents command line tool to test Java Regular Expressions.
 * Algorithm implemented:
 * <ul>
 *     <li>get from the user input file during object creation</li>
 *     <li>get the regexp from the user</li>
 *     <li>search for given pattern in the file</li>
 *     <li>print out the results</li>
 *     <li>repeat step 2 to 4 until user enters EXIT_PROGRAM_SIGN</li>
 * </ul>
 *
 * @author TheDioniz, created on 20.04.2017.
 */
public class RegexTester {

    private final String EXIT_PROGRAM_SIGN = "q";
    private File file;
    private final String PROMPT = "Regex> ";
    private final boolean verbose;
    private Scanner scanner;
    private List<String> inputFileLines;

    public RegexTester(File file, boolean verbose) throws IOException {
        this(file, verbose, System.in); // default data source = user input from the console
    }

    /**
     *
     * @param file to be searched by regular expressions
     * @param verbose decides if welcome message should be printed
     * @param in InputStream object for working as a data source
     */
    public RegexTester(File file,  boolean verbose, InputStream in) {
        this.verbose = verbose;
        setFile(file);
        scanner = new Scanner(in);
    }

    public RegexTester(List<String> lines, boolean verbose) throws IOException {
        this(lines, verbose, System.in); // default data source = user input from the console
    }

    /**
     *
     * @param lines file read as lines to be searched by regular expressions
     * @param verbose decides if welcome message should be printed
     * @param in InputStream object for working as a data source
     */
    public RegexTester(List<String> lines,  boolean verbose, InputStream in) {
        this.verbose = verbose;
        setInputFileAsLines(lines);
        scanner = new Scanner(in);
    }

    /**
     *
     * @return list of String objects representing lines of the file
     * @throws IOException
     */
    private List<String> readFile() throws IOException {
        // TODO niewydajne wczytywanie, FIX-ME!
        return (inputFileLines == null) ? Files.readAllLines(file.toPath()) : inputFileLines;
    }

    public void runInteractive() throws IOException {

        inputFileLines = readFile();

        if (verbose) {
            welcome();
        }

        // initialization not needed, thanks to do-while
        String pattern;

        // main loop
         do {

            pattern = getRegexLine();

            // if help needed - print it
            if ("help".equals(pattern)) {
                printHelp();
            }

             if ("print".equals(pattern)) {
                 printFile();
             }

            // file VS regex
            for (String line : inputFileLines) {
                if (line.matches(pattern)) {
                    System.out.println(line);
                }
            }


         } while (!EXIT_PROGRAM_SIGN.equals(pattern));

         System.out.println("Bye!");
    }

    public List<String> getInputFileLines() {
        return (inputFileLines == null) ? null : new ArrayList<>(inputFileLines);
    }

    public void setInputFileAsLines(List<String> inputFileLines) {
        this.inputFileLines = new ArrayList<>(inputFileLines);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {

        if (file == null) {
            throw new IllegalArgumentException("Plik musi byc zainicjalizowany! (null reference)");
        }

        this.file = file;
    }

    private void printFile() {
        for (String line: inputFileLines) {
            System.out.println(line);
        }
    }
    // TODO this way, or load the file from external .txt?
    public void printHelp() {
        System.out.println(". - jeden znak (bez znaku nowej linii)");
        System.out.println(".* - zero lub więcej znaków");
        System.out.println("\\w* - zero lub więcej słów");
    }

    private String getRegexLine() {
        System.out.print(PROMPT);
        return scanner.nextLine();
    }

    public boolean isVerbose() {
        return verbose;
    }

    public Scanner getScanner() {
        return scanner;
    }

    private void welcome() {
        System.out.println("------------------------------------------------------------------");
        System.out.printf("%48s", "Witaj w progrmie Regex Tester!\n\n");
        System.out.println("Załadowano następujący plik do przeprowadzania testów:\n" + file);
        System.out.println("\nMiłej zabawy z wyrażeniami regularnymi w Java!");
        System.out.println("\n\n[[ 1. By wyjść wpisz 'q' i zatwierdź ]]");
        System.out.println("[[ 2. By uruchomić program z zewnętrznym plikiem, przekaż ścieżkę jako argument programu. ]]");
        System.out.println("------------------------------------------------------------------");
    }
}
