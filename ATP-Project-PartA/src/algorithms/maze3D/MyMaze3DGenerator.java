package algorithms.maze3D;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator{


    private ArrayList<Position3D> walls_list;
    private Maze3D my_maze;
    private Random randomizer;

    private int number_of_rows;
    private int number_of_columns;
    private int depth_level;



    @Override
    public Maze3D generate(int depth, int rows_num, int columns_num) {

        if (check_inputs(rows_num, columns_num, depth)) {
            return my_maze;
        }
        number_of_rows = rows_num;
        number_of_columns = columns_num;
        depth_level = depth;


        randomizer = new Random();
        walls_list = new ArrayList();

        int[][][] ones_maze_board = initialize_with_ones();
        my_maze = new Maze3D(ones_maze_board, rows_num, columns_num, depth);

        Position3D startPosition = new Position3D(0, 0, 0);
        my_maze.setStartPosition(startPosition);
        my_maze.set_value_of_position(startPosition, 0); //0 in Start position
        my_maze.setGoalPosition(new Position3D(0, rows_num -1 ,columns_num - 1 ));
        this.add_wall_to_list(startPosition);

        primAlgo(rows_num, columns_num, startPosition);
        random_my_maze();

        return my_maze;
    }

    private void primAlgo(int rows, int columns, Position3D startPosition){
        /**
         * implement the prim algorithms, choose random wall to break and then
         * add all the walls that connected to this cell
         *
         */
        int counter = 2;
        Position3D GoalPosition = new Position3D(0,rows - 1, columns - 1);
        Position3D cur_position = startPosition;

        while(my_maze.get_value_of_position(GoalPosition) == 1){

            if(walls_list.size() == 0){
                break;
            }
            cur_position = walls_list.remove(randomizer.nextInt(walls_list.size()));

            //if(num_of_neighbors(cur_position) < 3){
                my_maze.set_value_of_position(cur_position, 0);
                add_wall_to_list(cur_position);
                if(cur_position.getColumnIndex() == columns-1 && cur_position.getRowIndex() == rows -1 && cur_position.getDepthIndex() == depth_level -1){
                    Position3D down_one_depth = new Position3D(depth_level - counter, number_of_rows - counter, number_of_columns - counter);
                    walls_list.add(down_one_depth);
                    counter++;
                }

            //}
        }
    }

    private void random_my_maze(){
        int num;
        for(int k=0; k<depth_level; k++){
            for(int i=0; i<number_of_rows; i++){
                for (int j=0; j<number_of_columns; j++){
                    if(my_maze.getMap()[k][i][j] == 1){
                        num = randomizer.nextInt(10);
                        if(num < 5)
                            my_maze.getMap()[k][i][j] = randomizer.nextInt(2);
                    }
                }
            }
        }

    }

    private int[][][] initialize_with_ones() {

        int[][][] mat = new int[depth_level][number_of_rows][number_of_columns];
        for(int k=0; k<depth_level; k++){
            for(int i=0; i<number_of_rows; i++){
                for (int j=0; j<number_of_columns; j++){
                    mat[k][i][j] = 1;
                }
            }
        }
        return mat;
    }


   /* private int num_of_neighbors(Position3D p){

        int row = p.getRowIndex();
        int column = p.getColumnIndex();
        int depth = p.getDepthIndex();
        int num_of_neighbors = 0;
        Position3D pos = new Position3D(depth, row, column - 1);

        if (this.is_valid_position(row, column - 1, depth) && (my_maze.get_value_of_position(pos) == 0)) {
            num_of_neighbors++;
        }
        pos = new Position3D(depth, row,column + 1);
        if (this.is_valid_position(row, column + 1, depth) && (my_maze.get_value_of_position(pos) == 0)) {
            num_of_neighbors++;
        }
        pos = new Position3D(depth,row - 1, column);
        if (this.is_valid_position(row - 1, column, depth) && (my_maze.get_value_of_position(pos) == 0)) {
            num_of_neighbors++;
        }
        pos = new Position3D(depth,row + 1, column);
        if (this.is_valid_position(row + 1, column, depth) && (my_maze.get_value_of_position(pos) == 0)) {
            num_of_neighbors++;
        }
        pos = new Position3D(depth+1,row , column);
        if (this.is_valid_position(row , column, depth+1) && (my_maze.get_value_of_position(pos) == 0)) {
            num_of_neighbors++;
        }
        pos = new Position3D(depth-1, row , column);
        if (this.is_valid_position(row , column, depth-1) && (my_maze.get_value_of_position(pos) == 0)) {
            num_of_neighbors++;
        }
        return num_of_neighbors;
    }*/

    private void add_wall_to_list(Position3D p){

        int row = p.getRowIndex();
        int column = p.getColumnIndex();
        int depth = p.getDepthIndex();
        //Position3D pos = new Position3D(depth,row - 1, column);
        walls_list.clear();

       /* if (is_valid_position(row - 1, column, depth) && my_maze.get_value_of_position(pos) == 1) {
            walls_list.add(new Position3D( depth, row - 1, column));
        }*/

        Position3D pos = new Position3D(depth,row + 1, column);
        if (this.is_valid_position(row + 1, column, depth) && my_maze.get_value_of_position(pos) == 1) {
            walls_list.add(new Position3D(depth,row + 1, column));
        }

        /*pos = new Position3D(depth, row, column - 1);
        if (this.is_valid_position(row, column - 1, depth) && my_maze.get_value_of_position(pos) == 1) {
            walls_list.add(new Position3D(depth, row, column - 1));
        }*/

        pos = new Position3D(depth, row,column + 1);
        if (this.is_valid_position(row, column + 1, depth) && my_maze.get_value_of_position(pos) == 1) {
            walls_list.add(new Position3D(depth, row, column + 1));
        }

        pos = new Position3D(depth+1,row , column);
        if (this.is_valid_position(row , column, depth+1) && (my_maze.get_value_of_position(pos) == 1)) {
            walls_list.add(new Position3D(depth+1, row, column));
        }

        pos = new Position3D(depth-1, row , column);
        if (this.is_valid_position(row , column, depth-1) && (my_maze.get_value_of_position(pos) == 1)) {
            walls_list.add(new Position3D(depth-1, row, column));
        }
    }

    private boolean is_valid_position(int row, int column, int depth){

        if(row < 0 || row > number_of_rows - 1 || column < 0 || column > number_of_columns - 1){
            return false;
        }
        if (depth < 0 || depth > depth_level - 1){
            return false;
        }
        return true;
    }
}
