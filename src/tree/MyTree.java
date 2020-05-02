package tree;


import java.util.LinkedList;
import java.util.Stack;

/**
 * 二叉树
 */
public class MyTree {

    private TreeNode mRoot;


    public MyTree() {
    }


    /**
     * 创建一个Tree
     *
     * @param linkedList
     * @return
     */
    public TreeNode createTree(LinkedList<Integer> linkedList) {
        TreeNode node = null;
        if (linkedList == null || linkedList.isEmpty()) return null;
        Integer value = linkedList.removeFirst();
        if (value != null) {
            node = new TreeNode(value);
            node.leftChild = createTree(linkedList);
            node.rightChild = createTree(linkedList);
        }
        return node;
    }


    public TreeNode createQueryTree(TreeNode node, int value) {
        if (node == null) {
            node = new TreeNode(value);
        }

        int currentNodeValue = node.data;

        if (currentNodeValue > value) {
            node.leftChild = createQueryTree(node.leftChild, value);
        } else if (currentNodeValue < value) {
            node.rightChild = createQueryTree(node.rightChild, value);
        } else {
            return node;
        }
        return node;
    }

    public void addNote(int value) {
        mRoot = createQueryTree(mRoot, value);
    }


    //以下三种为深度优先遍历的递归方式实现

    /**
     * 前序遍历
     *
     * @param node
     */
    public void preOrder(TreeNode node) {
        if (node == null) return;
        System.out.println(node.data);
        preOrder(node.leftChild);
        preOrder(node.rightChild);

    }


    /**
     * 中序遍历
     *
     * @param node
     */
    public void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.leftChild);
        System.out.println(node.data);
        inOrder(node.rightChild);
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public void afterOrder(TreeNode node) {
        if (node == null) return;
        afterOrder(node.leftChild);
        afterOrder(node.rightChild);
        System.out.println(node.data);
    }


    public void preOrderWithoutRecursion(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (stack != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.println(node.data);
                stack.push(node);
                node = node.leftChild;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.rightChild;
            }
        }
    }


    public static void main(String[] args) {

//        LinkedList<Integer> linkedList = new LinkedList<>(
//                Arrays.asList(new Integer[]{3, 2, 9, null, null, 10, null, null, 8, null, 4})
//        );
//        MyTree myTree = new MyTree();
//        TreeNode node = myTree.createTree(linkedList);
//        System.out.println(node);

        MyTree tree = new MyTree();
        tree.addNote(6);
        tree.addNote(4);
        tree.addNote(8);
        tree.addNote(10);
        System.out.println(tree.mRoot);

        System.out.println("前序遍历");
        tree.preOrder(tree.mRoot);

        System.out.println("中序遍历");
        tree.inOrder(tree.mRoot);

        System.out.println("后序遍历");
        tree.afterOrder(tree.mRoot);

        System.out.println("非递归前序遍历");
        tree.preOrderWithoutRecursion(tree.mRoot);

    }
}
