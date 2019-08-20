package parser;

import java.util.StringTokenizer;

public class Test {

    public static void main(String[] args) {
        StringTokenizer tokenizer = new StringTokenizer("<hello>Words</hello>", "<");
        String word = tokenizer.nextToken(">");
        String word2 = tokenizer.nextToken("<");
        String word3 = tokenizer.nextToken(">");
        System.out.println(word);
        System.out.println(word2);
        System.out.println(word3);
    }

}
