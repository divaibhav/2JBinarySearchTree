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
                if (comapre(element, temp) <= 0) {
                    //left subtree
                    temp = temp.getLeft();
                } else {
                    temp = temp.getRight();
                }
            }
            //check whether new node will be left child or right child of
            //parent node
            if (comapre(element, parent) <= 0) {
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
            if(comapre(searchElement, temp) == 0){
                response = true;
                break;
            }
            else if(comapre(searchElement, temp) < 0){
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
    //delete
    public void delete(E deletingElement) {
        Node<E> temp = root;
        Node<E> parent = null;
        while (temp != null) {
            if (comapre(deletingElement, temp) == 0) {
                break;
            } else {
                parent = temp;
                if (comapre(deletingElement, temp) < 0) {
                    temp = temp.getLeft();
                } else {
                    temp = temp.getRight();
                }
            }
        }
        if (temp != null) {
            //leaf
            if (isLeaf(temp)) {
                //root
                if (parent == null) {
                    root = null;
                } else {
                    if (comapre(deletingElement, parent) < 0) {
                        parent.setLeft(null);
                    } else {
                        parent.setRight(null);
                    }
                }
            }

            //single left child
            else if (hasLeftChild(temp)) {
                //root
                if (parent == null) {
                    root = root.getLeft();
                } else {
                    if (comapre(deletingElement, parent) < 0) {
                        parent.setLeft(temp.getLeft());
                    } else {
                        parent.setRight(temp.getLeft());
                    }
                }
            }

            //single right child
            else if (hasRightChild(temp)) {
                //root
                if (parent == null) {
                    root = root.getRight();
                } else {
                    if (comapre(deletingElement, parent) < 0) {
                        parent.setLeft(temp.getRight());
                    } else {
                        parent.setRight(temp.getRight());
                    }
                }
            }

            //two children
            else {
                //get successor
                Node<E> successor = getSuccessor(temp);
                //delete successor
                delete(successor.getData());
                successor.setLeft(temp.getLeft());
                successor.setRight(temp.getRight());
                //root
                if (parent == null) {
                    root = successor;
                } else {
                    if (comapre(deletingElement, parent) < 0) {
                        parent.setLeft(successor);
                    } else {
                        parent.setRight(successor);
                    }
                }

            }
            //root
        } else {
            System.out.println("cannot delete, element not present in binary search tree");
        }
    }

    private Node<E> getSuccessor(Node<E> node) {
        Node<E> temp = node.getRight();
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        return temp;
    }

    private boolean hasRightChild(Node<E> temp) {
        return temp.getRight() != null && temp.getLeft() == null;
    }

    private boolean hasLeftChild(Node<E> temp) {
        return temp.getLeft() != null && temp.getRight() == null;
    }

    private boolean isLeaf(Node<E> temp) {
        return temp.getLeft() == null && temp.getRight() == null;
    }

    private int comapre(E deletingElement, Node<E> temp) {
        return deletingElement.compareTo(temp.getData());
    }
    public int height(Node<E> node){
        //base condition
        if(node == null){
            return -1;
        }
        else{
            return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        }
    }
}
