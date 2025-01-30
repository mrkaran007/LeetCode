//  https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/?envType=daily-question&envId=2025-01-30

//  Q.> 2493. Divide Nodes Into the Maximum Number of Groups


/**
You are given a positive integer n representing the number of nodes in an undirected graph. The nodes are labeled from 1 to n.

You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that there is a bidirectional edge between nodes ai and bi. Notice that the given graph may be disconnected.

Divide the nodes of the graph into m groups (1-indexed) such that:

Each node in the graph belongs to exactly one group.
For every pair of nodes in the graph that are connected by an edge [ai, bi], if ai belongs to the group with index x, and bi belongs to the group with index y, then |y - x| = 1.
Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes. Return -1 if it is impossible to group the nodes with the given conditions.

 

Example 1:


Input: n = 6, edges = [[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]
Output: 4
Explanation: As shown in the image we:
- Add node 5 to the first group.
- Add node 1 to the second group.
- Add nodes 2 and 4 to the third group.
- Add nodes 3 and 6 to the fourth group.
We can see that every edge is satisfied.
It can be shown that that if we create a fifth group and move any node from the third or fourth group to it, at least on of the edges will not be satisfied.
Example 2:

Input: n = 3, edges = [[1,2],[2,3],[3,1]]
Output: -1
Explanation: If we add node 1 to the first group, node 2 to the second group, and node 3 to the third group to satisfy the first two edges, we can see that the third edge will not be satisfied.
It can be shown that no grouping is possible.
 

Constraints:

1 <= n <= 500
1 <= edges.length <= 104
edges[i].length == 2
1 <= ai, bi <= n
ai != bi
There is at most one edge between any pair of vertices.
*/


class Solution {
    private List<Integer>[] graph; // The graph represented as an adjacency list
    private List<Integer> componentNodes = new ArrayList<>(); // List to store nodes of the currently visited component
    private boolean[] visited; // Array to track visited nodes
    private int totalNodes; // Total number of nodes in the graph

    // Method to compute the magnificent sets value for the given graph
    public int magnificentSets(int n, int[][] edges) {
        totalNodes = n;
        graph = new List[n + 1];
        Arrays.setAll(graph, k -> new ArrayList<>()); // Initialize adjacency list for each node
        for (int[] edge : edges) { // Build the graph
            int nodeA = edge[0], nodeB = edge[1];
            graph[nodeA].add(nodeB);
            graph[nodeB].add(nodeA);
        }

        visited = new boolean[n + 1];
        int totalMagnificentSets = 0;
        for (int i = 1; i <= n; ++i) {
            if (!visited[i]) {
                dfs(i); // Perform Depth-First Search to find all nodes in the component
                int largestDepth = -1;
                // For each node in the component, use BFS to find the largest depth
                for (int node : componentNodes) {
                    largestDepth = Math.max(largestDepth, bfs(node));
                }
                if (largestDepth == -1) {
                    return -1; // If it's not a magnificent set, return -1
                }
                totalMagnificentSets += largestDepth; // Add the largest depth to the total
                componentNodes.clear(); // Clear nodes list for next component
            }
        }
        return totalMagnificentSets; // Return the total magnificent sets of all components
    }

    // Helper method for BFS to calculate the largest depth of the BFS tree from the starting node
    private int bfs(int startNode) {
        int[] depth = new int[totalNodes + 1];
        Arrays.fill(depth, Integer.MAX_VALUE); // Set initial depth to a high value
        depth[startNode] = 1; // Depth of start node is 1
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(startNode); // Initialize the queue with the starting node

        int maxDepth = 1; // Track the maximum depth
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            for (int neighbor : graph[currentNode]) {
                if (depth[neighbor] == Integer.MAX_VALUE) {
                    depth[neighbor] = depth[currentNode] + 1; // Update the depth of the neighbor
                    maxDepth = depth[neighbor]; // Update the max depth
                    queue.offer(neighbor); // Add the neighbor node to the queue
                }
            }
        }

        // Update the depth for any node that hasn't been reached by BFS
        for (int node : componentNodes) {
            if (depth[node] == Integer.MAX_VALUE) {
                depth[node] = ++maxDepth;
            }
        }

        // Verify that all edges in the component have depths differing by 1
        for (int node : componentNodes) {
            for (int neighbor : graph[node]) {
                if (Math.abs(depth[node] - depth[neighbor]) != 1) {
                    return -1; // This component isn't magnificent
                }
            }
        }

        return maxDepth; // Return the largest depth in the BFS tree
    }

    // Helper method for DFS to traverse all nodes in a connected component
    private void dfs(int currentNode) {
        componentNodes.add(currentNode); // Add current node to component list
        visited[currentNode] = true; // Mark current node as visited
        for (int neighbor : graph[currentNode]) { // Visit all unvisited neighbors
            if (!visited[neighbor]) {
                dfs(neighbor); // Recursively visit neighbors
            }
        }
    }
}
