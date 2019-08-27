package parser;

import java.util.ArrayList;
import java.util.List;

public class ParseNode {

    private String tagName;
    private String body;
    private List<ParseNode> children;
    //    private List<String> attributes;

    public ParseNode() {
        children = new ArrayList<>();
    }

    public ParseNode(String tagName) {
        this();
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String bodyText) {
        body = bodyText;
    }

    public void addChild(ParseNode child) {
        children.add(child);
    }

    public List<ParseNode> getChildren() {
        return this.children;
    }

}
