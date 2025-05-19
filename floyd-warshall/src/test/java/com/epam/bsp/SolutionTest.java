package com.epam.bsp;

import org.javatuples.Pair;

import org.junit.jupiter.api.Test;

import static com.epam.bsp.Solution.*;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {
    @Test
    public void testGetSumOfAllShortestPaths() {
        //sample tests
        assertEquals(
                Pair.with(126, 0),
                getSumOfAllShortestPaths(
                        5,
                        new int[][]{
                                {0, 2, 0, 6, 0},
                                {2, 0, 3, 8, 5},
                                {0, 3, 0, 0, 7},
                                {6, 8, 0, 0, 9},
                                {0, 5, 7, 9, 0}
                        }
                )
        );
        assertEquals(
                Pair.with(70, 8),
                getSumOfAllShortestPaths(
                        5,
                        new int[][]{
                                {0, 2, 0, 6, 0},
                                {2, 0, 3, 8, 0},
                                {0, 3, 0, 0, 0},
                                {6, 8, 0, 0, 0},
                                {0, 0, 0, 0, 0}
                        }
                )
        );
        assertEquals(
                Pair.with(42, 0),
                getSumOfAllShortestPaths(
                        3,
                        new int[][]{
                                {0, 10, 5},
                                {10, 0, 6},
                                {5, 6, 0}
                        }
                )
        );
    }
}