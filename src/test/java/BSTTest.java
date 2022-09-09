import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertFalse(stringBST.deleteIfLeaf(stringBST.Root));
        assertFalse(stringBST.deleteIfLeaf(stringBST.Root.RightChild.RightChild));
        assertTrue(stringBST.deleteIfLeaf(stringBST.Root.RightChild.RightChild.RightChild));
        assertFalse(stringBST.deleteIfLeaf(stringBST.Root.RightChild.RightChild));
        assertEquals(stringBST.Root.RightChild.RightChild.RightChild, null);
        assertTrue(stringBST.deleteIfLeaf(stringBST.Root.RightChild.RightChild.LeftChild));
        assertEquals(stringBST.Root.RightChild.RightChild.LeftChild, null);
        assertTrue(stringBST.deleteIfLeaf(stringBST.Root.RightChild.RightChild));
        assertEquals(stringBST.Root.RightChild.RightChild, null);

        assertEquals(stringBST.FindNodeByKey(14).Node.NodeKey, 12);
        assertEquals(stringBST.FindNodeByKey(14).NodeHasKey, false);
        assertEquals(stringBST.FindNodeByKey(13).Node.NodeKey, 12);
        assertEquals(stringBST.FindNodeByKey(13).NodeHasKey, false);
        assertEquals(stringBST.FindNodeByKey(15).Node.NodeKey, 12);
        assertEquals(stringBST.FindNodeByKey(15).NodeHasKey, false);

        stringBST.Root = new BSTNode<>(8, "", null);
        assertTrue(stringBST.deleteIfLeaf(stringBST.Root));
        assertEquals(stringBST.FindNodeByKey(8).Node, null);
        assertEquals(stringBST.FindNodeByKey(8).NodeHasKey, false);

    }
//    @Test
//    void deleteIfNoRightChild() {
//        BST<String> stringBST = new BST<>(null);
//        stringBST.Root = new BSTNode<>(8, "", null);
//        int[] init = new int[]{4, 12, 10, 9, 11, 14, 13, 15};
//        for (int i : init) {
//            stringBST.AddKeyValue(i, "");
//        }
//        assertFalse(stringBST.deleteIfNoRightChild(stringBST.Root));
//        assertFalse(stringBST.deleteIfNoRightChild(stringBST.Root.RightChild.LeftChild));
//        assertEquals(stringBST.FindNodeByKey(8).NodeHasKey, true);
//        assertEquals(stringBST.FindNodeByKey(10).NodeHasKey, true);
//        assertFalse(stringBST.deleteIfNoRightChild(stringBST.Root.RightChild.LeftChild.LeftChild));
//        assertEquals(stringBST.FindNodeByKey(9).NodeHasKey, true);
//        stringBST.deleteIfLeaf(stringBST.Root.RightChild.LeftChild.RightChild); // убрал 9
//        assertTrue(stringBST.deleteIfNoRightChild(stringBST.Root.RightChild.LeftChild));
//
//
//    }

    @Test
    void deleteNodeByKey() {
        BST<String> stringBST = new BST<>(new BSTNode<>(3, "fw", null));
        assertFalse(stringBST.DeleteNodeByKey(0));
        assertTrue(stringBST.DeleteNodeByKey(3));
        assertEquals(stringBST.Root, null);

        stringBST.Root = new BSTNode<>(8, "", null);
        stringBST.AddKeyValue(4, "");
        assertTrue(stringBST.DeleteNodeByKey(8));
        assertEquals(stringBST.Root.NodeKey, 4);
        assertEquals(stringBST.Root.LeftChild, null);
        assertEquals(stringBST.Root.RightChild, null);


        stringBST.Root = new BSTNode<>(8, "", null);
        int[] init = new int[]{4, 12};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }
        assertTrue(stringBST.DeleteNodeByKey(8));
        assertEquals(stringBST.Root.NodeKey, 12);
        assertEquals(stringBST.Root.LeftChild.NodeKey, 4);
        assertEquals(stringBST.Root.RightChild, null);

        // ДОЛЖНА 9 ВСТАВИТЬСЯ
        stringBST.Root = new BSTNode<>(8, "", null);
        init = new int[]{4, 12, 10, 9, 11, 14, 13, 15};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }

        assertTrue(stringBST.DeleteNodeByKey(8));
        assertEquals(stringBST.Root.NodeKey, 9);
        assertEquals(stringBST.Root.LeftChild.NodeKey, 4);
        assertEquals(stringBST.Root.RightChild.NodeKey, 12);
        assertEquals(stringBST.Root.RightChild.RightChild.NodeKey, 14);
        assertEquals(stringBST.Root.RightChild.LeftChild.NodeKey, 10);
        assertEquals(stringBST.Root.RightChild.LeftChild.LeftChild, null);
        assertEquals(stringBST.Root.RightChild.LeftChild.RightChild.NodeKey, 11);


        // ДОЛЖНА 10 ВСТАВИТЬСЯ, 10-12-11
        stringBST.Root = new BSTNode<>(8, "", null);
        init = new int[]{4, 12, 10, 11, 14, 13, 15};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }

        assertTrue(stringBST.DeleteNodeByKey(8));
        assertTrue(stringBST.DeleteNodeByKey(15));
        assertEquals(stringBST.Root.NodeKey, 10);
        assertEquals(stringBST.Root.LeftChild.NodeKey, 4);
        assertEquals(stringBST.Root.RightChild.NodeKey, 12);
        assertEquals(stringBST.Root.RightChild.RightChild.NodeKey, 14);
        assertEquals(stringBST.Root.RightChild.RightChild.RightChild, null);
        assertEquals(stringBST.Root.RightChild.RightChild.LeftChild.NodeKey, 13);
        assertEquals(stringBST.Root.RightChild.LeftChild.NodeKey, 11);
        assertEquals(stringBST.Root.RightChild.LeftChild.LeftChild, null);
        assertEquals(stringBST.Root.RightChild.LeftChild.RightChild, null);


        stringBST.Root = new BSTNode<>(8, "", null);
        init = new int[]{4, 12, 14, 13, 15};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }

        assertTrue(stringBST.DeleteNodeByKey(8));
        assertEquals(stringBST.Root.NodeKey, 12);
        assertEquals(stringBST.Root.RightChild.NodeKey, 14);

        stringBST.Root = new BSTNode<>(8, "", null);
        init = new int[]{4, 2, 6};
        for (int i : init) {
            stringBST.AddKeyValue(i, "");
        }

        assertTrue(stringBST.DeleteNodeByKey(8));
        assertEquals(stringBST.Root.NodeKey, 4);
        assertEquals(stringBST.Root.LeftChild.NodeKey, 2);
        assertEquals(stringBST.Root.RightChild.NodeKey, 6);
        assertEquals(stringBST.FindNodeByKey(4).NodeHasKey, true);
        assertEquals(stringBST.FindNodeByKey(4).Node.NodeKey, 4);
        assertEquals(stringBST.FindNodeByKey(2).NodeHasKey, true);
        assertEquals(stringBST.FindNodeByKey(2).Node.NodeKey, 2);
        assertEquals(stringBST.FindNodeByKey(6).NodeHasKey, true);
        assertEquals(stringBST.FindNodeByKey(6).Node.NodeKey, 6);
        assertEquals(stringBST.FindNodeByKey(8).NodeHasKey, false);
        assertEquals(stringBST.FindNodeByKey(8).Node.NodeKey, 6);
        assertEquals(stringBST.FindNodeByKey(8).ToLeft, false);

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
}