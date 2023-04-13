package algorithms.mazeGenerators;

import java.util.*;

public class MyMazeGenerator extends AMazeGenerator{
    private Map<Integer, List<Integer>> adjacency_map;
    private Random randomizer;
    private int column_len;
    private int row_len;

    public MyMazeGenerator(){}

    @Override
    public Maze generate(int column_num, int row_num) {
        return null;
    }
    

    private int get_neighbour_direction(Integer cur_node, Integer next_cell){
        if (cur_node - row_len == next_cell){
            return 0; // TOP
        }
        else if (cur_node - 1 == next_cell){
            return 3; // LEFT
        }
        else if (cur_node + row_len == next_cell){
            return 2; // BOTTOM
        }
        else{
            return 1; // RIGHT
        }
    }

    private Integer[] edges_to_unvisited_cells(List<Integer> visited){
        for(Integer cell_num : visited){
           int row = cell_num / row_len;
           int column = cell_num % row_len;

           if(row > 0){

           }
        }
    }


}
