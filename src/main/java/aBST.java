class aBST {
    public Integer[] Tree; // массив ключей

    public aBST(int depth) {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = ((int) Math.pow(2, (depth + 1))) - 1;
        Tree = new Integer[tree_size];
        for (int i = 0; i < tree_size; i++) Tree[i] = null;
    }

    public Integer FindKeyIndex(int key) {
        // ищем в массиве индекс ключа
        for (int currentNode = 0, leftNode = 0, rightNode = 0; currentNode < Tree.length; leftNode = currentNode * 2 + 1, rightNode = currentNode * 2 + 2) {
            if (Tree[currentNode] == null)
                return -currentNode;

            if (Tree[currentNode] == key)
                return currentNode;

            if (key < Tree[currentNode] && leftNode < Tree.length) {
                currentNode = leftNode;
                continue;
            }

            if (Tree[currentNode] < key && rightNode < Tree.length) {
                currentNode = rightNode;
                continue;
            }

            return null; // не найден
        }
        return null;
    }

    public int AddKey(int key) {
        // добавляем ключ в массив
        Integer index = FindKeyIndex(key);
        if (index == null)
            return -1;

        if (index == 0 && Tree[0] == null) {
            Tree[0] = key;
        }

        if (index < 0) {
            index = -index;
            Tree[index] = key;
        }

        return index;
        // индекс добавленного/существующего ключа или -1 если не удалось
    }

}