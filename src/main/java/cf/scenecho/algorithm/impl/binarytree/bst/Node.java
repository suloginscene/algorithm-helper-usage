package cf.scenecho.algorithm.impl.binarytree.bst;

import com.github.suloginscene.algorithm.helper.binarytree.bst.KvNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(of = {"key", "value"}) @EqualsAndHashCode(of = "key")
public class Node implements KvNode<Node, Integer, Object> {

    private final Integer key;
    private final Object value;

    private Node left;
    private Node right;


    @Override
    public String toSummary() {
        return key.toString();
    }

}
