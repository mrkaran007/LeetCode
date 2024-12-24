//  https://leetcode.com/problems/find-minimum-diameter-after-merging-two-trees/description/?envType=daily-question&envId=2024-12-24

//  Q.> 3203. Find Minimum Diameter After Merging Two Trees

/**
There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1, respectively. You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.

You must connect one node from the first tree with another node from the second tree with an edge.

Return the minimum possible diameter of the resulting tree.

The diameter of a tree is the length of the longest path between any two nodes in the tree.

 

Example 1:

Input: edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]

Output: 3

Explanation:

We can obtain a tree of diameter 3 by connecting node 0 from the first tree with any node from the second tree.

Example 2:


Input: edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]

Output: 5

Explanation:

We can obtain a tree of diameter 5 by connecting node 0 from the first tree with node 0 from the second tree.

 

Constraints:

1 <= n, m <= 105
edges1.length == n - 1
edges2.length == m - 1
edges1[i].length == edges2[i].length == 2
edges1[i] = [ai, bi]
0 <= ai, bi < n
edges2[i] = [ui, vi]
0 <= ui, vi < m
The input is generated such that edges1 and edges2 represent valid trees.
*/



public class Solution {
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        int[][] g = packU(n, edges1);
        int m = edges2.length + 1;
        int[][] h = packU(m, edges2);
        int[] d1 = diameter(g);
        int[] d2 = diameter(h);
        int ans = Math.max(d1[0], d2[0]);
        ans = Math.max((d1[0] + 1) / 2 + (d2[0] + 1) / 2 + 1, ans);
        return ans;
    }

    private int[] diameter(int[][] g) {
        int n = g.length;
        int f0;
        int f1;
        int d01;
        int[] q = new int[n];
        boolean[] ved = new boolean[n];
        int qp = 0;
        q[qp++] = 0;
        ved[0] = true;
        for (int i = 0; i < qp; i++) {
            int cur = q[i];
            for (int e : g[cur]) {
                if (!ved[e]) {
                    ved[e] = true;
                    q[qp++] = e;
                }
            }
        }
        f0 = q[n - 1];
        int[] d = new int[n];
        qp = 0;
        Arrays.fill(ved, false);
        q[qp++] = f0;
        ved[f0] = true;
        for (int i = 0; i < qp; i++) {
            int cur = q[i];
            for (int e : g[cur]) {
                if (!ved[e]) {
                    ved[e] = true;
                    q[qp++] = e;
                    d[e] = d[cur] + 1;
                }
            }
        }
        f1 = q[n - 1];
        d01 = d[f1];
        return new int[] {d01, f0, f1};
    }

    private int[][] packU(int n, int[][] ft) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int[] u : ft) {
            p[u[0]]++;
            p[u[1]]++;
        }
        for (int i = 0; i < n; i++) {
            g[i] = new int[p[i]];
        }
        for (int[] u : ft) {
            g[u[0]][--p[u[0]]] = u[1];
            g[u[1]][--p[u[1]]] = u[0];
        }
        return g;
    }
}
