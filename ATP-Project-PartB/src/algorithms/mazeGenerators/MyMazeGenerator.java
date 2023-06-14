package algorithms.mazeGenerators;

import algorithms.search.*;

import java.util.*;
public class MyMazeGenerator extends AMazeGenerator{
        private ArrayList<Position> walls_list;
        private Maze my_maze;
        private Random randomizer;
        public MyMazeGenerator() {}

        @Override
        public Maze generate(int rows_num, int columns_num) {
            if (check_inputs(rows_num, columns_num)) { // check inputs
               return my_maze;
            }

            number_of_rows = rows_num;
            number_of_columns = columns_num;

            randomizer = new Random();
            walls_list = new ArrayList<>();

            int[][] ones_maze_board = initialize_with_ones(); // fill the maze board with walls
            my_maze = new Maze(ones_maze_board, rows_num, columns_num);
            Position startPosition = my_maze.getStartPosition();
            my_maze.set_value_of_position(0, 0, 0);
            this.add_wall_to_list(startPosition);
            primAlgo(); // run prime algorithm

            // check the validity of the maze (that the maze has a solution)
            SearchableMaze searchableMaze = new SearchableMaze(my_maze);
            BestFirstSearch bestFS = new BestFirstSearch();
            Solution solution = bestFS.solve(searchableMaze);
            ArrayList<AState> solutionPath = solution.getSolutionPath();
            if(solutionPath.size() == 0){ // if we don't have solution regenerate the maze
                generate(rows_num, columns_num);
            }

            return my_maze;
        }

        private void primAlgo(){
            /**
             * implement the prim algorithms, choose random wall to break and then
             * add all the walls that connected to this cell
             *
             */
            Position GoalPosition = my_maze.getGoalPosition();

            while(!walls_list.isEmpty()){ // check if the wall list is empty

                Position cur_position = walls_list.remove(randomizer.nextInt(walls_list.size())); // remove random wall

                if(num_of_neighbors(cur_position) == 1){
                    my_maze.set_value_of_position(cur_position.getRowIndex(), cur_position.getColumnIndex(),0); // break the wall
                    add_wall_to_list(cur_position); // add his neighbors to the wall list

                }
            }

            my_maze.setGoalPosition(GoalPosition);
            //if we didn't get to the goal try to connect the goal to the start path
            if(my_maze.get_value_of_position(number_of_rows -1, number_of_columns - 1) == 1){
                my_maze.set_value_of_position(my_maze.getGoalPosition().getRowIndex(), my_maze.getGoalPosition().getColumnIndex(), 0);
                this.add_wall_to_list(my_maze.getGoalPosition());
                while(!walls_list.isEmpty()){

                    Position cur_position = walls_list.remove(randomizer.nextInt(walls_list.size()));

                    if(num_of_neighbors(cur_position) == 1){
                        if(my_maze.get_value_of_position(cur_position.getRowIndex(), cur_position.getColumnIndex()) == 0){
                            break;
                        }
                        my_maze.set_value_of_position(cur_position.getRowIndex(), cur_position.getColumnIndex(),0);
                        add_wall_to_list(cur_position);
                    }
                }
            }
        }

        private int[][] initialize_with_ones() {
            /**
             * initialize the maze board in ones
             */

            int[][] mat = new int[number_of_rows][number_of_columns];
            for(int i=0; i<number_of_rows; i++){
                for (int j=0; j<number_of_columns; j++){
                    mat[i][j] = 1;
                }
            }
            return mat;
        }


        private int num_of_neighbors(Position p){
            /**
             * check how many neighbors that we already visited there for this position
             */

            int row = p.getRowIndex();
            int column = p.getColumnIndex();
            int num_of_neighbors = 0;

            if (this.is_valid_position(row, column - 1) && (my_maze.get_value_of_position(row,column - 1) == 0)) { // left neighbor
                num_of_neighbors++;
            }
            if (this.is_valid_position(row, column + 1) && (my_maze.get_value_of_position(row,column + 1) == 0)) { // right neighbor
                num_of_neighbors++;
            }
            if (this.is_valid_position(row - 1, column) && (my_maze.get_value_of_position(row - 1, column) == 0)) { // up neighbor
                num_of_neighbors++;
            }
            if (this.is_valid_position(row + 1, column) && (my_maze.get_value_of_position(row + 1, column) == 0)) { // down neighbor
                num_of_neighbors++;
            }
            return num_of_neighbors;
        }

        private void add_wall_to_list(Position p){
            /**
             * add all the neighbors of this position to the wall lists (if they are walls)
             */

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
            /**
             * check if position is inside the maze board
             */

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



