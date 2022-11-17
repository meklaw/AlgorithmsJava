package school.tree.simple;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class SimpleTree<T> {
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
        for (SimpleTreeNode<T> nodeCheck = Root; nodeCheck != null; nodeCheck = nodesToCheck.poll()) {
            if (nodeCheck.Children != null) {
                allNodes.addAll(nodeCheck.Children);
                nodesToCheck.addAll(nodeCheck.Children);
            }
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
        DeleteNode(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count() {
        // количество всех узлов в дереве
        return GetAllNodes().size();
    }

    public int LeafCount() {
        // количество листьев в дереве
        return (int) GetAllNodes().stream()
                .filter(node -> node.Children == null || node.Children.size() == 0)
                .count();
    }

    public ArrayList<T> EvenTrees() {
        ArrayList<T> resultList = new ArrayList<>();
        Queue<SimpleTreeNode<T>> nodesToCheck = new ArrayDeque<>(Root.Children);
        for (SimpleTreeNode<T> checkNode = nodesToCheck.poll(); checkNode != null; checkNode = nodesToCheck.poll()) {
            int countElements = countDFS(checkNode);
            if (countElements % 2 == 0) {
                resultList.add(checkNode.Parent.NodeValue);
                resultList.add(checkNode.NodeValue);
            }
            if (checkNode.Children != null)
                nodesToCheck.addAll(checkNode.Children);
        }
        return resultList;
    }

    public int countDFS(SimpleTreeNode<T> node) {
        if (node.Children == null || node.Children.size() == 0)
            return 1;
        int resultSumNodes = 1;
        resultSumNodes += node.Children.stream().mapToInt(this::countDFS).sum();

        return resultSumNodes;
    }
}