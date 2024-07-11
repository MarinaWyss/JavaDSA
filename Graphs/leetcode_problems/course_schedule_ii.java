// https://leetcode.com/problems/course-schedule-ii/description/

import java.util.Map; 
import java.util.HashMap; 
import java.util.List; 
import java.util.ArrayList; 
import java.util.Collections; 

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Create an adjacency list to represent the graph
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<>());
        }
        // Populate the adjacency list with the given edges
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prereqCourse = prereq[1];
            adj.get(prereqCourse).add(course);
        }
        // List to store the topological order
        List<Integer> result = new ArrayList<>();
        // Array to keep track of visited nodes to detect cycles and avoid reprocessing
        int[] visited = new int[numCourses];
        // Perform DFS from each node to ensure all nodes are processed
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, adj, visited, result)) {
                return new int[0]; // If a cycle is detected, return an empty array
            }        
        }
        // Reverse the result list to get the correct topological order
        Collections.reverse(result);
        // Convert the list to an array
        int[] order = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            order[i] = result.get(i);
        }
        return order;
    }

    private boolean dfs(int node, Map<Integer, ArrayList<Integer>> adj, int[] visited, List<Integer> result) {
        if (visited[node] == 1) { // Node is being visited, so we have a cycle
            return false;
        }
        if (visited[node] == 2) { // Node has been visited, so skip it
            return true;
        }
        // Mark the current node as being visited
        visited[node] = 1;
    
        // Recursively visit all neighbors of the current node
        for (int neighbor : adj.get(node)) {
            if (!dfs(neighbor, adj, visited, result)) {
                return false;
            }
        }
        // Mark the current node as visited
        visited[node] = 2;
        // Add the current node to the result list AFTER visiting its neighbors (post-order)
        result.add(node);
        return true;
    }
}