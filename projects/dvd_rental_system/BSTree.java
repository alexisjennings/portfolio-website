// Alexis Jennings
// aej190000
/*
 * Description:
 * This class implements a simple binary search tree. It has a recursive search method to find a given node,
 * a recursive insertion method, and a recursive removal method.
 * Other methods include:
 * 1. inOrderSuccessor: This method finds the node with the smallest key greater than the key of the input node.
 * It is used in the recursive removal method to restructure the tree after removing a node.
 * 2. BSTPrintInorder: Prints the the nodes of the tree in increasing order.
 */

public class BSTree<G extends Comparable<G>>
{
    // member variable
    private Node<G> root;
    
    // default constructor
    public BSTree()
    {
	this.root = null;
    }
    
    // overloaded constructor
    public BSTree(Node<G> newRoot)
    {
	this.root = newRoot;
    }
    
    // accessor
    public Node<G> getRoot()
    {
	return this.root;
    }
    
    // mutator
    public void setRoot(Node<G> newRoot)
    {
	this.root = newRoot;
    }
    
    // search method that calls the recursive search method
    public Node<G> BSTSearch(G key)
    {
	return BSTSearchRecursive(this.getRoot(), key);
    }
    
    // recursive search method
    // returns the node pointer if found, otherwise returns null
    private Node<G> BSTSearchRecursive(Node<G> node, G key)
    {
	if (node != null)
	{
	    if (key.compareTo(node.getPayload()) == 0)
	    {
		return node;
	    }
	    else if (key.compareTo(node.getPayload()) < 0)
	    {
		return BSTSearchRecursive(node.getLeft(), key);
	    }
	    else
	    {
		return BSTSearchRecursive(node.getRight(), key);
	    }
	}
	return null;
    }
    
    // insert method that calls the recursive insert method
    public void BSTInsert(Node<G> node)
    {
	// if the tree is empty, set this node as the tree's root
	if (this.getRoot() == null)
	{
	    this.setRoot(node);
	}
	// if the tree is not empty, recursively find the correct placement
	else
	{
	    BSTInsertRecursive(this.getRoot(), node);
	}
    }

    // recursive insert method
    // returns nothing
    private void BSTInsertRecursive(Node<G> parent, Node<G> nodeToInsert)
    {
	if (nodeToInsert.getPayload().compareTo(parent.getPayload()) == -1)
	{
	    if (parent.getLeft() == null)
	    {
		parent.setLeft(nodeToInsert);
	    }
	    else
	    {
		BSTInsertRecursive(parent.getLeft(), nodeToInsert);
	    }
	}
	else
	{
	    if (parent.getRight() == null)
	    {
		parent.setRight(nodeToInsert);
	    }
	    else
	    {
		BSTInsertRecursive(parent.getRight(), nodeToInsert);
	    }
	}
    }
    
    // remove method that calls the recursive remove method
    // returns true if the node was successfully deleted, otherwise returns false
    public boolean BSTRemove(G key)
    {
	Node<G> result = BSTRemoveRecursive(this.root, key);
	if (result == null)
	{
	    return false;
	}
	else
	{
	    return true;
	}
    }
    
    // recursive remove method
    // returns a node pointer
    public Node<G> BSTRemoveRecursive(Node<G> curr, G key)
    {
	if (curr == null)
	{
	    return curr;
	}

        if (key.compareTo(curr.getPayload()) == -1)
        {
            curr.setLeft(BSTRemoveRecursive(curr.getLeft(), key));
        }
        else if (key.compareTo(curr.getPayload()) == 1)
        {
            curr.setRight(BSTRemoveRecursive(curr.getRight(), key));
        }
        else
        {
            if (curr.getLeft() == null)
            {
                return curr.getRight();
            }
            else if (curr.getRight() == null)
            {
        	return curr.getLeft();
            }
            
            curr.setPayload(inOrderSuccessor(curr.getRight()));
            curr.setRight(BSTRemoveRecursive(curr.getRight(), curr.getPayload()));
        }
        
        return curr;
    }

    // method to find the successor of a node (used in BSTRemoveRecursive)
    // returns the payload
    public G inOrderSuccessor(Node<G> curr)
    {
        G minimum = curr.getPayload();
        while (curr.getLeft() != null)
        {
            minimum = curr.getLeft().getPayload();
            curr = curr.getLeft();
        }
        return minimum;
    }
    
    // prints the contents of the tree in order
    public void BSTPrintInorder(Node<DVD> node)
    {
	// if the tree is empty, do nothing
	if (node == null)
	{
	    return;
	}
	BSTPrintInorder(node.getLeft()); 
	System.out.format("%36s%8d%8d", node.getPayload().getTitle(), node.getPayload().getAvailable(), node.getPayload().getRented());
	System.out.println();
	BSTPrintInorder(node.getRight());  
    }
}