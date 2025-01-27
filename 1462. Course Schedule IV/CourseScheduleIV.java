//  https://leetcode.com/problems/course-schedule-iv/?envType=daily-question&envId=2025-01-27

//  Q.> 1462. Course Schedule IV



/**
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.

For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.

You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.

Return a boolean array answer, where answer[j] is the answer to the jth query.

 

Example 1:


Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
Output: [false,true]
Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
Course 0 is not a prerequisite of course 1, but the opposite is true.
Example 2:

Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
Output: [false,false]
Explanation: There are no prerequisites, and each course is independent.
Example 3:


Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
Output: [true,true]
 

Constraints:

2 <= numCourses <= 100
0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
prerequisites[i].length == 2
0 <= ai, bi <= numCourses - 1
ai != bi
All the pairs [ai, bi] are unique.
The prerequisites graph has no cycles.
1 <= queries.length <= 104
0 <= ui, vi <= numCourses - 1
ui != vi
*/



class Solution {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // Floyd-Warshall algorith to determine transitive closure of prerequisites
        boolean[][] transitiveClosure = new boolean[numCourses][numCourses];
        List<Integer>[] graph = new List[numCourses];
        int[] inDegree = new int[numCourses]; // For topological sorting

        // Initialize adjacency list
        Arrays.setAll(graph, i -> new ArrayList<>());
      
        // Build graph and in-degree array from prerequisites
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[0]].add(prerequisite[1]);
            ++inDegree[prerequisite[1]]; // Increment in-degree of successor
        }
      
        // Queue used for topological sorting
        Deque<Integer> queue = new ArrayDeque<>();
      
        // Adding all nodes with in-degree 0 to queue
        for (int i = 0; i < numCourses; ++i) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
      
        // Perform topological sort (Kahn's algorithm)
        while (!queue.isEmpty()) {
            int course = queue.poll();
          
            // Explore all neighbors of the current course
            for (int neighbor : graph[course]) {
              
                transitiveClosure[course][neighbor] = true;
              
                // Update transitive closure for all nodes that lead to current
                for (int preCourse = 0; preCourse < numCourses; ++preCourse) {
                    transitiveClosure[preCourse][neighbor] |= transitiveClosure[preCourse][course];
                }
              
                // Decrement in-degree of neighbor and if 0, add to queue
                if (--inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
      
        // Prepare the answer list to fulfill queries
        List<Boolean> answers = new ArrayList<>();
      
        // Check in the transitive closure if prerequisites are met
        for (int[] query : queries) {
            answers.add(transitiveClosure[query[0]][query[1]]);
        }
      
        // Return the list of results for each query
        return answers;
    }
}
