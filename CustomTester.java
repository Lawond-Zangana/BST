/*
 * Name: Lawond Zangana
 * Email: lazangana@ucsd.edu
 * PID: A17708568
 * Sources used: Tutors
 */

import org.junit.Test;
import org.junit.Before;
import java.util.ArrayList;
import static org.junit.Assert.*;



public class CustomTester {
    MyBST<Integer, Integer> tree;

    @Before
    public void setup(){
        MyBST.MyBSTNode<Integer, Integer> root =
            new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two =
            new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six =
            new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one =
            new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three =
            new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five =
            new MyBST.MyBSTNode<>(5, 50, six); 
        
        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        tree.size = 6;
    }

    @Test
    public void testSuccessor() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = 
        tree.root.getRight().getLeft();
        assertSame(tree.root.getRight(),treeRoot.successor());
        MyBST.MyBSTNode<Integer, Integer> biggestNode =
         tree.root.getRight();
        assertSame(null, biggestNode.successor());
        MyBST.MyBSTNode<Integer, Integer> smallestNode =
        tree.root.getLeft().getLeft();
        assertSame(tree.root.getLeft(), smallestNode.successor());
    }

    @Test
    public void testInsert() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertThrows(NullPointerException.class, ()
            -> {
                tree.insert(null, 3);
            });
        tree.insert(7, 3);
        assertEquals(7, root.getRight().getRight().getKey().intValue());
        assertSame(root.getRight(), root.getRight().getRight().getParent());
        assertEquals("size of tree", 7, tree.size);
    }

    @Test
    public void testSearch() {
        assertNull(tree.search(50));
        assertEquals(2, tree.search(1).intValue());
        MyBST<Integer, Integer> newTree = new MyBST<>();
        MyBST.MyBSTNode<Integer, Integer> root =
        new MyBST.MyBSTNode<>(4, 1, null);
        newTree.root = root; 
        newTree.size = 1; 
        assertEquals(1, newTree.search(4).intValue());
        assertEquals(null, newTree.root.getLeft());
        assertEquals(null, newTree.root.getRight());
        assertEquals(null, newTree.root.getParent());
        assertEquals(4, newTree.root.getKey().intValue());
        assertEquals(1, newTree.root.getValue().intValue());

    }

    @Test
    public void testRemove() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertNull(tree.remove(99));
        assertEquals(50, tree.remove(5).intValue());
        assertEquals("size of tree", 5, tree.size);
        assertNull(root.getRight().getLeft());  
        assertEquals(1, tree.remove(6).intValue());
        assertEquals("size of tree", 4, tree.size);
        assertNull(root.getRight());
        assertEquals(2, tree.remove(1).intValue());
        assertNull(root.getLeft().getLeft());
        assertEquals("size of tree", 3, tree.size);
        

        
    }

    @Test
    public void testInorder() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
        new ArrayList<>();
        expectedRes.add(root.getLeft().getLeft());
        expectedRes.add(root.getLeft());
        expectedRes.add(root.getLeft().getRight());
        expectedRes.add(root);
        expectedRes.add(root.getRight().getLeft());
        expectedRes.add(root.getRight());
        assertEquals(expectedRes, tree.inorder());
        }

    @Test 
    public void testCopy() {
        MyBST<Integer, Integer> copiedTree = tree.copy();
        MyBST.MyBSTNode<Integer, Integer> rootOrig = tree.root;
        MyBST.MyBSTNode<Integer, Integer> rootCopy = copiedTree.root;
         // Check size is the same
         assertEquals(6, copiedTree.size);
        
         // Compare left/right pointers and node values
         assertEquals(rootOrig.getKey().intValue(),
                 rootCopy.getKey().intValue());
         assertEquals(rootOrig.getLeft().getKey().intValue(),
                 rootCopy.getLeft().getKey().intValue());
         assertEquals(rootOrig.getLeft().getLeft().getKey().intValue(),
                 rootCopy.getLeft().getLeft().getKey().intValue());
         assertEquals(rootOrig.getLeft().getRight().getKey().intValue(),
                 rootCopy.getLeft().getRight().getKey().intValue());
         assertEquals(rootOrig.getRight().getKey().intValue(),
                 rootCopy.getRight().getKey().intValue());
         assertEquals(rootOrig.getRight().getLeft().getKey().intValue(),
                 rootCopy.getRight().getLeft().getKey().intValue());

        MyBST<Integer, Integer> tree2 = new MyBST<>();
        MyBST<Integer, Integer> copiedTree2 = tree2.copy();
        MyBST.MyBSTNode<Integer, Integer> ogRoot = tree2.root;
        MyBST.MyBSTNode<Integer, Integer> newRoot = copiedTree2.root;
        assertEquals(0, copiedTree2.size);
        
        assertThrows(NullPointerException.class, ()
        -> {
            assertEquals(null, newRoot.getLeft()); 
        });
        assertThrows(NullPointerException.class, ()
        -> {
            assertEquals(null, ogRoot.getLeft());
        });
    
        
    }


    
}
