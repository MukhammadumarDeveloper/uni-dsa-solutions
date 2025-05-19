package com.epam.bsp;

import java.util.List;

import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import static com.epam.bsp.Solution.*;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {
    @Test
    public void testFindTasksOrder() {
        //sample tests
        List<Pair<Integer, Integer>> dependencies = List.of(
                new Pair<>(1, 0),
                new Pair<>(3, 0),
                new Pair<>(0, 4)
        );
        checkOrder(5, dependencies, findTasksOrder(5, dependencies));

        dependencies = List.of(
                new Pair<>(0, 1),
                new Pair<>(1, 2),
                new Pair<>(2, 3),
                new Pair<>(3, 4)
        );
        checkOrder(5, dependencies, findTasksOrder(5, dependencies));

        dependencies = List.of(
                new Pair<>(2, 5),
                new Pair<>(5, 3),
                new Pair<>(3, 1),
                new Pair<>(1, 4),
                new Pair<>(3, 0)
        );
        checkOrder(6, dependencies, findTasksOrder(6, dependencies));
    }

    private static void checkOrder(int numTasks, List<Pair<Integer, Integer>> dependencies, List<Integer> order) {
        int[] reversedIndex = new int[numTasks];
        int index = 0;
        for (int task: order) {
            reversedIndex[task] = index++;
        }
        for (Pair<Integer, Integer> dependency: dependencies) {
            int taskA = dependency.getValue0();
            int taskB = dependency.getValue1();
            assertTrue(reversedIndex[taskA] <= reversedIndex[taskB]);
        }
    }
}