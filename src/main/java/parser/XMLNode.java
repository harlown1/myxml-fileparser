package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLNode {

    private String tagName;
    private String body;
    private List<XMLNode> children;
    private Map<String, String> attributes;

    /**
     * Creates a new node with the given tag name. The tagname is required and also immutable after creation.
     * @param tagName
     */
    public XMLNode(String tagName) {
        children = new ArrayList<>();
        attributes = new HashMap<>();
        this.tagName = tagName;
    }

    /**
     * returns the name of the tag.
     * @return
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Gets the current value of the body. If the body contains no value it returns the empty string.
     * @return String - the current value of the body - empty string if there is no value/null
     */
    public String getBody() {
        return (body != null ? body : "");
    }

    /**
     * Sets the body of the node to be bodyText.
     * @param bodyText The value to set the body to.
     */
    public void setBody(String bodyText) {
        body = bodyText;
    }

    /**
     * Adds the node child as a child of 'this' node
     * @param child
     */
    public void addChild(XMLNode child) {
        children.add(child);
    }

    /**
     * Returns the list of nodes that are the children of 'this'
     * @return
     */
    public List<XMLNode> getChildren() {
        return this.children;
    }

    /**
     * Adds the attribute to the node with an empty value.
     * @param attributeName
     */
    public void addAttributeName(String attributeName) {
        attributes.put(attributeName, "");
    }

    /**
     * Adds the attribute to the node with the value of val.
     * @param attributeName The name of the attribute to add.
     * @param val The value of the attribute
     */
    public void addValToAttribute(String attributeName, String val) {
        attributes.put(attributeName, val);
    }

    /**
     * Returns the string value associated with the given attribute name. "" if no value is associated, null if
     * the attribute isn't a given node.
     * @param attributeName
     * @return null if there is no attribute with the given name. Empty string if attribute has
     *         no value associated with it.
     */
    public String getAttributeValue(String attributeName) {
        return attributes.get(attributeName);
    }

    /**
     * Gets a list of all the attributes that are valid attributes of the node.
     * @return A list of the attributes that are defined as part of the node.
     */
    public List<String> getAllAttributeNames() {
        return new ArrayList<String>(attributes.keySet());
    }

}
