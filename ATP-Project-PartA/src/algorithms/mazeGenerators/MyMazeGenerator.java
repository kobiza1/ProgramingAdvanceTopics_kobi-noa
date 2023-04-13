package algorithms.mazeGenerators;

import java.util.*;

public class MyMazeGenerator extends AMazeGenerator{
    private Map<Integer, List<Integer>> adjacency_map;
    private Random randomizer;
    private Integer column_len;
    private Integer row_len;

    private int[][] maze_map;

    public MyMazeGenerator(){
        maze_map = null;
        randomizer = new Random();
        adjacency_map = new HashMap<>();
    }

    @Override
    public Maze generate(int columns_num, int rows_num) {
            column_len = rows_num;
            row_len = columns_num;
            maze_map = new int[rows_num][columns_num];
            for(int i=0; i<rows_num; i++){
                for(int j=0; j<columns_num; j++){
                    maze_map[i][j] = 1;
                }
            }
            initialize_adjacency_map(columns_num, rows_num);
            primAlgo();
            return new Maze(maze_map, rows_num, columns_num);

    }

    private void primAlgo(){

        List<Integer> visited = new ArrayList<>();
        List<Integer> unvisited = new ArrayList<>();

        for(int i=1; i<column_len*row_len; i++){
            unvisited.add(i);
        }

        int cur_cell = 0;
        // put 0 in the first cell and the last one
        maze_map[0][0] = 0;
        maze_map[column_len-1][row_len-1] = 0;
        visited.add(cur_cell);


        while (!unvisited.isEmpty()){
            List<Integer[]> edges_pool = edges_to_unvisited_cells(visited); // chose random cell to visit
            Integer[] edge = edges_pool.get(randomizer.nextInt(edges_pool.size()));
            int i = (edge[1] / row_len);
            int j = edge[1] % row_len;
            maze_map[i][j] = 0;
            visited.add(edge[1]);
            unvisited.remove(edge[1]);
        }
    }

    private void initialize_adjacency_map(int column_num, int row_num){
        for(int i=0; i<row_num; i++){
            for(int j=0; j<column_num; j++){
                List<Integer> cur_list = new ArrayList<>();
                Integer cur_cell = i*row_len + j;
                if(i != 0){
                    cur_list.add(cur_cell - row_len);
                }
                if(i != column_len-1){
                    cur_list.add(cur_cell + row_len);
                }
                if(j != 0){
                    cur_list.add(cur_cell - 1);
                }
                if(j != row_len-1){
                    cur_list.add(cur_cell+1);
                }
                adjacency_map.put(cur_cell, cur_list);
            }
        }
    }
    

   /* private int get_neighbour_direction(Integer cur_node, Integer next_cell){
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
    }*/

    private List<Integer[]> edges_to_unvisited_cells(List<Integer> visited){
        List<Integer[]> edges_pool = new ArrayList<>();

        for(Integer num : visited){

            int row = num / row_len;
            int col = num % row_len;

            if (row > 0){
                int top_node = num - row_len;
                if(!visited.contains(top_node)){
                    Integer[] edge = {num, top_node};
                    edges_pool.add(edge);
                }
            }

            if (col > 0){
                int left_node = num - 1;
                if (!visited.contains(left_node)) {
                    Integer[] edge = {num, left_node};
                    edges_pool.add(edge);
                }
            }

            if (row < row_len - 1) {
                int bottom_node = num + row_len;
                if (!visited.contains(bottom_node)) {
                    Integer[] edge = {num, bottom_node};
                    edges_pool.add(edge);
                }
            }

            if (col < row_len - 1) {
                int right_node = num + 1;
                if(!visited.contains(right_node)) {
                    Integer[] edge = {num, right_node};
                    edges_pool.add(edge);
                }
            }
        }

        return edges_pool;
    }


}
