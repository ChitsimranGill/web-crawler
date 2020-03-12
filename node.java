package test;
import java.awt.*;

/**
 * It creates nodes and contains the method to get a node's key, color, left node, right node and parent node.
 * It also contains the method to set color, parent, left and right node.
 * 
 * @author chitsimrangill
 *
 */
public class node 
{
	int key; 												// key of an node
	page Page; 												// page
	node parent, left, right; 								// the left, right, and parent nodes
	Color color; 											// color attribute of a node
	
	/**
	 * Constructor for creating a nil node
	 */
	node()
	{
		color = Color.BLACK;
		parent = null;
		left = null;
		right = null;
	}
	
	/**
	 *  Constructor for creating a node
	 * @param key - takes the score as key
	 * @param color - color of the node
	 * @param Page - page that needs to be made into node
	 */
	node(int key, Color color, page Page)
	{
		this.Page = Page;
		this.key = key;
		this.color = color;
		parent = null;
		left = null;
		right = null;
	}
	
	/**
	 * Gets the key of the node
	 * @return - the key (Score of the page)
	 */
	public int getKey()
	{
		return key;
	}
	
	/**
	 * Gets the page of that node
	 * @return - page of that node
	 */
	public page getPage()
	{
		return Page;
	}
	
	/**
	 * Gets the left node
	 * @return - left node
	 */
	public node getLeft()
	{
		return left;
	}
	
	/**
	 * Gets the right node
	 * @return - right node
	 */
	public node getRight()
	{
		return right;
	}
	
	/**
	 * Gets the parent node
	 * @return
	 */
	public node getParent()
	{
		return parent;
	}
	
	/**
	 * Gets the color of a node
	 * @return - the color of a node
	 */
	public String getColor()
	{
		if(color == Color.RED)
		{
			return "RED";
		}
		else
		{
			return "BLACK";
		}
	}
	
	/**
	 * Allows the user to change the left node
	 * @param n - node that needs to be assigned as the left node
	 */
	public void setLeft(node n)
	{
		this.left = n;
	}
	
	/**
	 * Allows the user to change the color of a node
	 * @param color - new color that the user wants
	 */
	public void setColor(Color c)
	{
		this.color = c;
	}
	
	/**
	 * Allows the user to change the right node
	 * @param n - node that needs to be assigned as the right node
	 */
	public void setRight(node n)
	{
		this.right = n;
	}
	
	/**
	 * Allows the user to change the parent node
	 * @param n - the new parent node
	 */
	public void setParent(node n)
	{
		this.parent = n;
	}
	
}
