import java.io.PrintWriter;


/**
 * Linked Tree Representation implementation for the {@link BSPTree} interface.
 * <p>
 * Your task is to complete the implementation of this class.
 * You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016. 
 * @author Yongli Ren, 2019.
 */
public class LinkedRepresentation<T> implements BSPTree<T> {

    private Node bspHead, bspPreorderHead, bspInorderHead, bspPostorderHead;
    private int bspLength;
    
    public LinkedRepresentation() {
        bspHead = null;
        bspLength = 0;

    } // end of LinkedRepresentation()

    @Override
    public void setRootNode(T nodeLabel) {
        if (bspHead == null) {
            Node node = new Node(nodeLabel);
            bspHead = node;
            bspLength++;
        }
        else {
            System.err.println("Root node already exists!");       
        }

    } // end of setRootNode()

    @Override
    public void splitNode(T srcLabel, T leftChild, T rightChild) {

        if (findNode(srcLabel)) {
            if (findChildren(srcLabel).equals(srcLabel)) {
                int index = 0;
                Node node = bspHead;
                
                //find srcLabel index
                for (int i = 0; i != bspLength; i++) {
                    if (node.getValue().equals(srcLabel)) {
                        index = i;
                        break;
                    }
                    node = node.getNext();
                }
                
                int childIndex = (2 * index) + 1; //relative index of left child node
                node = bspHead; //reset node location so child nodes can be added
                Node newNode; //new node for instantiating child nodes
                
                for (int i = 0; i != childIndex + 1; i++) {
                    if (i == childIndex) {
                        node.setValue(leftChild);
                        
                        //if next node has not yet been instantiated, add new node
                        if (node.getNext() == null) {
                            newNode = new Node(null);
                            node.setNext(newNode);
                            bspLength++;
                        }
                        node.getNext().setValue(rightChild);
                        break;
                    }
                    if (node.getNext() == null) {
                        newNode = new Node(null);
                        node.setNext(newNode);
                        bspLength++;
                    }
                    node = node.getNext();
                }
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
        
        Node node = bspHead;
        for (int i = 0; i != bspLength; i++) {
            if (node.getValue().equals(nodeLabel)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    } // end of findNode

    @Override
    public String findParent(T nodeLabel) {
        
        if (findNode(nodeLabel)) {
            int index = 0;
            Node node = bspHead;
            for (int i = 0; i != bspLength; i++) {
                if (node.getValue().equals(nodeLabel)) {
                    index = i;
                    break;
                }
                node = node.getNext();
            }
            if (index == 0) {
                System.err.println("First node has no parent!");  
                return null;
            }
            int parentIndex;
            if ((index % 2) == 0) {
                parentIndex = (index - 2) / 2;
            }
            else {
                parentIndex = (index - 1) / 2;
            }
            node = bspHead;
            for (int i = 0; i != parentIndex + 1; i++) {
                if (i == parentIndex) {
                    return nodeLabel + " " + node.getValue();
                }
                else {
                    node = node.getNext();
                }
            }    
        }
        else {
            System.err.println("Node does not exist!");  
        }
        return null;
    } // end of findParent

    @Override
    public String findChildren(T nodeLabel) {
       
        if (findNode(nodeLabel)) {
            int index = 0;
            Node node = bspHead;
            for (int i = 0; i != bspLength; i++) {
                if (node.getValue().equals(nodeLabel)) {
                    index = i;
                    break;
                }
                node = node.getNext();
            }
            int childIndex = (2 * index) + 1;
            node = bspHead;
            for (int i = 0; i != childIndex + 1; i++) {
                if (i != childIndex && node.getNext() == null) {
                    break;
                }
                else if (i == childIndex) {
                    return nodeLabel + " " + node.getValue() + " " + node.getNext().getValue();
                }
                node = node.getNext();
            }
            return String.valueOf(nodeLabel);
        }
        else {
            System.err.println("Node does not exist!");  
            return null;
        }    
    } // end of findParent

    @Override
    public void printInPreorder(PrintWriter writer) {
        
        assemblePreorder(bspHead.getValue());
        
        Node tempNode = bspPreorderHead;
        for (int i = 0; i != bspLength; i++) {
            writer.print(tempNode.getValue() + " ");
            tempNode = tempNode.getNext();
        }
    } // end of printInPreorder

    @Override
    public void printInInorder(PrintWriter writer) {
        
        assembleInorder(bspHead.getValue());
        
        Node tempNode = bspInorderHead;
        for (int i = 0; i != bspLength; i++) {
            writer.print(tempNode.getValue() + " ");
            tempNode = tempNode.getNext();
        }
} // end of printInInorder

    @Override
    public void printInPostorder(PrintWriter writer) {

        assemblePostorder(bspHead.getValue());
        
        Node tempNode = bspPostorderHead;
        for (int i = 0; i != bspLength; i++) {
            writer.print(tempNode.getValue() + " ");
            tempNode = tempNode.getNext();
        }
    } // end of printInPostorder
    
    public void assemblePreorder(T root) {
        
        // Recursive function to perform preorder traversal of the tree
        if (findNode(root)) {
            int index = 0;
            Node node = bspHead;
            for (int i = 0; i != bspLength; i++) {
                if (node.getValue().equals(root)) {
                    index = i;
                    break;
                }
                node = node.getNext();
            }
            Node newNode;
            if (bspPreorderHead == null) {
                newNode = new Node(root);
                bspPreorderHead = newNode;
            }
            else {
                node = bspPreorderHead;
                for (int i = 0; i != bspLength; i++) {
                    if (node.getNext() == null) {
                        newNode = new Node(root);
                        node.setNext(newNode);
                        break;
                    }
                    node = node.getNext();
                }
            }
            //check for children
            if (!findChildren(root).equals(root)) {
                int childIndex = (2 * index) + 1;
                node = bspHead;
                
                //get child node
                for (int i = 0; i != childIndex + 1; i++) {
                    if (i == childIndex) {
                        break;
                    }
                    node = node.getNext();
                }
                
                // Traverse the right subtree
                assemblePreorder(node.getValue());

                // Traverse the right subtree
                assemblePreorder(node.getNext().getValue());
            } 
        }       
    }
    public void assembleInorder(T root) {
        // Recursive function to perform preorder traversal of the tree

        if (findNode(root)) {
            
            //find root index
            int index = 0;
            Node node = bspHead;
            for (int i = 0; i != bspLength; i++) {
                if (node.getValue().equals(root)) {
                    index = i;
                    break;
                }
                node = node.getNext();
            }
            //check for children
            if (!findChildren(root).equals(root)) {
                int childIndex = (2 * index) + 1;
                node = bspHead;
                
                //get child node
                for (int i = 0; i != childIndex + 1; i++) {
                    if (i == childIndex) {
                        break;
                    }
                    node = node.getNext();
                }
                
                // Traverse the left subtree
                assembleInorder(node.getValue());
            }
            Node newNode;
            if (bspInorderHead == null) {
                newNode = new Node(root);
                bspInorderHead = newNode;
            }
            else {
                node = bspInorderHead;
                for (int i = 0; i != bspLength; i++) {
                    if (node.getNext() == null) {
                        newNode = new Node(root);
                        node.setNext(newNode);
                        break;
                    }
                    node = node.getNext();
                }
            }
            if (!findChildren(root).equals(root)) {
                int childIndex = (2 * index) + 2;
                node = bspHead;
                
                //get child node
                for (int i = 0; i != childIndex + 1; i++) {
                    if (i == childIndex) {
                        break;
                    }
                    node = node.getNext();
                }
                
                // Traverse the right subtree
                assembleInorder(node.getValue());
            }
        }
    }
    public void assemblePostorder(T root) {
        // Recursive function to perform preorder traversal of the tree

        if (findNode(root)) {
            
            //find root index
            int index = 0;
            Node node = bspHead;
            for (int i = 0; i != bspLength; i++) {
                if (node.getValue().equals(root)) {
                    index = i;
                    break;
                }
                node = node.getNext();
            }
            
            //check for children
            if (!findChildren(root).equals(root)) {
                int childIndex = (2 * index) + 1;
                node = bspHead;
                
                //get child node
                for (int i = 0; i != childIndex + 1; i++) {
                    if (i == childIndex) {
                        break;
                    }
                    node = node.getNext();
                }
                
                // Traverse the right subtree
                assemblePostorder(node.getValue());
                
                // Traverse the right subtree
                assemblePostorder(node.getNext().getValue());
            }
            Node newNode;
            if (bspPostorderHead == null) {
                newNode = new Node(root);
                bspPostorderHead = newNode;
            }
            else {
                node = bspPostorderHead;
                for (int i = 0; i != bspLength; i++) {
                    if (node.getNext() == null) {
                        newNode = new Node(root);
                        node.setNext(newNode);
                        break;
                    }
                    node = node.getNext();
                }
            }
        }
    } 
    private class Node {
        
        protected T bspVal;
        protected Node bspNext;

        public Node(T val) {
            bspVal = val;
            bspNext = null;
        }
        public T getValue() {
            return bspVal;
        }
        public Node getNext() {
            return bspNext;
        }
        public void setValue(T val) {
            bspVal = val;
        }
        public void setNext(Node next) {
            bspNext = next;
        }
    }
} // end of class LinkedRepresentation
