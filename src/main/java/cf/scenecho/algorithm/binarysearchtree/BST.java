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
        Node<Integer, String> node = findNode(key).orElse(null);
        if (node == null) return;

        Node<Integer, String> parent = null;
        Boolean isLeftChild = null;
        if (node == root) {
            root = null;
        } else {
            parent = root;
            while (!parent.isParentOf(node)) {
                parent = parent.isBiggerThan(node) ? parent.getLeft() : parent.getRight();
            }
            isLeftChild = parent.leftIs(node);
            if (isLeftChild) parent.setLeft(null);
            else parent.setRight(null);
        }

        Node<Integer, String> subs = null;
        if (node.hasOnlyChild()) {
            subs = (node.hasLeft()) ? node.getLeft() : node.getRight();
        } else if (node.hasBothChildren()) {
            subs = node.getRight();
            Node<Integer, String> parentOfSubs = node;
            while (subs.hasLeft()) {
                parentOfSubs = subs;
                subs = subs.getLeft();
            }
            if (parentOfSubs != node) {
                parentOfSubs.setLeft(subs.getRight());
                subs.setRight(node.getRight());
            }
            subs.setLeft(node.getLeft());
        }

        if (parent == null) {
            root = subs;
        } else {
            if (isLeftChild) parent.setLeft(subs);
            else parent.setRight(subs);
        }
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
