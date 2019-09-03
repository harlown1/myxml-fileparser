package unit;

import org.junit.Before;
import org.junit.Test;
import parser.XMLNode;
import parser.XMLTree;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class XMLTreeTest {

    public static final String TEST_FILE_PATH = "src/test/resources/unit/unit-test-parse.xml";

    @Test
    public void testParseFullFile() {
        XMLTree mainTree = XMLTree.parseFromFile(TEST_FILE_PATH);
        XMLNode root = mainTree.getRoot();
        assertEquals(root.getTagName(), "head");
        assertEquals(root.getAllAttributeNames().size(), 3);
        List<XMLNode> children = root.getChildren();
        assertEquals(children.size(), 3);
        assertEquals(children.get(1).getTagName(), "brother");
        XMLNode currNode = children.get(0);
        assertEquals(currNode.getTagName(), "child");
        assertEquals(currNode.getBody(), "main test child!");
        List<String>  gchildAttrs = currNode.getChildren().get(0).getAllAttributeNames();
        assertEquals(gchildAttrs.size(), 2);
        assertEquals("favorite", currNode.getChildren().get(0).getAttributeValue("id"));
        assertEquals(mainTree.getMetaNodes().size(), 1);
        assertEquals(mainTree.getMetaNodes().get(0).getTagName(), "?doc");
        assertEquals(mainTree.getMetaNodes().get(0).getAttributeValue("type"), "MyXML");
    }

    @Test
    public void testGetNodeById() {

    }

    @Test
    public void testGetNodesWithClass() {

    }

}
