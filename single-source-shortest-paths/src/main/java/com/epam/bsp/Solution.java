package com.epam.bsp;

import java.util.Map;
import java.util.Optional;
import org.javatuples.Pair;
import java.util.*;


public class Solution {

    /**
     * Returns the maximum probability of getting from vertex `v` to vertex `u` in an undirected weighted graph.
     *
     * The weight of (u,v)-edge represents the probability of successfully of traversing 
     *   from 'u' to 'v' and vice versa.
     *
     * The expected algorithm complexity is O(n^2), where n is the number of vertices.
     *   Vertices are enumerated from 0 to N-1.
     *
     * If no (v,u)-path exists, please, return 0.
     *
     * Your answer will be accepted if it differs from the correct answer by 1e-5 or less.
     *
     * For example, there is a graph with three vertices from 0 to 2 and the following edges:
     *   {0: {(1, 0.5), (2, 0.2)), 1: {(0, 0.5), (2, 0.5)}, 2: {(0, 0.2), (1, 0.5)}}
     * If the starting vertex is 0 and the ending vertex is 2, the expected result is 0.25.
     * There are two paths from start to end, one (0 -> 2) with a probability of success of 0.2
     *   the other (0 -> 1 -> 2) with a probability of 0.5 * 0.5 = 0.25.
     *
     * @param n the number of vertices in the graph, numbered from 0 to n-1.
     * @param edges the adjacency dictionary, which stores a set of adjacent vertices 
     *   and its weights for each vertex.
     * @param vertexV start vertex.
     * @param vertexU finish vertex.
     * @return the maximum probability of getting from vertex `v` to vertex `u`.
     */
    public static double getMaximumProbabilityPath(
        int n,
        Map<Integer, Map<Integer, Double>> edges,
        int vertexV,
        int vertexU
    ) {
        double[] probabilities = new double[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(probabilities, 0.0);
        probabilities[vertexV] = 1.0;

        while (true) {
            int nextVertex = -1;
            double maxProbability = 0.0;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && probabilities[i] > maxProbability) {
                    maxProbability = probabilities[i];
                    nextVertex = i;
                }
            }

            if (nextVertex == -1) {
                break;
            }

            visited[nextVertex] = true;
            if (nextVertex == vertexU) {
                return probabilities[vertexU];
            }

            if (edges.containsKey(nextVertex)) {
                Map<Integer, Double> adjacentVertices = edges.get(nextVertex);
                for (int adjacentVertex : adjacentVertices.keySet()) {
                    double edgeWeight = adjacentVertices.get(adjacentVertex);
                    double newProbability = probabilities[nextVertex] * edgeWeight;
                    if (newProbability > probabilities[adjacentVertex]) {
                        probabilities[adjacentVertex] = newProbability;
                    }
                }
            }
        }

        return 0.0;
    }


    public static Optional<Pair<Integer, Integer>> findShortestPathsCost(
            int n,
            Map<Integer, Map<Integer, Integer>> edges,
            int vertexV) {
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[vertexV] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int u = 0; u < n; u++) {
                if (edges.containsKey(u)) {
                    for (Map.Entry<Integer, Integer> entry : edges.get(u).entrySet()) {
                        int v = entry.getKey();
                        int weight = entry.getValue();
                        if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                            distances[v] = distances[u] + weight;
                        }
                    }
                }
            }
        }

        // Check for negative cycles
        for (int u = 0; u < n; u++) {
            if (edges.containsKey(u)) {
                for (Map.Entry<Integer, Integer> entry : edges.get(u).entrySet()) {
                    int v = entry.getKey();
                    int weight = entry.getValue();
                    if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                        return Optional.empty();
                    }
                }
            }
        }

        int unachievableCount = 0;
        int sumOfMinimumCostPaths = 0;

        for (int i = 0; i < n; i++) {
            if (distances[i] != Integer.MAX_VALUE) {
                sumOfMinimumCostPaths += distances[i];
            } else {
                unachievableCount++;
            }
        }

        return Optional.of(Pair.with(sumOfMinimumCostPaths, unachievableCount));
    }
}
