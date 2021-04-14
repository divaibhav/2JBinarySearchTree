package implementation;

public class MyBinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;//head

    public Node<E> getRoot() {
        return root;
    }

    //insert
    public void insert(E element) {
        Node<E> node = new Node<>(element);
        //empty
        if (isEmpty()) {
            root = node;
        } else {
            Node<E> temp = root;
            Node<E> parent = null;
            while (temp != null) {
                parent = temp;
                if (element.compareTo(temp.getData()) <= 0) {
                    //left subtree
                    temp = temp.getLeft();
                } else {
                    temp = temp.getRight();
                }
            }
            //check whether new node will be left child or right child of
            //parent node
            if (element.compareTo(parent.getData()) <= 0) {
                //left child
                parent.setLeft(node);
            } else {
                //right child
                parent.setRight(node);
            }
        }
        }


    private boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    public boolean search(E searchElement){
        boolean response = false;
        Node<E> temp = root;
        while (temp != null){
            if(searchElement.compareTo(temp.getData()) == 0){
                response = true;
                break;
            }
            else if(searchElement.compareTo(temp.getData()) < 0){
                temp = temp.getLeft();
            }
            else {
                temp = temp.getRight();
            }
        }
        return response;
    }
//traversal
    public void preOrder(Node<E> node){
        if(node != null){
            //step 1
            System.out.print(node.getData() + ", ");
            //step 2 - process left subtree inb preorder
            //recursion
            preOrder(node.getLeft());
            //step 3
            preOrder(node.getRight());
        }
    }
}
