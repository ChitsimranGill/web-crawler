package test;

import java.awt.Color;

/**
 * Contain the methods to create a tree and all the other methods related to RB tree like insert, delete and search.
 * @author chitsimrangill
 *
 */
public class tree 
{
	private node nil = new node(); // nil node
	private node root = nil; // root nod of the tree
	private final Color RED = Color.RED; // red color of node
	private final Color BLACK = Color.BLACK; // black color of node
	int rank = 0;
	
	/**
	 * Tree constructor
	 */
	public tree()
	{
		root.left = nil;
		root.right = nil;
		root.parent = nil;
	}
	
	/**
	 *  Inserts a node into a tree and calls RBInsertFixUp 
	 * @param T - tree in which the node needs to added
	 * @param z - node that needs to be added
	 */
	public void RBInsert(tree T, node z)
	{
		node y = T.nil; // trailing pointer and parent of x
		node x = T.root;
		
		while(x!=T.nil)
		{
			y=x;
			if(z.key<x.key)
			{
				x = x.left;
			}
			else
			{
				x = x.right;
			}
		}
		z.parent = y;
		
		if (y==T.nil)
		{
			T.root = z; // tree was empty
		}
		else if(z.key<y.key)
		{
			y.left = z;
		}
		else
		{
			y.right = z;
		}
		z.left = T.nil;
		z.right = T.nil;
		z.color = RED;
		RBInsertFixUp(T,z);
		
	}
	
	/**
	 * Performs fix up on  tree after a node is inserted and restores the properties
	 * @param T - tree on which the fix up needs to be done
	 * @param z - node around which fix up needs to be done
	 */
	public void RBInsertFixUp(tree T, node z)
	{
		node y;
		while(z.parent.color == RED)
		{
			if (z.parent == z.parent.parent.left)
			{
				y = z.parent.parent.right; // y now is z's uncle
				if(y.color == RED) // CASE 1
				{
					z.parent.color = BLACK; 
					y.color = BLACK;
					z.parent.parent.color = RED;
					z = z.parent.parent;
				}
			    else if(z==z.parent.right) //y.color != RED
			    {
			    		z = z.parent; // CASE 2
			    		leftRotate(T,z);
			    }
			    else // CASE 3
			    {
			    		z.parent.color = BLACK;
			    		z.parent.parent.color = RED;
			    		rightRotate(T,z.parent.parent);
			    }
			}
			else
			{
				if (z.parent == z.parent.parent.right)
				{
					y = z.parent.parent.left; // y is now z's uncle
					if(y.color == RED) // CASE 1
					{
						z.parent.color = BLACK;
						y.color = BLACK;
						z.parent.parent.color = RED;
						z = z.parent.parent;
					}
					else if(z==z.parent.left) // y.color != RED
					{
						z = z.parent; // CASE 2
						rightRotate(T,z);
					}
					else // CASE 3
					{
						z.parent.color = BLACK;
						z.parent.parent.color = RED;
						leftRotate(T,z.parent.parent);
					}
				}	
			}
		}
		T.root.color = BLACK;
	}
	
	/**
	 *  Performs right rotate on a RB tree
	 * @param T - Tree on which the rotate needs to be done
	 * @param y - node around which the rotate needs to be done
	 */
	public void rightRotate(tree T, node x)
	{
		node y = x.left; //Set y
		x.left = y.right; // Turn y's right subtree into x's left subtree
		
		if(y.right != T.nil)
		{
			y.right.parent = x;
		}
		y.parent = x.parent; // Link 's parent to y
		
		if(x.parent == T.nil)
		{
			T.root = y;
		}
		else if(x==x.parent.right)
		{
			x.parent.right =y;
		}
		else
		{
			x.parent.left = y;
		}
		
		y.right = x; // Put  on y's left
		x.parent = y;
	}
	
	/**
	 *  Performs left rotate on a RB tree
	 * @param T - Tree on which the rotate needs to be done
	 * @param x - node around which the rotate needs to be done
	 */
	public void leftRotate(tree T, node x)
	{
		node y = x.right; // Set y node
		x.right = y.left; // Turn y's left subtree into 's right subtree
		
		if(y.left != T.nil)
		{
			y.left.parent = x;
		}
		y.parent = x.parent; // Link x's parent to y
		
		if(x.parent == T.nil)
		{
			T.root = y;
		}
		else if(x==x.parent.left)
		{
			x.parent.left = y;
		}
		else
		{
			x.parent.right = y;
		}
		y.left = x; // Put x on y's left
		x.parent = y;
	}
	
	/**
	 * Inserts a page into the url
	 * @param Page - takes the page that needs to be inserted as input
	 */
	public void insertPage(page Page)
	{
		node tempNode = new node(Page.getScore(),BLACK,Page); // creates a temp node
		RBInsert(this,tempNode);
		rank++;
	}
	
	/**
	 *  Performs transplant on an RB tree
	 * @param T - tree on which the transplant needs to be done
	 * @param u - node on which the transplant needs to be done
	 * @param v - node which is transplanted
	 */
	public void RBTransplant(tree T,node u, node v)
	{
		if(u.parent == T.nil) // CASE: if u is root of T
		{
			T.root = v;
		}
		else if (u == u.parent.left) // CASE: if u is a left child
		{
			u.parent.left = v;
		}
		else // CASE: if u is a right child  
		{
			u.parent.right = v;
		}
		v.parent = u.parent;
	}
	
