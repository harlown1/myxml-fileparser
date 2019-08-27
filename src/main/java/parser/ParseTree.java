package parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class ParseTree {
    private ParseNode root;

    /**
     * Creates a tree with a default root 'document' with no children, body, etc.
     */
    public ParseTree() {
        root = new ParseNode("document ");
    }

    /**
     * Parses the XML file with the given filename. If the file isn't found, a RuntimeException is thrown
     * @param fileName the name of the file to find and parse.
     * @return String The string representation of the XML file at the fileName.
     */
    public static ParseTree parseFromFile(String fileName) {
        String fileAsString = readFromFile(fileName);
        StringTokenizer tokenizer = new StringTokenizer(fileAsString, "<", true);
        return parseTokens(tokenizer);
    }

    /**
     * Takes the given filename and reads in the file as a String that can be tokenized and parsed.
     * @param fileName the file name to parse.
     * @return The file as a String.
     */
    private static String readFromFile(String fileName) {
        FileReader reader = null;
        try {
            reader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fileName);
        }
        StringBuilder builder = new StringBuilder();
        try {
            int currCharCode = reader.read();
            while (currCharCode != -1) {
                builder.append((char)(currCharCode));
                currCharCode = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file.");
        }
        return builder.toString();
    }

    private static ParseTree parseTokens(StringTokenizer tokenizer) {
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


    public ParseNode getRoot() {
        return this.root;
    }

    public String getNodeById() {
        return null;
    }

    public List<ParseNode> getNodesWithClass() {
        return null;
    }



}
