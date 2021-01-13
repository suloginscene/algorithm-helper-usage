package cf.scenecho.algorithm.binarysearchtree;

import lombok.NonNull;

import java.util.Optional;
import java.util.function.Consumer;


public class BinarySearchTree extends com.github.suloginscene.algorithmhelper.core.binarysearchtree.BinarySearchTree<Integer, String> {

    @Override
    protected void doSave(@NonNull Node<Integer, String> node) {

    }

    @Override
    public Optional<Node<Integer, String>> findNode(@NonNull Integer key) {
        return Optional.empty();
    }

    @Override
    protected void doDelete(@NonNull Integer key) {

    }

    @Override
    public void inOrder(Consumer<Node<Integer, String>> consumer) {

    }

}
