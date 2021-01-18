package cf.scenecho.algorithm.binarysearchtree;

import com.github.suloginscene.algorithmhelper.core.binarysearchtree.BinarySearchTree;
import com.github.suloginscene.algorithmhelper.core.binarysearchtree.Node;
import lombok.NonNull;

import java.util.Optional;
import java.util.function.Consumer;


public class AVL extends BinarySearchTree<Integer, String> {

    private N root;


    @Override
    protected N getRoot() {
        return root;
    }

    @Override
    protected void setRoot(Node<Integer, String> node) {
        root = (N) node;
    }


    @Override
    protected void doSave(@NonNull Node<Integer, String> superNode) {
        N node = new N(superNode.getKey(), superNode.getValue());

        if (root == null) {
            root = node;
            return;
        }

        N parent = null;
        N dest = root;
        while (dest != null) {
            parent = dest;
            dest = node.isSmallerThan(dest) ? dest.getLeft() : dest.getRight();
        }

        if (node.isSmallerThan(parent)) parent.setLeft(node);
        else parent.setRight(node);

        postSave(node);
    }

    private void postSave(@NonNull N node) {
        if (node == root) return;

        N parent = (N) findParentOf(node).orElseThrow();
        if (parent == root) return;

        N grandParent = (N) findParentOf(parent).orElseThrow();
        int left = (grandParent.hasLeft()) ? grandParent.getLeft().height() : 0;
        int right = (grandParent.hasRight()) ? grandParent.getRight().height() : 0;

        N next;
        if (Math.abs(left - right) >= 2) {
            if (grandParent.leftIs(parent) && parent.leftIs(node)) {
                rotateRight(grandParent);
                next = parent;
            } else if (grandParent.rightIs(parent) && parent.rightIs(node)) {
                rotateLeft(grandParent);
                next = parent;
            } else if (grandParent.leftIs(parent) && parent.rightIs(node)) {
                rotateLeft(parent);
                rotateRight(grandParent);
                next = node;
            } else {
                rotateRight(parent);
                rotateLeft(grandParent);
                next = node;
            }
        } else {
            next = parent;
        }
        postSave(next);
    }


    @Override
    public Optional<Node<Integer, String>> findNode(@NonNull Integer key) {
        if (root == null) return Optional.empty();

        N finder = root;
        while ((finder != null) && !finder.isIdentifiedBy(key)) {
            finder = finder.isBiggerThan(key) ? finder.getLeft() : finder.getRight();
        }
        return Optional.ofNullable(finder);
    }

    @Override
    protected void doDelete(@NonNull Integer key) {
        N target = (N) findNode(key).orElse(null);
        if (target == null) return;

        N subs;
        N fixEntry;
        if (!target.hasChild()) {
            subs = null;
            fixEntry = (N) findParentOf(target).orElse(null);
        } else if (target.hasOnlyChild()) {
            subs = (target.hasLeft()) ? target.getLeft() : target.getRight();
            fixEntry = (N) findParentOf(target).orElse(null);
        } else {
            subs = (N) findSuccessorOf(target).orElseThrow();
            subs.setLeft(target.getLeft());
            if (target.rightIs(subs)) {
                transplant(subs, null);
            } else {
                transplant(subs, subs.getRight());
                subs.setRight(target.getRight());
            }
            fixEntry = subs.getRight();
        }
        transplant(target, subs);
        postDelete(fixEntry);
    }

    private void postDelete(N node) {
        if (node == null) return;

        int left = (node.hasLeft()) ? node.getLeft().height() : 0;
        int right = (node.hasRight()) ? node.getRight().height() : 0;

        N next;
        if (Math.abs(left - right) >= 2) {
            if (left > right) {
                N child = node.getLeft();
                int ll = (child.hasLeft()) ? child.getLeft().height() : 0;
                int lr = (child.hasRight()) ? child.getRight().height() : 0;
                if (ll > lr) {
                    rotateRight(node);
                    next = child;
                } else {
                    rotateLeft(child);
                    rotateRight(node);
                    next = (N) findParentOf(node).orElse(null);
                }
            } else {
                N child = node.getRight();
                int rl = (child.hasLeft()) ? child.getLeft().height() : 0;
                int rr = (child.hasRight()) ? child.getRight().height() : 0;
                if (rr > rl) {
                    rotateLeft(node);
                    next = child;
                } else {
                    rotateRight(child);
                    rotateLeft(node);
                    next = (N) findParentOf(node).orElse(null);
                }
            }
        } else {
            next = (N) findParentOf(node).orElse(null);
        }
        postDelete(next);
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


    public static class N extends Node<Integer, String> {
        public N(@NonNull Integer key, @NonNull String value) {
            super(key, value);
        }

        @Override
        public N getLeft() {
            return (N) super.getLeft();
        }

        @Override
        public N getRight() {
            return (N) super.getRight();
        }
    }

}
