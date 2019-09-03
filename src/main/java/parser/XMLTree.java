package parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class XMLTree {
    private XMLNode root;
    private List<XMLNode> metaNodes;

    public XMLTree() {
        metaNodes = new ArrayList<>();
    }

    /**
     * Parses the XML file with the given filename. If the file isn't found, a RuntimeException is thrown
     * @param fileName the name of the file to find and parse.
     * @return String The string representation of the XML file at the fileName.
     */
    public static XMLTree parseFromFile(String fileName) {
        String fileAsString = readFromFile(fileName);
        StringTokenizer tokenizer = new StringTokenizer(fileAsString, "<", true);
        return parseTokens(tokenizer);
    }

    /**
     * Takes the given filename and reads in the file as a String that can be tokenized and parsed.
     * @param fileName the file name to parse.
     * @return The file as a String.
     * @throws RuntimeException is thrown under some errors, but if the file isn't well defined
     *                          or well formed, nothing is guaranteed.
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

    /**
     * Parses the tokens made by the tokenizer using the tag delimiters.
     * @param tokenizer the tokenizer with delimiters returned, starting with "<" as the first delimiter set.
     * @return The parsed XMLTree.
     */
    private static XMLTree parseTokens(StringTokenizer tokenizer) {
        XMLTree tree = new XMLTree();
        Stack<XMLNode> nodeStack = new Stack<>();
        boolean nextIsTag = true;
        boolean openTag = true;
        while (tokenizer.hasMoreTokens()) {
            String token = nextIsTag ? tokenizer.nextToken("<") : tokenizer.nextToken(">");
            token = token.trim();
            if ("".equals(token)) {
                //Do nothing...
            } else if (nodeStack.isEmpty() && token.startsWith("?")) { // Meta/header tag.
                XMLNode currNode = parseOpenTag(token);
                tree.addMetaNode(currNode);
            } else if ("<".equals(token)) { // Open a tag
                nextIsTag = !nextIsTag;
                openTag = true;
            } else if (">".equals(token)) { // Close a tag
                nextIsTag = !nextIsTag;
                openTag = false;
            } else if (token.startsWith("/") && openTag) { // Do closing tag stuff
                if (!nodeStack.peek().getTagName().equals(token.substring(1))) {
                    throw new RuntimeException("Invalid file format: Tag not closed " + nodeStack.peek().getTagName());
                }
                nodeStack.pop();
            } else if (openTag) { // Do opening tag processing
                // Parse the tag to get name and all attributes
                XMLNode currNode = parseOpenTag(token);
                if (!nodeStack.isEmpty()) {
                    XMLNode parent = nodeStack.peek();
                    parent.addChild(currNode);
                } else {
                    XMLNode root = tree.getRoot();
                    if (root != null) {
                        throw new RuntimeException("File has more than one root defined");
                    }
                    tree.setRoot(currNode);
                }
                nodeStack.push(currNode);
            } else if (nextIsTag) { // Add things to the body
                XMLNode topNode = nodeStack.peek();
                topNode.setBody(topNode.getBody() + token);
            } else {
                throw new RuntimeException("Error parsing the file");
            }
        }
        if (tree.getRoot() == null) {
            throw new RuntimeException("Error parsing the file");
        }
        return tree;
    }

    private static XMLNode parseOpenTag(String tagContents) {
        String[] spaceSeparatedTag = tagContents.split(" ");
        String tagName = spaceSeparatedTag[0]; // Extract the tag name
        StringBuilder tagNoNameBuilder = new StringBuilder();
        for (int i = 1; i < spaceSeparatedTag.length; i++) {
            tagNoNameBuilder.append(spaceSeparatedTag[i] + " ");
        }
        String tagNoName = tagNoNameBuilder.toString().trim();
        //Create the node from the tag.
        XMLNode currNode = new XMLNode(tagName);
        if (!"".equals(tagNoName.trim())) {
            parseAttributes(tagNoName, currNode);
        }
        return currNode;
    }

    /**
     * Parses the attributes part of the tag.  The tag head must be removed before calling.
     * @param attributesString
     * @param node
     */
    private static void parseAttributes(String attributesString, XMLNode node) {
        StringTokenizer tokenizer = new StringTokenizer(attributesString, "\"", true);
        String lastAttrName = "";
        boolean openAttr = false;
        while (tokenizer.hasMoreTokens()) {
            String currToken = tokenizer.nextToken().trim();
            if ("\"".equals(currToken)) {
                openAttr = !openAttr;
            } else if (!openAttr) {
                currToken = currToken.replace("=", "");
                node.addAttributeName(currToken);
                lastAttrName = currToken;
            } else {
                node.addValToAttribute(lastAttrName, currToken);
            }
        }
    }

    /**
     * Gets the root of the tree and returns the node. The root by default is named 'document'
     * @return
     */
    public XMLNode getRoot() {
        return this.root;
    }

    public void setRoot(XMLNode root) {
        this.root = root;
    }

    public void addMetaNode(XMLNode metaNode) {
        metaNodes.add(metaNode);
    }

    public List<XMLNode> getMetaNodes() {
        return metaNodes;
    }

    /**
     * Gets the root of the tree with the given ID. ID's should be unique but isn't strictly enforced.  Any
     * single node with the given ID will be returned if there are multiple. If no node with that ID is found,
     * then null is returned.  Otherwise returns the node with the given ID.
     * @return
     */
    public XMLNode getNodeById(String nodeId) {
        return null;
    }

    /**
     * Gets all the nodes that have the class of className.  WIll return an empty list if there are no nodes with
     * the given class.
     * @return List of all nodes with the given class.
     */
    public List<XMLNode> getNodesWithClass(String className) {
        return null;
    }

}
