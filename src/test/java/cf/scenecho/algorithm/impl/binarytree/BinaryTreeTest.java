package cf.scenecho.algorithm.impl.binarytree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


class BinaryTreeTest {

    int n = 16;
    BinaryTree binaryTree;


    @BeforeEach
    void setup() {
        binaryTree = new BinaryTree();
    }

    @AfterEach
    void print() {
        binaryTree.print();
    }


    @Test
    void save() {
        for (int i = 1; i <= n; i++) {
            binaryTree.save(new Node(i));
        }

        List<Node> nodes = binaryTree.inOrder();
        assertEquals(n, nodes.size());
    }

    @Test
    void find_success() {
        save();

        Integer key = 1;
        Node target = new Node(key);
        Node found = binaryTree.find(target).orElse(null);

        assertNotNull(found);
        assertEquals(key, found.getValue());
    }

    @Test
    void find_fail() {
        save();

        Integer key = n + 1;
        Node target = new Node(key);
        Node found = binaryTree.find(target).orElse(null);

        assertNull(found);
    }

    @Test
    void delete_root() {
        save();

        Integer key = 1;
        Node target = new Node(key);
        binaryTree.delete(target);

        Node found = binaryTree.find(target).orElse(null);
        assertNull(found);
        List<Node> nodes = binaryTree.inOrder();
        assertEquals(n - 1, nodes.size());
    }

    @Test
    void delete_leaf() {
        save();

        Integer key = n;
        Node target = new Node(key);
        binaryTree.delete(target);

        Node found = binaryTree.find(target).orElse(null);
        assertNull(found);
        List<Node> nodes = binaryTree.inOrder();
        assertEquals(n - 1, nodes.size());
    }

}