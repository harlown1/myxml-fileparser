package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ParserMain {

    public static final String FILE_NAME = "";

    public static void main(String[] args) {
        String fileString = fileToString(FILE_NAME);
        StringTokenizer tokenizer = new StringTokenizer(fileString, "<", true);

        while (tokenizer.hasMoreTokens()) {
            tokenizer.nextToken();
        }

    }

    public static String fileToString(String fileName) {
        FileReader reader = loadFile(fileName);
        StringBuilder builder = new StringBuilder();
        try {
            int currCharCode = reader.read();
            while (currCharCode != -1) {
                builder.append(currCharCode);
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return builder.toString();
    }

    public static FileReader loadFile(String fileName) {
        FileReader reader = null;
        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fileName);
        }
        return reader;
    }

    public static String getTag(StringTokenizer tokenizer) {
        return null;
    }

    public static String getBody(StringTokenizer tokenizer) {
        return null;
    }

}
