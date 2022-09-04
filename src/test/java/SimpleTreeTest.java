import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertFalse(value10.Children.contains(value20));
        assertTrue(value10.Children.contains(newParent));
        assertEquals(value10.Children.size(), 2);
        assertEquals(value20.Parent, newParent);


        SimpleTreeNode<Integer> newParent2 =  new SimpleTreeNode<>(200, null);
        tree.MoveNode(value10, newParent2);
        assertEquals(tree.Root, newParent2);
        assertTrue(newParent2.Children.contains(value10));
        assertEquals(value10.Parent, newParent2);

    }

    @Test
    void count() {
        assertEquals(6, tree.Count());
        assertEquals(0, new SimpleTree<>(null).Count());

    }

    @Test
    void leafCount() {
        assertEquals(4, tree.LeafCount());
        assertEquals(0, new SimpleTree<>(null).LeafCount());
    }
}