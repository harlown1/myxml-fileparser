package parser;

import java.util.List;
import java.util.ArrayList;

public class ParseTree {
    private ParseNode root;

    /**
     * Creates a tree with a default root 'document' with no children, body, etc.
     */
    public ParseTree() {
        root = new ParseNode("document ");
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
