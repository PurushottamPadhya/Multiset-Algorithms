package implementation;

import java.util.List;
import java.util.ArrayList;
/**
 * BST implementation of a multiset.  See comments in RmitMultiset to
 * understand what each overriden method is meant to do.
 *
 * @author Jeffrey Chan & Yongli Ren, RMIT 2020
 */
public class BstMultiset extends RmitMultiset{
	// root of BST
	TreeNode root;
	int size;
  String output ;
  List<String> keysResult;

	public BstMultiset() {

		root = null;
	}

	/*
	 *
	 * */
	@Override
	public void add(String item) {
		// Implement me!

		root = insertRecursuively(root, item);

	} // end of add()

	@Override
	public int search(String item) {
		// Implement me!

    int value = getNodeValueIfExist(root, item);
    if (value != searchFailed)
      return value;
		// Placeholder, please update.
		return searchFailed;
	} // end of search()

	@Override
	public List<String> searchByInstance(int instanceCount) {

    // Placeholder, please update.
		keysResult = new ArrayList<String>();
		getNodeKeyIfInstanceNumberMatched(root, instanceCount);
		if (keysResult.size() > 0 ){
			return keysResult;
		}

		return null;
	} // end of searchByInstance

/*
* check untill the key not found and traverseral goes root at first and then left tree and then right
*/
	@Override
	public boolean contains(String item) {
		// Implement me!

    if (checkNodeExist(root, item))
    return true;

		// Placeholder, please update.
		return false;
	} // end of contains()

	@Override
	public void removeOne(String item) {
		// Implement me!
    removeRecursively(root, item);
	} // end of removeOne()

	@Override
	public String print() {

   output = "";
	 inorderTraversal(root);
   if (output.length() > 0 )
    return output;
		// Placeholder, please update.

		return new String();
	} // end of OrderedPrint

	@Override
	public String printRange(String lower, String upper) {
    output = "";
    inorderTraversal(root, lower, upper);
    if (output.length() > 0 )
     return output;

		// Placeholder, please update.
		return new String();
	} // end of printRange()

	@Override
	public RmitMultiset union(RmitMultiset other) {

		// Placeholder, please update.
		return null;
	} // end of union()

	@Override
	public RmitMultiset intersect(RmitMultiset other) {

		// Placeholder, please update.
		return null;
	} // end of intersect()

	@Override
	public RmitMultiset difference(RmitMultiset other) {

		// Placeholder, please update.
		return null;
	} // end of difference()

	/*
	 * Other added methods on comparing stirng if result would be positive the item
	 * is greater than root else lesser than root
	 */
   // this is adding a item with instance value on the node
 	public void add(String item, Integer value) {
 		// Implement me!

 		root = insertRecursuively(root, item, value);

 	} // end of add()

	public TreeNode insertRecursuively(TreeNode root, String item) {
        // Implement me!
    	if (root == null) {
    		root = new TreeNode(item);
    		return root;
    	}
    	else {

    		if (item.compareTo(root.getKey()) > 0 ) {
    			root.rightTree = insertRecursuively(root.rightTree, item);
    		}
    		else if (item.compareTo(root.getKey()) < 0 ) {
    			root.leftTree = insertRecursuively(root.leftTree, item);
    		}
        else if (item.compareTo(root.getKey()) == 0){
          root.setValue(root.getValue() + 1);
        }
    	}
    	return root;
	}


  public TreeNode insertRecursuively(TreeNode root, String item, Integer value) {
      // Implement me!

    if (root == null) {
      root = new TreeNode(item);
      return root;
    }
    else {

      if (item.compareTo(root.getKey()) > 0 ) {
        root.rightTree = insertRecursuively(root.rightTree, item, value);
      }
      else if (item.compareTo(root.getKey()) < 0 ) {
        root.leftTree = insertRecursuively(root.leftTree, item, value);
      }
      else if (item.compareTo(root.getKey()) == 0){
        root.setValue(root.getValue() + value);
      }
    }
    return root;

}

	/*
	 * Inorder traversal in tree for print only
	 */

   public void inorderTraversal(TreeNode root) {
     if (root != null){
       inorderTraversal(root.getLeftTree());
       String currentOutput = "Key " + root.getKey() + " Value: " + root.getValue() + "\n";
       output = output + currentOutput ;
       //System.out.println("Key " + root.getKey() + " Value: " + root.getValue() + "\n");
       inorderTraversal(root.getRightTree());
     }

   }

// print in a range
public void inorderTraversal(TreeNode root, String lower, String upper) {

  if (root != null) {

        inorderTraversal(root.getLeftTree(), lower, upper);
        if ((root.getKey().compareTo(upper)) < 0  && (root.getKey().compareTo(lower) > 0)){
          String currentOutput = "Key " + root.getKey() + " Value: " + root.getValue() + "\n";
          output = output + currentOutput ;
        }
        inorderTraversal(root.getRightTree(), lower, upper);
  }
}

  /*
  * check for if node is exist or not
  */

  public boolean checkNodeExist(TreeNode node, String item){

    if (node == null){
      return false;
    }

    if (node.getKey().equals(item)){
      return true;
    }

    if (checkNodeExist(node.getLeftTree(), item))
      return true;

    if (checkNodeExist(node.getRightTree(), item))
    return true;

    return false;
  }

/*
* this method recursevely checked if the key is on node or not if present return the value on that node
*/
  public int getNodeValueIfExist(TreeNode node, String item){

    if (node == null){
      return searchFailed;
    }

    if (node.getKey().equals(item)){
      return node.getValue();
    }
    int value = getNodeValueIfExist(node.getLeftTree(), item);
    if (value != searchFailed)
      return value;

    value = getNodeValueIfExist(node.getLeftTree(), item);
    return value;
  }


  /*
  * this method recursevely checked and return the key of the node
  if value of that node is matched with the given parameter
  */
  public void getNodeKeyIfInstanceNumberMatched(TreeNode node, int instance){

    if (node != null) {

        getNodeKeyIfInstanceNumberMatched( node.getLeftTree(), instance);
        if (node.getValue() == instance){
          keysResult.add(node.getKey());
        }
        getNodeKeyIfInstanceNumberMatched( node.getRightTree(), instance);

    }

  }

  /*
 * First case if root null return root;
 * second case traverse through the node to find the exact key and remove that
 * third case node with only one or no child case
 * last one node with two children case
 * */

public TreeNode findMinElement(TreeNode root) {
  if (root.getLeftTree() == null) {
    return root;
  }
  else {
    return findMinElement(root.getLeftTree());
  }
}

public TreeNode removeRecursively(TreeNode root, String key) {

  if (root == null)
    return root;

  if (key.compareTo(root.getKey())< 0){
    root.setLeftTree(removeRecursively(root.getLeftTree(), key));
  }
  else if (key.compareTo(root.getKey())> 0){
    root.setRightTree(removeRecursively(root.getRightTree(), key));
  }
  else {

    if (root.getLeftTree() != null && root.getRightTree() != null) {

      TreeNode temp = root;
      TreeNode minNode =  findMinElement(temp.getRightTree());
      root.setKey(minNode.getKey());
      root.setValue(minNode.getValue());
      root.setRightTree(removeRecursively(root.getRightTree(), minNode.getKey()));
    }
    else if(root.getLeftTree() != null) {
      root = root.getLeftTree();
    }
    else if(root.getRightTree() != null) {
      root = root.getRightTree();
    }
    else
      root = null;

  }



  return root;
}


}  // end of class BstMultiset
