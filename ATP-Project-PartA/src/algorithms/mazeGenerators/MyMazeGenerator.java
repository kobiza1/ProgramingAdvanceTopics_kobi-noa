package algorithms.mazeGenerators;

import java.util.*;

public class MyMazeGenerator extends AMazeGenerator{
    private Map<Integer, List<Integer>> adjacency_map;
    private Random randomizer;
    private Integer column_len;
    private Integer row_len;

    private int[][] maze_map;
    private List<Integer> edges_pool;


    public MyMazeGenerator(){
        maze_map = null;
        randomizer = new Random();
        edges_pool = new ArrayList<Integer>();
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

        //List<Integer> visited = new ArrayList<>();
        //List<Integer> unvisited = new ArrayList<>();

       /* for(int i=1; i<column_len*row_len; i++){
            unvisited.add(i);
        }*/

        int cur_cell = 0;
        // put 0 in the first cell and the last one
        maze_map[0][0] = 0;
       // visited.add(cur_cell);

        Integer edge = null;
        int row, col, random;
        while (maze_map[column_len-1][row_len-1] != 0){
            edges_to_unvisited_cells(cur_cell); // choose random cell to visit
            //if(edges_pool.size() <= 0) {break;}
            random = randomizer.nextInt(10);
            if(random < 7)
                edge = edges_pool.get(edges_pool.size() - 1);//randomizer.nextInt(edges_pool.size()));
            else
                edge = edges_pool.get(randomizer.nextInt(edges_pool.size()));
            edges_pool.remove(edge);
            row = (edge / row_len);
            col = edge % row_len;
            maze_map[row][col] = 0;
            //unvisited.remove(edge);
            cur_cell = edge;
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

    private void edges_to_unvisited_cells(int cur_cell) {
        int row, column;
        for (Integer num : adjacency_map.get(cur_cell)) {
            row = num / row_len;
            column = num % row_len;
            if (maze_map[row][column] != 0 && !edges_pool.contains(num)) {
                    edges_pool.add(num);
            }
        }
        //for(int i=0; i<edges_pool.size(); i++){
         //   System.out.print(edges_pool.get(i) + " ");
       // }
       // System.out.println();
    }
}
