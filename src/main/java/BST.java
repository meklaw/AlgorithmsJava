import java.util.ArrayDeque;
import java.util.Queue;

class BSTNode<T> {
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок	

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T> {
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> Root; // корень дерева, или null

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

        BSTNode<T> heirNode = findHairNode(nodeToDelete);

        deleteNodeAndReplace(nodeToDelete, heirNode);

        return true;

    }

    public void deleteNodeAndReplace(BSTNode<T> nodeToDelete, BSTNode<T> heirNode) {
        BSTNode<T> parentHeirNode = heirNode.Parent;
        boolean isHeadToDelete = nodeToDelete == Root;
        if (isHeadToDelete) {
            heirNode.Parent = null;
            Root = heirNode;
        } else {
            heirNode.Parent = nodeToDelete.Parent;
        }

        if (heirNode == nodeToDelete.LeftChild) {
            return;
        }
        heirNode.LeftChild = nodeToDelete.LeftChild;
        heirNode.LeftChild.Parent = heirNode;
        if (heirNode == nodeToDelete.RightChild) {
            return;
        }
        parentHeirNode.LeftChild = null;
        if (heirNode.RightChild != null) {
            parentHeirNode.LeftChild = heirNode.RightChild;
            heirNode.RightChild.Parent = parentHeirNode;
        }
        heirNode.RightChild = nodeToDelete.RightChild;
        heirNode.RightChild.Parent = heirNode;
    }

    public BSTNode<T> findHairNode(BSTNode<T> nodeToDelete) {
        BSTNode<T> heirNode = nodeToDelete.RightChild;
        if (heirNode == null) {
            heirNode = nodeToDelete.LeftChild;
            return heirNode;
        }

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

}