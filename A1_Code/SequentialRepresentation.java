import java.io.PrintWriter;


/**
 * Sequential Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 * @author Yongli Ren, 2019.
 */
public class SequentialRepresentation<T> implements BSPTree<T> {

    /**
     * Constructs empty graph.
     */
    private T[] btArray, btArrayPreorder, btArrayInorder, btArrayPostorder;
    private int btArraySize;
    
    public SequentialRepresentation() {
        btArraySize = 10;
        btArray = (T[]) new Object[btArraySize];
    } // end of SequentialRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {

        if (btArray[0] == null) {
            btArray[0] = nodeLabel;
        }
        else {
            System.err.println("Root node already exists!");       
        }
        
    } // end of setRootNode()

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {
        if (findNode(srcLabel)) {
            int index = 0;
            for (int i = 0; i < btArray.length; i++) {
                if (btArray[i].equals(srcLabel)) {
                    index = i;
                    break;
                }
            }
            boolean childNode = btArray[(2 * index) + 1] != null ? true : false;
            
            if (!childNode) {
                if (btArray.length < (2 * index) + 3) {
                    btArraySize = 2 * btArraySize;
                    T[] btArrayNew = (T[]) new Object[btArraySize];
                    for (int i = 0; i < btArray.length; i++) {
                        btArrayNew[i] = btArray[i];
                    }
                    btArray = btArrayNew;
                }
                btArray[(2 * index) + 1] = leftChild;
                btArray[(2 * index) + 2] = rightChild;
            }
            else {
                System.err.println("Node already as children!");  
            }
        } 
        else {
            System.err.println("Node does not exist!!");  
        }  
    } // end of splitNode

    @Override
    public boolean findNode(T nodeLabel) {

        //iterates until the label given matches the one of the node
        for (int i = 0; i < btArray.length; i++) {
            if (btArray[i].equals(nodeLabel)) {
                return true;
            }
        }
        return false;
    } // end of findNode

    @Override
    public String findParent(T nodeLabel) {

        if (findNode(nodeLabel)) {
            int index = 0;
            for (int i = 0; i < btArray.length; i++) {
                if (btArray[i].equals(nodeLabel)) {
                    index = i;
                    break;
                }
            }
            if (index == 0){
                System.err.println("First node has no parent!");  
                return null;
            }
            //if index is even then...
            if ((index % 2) == 0) {
                return nodeLabel + " " + btArray[(index - 2) / 2];
            }
            else {
                return nodeLabel + " " + btArray[(index - 1) / 2];
            }
        }
        else {
            System.err.println("Node does not exist!");  
            return null;
        }
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {

        if (findNode(nodeLabel)) {
            int index = 0;
            for (int i = 0; i < btArray.length; i++) {
                if (btArray[i].equals(nodeLabel)) {
                    index = i;
                    break;
                }
            }
            
            boolean children = false;
            
            //if array is large enough for this position to have children then...
            if (!(btArray.length < (2 * index) + 2)) {
                children = btArray[(2 * index) + 1] != null ? true : false;
            }
            if (children) {
                return nodeLabel + " " + btArray[(2 * index) + 1] + " " + btArray[(2 * index) + 2];
            }
            else {
                return String.valueOf(nodeLabel);
            }
        }
        else {
            System.err.println("Node does not exist!");  
            return null;
        }
    } // end of findParent

    @Override
    public void printInPreorder(PrintWriter writer) {

        btArrayPreorder = (T[]) new Object[btArraySize];
        assemblePreorder(btArray[0]);
        
        for (int i = 0; i < btArrayPreorder.length; i++) {
            if (btArrayPreorder[i] != null) {
                writer.print(btArrayPreorder[i] + " ");
            }
        } 
    } // end of printInPreorder

    @Override
    public void printInInorder(PrintWriter writer) {
        
        btArrayInorder = (T[]) new Object[btArraySize];
        assembleInorder(btArray[0]);
        
        for (int i = 0; i < btArrayInorder.length; i++) {
            if (btArrayInorder[i] != null) {
                writer.print(btArrayInorder[i] + " ");
            }
        } 
    } // end of printInInorder

    @Override
    public void printInPostorder(PrintWriter writer) {
         
        btArrayPostorder = (T[]) new Object[btArraySize];
        assemblePostorder(btArray[0]);
        
        for (int i = 0; i < btArrayPostorder.length; i++) {
            if (btArrayPostorder[i] != null) {
                writer.print(btArrayPostorder[i] + " ");
            }
        }
    } // end of printInPostorder
    
    public void assemblePreorder(T root) {
        // Recursive function to perform preorder traversal of the tree
        
        //find root index
        int index = 0;
        for (int i = 0; i != btArray.length; i++) {
            if (btArray[i].equals(root)) {
                index = i;
                break;
            }
        }
        // Add new root node to the preorder tree in next empty spot
        for (int i = 0; i < btArrayPreorder.length; i++) {
            if (btArrayPreorder[i] == null) {
                btArrayPreorder[i] = root;
                break;
            }
        }
        //check for children
        if (!findChildren(root).equals(root)) {
            // Traverse the left subtree
            assemblePreorder(btArray[(2 * index) + 1]);

            // Traverse the right subtree
            assemblePreorder(btArray[(2 * index) + 2]);
        }
    }
    public void assembleInorder(T root) {
        // Recursive function to perform inorder traversal of the tree
        
        //find root index
        int index = 0;
        for (int i = 0; i != btArray.length; i++) {
            if (btArray[i].equals(root)) {
                index = i;
                break;
            }
        }
        //check for children        
        if (!findChildren(root).equals(root)) {
            // Traverse the left subtree
            assembleInorder(btArray[(2 * index) + 1]);
        }
        // Add new root node to the inorder tree in next empty spot
        for (int i = 0; i < btArrayInorder.length; i++) {
            if (btArrayInorder[i] == null) {
                btArrayInorder[i] = root;
                break;
            }
        }
        if (!findChildren(root).equals(root)) {
            // Traverse the right subtree
            assembleInorder(btArray[(2 * index) + 2]);
        }
    }
    public void assemblePostorder(T root) {
        // Recursive function to perform postorder traversal of the tree
        
        //find root index
        int index = 0;
        for (int i = 0; i != btArray.length; i++) {
            if (btArray[i].equals(root)) {
                index = i;
                break;
            }
        }
        //check for children
        boolean children = findChildren(root) != null ? true : false;
        
        if (!findChildren(root).equals(root)) {
            // Traverse the left subtree
            assemblePostorder(btArray[(2 * index) + 1]);
            // Traverse the right subtree
            assemblePostorder(btArray[(2 * index) + 2]);
        }
        // Add new root node to the postorder tree in next empty spot
        for (int i = 0; i < btArrayPostorder.length; i++) {
            if (btArrayPostorder[i] == null) {
                btArrayPostorder[i] = root;
                break;
            }
        }
    }
} // end of class SequentialRepresentation