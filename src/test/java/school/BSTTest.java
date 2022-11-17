package school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.tree.BST;
import school.tree.BSTNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    private BST<String> tree;
    private BSTNode<String> head;

    @BeforeEach
    void setUp() {
        head = new BSTNode<>(8, "восемь", null);
        tree = new BST<>(head);

        tree.AddKeyValue(12, "двенадцать");
        tree.AddKeyValue(4, "четыре");
        tree.AddKeyValue(10, "десять");
        tree.AddKeyValue(14, "четырнадцать");
    }

    @Test
    void findNodeByKey() {
        BST<String> tree2 = new BST<>(null);
        assertEquals(tree2.FindNodeByKey(10).Node, null);
        assertEquals(tree2.FindNodeByKey(10).NodeHasKey, false);

        tree2.Root = new BSTNode<>(8, "8th", null);

        assertEquals(tree2.FindNodeByKey(8).Node.NodeKey, 8);
        assertEquals(tree2.FindNodeByKey(8).NodeHasKey, true);

        tree2.Root.RightChild = new BSTNode<>(12, "12th", tree2.Root);

        assertEquals(tree2.FindNodeByKey(12).Node.NodeKey, 12);
        assertEquals(tree2.FindNodeByKey(9).Node.NodeKey, 12);
        assertEquals(tree2.FindNodeByKey(114).Node.NodeKey, 12);


        tree2.Root.LeftChild = new BSTNode<>(4, "4th", tree2.Root);

        assertEquals(tree2.FindNodeByKey(4).Node.NodeKey, 4);
        assertEquals(tree2.FindNodeByKey(6).Node.NodeKey, 4);
        assertEquals(tree2.FindNodeByKey(6).NodeHasKey, false);
        assertEquals(tree2.FindNodeByKey(6).ToLeft, false);
        assertEquals(tree2.FindNodeByKey(2).Node.NodeKey, 4);
        assertEquals(tree2.FindNodeByKey(2).NodeHasKey, false);
        assertEquals(tree2.FindNodeByKey(2).ToLeft, true);

        tree2.Root.LeftChild.LeftChild = new BSTNode<>(2, "2th", tree2.Root.LeftChild);
        assertEquals(tree2.FindNodeByKey(1).Node.NodeKey, 2);
        assertEquals(tree2.FindNodeByKey(3).Node.NodeKey, 2);
        assertEquals(tree2.FindNodeByKey(6).Node.NodeKey, 4);


        tree2.Root.RightChild.RightChild = new BSTNode<>(14, "14th", tree2.Root.RightChild);
        tree2.Root.RightChild.LeftChild = new BSTNode<>(10, "10th", tree2.Root.RightChild);

        assertEquals(tree2.FindNodeByKey(10).Node.NodeKey, 10);
        assertEquals(tree2.FindNodeByKey(10).NodeHasKey, true);
        assertEquals(tree2.FindNodeByKey(14).Node.NodeKey, 14);
        assertEquals(tree2.FindNodeByKey(10).NodeHasKey, true);

        assertEquals(tree2.FindNodeByKey(9).Node.NodeKey, 10);
        assertEquals(tree2.FindNodeByKey(9).NodeHasKey, false);
        assertEquals(tree2.FindNodeByKey(9).ToLeft, true);
        assertEquals(tree2.FindNodeByKey(11).Node.NodeKey, 10);
        assertEquals(tree2.FindNodeByKey(11).NodeHasKey, false);
        assertEquals(tree2.FindNodeByKey(11).ToLeft, false);


        assertEquals(tree2.FindNodeByKey(13).Node.NodeKey, 14);
        assertEquals(tree2.FindNodeByKey(13).NodeHasKey, false);
        assertEquals(tree2.FindNodeByKey(13).ToLeft, true);
        assertEquals(tree2.FindNodeByKey(15).Node.NodeKey, 14);
        assertEquals(tree2.FindNodeByKey(15).NodeHasKey, false);
        assertEquals(tree2.FindNodeByKey(15).ToLeft, false);

    }

    @Test
    void addKeyValue() {
        BST<String> tree = new BST<>(new BSTNode<>(8, "восемь", null));
        assertEquals(tree.Root.NodeKey, 8);

        tree.AddKeyValue(12, "двенадцать");

        assertEquals(tree.Root.RightChild.NodeKey, 12);

        tree.AddKeyValue(4, "четыре");

        assertEquals(tree.Root.LeftChild.NodeKey, 4);

        assertTrue(tree.AddKeyValue(10, "десять"));
        assertFalse(tree.AddKeyValue(10, "error"));

        assertEquals(tree.Root.RightChild.LeftChild.NodeKey, 10);

        assertTrue(tree.AddKeyValue(14, "четырнадцать"));

        assertEquals(tree.Root.NodeKey, 8);
        assertEquals(tree.Root.RightChild.NodeKey, 12);
        assertEquals(tree.Root.LeftChild.NodeKey, 4);
        assertEquals(tree.Root.RightChild.LeftChild.NodeKey, 10);
        assertEquals(tree.Root.RightChild.RightChild.NodeKey, 14);

        BST<String> tree2 = new BST<>(null);
        assertTrue(tree2.AddKeyValue(15, "15th"));
        assertEquals(tree2.Root.NodeKey, 15);
        assertNull(tree2.Root.LeftChild);
        assertNull(tree2.Root.RightChild);
        assertTrue(tree2.AddKeyValue(20, "20th"));
        assertTrue(tree2.AddKeyValue(7, "20th"));
        assertEquals(tree2.Root.LeftChild.NodeKey, 7);
        assertEquals(tree2.Root.RightChild.NodeKey, 20);

    }

    @Test
    void finMinMax() {
        assertEquals(tree.FinMinMax(tree.Root, true).NodeKey, 14);
        assertEquals(tree.FinMinMax(tree.Root, false).NodeKey, 4);
        assertNull(tree.FinMinMax(null, false));
    }

    @Test
    void deleteLeaf() {
        BST<String> stringBST = new BST<>(null);
        stringBST.Root = new BSTNode<>(8, "", null);
        int[] init = new int[]{4, 12, 10, 9, 11, 14, 13, 15};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        stringBST.deleteLeaf(stringBST.Root.RightChild.RightChild.RightChild);
        assertEquals(stringBST.Root.RightChild.RightChild.RightChild, null);
        stringBST.deleteLeaf(stringBST.Root.RightChild.RightChild.LeftChild);
        assertEquals(stringBST.Root.RightChild.RightChild.LeftChild, null);
        stringBST.deleteLeaf(stringBST.Root.RightChild.RightChild);
        assertEquals(stringBST.Root.RightChild.RightChild, null);

        assertEquals(stringBST.FindNodeByKey(14).Node.NodeKey, 12);
        assertEquals(stringBST.FindNodeByKey(14).NodeHasKey, false);
        assertEquals(stringBST.FindNodeByKey(13).Node.NodeKey, 12);
        assertEquals(stringBST.FindNodeByKey(13).NodeHasKey, false);
        assertEquals(stringBST.FindNodeByKey(15).Node.NodeKey, 12);
        assertEquals(stringBST.FindNodeByKey(15).NodeHasKey, false);

        stringBST.Root = new BSTNode<>(8, "", null);
        stringBST.deleteLeaf(stringBST.Root);
        assertEquals(stringBST.FindNodeByKey(8).Node, null);
        assertEquals(stringBST.FindNodeByKey(8).NodeHasKey, false);

    }

    @Test
    void deleteIfHasOneChild() {
        BST<String> stringBST = new BST<>(null);
        int[] init = new int[]{12, 14, 15};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        stringBST.deleteIfHasOneChild(stringBST.Root, stringBST.Root.RightChild);
        assertEquals(stringBST.Root.NodeKey, 14);
        assertEquals(stringBST.Root.RightChild.NodeKey, 15);
        assertEquals(stringBST.Root.RightChild.Parent.NodeKey, 14);
        assertEquals(stringBST.Root.Parent, null);


        stringBST = new BST<>(null);
        init = new int[]{10, 14, 16, 17};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        stringBST.deleteIfHasOneChild(stringBST.Root.RightChild, stringBST.Root.RightChild.RightChild);
        assertEquals(stringBST.Root.NodeKey, 10);
        assertEquals(stringBST.Root.RightChild.NodeKey, 16);
        assertEquals(stringBST.Root.RightChild.RightChild.NodeKey, 17);
        assertEquals(stringBST.Root.RightChild.RightChild.Parent.NodeKey, 16);
        assertEquals(stringBST.Root.RightChild.Parent.NodeKey, 10);
        assertEquals(stringBST.Root.Parent, null);

        stringBST = new BST<>(null);
        init = new int[]{20, 14, 16, 17};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        stringBST.deleteIfHasOneChild(stringBST.Root.LeftChild, stringBST.Root.LeftChild.RightChild);
        assertEquals(stringBST.Root.NodeKey, 20);
        assertEquals(stringBST.Root.LeftChild.NodeKey, 16);
        assertEquals(stringBST.Root.LeftChild.RightChild.NodeKey, 17);
        assertEquals(stringBST.Root.LeftChild.RightChild.Parent.NodeKey, 16);
        assertEquals(stringBST.Root.LeftChild.Parent.NodeKey, 20);
        assertEquals(stringBST.Root.Parent, null);


        stringBST = new BST<>(null);
        init = new int[]{12, 8, 6, 4};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        stringBST.deleteIfHasOneChild(stringBST.Root, stringBST.Root.LeftChild);
        assertEquals(stringBST.Root.NodeKey, 8);
        assertEquals(stringBST.Root.LeftChild.NodeKey, 6);
        assertEquals(stringBST.Root.LeftChild.LeftChild.NodeKey, 4);
        assertEquals(stringBST.Root.LeftChild.LeftChild.Parent.NodeKey, 6);
        assertEquals(stringBST.Root.LeftChild.Parent.NodeKey, 8);
        assertEquals(stringBST.Root.Parent, null);

    }


    @Test
    void findHairNode() {
        BST<String> stringBST = new BST<>(null);
        int[] init = new int[]{8, 4, 12, 10, 9, 11, 14, 13, 15};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        assertEquals(stringBST.findHairNode(stringBST.Root).NodeKey, 9);

        stringBST = new BST<>(null);
        init = new int[]{9, 4, 12, 10, 11, 14, 13, 15};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        assertEquals(stringBST.findHairNode(stringBST.Root).NodeKey, 10);

        stringBST = new BST<>(null);
        init = new int[]{10, 4, 12, 11, 14, 13, 15};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        assertEquals(stringBST.findHairNode(stringBST.Root).NodeKey, 11);

        stringBST = new BST<>(null);
        init = new int[]{11, 4, 12, 14, 13, 15};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        assertEquals(stringBST.findHairNode(stringBST.Root).NodeKey, 12);
    }

    @Test
    void deleteNodeByKey() {
        BST<String> stringBST = new BST<>(null);
        int[] init = new int[]{8, 4, 12, 10, 9, 11, 14, 13, 15};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        assertTrue(stringBST.DeleteNodeByKey(15));
        assertEquals(stringBST.FindNodeByKey(14).Node.RightChild, null);
        assertTrue(stringBST.DeleteNodeByKey(14));
        assertEquals(stringBST.FindNodeByKey(13).Node.RightChild, null);
        assertEquals(stringBST.FindNodeByKey(13).Node.LeftChild, null);
        assertEquals(stringBST.FindNodeByKey(13).Node.Parent.NodeKey, 12);


        stringBST = new BST<>(null);
        init = new int[]{8, 4, 12, 10, 9, 11, 14, 13, 15};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        assertTrue(stringBST.DeleteNodeByKey(12));
        assertEquals(stringBST.FindNodeByKey(13).Node.RightChild.NodeKey, 14);
        assertEquals(stringBST.FindNodeByKey(13).Node.RightChild.LeftChild, null);
        assertEquals(stringBST.FindNodeByKey(13).Node.RightChild.RightChild.NodeKey, 15);
        assertEquals(stringBST.FindNodeByKey(13).Node.RightChild.Parent.NodeKey, 13);
        assertEquals(stringBST.FindNodeByKey(13).Node.LeftChild.NodeKey, 10);
        assertEquals(stringBST.FindNodeByKey(13).Node.LeftChild.Parent.NodeKey, 13);
        assertEquals(stringBST.FindNodeByKey(13).Node.Parent.NodeKey, 8);

        assertTrue(stringBST.DeleteNodeByKey(13));
        assertEquals(stringBST.FindNodeByKey(14).Node.RightChild.NodeKey, 15);
        assertEquals(stringBST.FindNodeByKey(14).Node.LeftChild.NodeKey, 10);
        assertEquals(stringBST.FindNodeByKey(14).Node.Parent.NodeKey, 8);

        assertTrue(stringBST.DeleteNodeByKey(8));
        assertEquals(stringBST.Root.NodeKey, 9);
        assertEquals(stringBST.Root.RightChild.NodeKey, 14);
        assertEquals(stringBST.Root.RightChild.LeftChild.NodeKey, 10);
        assertEquals(stringBST.Root.RightChild.RightChild.NodeKey, 15);
        assertEquals(stringBST.Root.RightChild.LeftChild.RightChild.NodeKey, 11);
        assertEquals(stringBST.Root.RightChild.LeftChild.LeftChild, null);
        assertEquals(stringBST.Root.RightChild.LeftChild.RightChild.Parent.NodeKey, 10);
        assertEquals(stringBST.Root.RightChild.LeftChild.Parent.NodeKey, 14);
        assertEquals(stringBST.Root.RightChild.Parent.NodeKey, 9);

        assertTrue(stringBST.DeleteNodeByKey(9));
        assertEquals(stringBST.Root.NodeKey, 10);
        assertEquals(stringBST.Root.RightChild.NodeKey, 14);
        assertEquals(stringBST.Root.RightChild.LeftChild.NodeKey, 11);
        assertEquals(stringBST.Root.RightChild.LeftChild.Parent.NodeKey, 14);
        assertEquals(stringBST.Root.RightChild.Parent.NodeKey, 10);
        assertEquals(stringBST.Root.Parent, null);

        assertTrue(stringBST.DeleteNodeByKey(11));
        assertTrue(stringBST.DeleteNodeByKey(10));
        assertEquals(stringBST.Root.NodeKey, 14);
        assertEquals(stringBST.Root.LeftChild.NodeKey, 4);
        assertEquals(stringBST.Root.RightChild.NodeKey, 15);
        assertEquals(stringBST.Root.RightChild.LeftChild, null);
        assertEquals(stringBST.Root.RightChild.RightChild, null);

        assertTrue(stringBST.DeleteNodeByKey(14));

        assertEquals(stringBST.Root.NodeKey, 15);
        assertEquals(stringBST.Root.LeftChild.NodeKey, 4);
        assertEquals(stringBST.Root.LeftChild.Parent.NodeKey, 15);
        assertEquals(stringBST.Root.RightChild, null);
        assertEquals(stringBST.FindNodeByKey(16).Node.NodeKey, 15);
        assertEquals(stringBST.FindNodeByKey(16).ToLeft, false);
        assertEquals(stringBST.FindNodeByKey(16).NodeHasKey, false);


    }

    @Test
    void count() {
        BST<String> stringBST = new BST<>(new BSTNode<>(3, "fw", null));
        stringBST.DeleteNodeByKey(0);
        assertEquals(stringBST.Count(), 1);
        stringBST.DeleteNodeByKey(3);
        assertEquals(stringBST.Count(), 0);

        stringBST.Root = new BSTNode<>(8, "", null);
        stringBST.AddKeyValue(4, "");
        stringBST.DeleteNodeByKey(8);
        assertEquals(stringBST.Count(), 1);

        stringBST.Root = new BSTNode<>(8, "", null);
        int[] init = new int[]{4, 12};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        assertEquals(stringBST.Count(), 3);
    }

    @Test
    void WideAllNodes() {
        BST<String> bst = new BST<>(null);
        int[] init = new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        for (int i : init) {
            bst.AddKeyValue(i, "");
        }
        List<BSTNode<String>> bstNodes = bst.WideAllNodes();
        for (int i = 0; i < init.length; i++) {
            assertEquals(bstNodes.get(i).NodeKey, init[i]);
        }
    }

    @Test
    void DeepAllNodes() {
        BST<String> bst = new BST<>(null);
        int[] init = new int[]{8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        for (int i : init) {
            bst.AddKeyValue(i, "");
        }
        List<BSTNode<String>> bstNodes = bst.DeepAllNodes(0);
        for (int i = 1; i <= 15; i++) {
            assertEquals(bstNodes.get(i - 1).NodeKey, i);
        }

        bstNodes = bst.DeepAllNodes(1);
        int[] tests = new int[]{1, 3, 2, 5, 7, 6, 4, 9, 11, 10, 13, 15, 14, 12, 8};
        for (int i = 0; i < tests.length; i++) {
            assertEquals(bstNodes.get(i).NodeKey, tests[i]);
        }

        bstNodes = bst.DeepAllNodes(2);
        tests = new int[]{8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15};
        for (int i = 0; i < tests.length; i++) {
            assertEquals(bstNodes.get(i).NodeKey, tests[i]);
        }
    }
}