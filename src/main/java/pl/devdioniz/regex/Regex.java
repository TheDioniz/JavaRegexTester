package pl.devdioniz.regex;


import pl.devdioniz.regextester.RegexTester;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

/**
 * @author TheDioniz, created on 19.04.2017.
 */
public class Regex {

    public static void main(String[] args) throws IOException {

        String fileLocation;

        // if no argument passed, run with build-in test log file
        if (args.length == 0) {

            URL url = Regex.class.getClass().getResource("/apache.log");
            // if for some reason the file cannot be loaded from resources dir
            if (url == null) {
                System.out.printf("Podany plik nie istnieje, bądź jest niemożliwy do odczytania. Brak uprawnień?");
                System.exit(0);
            }

            fileLocation = url.getFile();

        } else {
            fileLocation = args[0];
        }

        File file = new File(fileLocation);

        if (file == null || !file.exists() || !file.canRead()) {
            System.out.printf("Podany plik nie istnieje, bądź jest niemożliwy do odczytania. Brak uprawnień?");
        }

        RegexTester regexTester = new RegexTester(file, true);
        regexTester.runInteractive();

    }
}
