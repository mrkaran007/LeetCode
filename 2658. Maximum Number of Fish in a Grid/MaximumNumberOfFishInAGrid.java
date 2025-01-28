//  https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/?envType=daily-question&envId=2025-01-28

//  Q.> 2658. Maximum Number of Fish in a Grid


/**
You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:

A land cell if grid[r][c] = 0, or
A water cell containing grid[r][c] fish, if grid[r][c] > 0.
A fisher can start at any water cell (r, c) and can do the following operations any number of times:

Catch all the fish at cell (r, c), or
Move to any adjacent water cell.
Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists.

An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.

 

Example 1:


Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
Output: 7
Explanation: The fisher can start at cell (1,3) and collect 3 fish, then move to cell (2,3) and collect 4 fish.
Example 2:


Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
Output: 1
Explanation: The fisher can start at cells (0,0) or (3,3) and collect a single fish. 
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
0 <= grid[i][j] <= 10
*/



class Solution {
  
    private int[][] grid; // The grid representing the pond.
    private int rows; // Number of rows in the pond grid.
    private int cols; // Number of columns in the pond grid.

    // This method calculates the maximum number of fish that can be found in a straight line.
    public int findMaxFish(int[][] grid) {
        rows = grid.length; // Assigns the number of rows of the grid.
        cols = grid[0].length; // Assigns the number of columns of the grid.
        this.grid = grid; // Stores the grid in the instance variable for easy access.
        int maxFishCount = 0; // Starts with zero as the maximum number of fish found.
      
        // Iterates through each cell in the grid.
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                // If the current cell contains fish, perform a DFS to find all connected fish.
                if (grid[i][j] > 0) {
                    maxFishCount = Math.max(maxFishCount, dfs(i, j));
                }
            }
        }
        // Return the largest group of connected fish found in the pond.
        return maxFishCount;
    }

    // This method performs a depth-first search (DFS) to find all connected fish starting from cell (i, j).
    private int dfs(int i, int j) {
        int fishCount = grid[i][j]; // Counts the fish at the current cell.
        grid[i][j] = 0; // Marks the current cell as "visited" by setting its fish count to zero.
        // Array to calculate adjacent cell coordinates (up, right, down, left).
        int[] directions = {-1, 0, 1, 0, -1};
      
        // Explore all four adjacent cells using the directions array.
        for (int k = 0; k < 4; ++k) {
            int x = i + directions[k]; // Row index of the adjacent cell.
            int y = j + directions[k + 1]; // Column index of the adjacent cell.
          
            // Check whether the adjacent cell is within grid bounds and contains fish.
            if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] > 0) {
                fishCount += dfs(x, y); // Accumulate fish count and continue DFS.
            }
        }
        // Return the total count of fish connected to cell (i, j).
        return fishCount;
    }
}
