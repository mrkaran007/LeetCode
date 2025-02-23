//  https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/?envType=daily-question&envId=2025-02-23

//  Q.> 889. Construct Binary Tree from Preorder and Postorder Traversal


/**
Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.

If there exist multiple answers, you can return any of them.

 

Example 1:


Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
Example 2:

Input: preorder = [1], postorder = [1]
Output: [1]
 

Constraints:

1 <= preorder.length <= 30
1 <= preorder[i] <= preorder.length
All the values of preorder are unique.
postorder.length == preorder.length
1 <= postorder[i] <= postorder.length
All the values of postorder are unique.
It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.
*/




class Solution {
  public TreeNode constructFromPrePost(int[] pre, int[] post) {
    Map<Integer, Integer> postToIndex = new HashMap<>();

    for (int i = 0; i < post.length; ++i)
      postToIndex.put(post[i], i);

    return build(pre, 0, pre.length - 1, post, 0, post.length - 1, postToIndex);
  }

  private TreeNode build(int[] pre, int preStart, int preEnd, int[] post, int postStart,
                         int postEnd, Map<Integer, Integer> postToIndex) {
    if (preStart > preEnd)
      return null;
    if (preStart == preEnd)
      return new TreeNode(pre[preStart]);

    final int rootVal = pre[preStart];
    final int leftRootVal = pre[preStart + 1];
    final int leftRootPostIndex = postToIndex.get(leftRootVal);
    final int leftSize = leftRootPostIndex - postStart + 1;

    TreeNode root = new TreeNode(rootVal);
    root.left = build(pre, preStart + 1, preStart + leftSize, post, postStart, leftRootPostIndex,
                      postToIndex);
    root.right = build(pre, preStart + leftSize + 1, preEnd, post, leftRootPostIndex + 1,
                       postEnd - 1, postToIndex);
    return root;
  }
}
