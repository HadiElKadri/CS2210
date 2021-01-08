/**
 * @author Hadi El-Kadri 251015226
 * Public class Binary Search Tree represents a BST 
 */

public class BinarySearchTree implements BinarySearchTreeADT {
	
	/**
	 * Every BST has a root node
	 */
	private BinaryNode root;
	
	/**
	 * Constructor of a BST making the root node a new BinaryNode
	 */
	public BinarySearchTree(){
		root = new BinaryNode();
	}
	
	/**
	 * Getter method to get the root of the tree
	 */
	public BinaryNode getRoot() {
		return root;
	}
	
	/**
	 * This method will return a pixel with the input key
	 * @param r
	 * @param key
	 * @return a pixel
	 */
	public Pixel get(BinaryNode r, Location key) {
		// If the tree is not empty
	        if(r.isLeaf() == false){
	            
	        	// The binary tree is traversed until a leaf is reached
	            while (r.isLeaf() == false) {
	               
	            	// If the key is found the pixel that stores it is returned
	                if (r.getData().getLocation().compareTo(key) == 0) {
	                    return r.getData();
	                }
	                else if (r.getData().getLocation().compareTo(key) < 0) {
	                    r = r.getRight();
	                }
	                else{
	                    r = r.getLeft();
	                }
	            }
	        }
	        
	        // Null is returned if the pixel is not in the tree
	        return null;
	    }
	
	/**
	 * Insert a pixel into the BST if not already in it, throw an exception if it is
	 * @param r
	 * @param data
	 */
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		// Nodes with information must be internal nodes thus initialize children for the node
	        BinaryNode lchild = new BinaryNode();
	        BinaryNode rchild = new BinaryNode();

	        // If the BST is empty make the pixel the root node and set both children to null nodes
	        if(r.isLeaf()){
	            r.setParent(null);
	            r.setData(data);
	            lchild.setParent(r);
	            rchild.setParent(r);
	            r.setLeft(lchild);
	            r.setRight(rchild);
	        }

