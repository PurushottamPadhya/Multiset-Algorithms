package implementation;

public class TreeNode {

	String key;
  Integer value ;

	TreeNode leftTree, rightTree;

	public TreeNode(String key) {
		this.key = key;
    value = 1;
		leftTree = rightTree = null;
	}
  public TreeNode(String key, Integer value) {
    this.key = key;
    this.value = value;
    leftTree = rightTree = null;
  }

	public String getKey() {
		return this.key;
	}

  public void setKey(String key) {
    this.key = key;
  }


  public Integer getValue() {
    return this.value;
  }


  	public void setValue(Integer value) {
  		this.value = value;
  	}


	public TreeNode getLeftTree() {
		return leftTree;
	}

	public void setLeftTree(TreeNode leftTree) {
		this.leftTree = leftTree;
	}

	public TreeNode getRightTree() {
		return rightTree;
	}

	public void setRightTree(TreeNode rightTree) {
		this.rightTree = rightTree;
	}

}
