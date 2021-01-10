package cf.scenecho.algorithm.impl.binarytree;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(of = "value") @ToString(of = "value")
public class Node implements com.github.suloginscene.algorithm.helper.binarytree.Node<Node, Integer> {

    private final Integer value;

    private Node left;
    private Node right;


    @Override
    public String toSummary() {
        return getValue().toString();
    }

}
