package pl.devdioniz.regex;

import pl.devdioniz.regextester.RegexTester;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author TheDioniz, created on 19.04.2017.
 */
public class Regex {

    public static void main(String[] args) throws IOException {

        String fileLocation;

        // only for inside IDE testing
        if (args.length == 0) {
            System.out.println("Brak argumentu: ścieżka do pliku");
            System.exit(1);
        }

        fileLocation = args[0];

        File file = new File(fileLocation);
        // verify user's given file
        if (!file.exists() || !file.canRead()) {
            System.out.println(file);
            System.out.printf("Podany plik nie istnieje, bądź jest niemożliwy do odczytania. Brak uprawnień?");
            System.exit(1);
        }

        RegexTester regexTester = new RegexTester(file, true);
        regexTester.runInteractive();

    }
}
