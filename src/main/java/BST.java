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
        if (nodeToDelete == Root) {
            Root = heirNode;

            if (nodeToDelete.RightChild == heirNode) {
                heirNode.Parent = nodeToDelete.Parent;
                heirNode.LeftChild = nodeToDelete.LeftChild;
                if (heirNode.LeftChild != null) {
                    heirNode.LeftChild.Parent = heirNode;
                }
                return;


            }
            if (nodeToDelete.RightChild != heirNode) {
                heirNode.Parent.LeftChild = heirNode.RightChild;
                if (heirNode.RightChild != null) {
                    heirNode.RightChild.Parent = heirNode.Parent;
                }

                heirNode.Parent = nodeToDelete.Parent;

                heirNode.LeftChild = nodeToDelete.LeftChild;
                if (heirNode.LeftChild != null) {
                    heirNode.LeftChild.Parent = heirNode;
                }
                heirNode.RightChild = nodeToDelete.RightChild;
                if (heirNode.RightChild != null) {
                    heirNode.RightChild.Parent = heirNode;
                }
            }
            return;
        }

        //не бошка

        if (nodeToDelete.RightChild == heirNode) {
            heirNode.Parent = nodeToDelete.Parent;
            heirNode.LeftChild = nodeToDelete.LeftChild;
            if (heirNode.LeftChild != null) {
                heirNode.LeftChild.Parent = heirNode;
            }
            if (nodeToDelete.Parent.RightChild == nodeToDelete) {
                nodeToDelete.Parent.RightChild = heirNode;
            }
            if (nodeToDelete.Parent.LeftChild == nodeToDelete) {
                nodeToDelete.Parent.LeftChild = heirNode;
            }
            return;


        }
        if (nodeToDelete.RightChild != heirNode) {
            heirNode.Parent.LeftChild = heirNode.RightChild;
            if (heirNode.RightChild != null) {
                heirNode.RightChild.Parent = heirNode.Parent;
            }

            heirNode.Parent = nodeToDelete.Parent;
            if (nodeToDelete.Parent.RightChild == nodeToDelete) {
                nodeToDelete.Parent.RightChild = heirNode;
            }
            if (nodeToDelete.Parent.LeftChild == nodeToDelete) {
                nodeToDelete.Parent.LeftChild = heirNode;
            }

            heirNode.LeftChild = nodeToDelete.LeftChild;
            if (heirNode.LeftChild != null) {
                heirNode.LeftChild.Parent = heirNode;
            }
            heirNode.RightChild = nodeToDelete.RightChild;
            if (heirNode.RightChild != null) {
                heirNode.RightChild.Parent = heirNode;
            }
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

}