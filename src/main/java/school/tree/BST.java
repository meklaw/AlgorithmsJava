package school.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;



public class BST<T> {
    public BSTNode<T> Root; // корень дерева, или null

    public BST(BSTNode<T> node) {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        // ищем в дереве узел и сопутствующую информацию по ключу
        BSTFind<T> bstFind = new BSTFind<>();
        if (Root == null) {
            return bstFind;
        }

        bstFind.NodeHasKey = false;
        bstFind.Node = Root;

        while (true) {
            if (bstFind.Node.NodeKey == key) {
                bstFind.NodeHasKey = true;
                break;
            }

            boolean isLeftChild = key < bstFind.Node.NodeKey;
            if (bstFind.Node.LeftChild == null && isLeftChild) {
                bstFind.ToLeft = true;
                break;
            }
            if (bstFind.Node.RightChild == null && !isLeftChild) {
                bstFind.ToLeft = false;
                break;
            }

            if (isLeftChild) {
                bstFind.Node = bstFind.Node.LeftChild;
                continue;
            }

            bstFind.Node = bstFind.Node.RightChild;
        }

        return bstFind;
    }


    public boolean AddKeyValue(int key, T val) {
        // добавляем ключ-значение в дерево
        BSTFind<T> findNode = FindNodeByKey(key);
        BSTNode<T> newNode = new BSTNode<>(key, val, null);
        if (findNode.NodeHasKey)
            return false; // если ключ уже есть
        if (findNode.Node == null) {
            Root = newNode;
            return true;
        }
        if (findNode.ToLeft) {
            findNode.Node.LeftChild = newNode;
            newNode.Parent = findNode.Node;
            return true;
        }

        findNode.Node.RightChild = newNode;
        newNode.Parent = findNode.Node;
        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        // ищем максимальный/минимальный ключ в поддереве
        if (FromNode == null)
            return null;
        while (true) {
            if (FindMax && FromNode.RightChild == null ||
                    !FindMax && FromNode.LeftChild == null) {
                return FromNode;
            }
            if (FindMax) {
                FromNode = FromNode.RightChild;
                continue;
            }
            FromNode = FromNode.LeftChild;
        }
    }

    public boolean DeleteNodeByKey(int key) {
        // удаляем узел по ключу
        BSTFind<T> findToDelete = FindNodeByKey(key);
        if (!findToDelete.NodeHasKey)
            return false; // если узел не найден
        BSTNode<T> nodeToDelete = findToDelete.Node;

        boolean isLeaf = nodeToDelete.LeftChild == null && nodeToDelete.RightChild == null;
        if (isLeaf) {
            deleteLeaf(nodeToDelete);
            return true;
        }

        if (nodeToDelete.RightChild == null) {
            deleteIfHasOneChild(nodeToDelete, nodeToDelete.LeftChild);
            return true;
        }

        if (nodeToDelete.LeftChild == null) {
            deleteIfHasOneChild(nodeToDelete, nodeToDelete.RightChild);
            return true;
        }

        BSTNode<T> heirNode = findHairNode(nodeToDelete);

        deleteNodeAndReplace(nodeToDelete, heirNode);

        return true;
    }

    public void deleteIfHasOneChild(BSTNode<T> nodeToDelete, BSTNode<T> heirNode) {
        heirNode.Parent = nodeToDelete.Parent;
        if (nodeToDelete == Root) {
            Root = heirNode;
            return;
        }
        if (heirNode.Parent.LeftChild == nodeToDelete) {
            heirNode.Parent.LeftChild = heirNode;
            return;
        }
        heirNode.Parent.RightChild = heirNode;

    }

    public void deleteNodeAndReplace(BSTNode<T> nodeToDelete, BSTNode<T> heirNode) {
        boolean isRoot = nodeToDelete == Root;
        if (isRoot) {
            Root = heirNode;
        }
        if (!isRoot && nodeToDelete.Parent.RightChild == nodeToDelete) {
            nodeToDelete.Parent.RightChild = heirNode;
        }
        if (!isRoot && nodeToDelete.Parent.LeftChild == nodeToDelete) {
            nodeToDelete.Parent.LeftChild = heirNode;
        }
        heirNode.LeftChild = nodeToDelete.LeftChild;
        if (heirNode.LeftChild != null) {
            heirNode.LeftChild.Parent = heirNode;
        }
        heirNode.Parent.LeftChild = heirNode.RightChild;
        if (heirNode.RightChild != null) {
            heirNode.RightChild.Parent = heirNode.Parent;
        }
        heirNode.Parent = nodeToDelete.Parent;

        if (nodeToDelete.RightChild == heirNode) {
            return;
        }
        heirNode.RightChild = nodeToDelete.RightChild;
        if (heirNode.RightChild != null) {
            heirNode.RightChild.Parent = heirNode;
        }

    }

