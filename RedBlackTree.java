package data_structures;

import java.util.Iterator;


public class RedBlackTree<K, V> implements RedBlackI<K, V> {
    //create a node class that handles the keys, values, isLeft, pointers and color
    Node<K, V> root;
    int size;
    class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right, parent;
        boolean isLeftChild, black;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = right = parent = null;
            black = false;
        }
    }
    //created an overloaded add method that adds the root if null or calls the overloaded method
    //also increases size
    @Override
    public void add(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        if (root == null){
            root = node;
            root.black = true;
            size++;
            return;
        }
        add(root, node); //calls recursive private add
        size++;
    }

    private void add(Node<K, V> parent, Node<K, V> newNode){
        if (((Comparable<K>)newNode.key).compareTo(parent.key) > 0){ //compare keys and go right if larger
            if (parent.right == null){ // if the right side is available, add there and check the tree
                parent.right = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = false;
                checkColor(newNode);
                return;
            }
            add(parent.right, newNode); //if the right side is not available, recurse through
        }
        if (((Comparable<K>)newNode.key).compareTo(parent.key) < 0){ // compare keys and go left if smaller
            if (parent.left == null){ // if the left side is available, add there and check the tree
                parent.left = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = true;
                checkColor(newNode);
                return;
        }
            add(parent.left, newNode); //if the left side is not available, recurse through again
        }
    }
    //this method checks the color of a node and decides to check color or correct the tree
    private void checkColor(Node<K, V> node) {
        if (node == root)
            return;
        if (!node.black && !node.parent.black) //red violation
            correctTree(node);
        if(node.parent != null)
            checkColor(node.parent);
    }
    //this method corrects the left and right side of the tree recursively
    public void correctTree(Node<K,V> node) {
        if (node.parent.isLeftChild) {
            //parent.right is the aunt
            if (node.parent.parent == null)
                return;
            if (node.parent.parent.right == null || node.parent.parent.right.black) {
                // if there's a black aunt, rotate
               rotate(node);
            } else {
                if (node.parent.parent.right != null)
                    node.parent.parent.right.black = true;
                if (node.parent.parent != root) // outlier case that gave me a null pointer error fixed it by checking the root
                    node.parent.parent.black = false;
                node.parent.black =  true;

            }
        } else {
            //parent.left is the aunt
            if (node.parent.parent.left == null || node.parent.parent.left.black) {
                //again, if there's a black aunt, rotate
                rotate(node);
            } else {
                if (node.parent.parent.left != null)
                    node.parent.parent.left.black = true;
                if (node.parent.parent != root) // outlier case that gave me a null pointer error fixed it by checking the root
                    node.parent.parent.black = false;
                node.parent.black =  true;
            }
        }
    }

    public void rotate(Node<K,V> node) {
        if (node.isLeftChild) {
            // Check if the parent is a left child
            if (node.parent.isLeftChild) {
                rightRotate(node.parent.parent);
                // after a right rotate, node becomes red, the parent becomes black, and the sibling is red
                node.black = false;
                // the parent becomes black
                node.parent.black = true;
                // Change sibling to be red
                if (node.parent.right != null)
                    node.parent.right.black = false;
                // otherwise, parent is a right child
            } else {
                rightLeftRotate(node.parent.parent);
                // after a right left rotate, the node we start at is the new parent and is black
                node.black = true;
                node.right.black = false;
                node.left.black = false;
            }
        } else {
            if (node.parent.isLeftChild) {
                leftRightRotate(node.parent.parent);
                // after a left right, the node we start at is the new parent and is black
                node.black = true;
                node.right.black = false;
                node.left.black = false;
            } else {
                leftRotate(node.parent.parent);
                // after a left rotate, node becomes red, its parent becomes black, and the sibling (node.parent.left) is red
                node.black = false;
                node.parent.black = true;
                if (node.parent.left != null)
                    node.parent.left.black = false;
            }
        }
    }
    //this method handles a right rotate
    public void rightRotate(Node<K,V> node) {
        Node<K,V> temp = node.left;
        node.left = temp.right;

        if (node.left != null) {
            node.left.parent = node;
            node.left.isLeftChild = true;
        }

        // check if node is the root
        if (node.parent == null) {
            node.black = true;
            temp.parent = null;
            temp.black = true;
            root = temp;
        } else {
            temp.parent = node.parent;
            if (node.isLeftChild) {
                temp.isLeftChild = true;
                temp.parent.left = temp;
            } else {
                temp.isLeftChild = false;
                temp.parent.right = temp;
            }
        }

        temp.right = node;
        node.isLeftChild = false;
        node.parent = temp;
    }
    //this method handles a left rotate
    public void leftRotate(Node<K,V> node) {
        Node<K,V> temp = node.right;
        node.right = temp.left;

        if (node.right != null) {
            node.right.parent = node;
            node.right.isLeftChild = false;
        }
        // check if we are the root node
        if (node.parent == null) {
            node.black = true;
            temp.parent = null;
            temp.black = true;
            root = temp;
        } else {
            temp.parent = node.parent;
            if (!node.isLeftChild) {
                temp.isLeftChild = false;
                temp.parent.right = temp;
            } else {
                temp.isLeftChild = true;
                temp.parent.left = temp;
            }
        }

        temp.left = node;
        node.isLeftChild = true;
        node.parent = temp;
    }
    //does a left rotate than a right rotate
    private void leftRightRotate(Node<K, V> node) {
        leftRotate(node.left);
        rightRotate(node);
    }
    //does a right rotate than a left one
    private void rightLeftRotate(Node<K, V> node) {
        rightRotate(node.right);
        leftRotate(node);
    }
    //overloaded method, returns contains and checks starting at the root
    @Override
    public boolean contains(K key) {
        return contains(key, root);
    }
    //this method recurses left or right through the tree and compares each each element until it's found
    private boolean contains(K key, Node<K, V> node) {
        if (node == null)
            return false;
        if (((Comparable<K>)key).compareTo(node.key) == 0) // return true if they're the same
            return true;
        if (((Comparable<K>)key).compareTo(node.key) > 0) // recurse right if not
            return contains(key, node.right);
        return contains(key, node.left); // recurse left otherwise
    }
    //overloaded method, returns contains and checks starting at the root
    @Override
    public V getValue(K key) {
        return getValue(key, root);
    }
    //this method recurses left or right through the tree and compares each each element until it's found then returns the value
    private V getValue(K key, Node<K, V> node) {
        if (node == null)
            return null;
        if (((Comparable<K>)key).compareTo(node.key) == 0) // return the value if they're the same
            return node.value;
        if (((Comparable<K>)key).compareTo(node.key) > 0) // recurse right if not
            return getValue(key, node.right);
        return getValue(key, node.left); // recurse left otherwise
    }
    //returns the size of the tree
    @Override
    public int size() {
        return size;
    }
    //returns true if the tree is empty
    @Override
    public boolean isEmpty() {
        return root == null;
    }
    //recursive method to measure the height, 0 if tree is null
    @Override
    public int height() {
        if (root == null)
            return 0;
        return height(root) - 1;
    }
    //this method calls itself until the high its calculated by adding 1
    public int height(Node<K, V> node) {
        if (node == null)
           return 0;
        int leftHeight = height(node.left) + 1; // calls method and checks left
        int rightHeight = height(node.right) + 1; // calls method and checks right
        if (leftHeight > rightHeight)
            return leftHeight;
        return rightHeight;
    }
    // returns the numer of black nodes in the tree
    public int blackNodes(Node<K, V> node){
        if (node == null) // root is always black
            return 1;
        int rightBlackNodes = blackNodes(node.right); // calls method and checks right
        int leftBlackNodes = blackNodes(node.left); // calls method and checks left
        if (rightBlackNodes != leftBlackNodes)
            correctTree(node);
        if (node.black)
            leftBlackNodes++;
        return leftBlackNodes;
    }

    // this class helps iterate through the tree
    class IteratorHelper<K> implements Iterator<K>{
        K[] arr;
        int position;
        // constructor initialized a generic array of size
        // it traverses through the tree starting at the root
        public IteratorHelper(){
            arr = (K[]) new Object[size];
            position = 0;
            traverse((Node<K, V>) root);
            position = 0;
        }
        //traverse goes through the tree and adds to the array in order
        public void traverse(Node<K, V> node){
            if (node == null)
                return;
            traverse(node.left); // check left
            arr[position++] = node.key; // add
            traverse(node.right); // check right
        }
        //returns true if there is more data to iterate through
        public boolean hasNext(){
            return position < arr.length;
        }
        //returns the next val from position
        public K next(){
            if(!hasNext())
                return null;
            return arr[position++];
        }
    }
    //create a new iterator
    @Override
    public Iterator<K> iterator() {
        return new IteratorHelper();
    }
    //overloaded print method that has similar functionality as travers
    @Override
    public void print() {
        print(root, 0);
    }
    //recurses through the tree from the null and counts the height by perios
    private void print(Node<K, V> node, int i) {
        if (node == null) // return if tree is null
            return;
        print(node.left, i+1); // check the left side
        for(int j = 0; j<i; j++) // print periods according to height variable i
            System.out.print(".");
        System.out.print(node.key + " : "); // print out the node's key
        if (node == root)                   //decides which color to print out after key
            System.out.print("black (root)");
        else if (node.black)
            System.out.print("black");
        else
            System.out.print("Red");

        System.out.println(); //add a new line
        print(node.right, i+1); // check right
    }

}