	        else{
	            // If the BST is not empty then traverse through it
	            while (r.isLeaf() == false) {

	                // If a node with the same key is found then throw a duplicated key exception
	                if (r.getData().getLocation().compareTo(data.getLocation()) == 0) {
	                    throw new DuplicatedKeyException();
	                }
	                else if (r.getData().getLocation().compareTo(data.getLocation()) < 0) {
	                    r = r.getRight();
	                }
	                else{
	                    r = r.getLeft();
	                }
	            }

	            // Put the record into the tree and set both its children to null nodes
	            r.setParent(r.getParent());
	            r.setData(data);
	            lchild.setParent(r);
	            rchild.setParent(r);
	            r.setLeft(lchild);
	            r.setRight(rchild);
	        }
	    }
	
	/**
	 * Method remove removes a node from the BST with an associated input key
	 * @param r
	 * @param key
	 */
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		// Get the node from the BST using the key and helper method getNode
		r = getNode(key);
		
		// If the node is a leaf throw an exception
		if (r.isLeaf()){
			throw new InexistentKeyException();
		}
		
		else{
			
			// If one of the nodes children is a leaf
			if (r.getLeft().isLeaf() || r.getRight().isLeaf()){
				
				// If the left child is a leaf
				if(r.getLeft().isLeaf()){
					BinaryNode right = r.getRight();
					BinaryNode parent = r.getParent();
					
					// If this node is root, the non-leaf child is made the new root
					if (parent == null){
						this.root = right;
						right.setParent(null);
					}
					
					// Or the parent points to its non-leaf child
					else{
						if(parent.getRight().equals(r)){
							parent.setRight(right);
							right.setParent(parent);
						}
						else{
							parent.setLeft(right);
							right.setParent(parent);
						}
					}
				}
				
				// If the right child is a leaf
				else{
					BinaryNode left = r.getLeft();
					BinaryNode parent = r.getParent();
					
					// If this node is root, the non-leaf child is made the new root
					if (parent == null){
						this.root = left ;
						left.setParent(null);
					}
					else{
						
						// Or the parent points to its non-leaf child
						if(parent.getRight().equals(r)){
							parent.setRight(left);
							left.setParent(parent);
				}
						else{
							parent.setLeft(left);
							left.setParent(parent);
						}
					}
				}
			}
			
			else{
				
				// If the node is internal find the smallest of its right-subtree and replace it
				BinaryNode p = r.getRight();
				while (!p.isLeaf()){
					p = p.getLeft();
				}
				BinaryNode small = p.getParent();
				
				//The data is replaced with the smallest node of the subtree
				r.setData(small.getData());
				BinaryNode smallparent = small.getParent();
				
				//Parent points to smallest right
				if(smallparent.getLeft().equals(small)){
					smallparent.setLeft(small.getRight());
				}
				else{
					smallparent.setRight(small.getRight());
				}
				small.getRight().setParent(smallparent);
			}
			
		}
		
	}
	
	/**
	 * Successor method finds the record with the smallest key larger than the input key
	 * @param r
	 * @param key
	 * @return a pixel
	 */
	public Pixel successor(BinaryNode r, Location key) {
			
		// If r is a leaf return null
			if (r.isLeaf()){
				return null;
			}
			else{
				
				// Else find the smallest key larger than the input key
				BinaryNode s = getNode(key);
				if (!s.isLeaf() &&! s.getRight().isLeaf()){
					
					//Find the smallest of the right subtree if it is not a leaf
					s.setData(smallest(r));
					return s.getRight().getData();
				}
				else{
					
					// Else traverse through its parents
					BinaryNode parent = s.getParent();
					
					// Find the parent whose closest right child is s
					while(parent != null && parent.getRight() == s)
					{
						s = parent;
						parent = parent.getParent();
					}
					if(parent!= null){
					return parent.getData();
					}
					else{
						return null;
					}
				}
			}
	}
	
	/**
	 * Predecessor method finds the record with the largest key smaller than the input key
	 * @param r
	 * @param key
	 * @return a pixel
	 */
	public Pixel predecessor(BinaryNode r, Location key) {
		
		// If r is a leaf return null
				if(r.isLeaf()){
					return null;
				}
				else{
					
					// Find the largest key smaller than the input key
					BinaryNode p = getNode(key);
					
					// Find the largest key of the left subtree if it is not  a leaf
					if (!p.isLeaf() && !p.getLeft().isLeaf()){
						p.setData(largest(r));
						return p.getLeft().getData();
				}
					else{
						
						// Else traverse through its parents
						BinaryNode parent = p.getParent();
						
						// Find the parent whose closest left child is p
						while ( parent!= null && parent.getLeft()==(p)){
							p = parent;
							parent = parent.getParent();
							}
						
						if(parent == null){
							return null;
					  }
						else{
							return parent.getData();
						}
					}
				}
	}
	
	/**
	 * Method smallest finds the node with the smallest key
	 * returns the pixel
	 * @param r
	 * @return a pixel
	 */
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		r = root;
        BinaryNode parent = r;

        //Traverses the left side of the binary search tree for the smallest pixel (Smallest on left side of a BST)
        while (r.getLeft() != null) {
            parent = r;
            r = r.getLeft();
        }

        return parent.getData();
    }
	
	/**
	 * Method largest finds the node with the largest key
	 * returns the pixel
	 * @param r
	 * @return a pixel
	 */
	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		r = root;
		BinaryNode parent = r;
		
		//Traverses the ride side of the binary search tree for the largest pixel (Smallest on the right side of a BST)
		while (r.getRight() != null) {
			parent = r;
			r = r.getRight();
	        }
		return parent.getData();
		}
	
	/**
	 * Method getNode returns a node of a specified key
	 * @param key
	 * @return node
	 */
	private BinaryNode getNode(Location key){
		BinaryNode found = root;
		
		// If the tree is not empty
		if(found.isLeaf() == false){
			while (found.isLeaf() == false) {
				
				// Return the node if the node is found
				if (found.getData().getLocation().compareTo(key) == 0) {
					return found;
					}
				else if (found.getData().getLocation().compareTo(key) < 0) {
					found = found.getRight();
					}
				else{
					found = found.getLeft();
				}
			}
		}
		// Returns the node if its found and null if not found
		return found;
		}
}
