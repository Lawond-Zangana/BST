/*
 * Name: Lawond Zangana
 * Email: lazangana@ucsd.edu
 * PID: A17708568
 * Sources used: Tutors
 */

//imports
import java.util.ArrayList;


/*
 * This class serves as a BST. It contains methods that 
 * are similar to the methods a BST would have
 * 
 */
public class MyBST<K extends Comparable<K>, V> {
    //initialize the size and the root of the BST
    MyBSTNode<K, V> root = null;
    int size = 0;

    //size of the BST
    public int size() {
        return size;
    }

    /*
     * This method inserts a BSTNode, which contains a key and a value,
     * into the BST.
     * 
     * @param key The key of the BST Node
     * @param value The value of the BST Node
     * 
     * @return the value of the Node
     */
    public V insert(K key, V value) {
        // TODO

        //throw if true
        if(key == null){
            throw new NullPointerException();
        }

        //create a node to store input value 
        MyBSTNode<K, V> current = new MyBSTNode<>(key, value, null);

        //check if empty
        if(size == 0){
            root = current;
            size++;
            return null;
        }

       
        //create a tracker node
        MyBSTNode<K,V> tracker = root;
    
        //run until tracker becomes null
        while(tracker != null){

            //scenario if current node is less than our tracker
            if(current.key.compareTo(tracker.key) < 0){
                if(tracker.getLeft() != null){
                     tracker = tracker.getLeft(); 
                     }
                else if(tracker.getLeft() == null){
                      current.setParent(tracker);
                      tracker.setLeft(current);
                      size++;
                      break;  
                      }
                }

             //scenario if current node is greater than our tracker
             else if(current.key.compareTo(tracker.key) > 0){
                if(tracker.getRight() != null){
                    tracker = tracker.getRight(); 
                     }
                else if(tracker.getRight() == null){
                    current.setParent(tracker);
                    tracker.setRight(current);
                    size++;
                    break;
                     }
                }

            //when it's equal
            else if(current.key.compareTo(tracker.key) == 0){
                V valueTracker = tracker.value;
                tracker.value = current.value;
                return valueTracker;
            }
    }
        return null;
    }

    /*
     * This method searches for a BST Node using its key
     * 
     * @param key The key of the Node that will be searched for
     * 
     * @return the value of the Node
     */
    public V search(K key) {
        // TODO

        //create a tracker node
        MyBSTNode<K,V> tracker = root;

        //if key is null
        if(key == null){
            return null;
        }

        //loop through until key is found 
        while(tracker != null){

            //if keys match up, return the value
            if(tracker.key.compareTo(key) == 0){
                return tracker.value; 
            } else if(key.compareTo(tracker.key) < 0){
                tracker = tracker.left;
            } else {
                tracker = tracker.right;
            }

        }
    
        return null;
    }

    /*
     * This method finds and removes a Node in the BST
     * 
     * @param key The key that will be used for the search
     * 
     * @return the value of the node that was removed
     */
    public V remove(K key) {
        // TODO

        //create a tracker node
        MyBSTNode<K,V> tracker = root;

        if(key == null){
            return null; 
        }

        //loop through until key is found 
        while(tracker != null){

            //if keys match up, return the value
            if(tracker.key.compareTo(key) == 0){
                V newValue = tracker.getValue();
                //Case if Node is a leaf node
                if(tracker.getLeft() == null  && tracker.getRight() == null){
                    //If it is a leaf node and a left child
                    if(tracker.getParent() == null){
                        root = null; 
                    } else
                    if(tracker.parent.getLeft() == tracker){
                        tracker.parent.left = null; 
                        tracker.parent = null;
                    } else
                    //If it is a leaf node and a right child
                    if(tracker.parent.getRight() == tracker){
                        tracker.parent.right = null;
                        tracker.parent = null;
                    }
                    //Update size
                    size--;
                }
                //case if it has two children
                 else if(tracker.getLeft() != null && tracker.getRight() != null){
                    MyBSTNode<K,V> successorNode = tracker.successor(); 

                    K oldKey = tracker.getKey();
                    V oldValue = tracker.getValue();

                    K newKey = tracker.successor().getKey();
                    V newValues = tracker.successor().getValue();

                    tracker.value = newValues;
                    tracker.key = newKey; 

                    successorNode.key = oldKey; 
                    successorNode.value = oldValue; 

                    remove(oldKey);

                } 
                //Case if it only has one child (left child)
                else if(tracker.getLeft() != null && tracker.getRight() == null){
                    if(tracker.getParent().getRight() == tracker){
                        tracker.getParent().setRight(tracker.getLeft());
                        tracker.getLeft().setParent(tracker.getParent());
                        tracker = tracker.getLeft();
                    } else {
                        tracker.getParent().setLeft(tracker.getLeft());
                        tracker.getLeft().setParent(tracker.getParent());
                        tracker = tracker.getLeft();
                    }
                    size--;
                } 
                //Case if Node has one child (right child)
                else if(tracker.getLeft() == null && tracker.getRight() != null){
                    if(tracker.getParent().getLeft() == tracker){
                        tracker.getParent().setLeft(tracker.getRight());
                        tracker.getRight().setParent(tracker.getParent());
                        tracker = tracker.getRight();
                    } else {
                        tracker.getParent().setRight(tracker.getRight());
                        tracker.getRight().setParent(tracker.getParent());
                        tracker = tracker.getRight();
                    }
                    size--;
                }

               return newValue; 
                
            } else if(key.compareTo(tracker.key) < 0){
                tracker = tracker.left;

            } else if(key.compareTo(tracker.key) > 0){
                tracker = tracker.right;
            }

        }

        return null;
    }

