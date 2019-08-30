package parser;

import java.util.List;

public class ParserMain {

    public static final String FILE_NAME = "src/main/resources/test.xml";

    public static void main(String[] args) {
        XMLTree tree = XMLTree.parseFromFile(FILE_NAME);
        System.out.println(tree.getRoot().getChildren().get(0).getTagName());//.getChildren().get(0).getBody());
        List<String> attrList = tree.getRoot().getChildren().get(0).getAllAttributeNames();
        System.out.println(tree.getRoot().getChildren().get(0).getAttributeValue(attrList.get(1)));
    }
}
