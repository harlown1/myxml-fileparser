package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class ParserMain {

    public static final String FILE_NAME = "src/main/resources/test.xml";

    public static void main(String[] args) {
        String fileString = fileToString(FILE_NAME).replace(System.getProperty("line.separator"), "");
        StringTokenizer tokenizer = new StringTokenizer(fileString, "<", true);
        ParseTree tree = parseTokens(tokenizer);
        System.out.println(tree.getRoot().getChildren().get(0).getChildren().get(0).getBody());
    }

    public static String fileToString(String fileName) {
        FileReader reader = loadFile(fileName);
        StringBuilder builder = new StringBuilder();
        try {
            int currCharCode = reader.read();
            while (currCharCode != -1) {
                builder.append((char)(currCharCode));
                currCharCode = reader.read();
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

    public static ParseTree parseTokens(StringTokenizer tokenizer) {
        ParseTree tree = new ParseTree();
        Stack<ParseNode> nodeStack = new Stack<>();
        boolean nextIsTag = true;
        boolean openTag = true;
        while (tokenizer.hasMoreTokens()) {
            String token = nextIsTag ? tokenizer.nextToken("<") : tokenizer.nextToken(">");
            token = token.trim();
            if ("".equals(token)) {
                //Do nothing...
            } else if ("<".equals(token)) { //Open a tag
                nextIsTag = !nextIsTag;
                openTag = true;
            } else if (">".equals(token)) { //Close a tag
                nextIsTag = !nextIsTag;
                openTag = false;
            } else if (token.startsWith("/") && openTag) { //Do closing tag stuff
                if (!nodeStack.peek().getTagName().equals(token.substring(1))) {
                    throw new RuntimeException("Invalid file format: Tag not closed " + nodeStack.peek().getTagName());
                }
                nodeStack.pop();
            } else if (openTag) { // Do opening tag processing
                //Parse the tag to get all attributes

                //Create the node from the tag.
                ParseNode currNode = new ParseNode(token);
                if (!nodeStack.isEmpty()) {
                    ParseNode parent = nodeStack.peek();
                    parent.addChild(currNode);
                } else {
                    tree.getRoot().addChild(currNode);
                }
                nodeStack.push(currNode);
            } else if (nextIsTag){ // Add things to the body
                nodeStack.peek().setBody(token);
            } else {
                System.out.println("error");
            }
        }
        return tree;
    }

}
