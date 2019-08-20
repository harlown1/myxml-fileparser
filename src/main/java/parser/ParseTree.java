package parser;

import java.util.List;
import java.util.ArrayList;

public class ParseTree {
    private Node root;


    public String getNodeById() {
        return null;
    }

    public List<Node> getNodesWithClass() {
        return null;
    }

    private class Node {
        private String id;
        private List<String> classList;
        private String value;
        private List<Node> children;

        public Node() {
            id = null;
            value = null;
            children = new ArrayList<Node>();
            classList = new ArrayList<String>();
        }

        public String getId()  {
            return id;
        }

        public String getValue() {
            return value;
        }

        public List<Node> getChildren() {
            return children;
        }

        public List<String> getClassList() {
            return classList;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void addClass(String className) {
            this.classList.add(className);
        }

        public void addChild(Node child) {
            this.children.add(child);
        }

        public boolean isRootNode() {
            return children.isEmpty();
        }

    }

}
