// Alexis Jennings
// aej190000
/*
 * Description:
 * This class is a custom Node class, for use in a 2D grid of Nodes. The class has a generic type parameter,
 * so that it can be used with the Seat class. Each Node object has a pointer to the Node above it, below it,
 * to the right of it, and to the left of it. This class also has a generically-typed payload to store information
 * contained in the Node object.
 * 
 * The overloaded constructor sets all the pointers to null, and sets the payload to the given payload that was
 * passed into the constructor.
 */


public class Node<G>
{
    // member variables
    private Node<G> upPtr;
    private Node<G> downPtr;
    private Node<G> leftPtr;
    private Node<G> rightPtr;
    private G payload;
    
    // default constructor
    public Node()
    {
	this.upPtr = null;
	this.downPtr = null;
	this.leftPtr = null;
	this.rightPtr = null;
	this.payload = null;
    }
    
    // overloaded constructor
    public Node(G payload)
    {
	this.upPtr = null;
	this.downPtr = null;
	this.leftPtr = null;
	this.rightPtr = null;
	setPayload(payload);
    }
    
    // accessors
    public Node<G> getUp()
    {
	return this.upPtr;
    }
    
    public Node<G> getDown()
    {
	return this.downPtr;
    }
    
    public Node<G> getLeft()
    {
	return this.leftPtr;
    }
    
    public Node<G> getRight()
    {
	return this.rightPtr;
    }
    
    public G getPayload()
    {
	return this.payload;
    }
    
    // mutators
    public void setUp(Node<G> newUp)
    {
	this.upPtr = newUp;
    }
    
    public void setDown(Node<G> newDown)
    {
	this.downPtr = newDown;
    }
    
    public void setLeft(Node<G> newLeft)
    {
	this.leftPtr = newLeft;
    }
    
    public void setRight(Node<G> newRight)
    {
	this.rightPtr = newRight;
    }
    
    public void setPayload(G newPayload)
    {
	this.payload = newPayload;
    }
}
