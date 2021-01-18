package cf.scenecho.algorithm.encode;

import com.github.suloginscene.algorithmhelper.core.encode.Encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HuffmanEncoder extends Encoder {

    @Override
    protected List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();

        char symbol = text.charAt(0);
        int length = 0;

        for (int i = 1; i < text.length(); i++) {
            char current = text.charAt(i);
            length++;

            if (symbol != current) {
                String token = String.valueOf(symbol).repeat(length);
                tokens.add(token);

                symbol = current;
                length = 0;
            }
        }
        String token = String.valueOf(symbol).repeat(++length);
        tokens.add(token);

        return tokens;
    }

    @Override
    protected Map<String, String> mapTokenCode(List<String> tokens) {
        HuffmanTree huffmanTree = new HuffmanTree(tokens);
        return huffmanTree.getMetadata();
    }

}
