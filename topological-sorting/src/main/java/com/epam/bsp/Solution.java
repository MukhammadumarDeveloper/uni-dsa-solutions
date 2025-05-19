package com.epam.bsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.javatuples.Pair;

public class Solution {

    /**
     * Returns any appropriate order of tasks that complies with all dependencies.
     * 
     * NOTE: There is at least one eligible order.
     * 
     * For example, you have five tasks from 0 to 4 and the dependency list [(0,1), (3,4), (4,1)].
     * One of the eligible orders is [0, 3, 4, 1, 2].
     * 
     * @param numTasks the number of tasks.
     * @return any appropriate tasks order that complies with the dependencies.
     */
    public static List<Integer> findTasksOrder(int numTasks, List<Pair<Integer, Integer>> dependencies) {
        // Create an adjacency list to represent the dependencies
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numTasks; i++) {
            graph.add(new ArrayList<>());
        }
        for (Pair<Integer, Integer> dependency : dependencies) {
            int from = dependency.getValue0();
            int to = dependency.getValue1();
            graph.get(from).add(to);
        }
        
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[numTasks];
        for (int i = 0; i < numTasks; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited, stack);
            }
        }
        List<Integer> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        
        return order;
    }
    
    private static void dfs(int task, List<List<Integer>> graph, boolean[] visited, Stack<Integer> stack) {
        visited[task] = true;
        for (int dependency : graph.get(task)) {
            if (!visited[dependency]) {
                dfs(dependency, graph, visited, stack);
            }
        }
        stack.push(task);
    }
}
