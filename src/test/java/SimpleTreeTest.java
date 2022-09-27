import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {
    protected SimpleTreeNode<Integer> value10, value7, value20, value15, value25, value30;
    protected SimpleTree<Integer> tree = new SimpleTree<>(null);

    @BeforeEach
    void setUp() {
        value10 = new SimpleTreeNode<>(10, null);
        value7 = new SimpleTreeNode<>(7, null);
        value20 = new SimpleTreeNode<>(20, null);
        value15 = new SimpleTreeNode<>(15, null);
        value25 = new SimpleTreeNode<>(25, null);
        value30 = new SimpleTreeNode<>(30, null);
        tree.Root = value10;
        tree.AddChild(value10, value20);
        tree.AddChild(value20, value15);
        tree.AddChild(value20, value25);
        tree.AddChild(value10, value7);
        tree.AddChild(value20, value30);
    }

    @Test
    void addChild() {

        assertNull(value10.Parent);
        assertTrue(value10.Children.contains(value20));
        assertTrue(value10.Children.contains(value7));
        assertEquals(value10.Children.size(), 2);

        assertEquals(value7.Parent, value10);
        assertNull(value7.Children);

        assertEquals(value20.Parent, value10);
        assertTrue(value20.Children.contains(value15));
        assertTrue(value20.Children.contains(value25));
        assertTrue(value20.Children.contains(value30));
        assertEquals(value20.Children.size(), 3);

        assertEquals(value15.Parent, value20);
        assertNull(value15.Children);

        assertEquals(value25.Parent, value20);
        assertNull(value25.Children);

        assertEquals(value30.Parent, value20);
        assertNull(value30.Children);
    }

    @Test
    void deleteNode() {
        tree.DeleteNode(value20);

        assertEquals(tree.Root.Children.size(), 1);
        assertTrue(tree.Root.Children.contains(value7));
        assertFalse(tree.Root.Children.contains(value20));

    }

    @Test
    void getAllNodes() {
        List<SimpleTreeNode<Integer>> allNodes = tree.GetAllNodes();
        assertEquals(allNodes.size(), 6);
        assertTrue(allNodes.contains(value7));
        assertTrue(allNodes.contains(value10));
        assertTrue(allNodes.contains(value20));
        assertTrue(allNodes.contains(value15));
        assertTrue(allNodes.contains(value25));
        assertTrue(allNodes.contains(value30));
    }

    @Test
    void findNodesByValue() {
        assertEquals(tree.FindNodesByValue(5).size(), 0);
        assertEquals(tree.FindNodesByValue(7).size(), 1);
        tree.AddChild(value20, new SimpleTreeNode<>(7, null));
        assertEquals(tree.FindNodesByValue(7).size(), 2);
    }

    @Test
    void moveNode() {
        SimpleTreeNode<Integer> newParent =  new SimpleTreeNode<>(111, null);
        tree.MoveNode(value20, newParent);
        assertEquals(tree.GetAllNodes().size(), 2);
        assertEquals(tree.Root.NodeValue, 10);
        assertEquals(tree.Root.Children.get(0).NodeValue, 7);
    }

    @Test
    void count() {
        assertEquals(6, tree.Count());
        assertEquals(0, new SimpleTree<>(null).Count());

    }

    @Test
    void leafCount() {
        assertEquals(4, tree.LeafCount());
        tree.DeleteNode(value25);
        assertEquals(3, tree.LeafCount());
        tree.DeleteNode(value20);
        assertEquals(1, tree.LeafCount());
        tree.DeleteNode(value7);
        assertEquals(1, tree.LeafCount());

        assertEquals(0, new SimpleTree<>(null).LeafCount());
    }

    @Test
    void countDFS() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);
        SimpleTreeNode<Integer> val2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> val3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> val6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> val5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> val7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> val4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> val8 = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> val9 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> val10 = new SimpleTreeNode<>(10, null);
        tree.AddChild(root, val2);
        tree.AddChild(root, val3);
        tree.AddChild(root, val6);
        tree.AddChild(val2, val5);
        tree.AddChild(val2, val7);
        tree.AddChild(val3, val4);
        tree.AddChild(val6, val8);
        tree.AddChild(val8, val9);
        tree.AddChild(val8, val10);

        assertEquals(tree.countDFS(root), 10);
        assertEquals(tree.countDFS(val2), 3);
        assertEquals(tree.countDFS(val3), 2);
        assertEquals(tree.countDFS(val6), 4);
        assertEquals(tree.countDFS(val5), 1);
        assertEquals(tree.countDFS(val7), 1);
        assertEquals(tree.countDFS(val4), 1);
        assertEquals(tree.countDFS(val8), 3);
        assertEquals(tree.countDFS(val9), 1);
        assertEquals(tree.countDFS(val10), 1);
    }

    @Test
    void EvenTrees() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);
        SimpleTreeNode<Integer> val2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> val3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> val6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> val5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> val7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> val4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> val8 = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> val9 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> val10 = new SimpleTreeNode<>(10, null);
        tree.AddChild(root, val2);
        tree.AddChild(root, val3);
        tree.AddChild(root, val6);
        tree.AddChild(val2, val5);
        tree.AddChild(val2, val7);
        tree.AddChild(val3, val4);
        tree.AddChild(val6, val8);
        tree.AddChild(val8, val9);
        tree.AddChild(val8, val10);

        ArrayList<Integer> evenTrees = tree.EvenTrees();
        assertEquals(evenTrees.get(0), 1);
        assertEquals(evenTrees.get(1), 3);
        assertEquals(evenTrees.get(2), 1);
        assertEquals(evenTrees.get(3), 6);
    }
}