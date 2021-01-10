package cf.scenecho.algorithm.impl.binarytree;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Consumer;


/**
 * Complete binary tree
 */
@Getter
public class BinaryTree implements com.github.suloginscene.algorithm.helper.binarytree.BinaryTree<Node, Integer> {

    private Node root;


    @Override
    public void save(@NonNull Node node) {
        if (root == null) {
            root = node;
            return;
        }

        Queue<Node> parents = new LinkedList<>();
        parents.add(root);

        while (!parents.isEmpty()) {
            Node parent = parents.poll();
            if (!parent.hasLeft()) {
                parent.setLeft(node);
                return;
            }
            if (!parent.hasRight()) {
                parent.setRight(node);
                return;
            }
            parents.add(parent.getLeft());
            parents.add(parent.getRight());
        }
    }

    @Override
    public Optional<Node> find(@NonNull Node node) {
        if (root == null) return Optional.empty();

        List<Node> nodes = inOrder();
        return nodes.stream()
                .filter(n -> n.equals(node))
                .findAny();
    }

    @Override
    public void delete(@NonNull Node node) {
        Node target = find(node).orElse(null);
        if (target == null) return;

        if (target == root) {
            root = null;
        } else {
            List<Node> nodes = inOrder();
            Node parent = nodes.stream()
                    .filter(n -> (n.isParentOf(target)))
                    .findAny().orElseThrow(() -> new IllegalStateException("Target has no parent."));
            if (parent.leftIs(target)) parent.setLeft(null);
            else parent.setRight(null);
        }

        List<Node> descendants = new ArrayList<>();
        if (target.hasLeft()) {
            descendants.addAll(inOrder(target.getLeft()));
        }
        if (target.hasRight()) {
            descendants.addAll(inOrder(target.getRight()));
        }
        for (Node n : descendants) {
            save(new Node(n.getValue()));
        }
    }


    public List<Node> inOrder() {
        return inOrder(root);
    }

    private List<Node> inOrder(Node root) {
        List<Node> nodes = new ArrayList<>();
        recursiveInorder(root, nodes::add);
        return nodes;
    }

    private void recursiveInorder(Node node, Consumer<Node> consumer) {
        if (node == null) return;

        recursiveInorder(node.getLeft(), consumer);
        consumer.accept(node);
        recursiveInorder(node.getRight(), consumer);
    }

}
