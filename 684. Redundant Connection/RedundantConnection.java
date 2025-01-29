//  https://leetcode.com/problems/redundant-connection/?envType=daily-question&envId=2025-01-29

//  Q.>  684. Redundant Connection


/**
In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

 

Example 1:


Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]
Example 2:


Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]
 

Constraints:

n == edges.length
3 <= n <= 1000
edges[i].length == 2
1 <= ai < bi <= edges.length
ai != bi
There are no repeated edges.
The given graph is connected.
*/


class Solution {

    // Array representing the parents of each node in the disjoint-set (union-find).
    private int[] parent;

    // Function to find the redundant connection, taking edges of a graph.
    public int[] findRedundantConnection(int[][] edges) {

        // Initialize the parent array for a maximum of 1010 nodes.
        parent = new int[1010];
        for (int i = 0; i < parent.length; i++) {
            // Initially, each node is its own parent (self loop).
            parent[i] = i;
        }

        // Iterate through all edges.
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            // If both nodes have the same parent, the edge is redundant.
            if (find(node1) == find(node2)) {
                return edge;
            }

            // Union the sets of two nodes by setting the parent of one as the other.
            parent[find(node1)] = find(node2);
        }
      
        // If no redundant connection is found, return null (should not happen according to the problem statement).
        return null;
    }

    // Recursive function to find the parent of a given node.
    private int find(int node) {

        // Path compression: if the node is not its own parent, recursively find its parent and update the reference.
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        // Return the parent of the node.
        return parent[node];
    }
}
