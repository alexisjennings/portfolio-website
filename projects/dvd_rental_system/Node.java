// Alexis Jennings
/*
 * This class is a basic Node class for a doubly linked list. It has a generically typed payload G.
 * The class implements the Comparable interface, and has an overridden compareTo method to compare
 * the payloads of the Node objects.
 */

public class Node<G extends Comparable<G>> implements Comparable<G>
{
    // member variables
    private Node<G> left;
    private Node<G> right;
    private G payload;
    
    // default constructor
    public Node()
    {
	this.left = null;
	this.right = null;
	this.payload = null;
    }
    
    // overloaded constructor
    public Node(G payload)
    {
	this.left = null;
	this.right = null;
	this.payload = payload;
    }
    
    // accessors
    public Node<G> getLeft()
    {
	return this.left;
    }
    
    public Node<G> getRight()
    {
	return this.right;
    }
    
    public G getPayload()
    {
	return this.payload;
    }
    
    // mutators
    public void setLeft(Node<G> newLeft)
    {
	this.left = newLeft;
    }
    
    public void setRight(Node<G> newRight)
    {
	this.right = newRight;
    }
    
    public void setPayload(G newPayload)
    {
	this.payload = newPayload;
    }

    // overridden compareTo method
    @SuppressWarnings("unchecked")
    @Override
    public int compareTo(G o)
    {
        int val = 0;
        if (o instanceof Node)
        {
            val = payload.compareTo(((Node<G>) o).payload);
        }
        
        return val;
    }
}