	/**
	 * Gets the minimum node of a tree
	 * @param n - node from where the minimum needs to find
	 * @return - the minimum node
	 */
	public node RBtreeMinimum(node n)
	{
		while (n.left != nil)
		{
			n = n.left;
		}
		return n;
	}
	
	/**
	 * Deletes a node form an RB tree
	 * @param T - tree whose node needs to be deleted
	 * @param z - node that needs to be deleted
	 */
	public void RBdelete(tree T, node z)
	{
		node y = z;
		node x;
		Color YOriginalColor = y.color;
		
		if (z.left == T.nil) // CASE: if z has no left child
		{
			x = z.right;
			RBTransplant(T,z,z.right);
		}
		else if (z.right == T.nil) // CASE: if z has a left child but no right child
		{
			x = z.left;
			RBTransplant(T,z,z.left);
		}
		else // CASE: if z has tow children
		{
			y = RBtreeMinimum(z.right); // find z's successor
			YOriginalColor = y.color;
			x = y.right;
			
			if (y.parent == z) 
			{
				x.parent = y;
			}
			else
			{
				RBTransplant(T,y,y.right);
				y.right = z.right;
				y.right.parent =y;
			}
			RBTransplant(T,z,y);// CASE: if y is z's right child
			y.left = z.left; //replace y's left child by z's left child
			y.left.parent = y;
			y.color = z.color;
		}
		
		if (YOriginalColor == BLACK)
		{
			RBDeleteFixUp(T,x);
		}
		
	}
	
	/**
	 * Performs fix up on a tree after a node is deleted and restores the properties
	 * @param T - tree on which fix up needs to be done
	 * @param x - node around which fix up needs to be done
	 */
	public void RBDeleteFixUp(tree T, node x)
	{
		node w;
		
		while(x != T.root & x.color == BLACK)
		{
			if (x==x.parent.left)
			{
				w = x.parent.right;
				
				if (w.color == RED) // CASE 1
				{
					w.color = BLACK;
					x.parent.color = RED;
					leftRotate(T,x.parent);
					w = x.parent.right;
				}
				
				if (w.left.color == BLACK & w.right.color == BLACK) // x is still x.p.left
				{
					w.color = RED; // CASE 2
					x = x.parent;
				}
				else if(w.right.color == BLACK) // CASE 3
				{
					w.left.color = BLACK;
					w.color = RED;
					rightRotate(T,w);
					w = x.parent.right;
				}
				else
				{
					w.color = x.parent.color; // CASE 4
					x.parent.color = BLACK;
					w.right.color = BLACK;
					leftRotate(T,x.parent);
					x = T.root;
				}
			}
			else
			{
				w = x.parent.left;
				
				if(w.color == RED) // CASE 1
				{
					w.color = BLACK;
					x.parent.color = RED;
					rightRotate(T,x.parent);
					w = x.parent.left;
				}
				
				if(w.right.color == BLACK & w.left.color == BLACK) // x is still x.p.right
				{
					w.color = RED; // CASE 2
					x = x.parent;
				}
				else if(w.left.color == BLACK) // CASE 3
				{
					w.right.color = BLACK;
					w.color = RED;
					leftRotate(T,w);
					w = x.parent.left;
				}
				else // CASE 4
				{
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.left.color = BLACK;
					rightRotate(T,x.parent);
					x = T.root;
				}
			}
			
		}
		x.color = BLACK;
	}
	
	/**
	 * Deletes a page from the tree by searching the page by its score
	 * @param score - the score of the page that needs to be deleted
	 */
	public void deletePage(int score)
	{
		node temp = search(root, score); // finds the node that needs to be deleted
		RBdelete(this, temp); 
		rank--;
	}
	
	/**
	 * Performs inOrderWalk on a tree
	 * @param root - the root of the tree on which the walk needs to be done
	 */
	public void inOrderTreeWalk (node root)
	{
		if (root != nil)
		{
			inOrderTreeWalk(root.left);
			System.out.println("Index: "+ root.getPage().getIndex() + ", URl: " + root.getPage().getUrl() + ", Score: "+ root.getPage().getScore() + ", Color: "+ root.getColor() + ", PageRank: " + rank--);
			root.getPage().setRank(rank);
			inOrderTreeWalk(root.right);
		}
	}
	
	/**
	 * Sorts a tree using inOrderWalk
	 */
	public void sort()
	{
		inOrderTreeWalk(this.root);
	}
	
	/**
	 * Searches a node in a tree by its score
	 * @param n - the node from where the search needs to be started
	 * @param score - score of the node that needs to be found
	 * @return - the found node
	 */
	public node search(node n, int score)
	{
		if ( n == null || score == n.getKey())
		{
			return n;
		}
		if (score<n.getKey())
		{
			return search(n.getLeft(), score);
		}
		else
		{
			return search(n.getRight(), score);
		}
	}
	
	/**
	 * Searches a page by its score
	 * @param score -score of the page that needs to be found
	 */
	public void searchPage(int score)
	{
		node temp = search(root, score);
		int realRank = temp.getPage().getRank() +1;
		System.out.println("Index: "+ temp.getPage().getIndex() + ", URl: " + temp.getPage().getUrl() + ", Score: "+ temp.getPage().getScore() + ", Color: "+ temp.getColor() + ", PageRank: " + realRank);
	}
	
	
	
	
	
	
	
}
