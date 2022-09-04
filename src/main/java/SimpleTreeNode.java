import java.util.*;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        // ваш код добавления нового дочернего узла существующему ParentNode
        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }
        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        // ваш код удаления существующего узла NodeToDelete
        NodeToDelete.Parent.Children.remove(NodeToDelete);
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        // ваш код выдачи всех узлов дерева в произвольном порядке
        List<SimpleTreeNode<T>> allNodes = new ArrayList<>();
        if (Root == null)
            return allNodes;
        Queue<SimpleTreeNode<T>> nodesToCheck = new ArrayDeque<>();
        allNodes.add(Root);
        SimpleTreeNode<T> nodeCheck = Root;
        while (nodeCheck != null) {
            if (nodeCheck.Children != null) {
                allNodes.addAll(nodeCheck.Children);
                nodesToCheck.addAll(nodeCheck.Children);
            }
            nodeCheck = nodesToCheck.poll();
        }
        return allNodes;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        // ваш код поиска узлов по значению
        List<SimpleTreeNode<T>> allNodes = new ArrayList<>();
        GetAllNodes().stream()
                .filter(node -> node.NodeValue == val)
                .forEach(allNodes::add);
        return allNodes;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        // ваш код перемещения узла вместе с его поддеревом --
        // в качестве дочернего для узла NewParent
        NewParent.Parent = OriginalNode.Parent;

        if (NewParent.Children == null)
            NewParent.Children = new ArrayList<>();
        NewParent.Children.add(OriginalNode);

        OriginalNode.Parent = NewParent;

        if (Root == OriginalNode) {
            Root = NewParent;
            return;
        }
        NewParent.Parent.Children.remove(OriginalNode);
        NewParent.Parent.Children.add(NewParent);
    }

    public int Count() {
        // количество всех узлов в дереве
        return GetAllNodes().size();
    }

    public int LeafCount() {
        // количество листьев в дереве
        return (int) GetAllNodes().stream()
                .filter(node -> node.Children == null)
                .count();
    }
}