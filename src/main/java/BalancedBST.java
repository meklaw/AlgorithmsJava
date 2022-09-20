import java.util.Arrays;

class BSTNode {
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок	
    public int Level; // глубина узла

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    public BSTNode Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        Arrays.sort(a);
        int mid = a.length / 2;
        Root = new BSTNode(a[mid], null);
        Root.Level = 0;
        if (0 <= mid - 1)
            fromArrToBST(Root, a, 0, mid - 1);
        if (mid + 1 < a.length)
            fromArrToBST(Root, a, mid + 1, a.length - 1);
    }

    private void fromArrToBST(BSTNode parent, int[] a, int start, int end) {
        int mid = (start + end) / 2;
        BSTNode newNode = new BSTNode(a[mid], parent);
        newNode.Level = parent.Level + 1;
        if (newNode.NodeKey < parent.NodeKey)
            parent.LeftChild = newNode;
        else
            parent.RightChild = newNode;

        if (start == end)
            return;

        if (0 <= mid - 1)
            fromArrToBST(newNode, a, start, mid - 1);
        if (mid + 1 < a.length)
            fromArrToBST(newNode, a, mid + 1, end);
    }

    public boolean IsBalanced(BSTNode root_node) {
        if (root_node.LeftChild == null && root_node.RightChild == null)
            return true;

        int rightLevel = maxLevel(root_node.RightChild);
        int leftLevel = maxLevel(root_node.LeftChild);

        return Math.abs(leftLevel - rightLevel) - root_node.Level <= 1;// сбалансировано ли дерево с корнем root_node
    }

    private int maxLevel(BSTNode root_node) {
        if (root_node == null)
            return 0;
        return Math.max(root_node.Level, Math.max(maxLevel(root_node.LeftChild), maxLevel(root_node.RightChild)));
    }
}  