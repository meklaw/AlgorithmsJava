import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancedBSTTest {

    BalancedBST bst;
    int[] arr1;
    int[] arr2;
    int[] arr3;
    int[] arr4;
    int[] arr5;

    @BeforeEach
    void setUp() {
        bst = new BalancedBST();
        arr1 = new int[]{50};
        arr2 = new int[]{50, 75};
        arr3 = new int[]{25, 50, 75};
        arr4 = new int[]{20, 25, 50, 75};
        arr5 = new int[]{20, 25, 37, 50, 62, 75, 84};
    }

    @Test
    void generateTree() {
        bst.GenerateTree(arr1);
        assertEquals(bst.Root.NodeKey, 50);
        assertEquals(bst.Root.Parent, null);
        assertEquals(bst.Root.LeftChild, null);
        assertEquals(bst.Root.RightChild, null);
        assertEquals(bst.Root.Level, 0);

        bst.GenerateTree(arr2);
        assertEquals(bst.Root.NodeKey, 75);
        assertEquals(bst.Root.LeftChild.NodeKey, 50);
        assertEquals(bst.Root.LeftChild.Parent.NodeKey, 75);
        assertEquals(bst.Root.RightChild, null);

        bst.GenerateTree(arr3);
        assertEquals(bst.Root.NodeKey, 50);
        assertEquals(bst.Root.LeftChild.NodeKey, 25);
        assertEquals(bst.Root.LeftChild.Parent.NodeKey, 50);
        assertEquals(bst.Root.LeftChild.LeftChild, null);
        assertEquals(bst.Root.LeftChild.RightChild, null);
        assertEquals(bst.Root.RightChild.NodeKey, 75);
        assertEquals(bst.Root.RightChild.Parent.NodeKey, 50);
        assertEquals(bst.Root.RightChild.LeftChild, null);
        assertEquals(bst.Root.RightChild.RightChild, null);

        bst.GenerateTree(arr4);
        assertEquals(bst.Root.NodeKey, 50);
        assertEquals(bst.Root.LeftChild.NodeKey, 20);
        assertEquals(bst.Root.LeftChild.Parent.NodeKey, 50);
        assertEquals(bst.Root.LeftChild.LeftChild, null);
        assertEquals(bst.Root.LeftChild.RightChild.NodeKey, 25);
        assertEquals(bst.Root.LeftChild.RightChild.Parent.NodeKey, 20);
        assertEquals(bst.Root.RightChild.NodeKey, 75);
        assertEquals(bst.Root.RightChild.Parent.NodeKey, 50);
        assertEquals(bst.Root.RightChild.LeftChild, null);
        assertEquals(bst.Root.RightChild.RightChild, null);


        bst.GenerateTree(arr5);
        assertEquals(bst.Root.NodeKey, 50);
        assertEquals(bst.Root.LeftChild.NodeKey, 25);
        assertEquals(bst.Root.LeftChild.Level, 1);
        assertEquals(bst.Root.RightChild.NodeKey, 75);
        assertEquals(bst.Root.RightChild.Level, 1);
        assertEquals(bst.Root.LeftChild.LeftChild.NodeKey, 20);
        assertEquals(bst.Root.LeftChild.LeftChild.Level, 2);
        assertEquals(bst.Root.LeftChild.RightChild.NodeKey, 37);
        assertEquals(bst.Root.LeftChild.RightChild.Level, 2);
        assertEquals(bst.Root.RightChild.LeftChild.NodeKey, 62);
        assertEquals(bst.Root.RightChild.LeftChild.Level, 2);
        assertEquals(bst.Root.RightChild.RightChild.NodeKey, 84);
        assertEquals(bst.Root.RightChild.RightChild.Level, 2);

        assertEquals(bst.Root.LeftChild.LeftChild.LeftChild, null);
        assertEquals(bst.Root.LeftChild.LeftChild.RightChild, null);
        assertEquals(bst.Root.LeftChild.RightChild.LeftChild, null);
        assertEquals(bst.Root.LeftChild.RightChild.RightChild, null);
        assertEquals(bst.Root.RightChild.LeftChild.LeftChild, null);
        assertEquals(bst.Root.RightChild.LeftChild.RightChild, null);
        assertEquals(bst.Root.RightChild.RightChild.LeftChild, null);
        assertEquals(bst.Root.RightChild.RightChild.RightChild, null);


        assertEquals(bst.Root.LeftChild.Parent.NodeKey, 50);
        assertEquals(bst.Root.RightChild.Parent.NodeKey, 50);
        assertEquals(bst.Root.LeftChild.LeftChild.Parent.NodeKey, 25);
        assertEquals(bst.Root.LeftChild.RightChild.Parent.NodeKey, 25);
        assertEquals(bst.Root.RightChild.LeftChild.Parent.NodeKey, 75);
        assertEquals(bst.Root.RightChild.RightChild.Parent.NodeKey, 75);


    }

    @Test
    void isBalanced() {
        bst.GenerateTree(arr1);
        assertTrue(bst.IsBalanced(bst.Root));
        bst.GenerateTree(arr2);
        assertTrue(bst.IsBalanced(bst.Root));
        bst.GenerateTree(arr3);
        assertTrue(bst.IsBalanced(bst.Root));
        bst.GenerateTree(arr4);
        assertTrue(bst.IsBalanced(bst.Root));
        bst.GenerateTree(arr5);
        assertTrue(bst.IsBalanced(bst.Root));

        bst.GenerateTree(arr2);
        bst.Root.LeftChild.LeftChild = new BSTNode(10, "", bst.Root.LeftChild);
        bst.Root.LeftChild.LeftChild.Level = 2;
        assertFalse(bst.IsBalanced(bst.Root));
        bst.Root.LeftChild.LeftChild.LeftChild = new BSTNode(5, "", bst.Root.LeftChild.LeftChild);
        bst.Root.LeftChild.LeftChild.LeftChild.Level = 3;
        assertFalse(bst.IsBalanced(bst.Root.LeftChild));
    }
}