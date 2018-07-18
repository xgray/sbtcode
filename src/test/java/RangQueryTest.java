import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RangQueryTest {

  class NumArray {

    public class Node {
      public int left;
      public int right;
      public int sum;
      public Node lnode;
      public Node rnode;
    }

    public NumArray(int[] nums) {
      root = build(nums, 0, nums.length);
    }

    private Node root = null;

    public int sumRange(int i, int j) {
      return sum(root, i, j);
    }

    private int sum(Node node, int i, int j) {
      if( node.left > j || node.right <= i) {
        return 0;
      } else if( node.left >= i && node.right-1 <= j) {
        return node.sum;
      } else {
        return sum(node.lnode, i, j) + sum(node.rnode, i, j);
      }
    }

    private Node build(int[] nums, int left, int right) {
      Node node = new Node();
      node.left = left;
      node.right = right;
      if( left == right-1) {
        node.sum = nums[left];
      } else {
        node.lnode = build(nums, left, (left+right)/2);
        node.rnode = build(nums, (left+right)/2, right);
        node.sum = node.lnode.sum + node.rnode.sum;
      }
      return node;
    }
  }


  @Test
  public void rangQueryTest() {
    NumArray numArray = new NumArray(new int[] {1,5,7,2,-1});
    assertEquals(12, numArray.sumRange(1,2));
  }

}
