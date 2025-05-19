package com.epam.bsp;

import java.util.Map;
import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import static com.epam.bsp.Solution.*;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {

    private final static double DELTA = 1e-5;

    @Test
    public void testGetMaximumProbabilityPath() {
        //sample tests
        Map<Integer, Map<Integer, Double>> edges = Map.of(
                0, Map.of(
                        1, 0.5,
                        2, 0.2),
                1, Map.of(
                        0, 0.5,
                        2, 0.5),
                2, Map.of(
                        0, 0.2,
                        1, 0.5)
        );
        assertEquals(
                0.25, getMaximumProbabilityPath(3, edges, 0, 2), DELTA
        );

        edges = Map.of(
                0, Map.of(
                        1, 0.5,
                        2, 0.3),
                1, Map.of(
                        0, 0.5,
                        2, 0.5),
                2, Map.of(0, 0.3,
                        1, 0.5)
        );
        assertEquals(
                0.3, getMaximumProbabilityPath(3, edges, 0, 2), DELTA
        );

        edges = Map.of(
                0, Map.of(1, 0.5),
                1, Map.of(0, 0.5)
        );
        assertEquals(
                0.0, getMaximumProbabilityPath(3, edges, 0, 2), DELTA
        );

        edges = Map.of(
                0, Map.of(
                        1, 0.5),
                1, Map.of(
                        0, 0.5,
                        2, 0.5,
                        3, 0.2),
                2, Map.of(
                        1, 0.5,
                        3, 0.5),
                3, Map.of(
                        1, 0.2,
                        2, 0.5,
                        4, 0.5),
                4, Map.of(
                        3, 0.5)
        );
        assertEquals(
                0.0625, getMaximumProbabilityPath(5, edges, 0, 4), DELTA
        );
    }

    @Test
    public void testFindShortestPathsCost() {
        //sample tests
        Map<Integer, Map<Integer, Integer>> edges = Map.of(
                0, Map.of(1, 5, 2, 2),
                1, Map.of(0, 5, 2, 2),
                2, Map.of(0, 2, 1, 2)
        );
        assertEquals(
                Pair.with(6, 0),
                findShortestPathsCost(3, edges, 0).get()
        );

        edges = Map.of(
                0, Map.of(1, -1),
                1, Map.of(0, -2)
        );
        assertTrue(
                findShortestPathsCost(3, edges, 0).isEmpty()
        );


        edges = Map.of(
                0, Map.of(1, 3, 2, -7),
                1, Map.of(0, 5, 2, -1)
        );
        assertEquals(
                Pair.with(3, 0),
                findShortestPathsCost(3, edges, 1).get()
        );

        edges = Map.of(
                0, Map.of(1, 3, 2, 7),
                1, Map.of(2, 5),
                2, Map.of(3, 5)
        );
        assertEquals(
                Pair.with(15, 1),
                findShortestPathsCost(4, edges, 1).get()
        );

        edges = Map.of(
                0, Map.of(1, 3, 2, 7),
                1, Map.of(2, 5),
                2, Map.of(3, 5)
        );
        assertEquals(
                Pair.with(22, 0),
                findShortestPathsCost(4, edges, 0).get()
        );
    }
}