//  https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/description/?envType=daily-question&envId=2024-12-23

//  Q.>  2471. Minimum Number of Operations to Sort a Binary Tree by Level

/**
You are given the root of a binary tree with unique values.

In one operation, you can choose any two nodes at the same level and swap their values.

Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.

The level of a node is the number of edges along the path between it and the root node.

 

Example 1:


Input: root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
Output: 3
Explanation:
- Swap 4 and 3. The 2nd level becomes [3,4].
- Swap 7 and 5. The 3rd level becomes [5,6,8,7].
- Swap 8 and 7. The 3rd level becomes [5,6,7,8].
We used 3 operations so return 3.
It can be proven that 3 is the minimum number of operations needed.
Example 2:


Input: root = [1,3,2,7,6,5,4]
Output: 3
Explanation:
- Swap 3 and 2. The 2nd level becomes [2,3].
- Swap 7 and 4. The 3rd level becomes [4,6,5,7].
- Swap 6 and 5. The 3rd level becomes [4,5,6,7].
We used 3 operations so return 3.
It can be proven that 3 is the minimum number of operations needed.
Example 3:


Input: root = [1,2,3,4,5,6]
Output: 0
Explanation: Each level is already sorted in increasing order so return 0.
 

Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 105
All the values of the tree are unique.
*/


class Solution {
  public int minimumOperations(TreeNode root) {
    int ans = 0;
    Queue<TreeNode> q = new LinkedList<>(Arrays.asList(root));

    // e.g. vals = [7, 6, 8, 5]
    // [2, 1, 3, 0]: Initialize the ids based on the order of vals.
    // [3, 1, 2, 0]: Swap 2 with 3, so 2 is in the right place (i == ids[i]).
    // [0, 1, 2, 3]: Swap 3 with 0, so 3 is in the right place.
    while (!q.isEmpty()) {
      List<Integer> vals = new ArrayList<>();
      List<Integer> ids = new ArrayList<>();
      for (int sz = q.size(); sz > 0; --sz) {
        TreeNode node = q.poll();
        vals.add(node.val);
        if (node.left != null)
          q.offer(node.left);
        if (node.right != null)
          q.offer(node.right);
      }
      for (int i = 0; i < vals.size(); ++i)
        ids.add(i);
      Collections.sort(ids, (i, j) -> vals.get(i) - vals.get(j));
      for (int i = 0; i < ids.size(); ++i)
        for (; ids.get(i) != i; ++ans)
          swap(ids, i, ids.get(i));
    }

    return ans;
  }

  private void swap(List<Integer> ids, int i, int j) {
    final int temp = ids.get(i);
    ids.set(i, ids.get(j));
    ids.set(j, temp);
  }
}
