// https://leetcode.com/problems/course-schedule-iv/description/

import java.util.*;

class Solution {
    /**
     * Use the Floyd-Warshall algorithm to determine if one course is a prerequisite for another.
     * 
     * Steps:
     * 1. Build a reachability matrix where reachable[i][j] indicates if course i is a prerequisite of course j.
     * 2. Use the Floyd-Warshall algorithm to compute the transitive closure of the reachability matrix. Basically
     * if one node is reachable from another.
     * 3. Answer each query by checking the reachability matrix.
     * 
     * Example:
     * numCourses = 4
     * prerequisites = [[0, 1], [1, 2], [2, 3]]
     * queries = [[0, 3], [3, 0], [1, 3], [3, 1]]
     * 
     * Step 1: Initial reachability matrix:
     * 0 1 2 3
     * 0 F T F F
     * 1 F F T F
     * 2 F F F T
     * 3 F F F F
     * 
     * Step 2: After Floyd-Warshall:
     * 0 1 2 3
     * 0 F T T T
     * 1 F F T T
     * 2 F F F T
     * 3 F F F F
     * 
     * Step 3: Answer queries:
     * [[0, 3] -> true, [3, 0] -> false, [1, 3] -> true, [3, 1] -> false]
     */
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // Create a reachability matrix, initialized to false
        boolean[][] reachable = new boolean[numCourses][numCourses];

        // Populate the reachability matrix with direct prerequisites
        for (int[] prereq : prerequisites) {
            reachable[prereq[0]][prereq[1]] = true;
        }

        // Floyd-Warshall algorithm to compute the transitive closure
        for (int k = 0; k < numCourses; k++) {
            for (int i = 0; i < numCourses; i++) {
                for (int j = 0; j < numCourses; j++) {
                    // If course i is reachable to course k and course k is reachable to course j,
                    // then course i is reachable to course j
                    if (reachable[i][k] && reachable[k][j]) {
                        reachable[i][j] = true;
                    }
                }
            }
        }

        List<Boolean> answer = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            int u = query[0]; // Source course
            int v = query[1]; // Destination course
            // Check if course u is a prerequisite of course v
            answer.add(reachable[u][v]);
        }

        return answer;
    }
}

