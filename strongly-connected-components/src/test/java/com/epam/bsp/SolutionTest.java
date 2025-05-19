package com.epam.bsp;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {
    @Test
    public void testFindNumberOfSCC() {
        //sample tests
        Map<Integer, Set<Integer>> edges = Map.of(
                0, Set.of(1, 2, 3),
                2, Set.of(0, 4)
        );
        assertEquals(4, Solution.findNumberOfSCC(5, edges));

        edges = Map.of(
                0, Set.of(1, 2, 3, 4),
                1, Set.of(0, 2),
                2, Set.of(0, 1),
                3, Set.of(4),
                4, Set.of(3)
        );
        assertEquals(2, Solution.findNumberOfSCC(5, edges));

        edges = new HashMap<>();
        assertEquals(5, Solution.findNumberOfSCC(5, edges));

        edges = Map.of(
                0, Set.of(1, 5),
                1, Set.of(0),
                3, Set.of(4),
                4, Set.of(3),
                5, Set.of(0)
        );
        assertEquals(3, Solution.findNumberOfSCC(6, edges));
    }

}