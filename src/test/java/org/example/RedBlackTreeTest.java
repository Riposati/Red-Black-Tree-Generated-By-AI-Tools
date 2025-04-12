package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackTreeTest {
    private RedBlackTree tree;

    @BeforeEach
    void setUp() {
        tree = new RedBlackTree();
    }

    @Test
    void testEmptyTree() {
        assertNull(tree.getRoot());
    }

    @Test
    void testInsertSingleNode() {
        tree.insert(10);
        assertEquals(10, tree.getRoot().value);
        assertFalse(tree.getRoot().isRed);
    }

    @Test
    void testInsertMultipleNodesStructureValid() {
        int[] values = {7, 3, 18, 10, 22, 8, 11, 26};
        for (int value : values) {
            tree.insert(value);
        }

        assertFalse(tree.getRoot().isRed);
        for (int value : values) {
            assertNotNull(findNode(tree.getRoot(), value));
        }

        assertTrue(validateRedBlackProperties(tree.getRoot()));
    }

    @Test
    void testRedBlackProperties() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        assertFalse(tree.getRoot().isRed);
        assertTrue(validateRedBlackProperties(tree.getRoot()));
    }

    @Test
    void testDelete() {
        int[] values = {7, 3, 18, 10, 22};
        for (int value : values) {
            tree.insert(value);
        }

        tree.delete(3);
        assertNull(findNode(tree.getRoot(), 3));
        tree.delete(18);
        assertNull(findNode(tree.getRoot(), 18));
        assertTrue(validateRedBlackProperties(tree.getRoot()));
    }

    @Test
    void testDuplicateInsert() {
        tree.insert(5);
        tree.insert(5);
        assertEquals(5, tree.getRoot().value);
        assertNull(tree.getRoot().left);
        assertNull(tree.getRoot().right);
    }

    @Test
    void testRotations() {
        tree.insert(10);
        tree.insert(15);
        tree.insert(20);
        assertEquals(15, tree.getRoot().value);
        assertEquals(10, tree.getRoot().left.value);
        assertEquals(20, tree.getRoot().right.value);

        tree = new RedBlackTree();
        tree.insert(20);
        tree.insert(15);
        tree.insert(10);
        assertEquals(15, tree.getRoot().value);
        assertEquals(10, tree.getRoot().left.value);
        assertEquals(20, tree.getRoot().right.value);
    }

    @Test
    void testColorFlipping() {
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        assertFalse(tree.getRoot().isRed);
        assertFalse(tree.getRoot().left.isRed);
        assertFalse(tree.getRoot().right.isRed);
    }

    @Test
    void testRootIsAlwaysBlack() {
        int[] values = {10, 20, 30, 15, 5, 25};
        for (int value : values) {
            tree.insert(value);
            assertFalse(tree.getRoot().isRed);
        }
    }

    @Test
    void testBlackHeightIsConsistent() {
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        for (int value : values) {
            tree.insert(value);
        }

        int blackHeight = countBlackHeight(tree.getRoot());
        assertTrue(checkBlackHeight(tree.getRoot(), blackHeight));
    }

    @Test
    void testNoConsecutiveRedNodes() {
        int[] values = {40, 20, 60, 10, 30, 50, 70};
        for (int value : values) {
            tree.insert(value);
        }

        assertTrue(noConsecutiveReds(tree.getRoot()));
    }

    @Test
    void testRemoveAll() {
        int[] values = {50, 25, 75, 10, 30, 60, 80};
        for (int value : values) {
            tree.insert(value);
        }

        for (int value : values) {
            tree.delete(value);
            if (tree.getRoot() != null)
                assertFalse(tree.getRoot().isRed);
        }

        assertNull(tree.getRoot());
    }

    @Test
    void testDeleteVariousCases() {
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        tree.delete(20);
        assertNull(findNode(tree.getRoot(), 20));

        tree.delete(60);
        assertNull(findNode(tree.getRoot(), 60));

        tree.delete(70);
        assertNull(findNode(tree.getRoot(), 70));

        assertTrue(validateRedBlackProperties(tree.getRoot()));
    }

    @Test
    void testMassiveRandomInsertions() {
        Random rand = new Random(42);
        Set<Integer> inserted = new HashSet<>();
        for (int i = 0; i < 10_000; i++) {
            int val = rand.nextInt(100_000);
            tree.insert(val);
            inserted.add(val);
        }

        for (int val : inserted) {
            assertNotNull(findNode(tree.getRoot(), val));
        }

        assertTrue(validateRedBlackProperties(tree.getRoot()));
    }

    @Test
    void testProgressiveDeletions() {
        int[] values = IntStream.rangeClosed(1, 1000).toArray();
        for (int val : values) {
            tree.insert(val);
        }

        for (int val : values) {
            tree.delete(val);
            assertNull(findNode(tree.getRoot(), val));
            if (tree.getRoot() != null) {
                assertTrue(validateRedBlackProperties(tree.getRoot()));
            }
        }

        assertNull(tree.getRoot());
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 50, 100, 250, 500})
    void testTreeWithVariousSizes(int size) {
        for (int i = 0; i < size; i++) {
            tree.insert(i);
        }
        assertTrue(validateRedBlackProperties(tree.getRoot()));
    }

    // ----------------- Helpers --------------------

    private RedBlackTree.Node findNode(RedBlackTree.Node node, int value) {
        if (node == null || node.value == value) return node;
        return (value < node.value) ? findNode(node.left, value) : findNode(node.right, value);
    }

    private boolean validateRedBlackProperties(RedBlackTree.Node root) {
        if (root == null) return true;
        if (root.isRed) return false;
        if (!noConsecutiveReds(root)) return false;
        return getBlackHeight(root) != -1;
    }

    private boolean noConsecutiveReds(RedBlackTree.Node node) {
        if (node == null) return true;
        if (node.isRed) {
            if ((node.left != null && node.left.isRed) ||
                    (node.right != null && node.right.isRed)) return false;
        }
        return noConsecutiveReds(node.left) && noConsecutiveReds(node.right);
    }

    private int getBlackHeight(RedBlackTree.Node node) {
        if (node == null) return 1;
        int left = getBlackHeight(node.left);
        int right = getBlackHeight(node.right);
        if (left == -1 || right == -1 || left != right) return -1;
        return left + (node.isRed ? 0 : 1);
    }

    private int countBlackHeight(RedBlackTree.Node node) {
        int count = 0;
        while (node != null) {
            if (!node.isRed) count++;
            node = node.left;
        }
        return count;
    }

    private boolean checkBlackHeight(RedBlackTree.Node node, int blackHeight) {
        if (node == null) return blackHeight == 0;
        if (!node.isRed) blackHeight--;
        return checkBlackHeight(node.left, blackHeight) && checkBlackHeight(node.right, blackHeight);
    }
}
