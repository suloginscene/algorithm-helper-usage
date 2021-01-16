package cf.scenecho.algorithm.binarysearchtree;

import com.github.suloginscene.algorithmhelper.core.binarysearchtree.BinarySearchTree;
import com.github.suloginscene.algorithmhelper.core.binarysearchtree.Node;
import lombok.NonNull;

import java.util.Optional;
import java.util.function.Consumer;


public class BST extends BinarySearchTree<Integer, String> {

    private Node<Integer, String> root;


    @Override
    public Node<Integer, String> getRoot() {
        return root;
    }

    @Override
    public void setRoot(Node<Integer, String> node) {
        root = node;
    }


    @Override
    protected void doSave(@NonNull Node<Integer, String> node) {
        if (root == null) {
            root = node;
            return;
        }

        Node<Integer, String> parent = null;
        Node<Integer, String> dest = root;
        while (dest != null) {
            parent = dest;
            dest = node.isSmallerThan(dest) ? dest.getLeft() : dest.getRight();
        }

        if (node.isSmallerThan(parent)) parent.setLeft(node);
        else parent.setRight(node);
    }

    @Override
    public Optional<Node<Integer, String>> findNode(@NonNull Integer key) {
        if (root == null) return Optional.empty();

        Node<Integer, String> finder = root;
        while ((finder != null) && !finder.isIdentifiedBy(key)) {
            finder = finder.isBiggerThan(key) ? finder.getLeft() : finder.getRight();
        }
        return Optional.ofNullable(finder);
    }

    @Override
    protected void doDelete(@NonNull Integer key) {
        Node<Integer, String> target = findNode(key).orElse(null);
        if (target == null) return;

        Node<Integer, String> subs;
        if (!target.hasChild()) {
            subs = null;
        } else if (target.hasOnlyChild()) {
            subs = (target.hasLeft()) ? target.getLeft() : target.getRight();
        } else {
            subs = findSuccessorOf(target).orElseThrow();
            subs.setLeft(target.getLeft());
            if (target.rightIs(subs)) {
                transplant(subs, null);
            } else {
                transplant(subs, subs.getRight());
                subs.setRight(target.getRight());
            }
        }
        transplant(target, subs);
    }

    @Override
    public void inOrder(Consumer<Node<Integer, String>> consumer) {
        recursiveInorder(root, consumer);
    }

    private void recursiveInorder(Node<Integer, String> node, Consumer<Node<Integer, String>> consumer) {
        if (node == null) return;

        recursiveInorder(node.getLeft(), consumer);
        consumer.accept(node);
        recursiveInorder(node.getRight(), consumer);
    }

}
