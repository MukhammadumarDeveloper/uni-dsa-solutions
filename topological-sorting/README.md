# Topological sorting

## Purpose

The coding exercise is designed to test your knowledge of the following concepts:

* Building topological sorting 
* Applying topological sorting to practical problems 

## Overview

The coding exercise covers the following practical problem:
* Finding an eligible task schedule that complies with dependencies

## Coding exercises

### Exercise 1: Finding an eligible task schedule that complies with task dependencies

Given the number of tasks `numTasks` and `dependencies`, implement the static method below to find the eligible tasks order. The dependency list `dependencies` consists of pair with the numbers of two tasks, the second task can only be completed only after the first task. There is at least one eligible order. Tasks are enumerated from `0` to `numTasks` exclusively.

```java
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
        //put your code here
        return List.of();
}
```

**Example 1:**

`numTasks = 6` 
`dependencies = [(0, 1), (1, 2), (0, 3), (5, 3)]`

These dependencies can be represented as a directed graph:

```mermaid
graph TD;
    A((0)) --> B((1));
    B --> C((2));
    A --> D((3));
    E((4));
    F((5));
    F --> D;
```

Possible eligible order: [5, 0, 1, 2, 3, 4]


**Example 2:**

`numTasks = 6` 
`dependencies = [(0, 1), (1, 2), (2, 3), (3, 4), (4, 5)]`

Expected order: [0, 1, 2, 3, 4, 5]

<br/>

Please use the template `Solution::findTasksOrder` for the implementation.