    public BSTNode<T> findHairNode(BSTNode<T> nodeToDelete) {
        BSTNode<T> heirNode = nodeToDelete.RightChild;
        while (true) {
            if (heirNode.LeftChild == null) {
                return heirNode;
            }

            heirNode = heirNode.LeftChild;
        }
    }

    public void deleteLeaf(BSTNode<T> nodeToDelete) {
        boolean isNodeHead = Root == nodeToDelete;

        if (isNodeHead) {
            Root = null;
            return;
        }

        boolean isLeftChild = nodeToDelete.Parent.LeftChild == nodeToDelete;
        if (isLeftChild) {
            nodeToDelete.Parent.LeftChild = null;
            return;
        }

        nodeToDelete.Parent.RightChild = null;
    }

    public int Count() {
        int count = 0;
        if (Root == null)
            return count;
        Queue<BSTNode<T>> nodesToCheck = new ArrayDeque<>();
        count++;
        BSTNode<T> nodeCheck = Root;
        while (nodeCheck != null) {
            if (nodeCheck.LeftChild != null) {
                count++;
                nodesToCheck.add(nodeCheck.LeftChild);
            }
            if (nodeCheck.RightChild != null) {
                count++;
                nodesToCheck.add(nodeCheck.RightChild);
            }
            nodeCheck = nodesToCheck.poll();
        }

        return count; // количество узлов в дереве
    }

    public List<BSTNode<T>> WideAllNodes() {
        List<BSTNode<T>> allNodes = new ArrayList<>();
        if (Root == null)
            return allNodes;
        ArrayDeque<BSTNode<T>> nodesToCheck = new ArrayDeque<>();
        nodesToCheck.add(Root);
        while (!nodesToCheck.isEmpty()) {
            BSTNode<T> checkNode = nodesToCheck.pollFirst();
            allNodes.add(checkNode);
            if (checkNode.LeftChild != null)
                nodesToCheck.add(checkNode.LeftChild);
            if (checkNode.RightChild != null)
                nodesToCheck.add(checkNode.RightChild);
        }
        return allNodes;
    }

    public List<BSTNode<T>> DeepAllNodes(int i) {
        List<BSTNode<T>> allNodes = new ArrayList<>();
        if (Root == null)
            return allNodes;
        if (i == 0)
            return inOrderDeep(Root, allNodes);
        if (i == 1)
            return postOrderDeep(Root, allNodes);
        return preOrderDeep(Root, allNodes);
    }

    private List<BSTNode<T>> inOrderDeep(BSTNode<T> checkNode, List<BSTNode<T>> allNodes) {
        if (checkNode.LeftChild != null)
            inOrderDeep(checkNode.LeftChild, allNodes);

        allNodes.add(checkNode);

        if (checkNode.RightChild != null)
            inOrderDeep(checkNode.RightChild, allNodes);

        return allNodes;
    }

    private List<BSTNode<T>> postOrderDeep(BSTNode<T> checkNode, List<BSTNode<T>> allNodes) {
        if (checkNode.LeftChild != null)
            postOrderDeep(checkNode.LeftChild, allNodes);

        if (checkNode.RightChild != null)
            postOrderDeep(checkNode.RightChild, allNodes);

        allNodes.add(checkNode);
        return allNodes;
    }

    private List<BSTNode<T>> preOrderDeep(BSTNode<T> checkNode, List<BSTNode<T>> allNodes) {
        allNodes.add(checkNode);
        if (checkNode.LeftChild != null)
            preOrderDeep(checkNode.LeftChild, allNodes);

        if (checkNode.RightChild != null)
            preOrderDeep(checkNode.RightChild, allNodes);

        return allNodes;
    }
}