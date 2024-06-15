// https://leetcode.com/problems/course-schedule/description/

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Edge cases
        if (numCourses <= 0) return false;
        if (prerequisites == null || prerequisites.length == 0) return true;
        
        // First, build a graph to represent relationships
        HashMap<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.put(i, new ArrayList<Integer>());
        }
        
        for (int[] node : prerequisites) {
            int course = node[0], prereq = node[1];
            adjList.get(prereq).add(course);
        }

        // What we are really trying to see is if there is a cycle in the graph
        // Use DFS to explore each node.
        // If we find a node that has already been visited, return false (we have a cycle).
        boolean[] visited = new boolean[numCourses];
        boolean[] path = new boolean[numCourses]; // To detect cycles in the current path
        
        for (int i = 0; i < numCourses; i++) {
            // If any courses can't be taken, return false
            if (hasCycle(i, adjList, visited, path)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCycle(int course, HashMap<Integer, List<Integer>> adjList, boolean[] visited, boolean[] path) {
        // Return false if we detect a cycle
        if (path[course]) return true;
        if (visited[course]) return false;

        // Mark the course as visited
        visited[course] = true;
        path[course] = true;

        // Call each prereq
        for (Integer next : adjList.get(course)) {
            if (hasCycle(next, adjList, visited, path)) return true;
        }

        // If we reach this point the course can be taken, so we backtrack
        path[course] = false;
        return false;
    }
}
