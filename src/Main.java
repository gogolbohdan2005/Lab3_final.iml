import Unit.Tank;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // WRITING TO FILE HANDLING EXCEPTIONS
        try {
            String filePath = "D:\\Java Programing\\Lab3_final\\src\\output.txt";
            // Create a FileOutputStream to write to the file
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            // Create a PrintStream that writes to the file
            PrintStream printStream = new PrintStream(fileOutputStream);
            // Redirect System.out to the file using System.setOut
            System.setOut(printStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error file", e);
        }
        Battlefield battlefield = new Battlefield(1);
    }
}