import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Error: Please provide exactly two file names.");
            return;
        }
        String workFileName = args[0];
        String jobFileName = args[1];

        Validator validator=new Validator();
        validator.fileControl(workFileName,jobFileName);

        FileParser fileParser = new FileParser();


        //validator.getErrors().add("T5 has a negative task size.");

        // Checking for errors
        if (validator.hasErrors()) {
            System.out.println("Errors occurred:");
            for (String error : validator.getErrors()) {
                System.out.println(error);
            }
            System.exit(1);
        } else {
            System.out.println("No errors detected.");
        }

        //fileParser.printFile();



    }


}