    /*
     * This method puts the BST in the correct order in an ArrayList format
     * 
     * @return the ArrayList version of the BST
     */
    public ArrayList<MyBSTNode<K, V>> inorder() {
        // TODO

        //Create an empty ArrayList 
        ArrayList<MyBSTNode<K, V>> arr = new ArrayList<MyBSTNode<K, V>>();

        //create a tracker node
        MyBSTNode<K,V> tracker = root;
    
        if(size == 0){
            return arr; 
        }

        
        //while loop that finds the left most node in the BST
        while(tracker.getLeft() != null){
            tracker = tracker.getLeft();
        } 

        //while loop adds Nodes to ArrayList
        while(tracker.successor() != null){
            arr.add(tracker);
            MyBSTNode<K,V> nodeTracker = tracker.successor();
            tracker = nodeTracker; 
        }
            arr.add(tracker);
        
        //Return an empty array if BST is empty
        return arr;
    }

    /*
     * This method copies a given BST and creates another version of it.
     * 
     * @return the new BST copy
     */
    public MyBST<K, V> copy() {
        //TODO

        //Create new BST
        MyBST<K,V> copied = new MyBST<>();
        
        //use helper method to copy to new BST
        preOrder(root, copied); 

        //return new BST
        return copied;
    }
    
    /*
     * This is a helper method that serves as a pre-order function
     * 
     * @param myBSTNode Requires a starter node, preferablly the root
     * @param copied A new BST which is the frame for where we want to copy 
     * over old BST from
     */
    private void preOrder(MyBSTNode<K, V> myBSTNode, MyBST<K,V> copied) {

        //if root is null return
        if(myBSTNode == null){
            return;
        }

        //Create a tracker node
        MyBSTNode<K,V> copyNode = 
        new MyBSTNode<K,V>(myBSTNode.getKey(), myBSTNode.getValue(), null);
        
        copied.insert(copyNode.getKey(), copyNode.getValue());
        

        // Set values of copyNode to what root would be pointing at
            preOrder(myBSTNode.getLeft(), copied); 
            preOrder(myBSTNode.getRight(), copied);
    
        }

    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         *
         * @param key    the key the MyBSTNode<K,V> will
         * @param value  the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         *
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         *
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /*
         * This method finds the successor of a given node
         * 
         * @return the successor node of the node it was called on
         */
        public MyBSTNode<K, V> successor() {

            // TODO
    
            //case if there is no right child
                if(this.getRight() == null){
                    MyBSTNode<K,V> parentNode = this.parent;
                    MyBSTNode<K,V> currentNode = this;
                        while(parentNode != null && currentNode == parentNode.right){
                            currentNode = currentNode.parent;
                            parentNode = parentNode.parent;  
                        }
                        return parentNode; 
                }
           
            /*
            *if there is a right child, find the subtree's leftmost child.
            * This is its successor
            */ 
                if(this.getRight() != null){
                    MyBSTNode<K,V> curr = this.getRight();
                    //while loop that goes down the left side of the subtree
                        while(curr.getLeft() != null){
                            curr = curr.getLeft();
                        } 
                        return curr; 
                }
                return null;
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
