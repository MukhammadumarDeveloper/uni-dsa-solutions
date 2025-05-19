package com.epam.bsp;

import java.util.*;

public class Solution {

    /**
     * Returns the number of strongly connected components in a directed graph.
     *
     * @param n     the number of vertices in the graph.
     * @param edges the adjacency dictionary which stores a set of adjacent vertices for each vertex.
     */
    public static int findNumberOfSCC(int n, Map<Integer, Set<Integer>> edges) {
        // Step 1: Create an adjacency list representation of the graph
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (Map.Entry<Integer, Set<Integer>> entry : edges.entrySet()) {
            int vertex = entry.getKey();
            Set<Integer> neighbors = entry.getValue();
            for (int neighbor : neighbors) {
                adjacencyList.get(vertex).add(neighbor);
            }
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, adjacencyList, visited, stack);
            }
        }

        List<List<Integer>> transpose = transposeGraph(adjacencyList);

        int count = 0;
        Arrays.fill(visited, false);
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                dfs(vertex, transpose, visited, null);
                count++;
            }
        }

        return count;
    }

    private static void dfs(int vertex, List<List<Integer>> graph, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;
        for (int neighbor : graph.get(vertex)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, stack);
            }
        }
        if (stack != null) {
            stack.push(vertex);
        }
    }

    private static List<List<Integer>> transposeGraph(List<List<Integer>> graph) {
        int n = graph.size();
        List<List<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            transpose.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int neighbor : graph.get(i)) {
                transpose.get(neighbor).add(i);
            }
        }
        return transpose;
    }
}