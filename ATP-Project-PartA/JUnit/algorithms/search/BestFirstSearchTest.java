package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    IMazeGenerator mg = new MyMazeGenerator();
    Maze maze = mg.generate(1000, 1000);
    SearchableMaze searchableMaze = new SearchableMaze(maze);
    BestFirstSearch bestFirstSearch = new BestFirstSearch();

    @Test
    void solve(){
        long start_time = System.currentTimeMillis();
        Solution solution = bestFirstSearch.solve(searchableMaze);
        long end_time = System.currentTimeMillis();
        boolean check1 = end_time - start_time <= 60000;
        boolean check2 = solution.getSolutionPath().size() > 0;
        assertTrue(check1);
        assertTrue(check2);
    }

    @Test
    void addCost() {
        AState startState = searchableMaze.getStartState();
        List<AState> nextStates = searchableMaze.getAllPossibleStates(startState);
        int cost_before, cost_after;

        for (AState nextState : nextStates){
            cost_before = nextState.getCost();
            bestFirstSearch.addCost(nextState);
            cost_after = nextState.getCost();
            assertEquals(cost_before, cost_after);
        }
        List<AState> evenMoreNextState = searchableMaze.getAllPossibleStates(nextStates.get(0));
        for (AState nextState : evenMoreNextState){
            cost_before = nextState.getCost();
            bestFirstSearch.addCost(nextState);
            cost_after = nextState.getCost();
            assertFalse(cost_before == cost_after);
        }
    }

    @Test
    void getName() {
        assertEquals(bestFirstSearch.getName(), "BestFirstSearch");
    }
}