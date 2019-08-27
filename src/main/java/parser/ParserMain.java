package parser;

public class ParserMain {

    public static final String FILE_NAME = "src/main/resources/test.xml";

    public static void main(String[] args) {
        ParseTree tree = ParseTree.parseFromFile(FILE_NAME);
        System.out.println(tree.getRoot().getChildren().get(0).getChildren().get(0).getBody());
    }

}
