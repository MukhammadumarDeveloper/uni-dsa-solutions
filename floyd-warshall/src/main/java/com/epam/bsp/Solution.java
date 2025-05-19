package com.epam.bsp;

import org.javatuples.Pair;

public class Solution {

    /**
     * Returns a pair in which the first value is the sum of the all-pairs shortest paths between 
     *   all connected vertices and the second is the number of disconnected pairs of vertices in 
     *   an undirected weighted graph.
     *
     * The weight of (u,v)-edge represents the distance between 'u' and 'v' and vice versa.
     *
     * Edges are stored as an adjacency matrix, where 0 means no edge and a positive value indicates 
     *   the presence of an edge and reflects its weight.
     * 
     * The expected algorithm complexity is O(n^3), where n is the number of vertices.
     * 
     * For example, you have a graph with three vertices from 0 to 2 and the following adjacency matrix:
     *   [[0, 10, 5], [10, 0, 6], [5, 6, 0]]
     * The expected result is (42, 0).
     * 
     * @param n number of vertices in the graph, vertices are enumerated from 0 to n-1.
     * @param adjacencyMatrix adjacency matrix with weights.
     * @return the sum of the all-pairs shortest paths between all connected vertices
     *   and the number of disconnected pairs of vertices.
     */
    public static Pair<Integer, Integer> getSumOfAllShortestPaths(int n, int[][] adjacencyMatrix) {
        int[][] distances = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    distances[i][j] = adjacencyMatrix[i][j];
                } else {
                    distances[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
                        distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                    }
                }
            }
        }

        int sumOfShortestPaths = 0;
        int disconnectedPairs = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (distances[i][j] != Integer.MAX_VALUE) {
                    sumOfShortestPaths += distances[i][j];
                } else {
                    disconnectedPairs++;
                }
            }
        }

        return Pair.with(sumOfShortestPaths * 2, disconnectedPairs * 2);
    }
}
