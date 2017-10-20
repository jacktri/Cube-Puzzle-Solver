package solver.utils;

import java.io.IOException;

public class FileWriter {

    private FileWriter(){

    }

    public static void writeToFile(String input, String file) throws IOException{
        try (java.io.FileWriter fileWriter = new java.io.FileWriter(file, false)) {
            fileWriter.write(input);
            fileWriter.close();
        }
    }
}
