package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ParserMain {

    public static final String fileName = "";

    public static void main(String[] args) {
        FileReader reader = loadFile(fileName);
        StringBuilder builder = new StringBuilder();
        StringTokenizer tokenizer = null;
        try {
            int currCharCode = reader.read();
            while (currCharCode != -1) {
                builder.append(currCharCode);
            }
            tokenizer = new StringTokenizer(builder.toString(), "<", true);
            String tag = tokenizer.nextToken(">");
            System.out.print(tag);
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    public static FileReader loadFile(String fileName) {
        FileReader reader = null;
        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
        return reader;
    }

}
