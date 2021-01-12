package cf.scenecho.algorithm.impl.binarytree.bst;

import lombok.Getter;
import lombok.NonNull;

import java.util.Optional;


@Getter
public class BinarySearchTree implements com.github.suloginscene.algorithm.helper.binarytree.bst.BinarySearchTree<Node, Integer, Object> {

    private Node root;


    @Override
    public void doSave(@NonNull Node node) {
        if (root == null) {
            root = node;
            return;
        }

        Node parent = null;
        Node dest = root;
        while (dest != null) {
            parent = dest;
            dest = node.isSmallerThan(dest) ? dest.getLeft() : dest.getRight();
        }

        if (node.isSmallerThan(parent)) parent.setLeft(node);
        else parent.setRight(node);
    }


    @Override
    public Optional<Node> findNode(@NonNull Integer key) {
        if (root == null) return Optional.empty();

        Node finder = root;
        while ((finder != null) && !finder.isIdentifiedBy(key)) {
            finder = finder.isBiggerThan(key) ? finder.getLeft() : finder.getRight();
        }
        return Optional.ofNullable(finder);
    }


    @Override
    public void doDelete(@NonNull Integer key) {
        Node node = findNode(key).orElse(null);
        if (node == null) return;

        Node parent = null;
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

        Node subs = null;
        if (node.hasChild() && !node.hasBothChildren()) {
            subs = (node.hasLeft()) ? node.getLeft() : node.getRight();
        } else if (node.hasBothChildren()) {
            subs = node.getRight();
            Node parentOfSubs = node;
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

}
