package algorithms.mazeGenerators;

import java.util.*;
public class MyMazeGenerator extends AMazeGenerator{
        private ArrayList<Position> walls_list;
        private Maze my_maze;
        private Random randomizer;
        public MyMazeGenerator() {}

        @Override
        public Maze generate(int rows_num, int columns_num) {

            if (rows_num <= 0 || columns_num <= 0) {
               return null;
            }

            number_of_rows = rows_num;
            number_of_columns = columns_num;

            randomizer = new Random();
            walls_list = new ArrayList();

            int[][] ones_maze_board = initialize_with_ones();
            my_maze = new Maze(ones_maze_board, rows_num, columns_num);

            Position startPosition = new Position(0, 0);
            my_maze.setStartPosition(startPosition);
            my_maze.set_value_of_position(startPosition.getRowIndex(), startPosition.getColumnIndex(), 0); //0 in Start position
            //my_maze.setGoalPosition(new Position(number_of_rows -1, number_of_columns - 1));
            this.add_wall_to_list(startPosition);

            primAlgo();

            return my_maze;
        }

        private void primAlgo(){
            /**
             * implement the prim algorithms, choose random wall to break and then
             * add all the walls that connected to this cell
             *
             */
            Position GoalPosition = new Position(0, 0);

            while(!walls_list.isEmpty()){

                Position cur_position = walls_list.remove(randomizer.nextInt(walls_list.size()));

                if(num_of_neighbors(cur_position) == 1){
                    my_maze.set_value_of_position(cur_position.getRowIndex(), cur_position.getColumnIndex(),0);
                    GoalPosition = cur_position;
                    add_wall_to_list(cur_position);

                }
            }

            my_maze.setGoalPosition(GoalPosition);
            //if(my_maze.get_value_of_position(number_of_rows -1, number_of_columns - 1) == 1){
               // primAlgo();
            //}
        }

        private int[][] initialize_with_ones() {

            int[][] mat = new int[number_of_rows][number_of_columns];
            for(int i=0; i<number_of_rows; i++){
                for (int j=0; j<number_of_columns; j++){
                    mat[i][j] = 1;
                }
            }
            return mat;
        }


        private int num_of_neighbors(Position p){

            int row = p.getRowIndex();
            int column = p.getColumnIndex();
            int num_of_neighbors = 0;

            if (this.is_valid_position(row, column - 1) && (my_maze.get_value_of_position(row,column - 1) == 0)) {
                num_of_neighbors++;
            }
            if (this.is_valid_position(row, column + 1) && (my_maze.get_value_of_position(row,column + 1) == 0)) {
                num_of_neighbors++;
            }
            if (this.is_valid_position(row - 1, column) && (my_maze.get_value_of_position(row - 1, column) == 0)) {
                num_of_neighbors++;
            }
            if (this.is_valid_position(row + 1, column) && (my_maze.get_value_of_position(row + 1, column) == 0)) {
                num_of_neighbors++;
            }
            return num_of_neighbors;
        }

        private void add_wall_to_list(Position p){

            int row = p.getRowIndex();
            int column = p.getColumnIndex();

            if (is_valid_position(row - 1, column) && my_maze.get_value_of_position(row - 1, column) == 1) {
                walls_list.add(new Position(row - 1, column));
            }

            if (this.is_valid_position(row + 1, column) && my_maze.get_value_of_position(row + 1, column) == 1) {
                walls_list.add(new Position(row + 1, column));
            }

            if (this.is_valid_position(row, column - 1) && my_maze.get_value_of_position(row,column - 1) == 1) {
                walls_list.add(new Position(row, column - 1));
            }

            if (this.is_valid_position(row, column + 1) && my_maze.get_value_of_position(row,column + 1) == 1) {
                walls_list.add(new Position(row, column + 1));
            }
        }

        private boolean is_valid_position(int row,int col){

            if(row < 0){
                return false;
            }
            if (row > number_of_rows - 1){
                return false;
            }
            if(col < 0){
                return false;
            }
            else{
                return (col <= number_of_columns - 1);
            }
        }

}
