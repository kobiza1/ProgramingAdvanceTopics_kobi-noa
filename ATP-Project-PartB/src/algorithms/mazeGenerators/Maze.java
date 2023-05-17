
package algorithms.mazeGenerators;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Maze {

    int[][] maze_board;
    int rows_number;
    int columns_number;

    Position StartPosition;
    Position GoalPosition;

    public Maze(int [][] maze_board, int rows_number, int columns_number){
        /**
         * create maze with rows_number rows and columns_number columns
         */
        this.maze_board = maze_board;
        this.rows_number = rows_number;
        this.columns_number = columns_number;
        this.GoalPosition = new Position(rows_number-1,columns_number-1); // always the end
        this.StartPosition = new Position(0,0); // always the start
    }
    public Maze(byte[] bytes_maze){
        set_rows_number(bytes_maze);
        set_col_number(bytes_maze);
        this.maze_board = new int[rows_number][columns_number];
        set_maze_content(bytes_maze);
        this.StartPosition = new Position(0,0);
        this.GoalPosition = new Position(this.rows_number - 1, this.columns_number - 1);
    }

    private void set_maze_content(byte[] bytesMaze) {
        int counter = 16;
        for(int i=0; i<this.rows_number; i++){
            for(int j=0; j<this.columns_number; j++){
                this.maze_board[i][j] = bytesMaze[counter];
                counter++;
            }
        }
    }

    private void set_col_number(byte[] bytesMaze) {
        byte[] subArray = Arrays.copyOfRange(bytesMaze, 8, 16);
        BigInteger bigInteger = new BigInteger(1, subArray);
        this.columns_number = bigInteger.intValue();
    }

    private void set_rows_number(byte[] bytesMaze) {
        byte[] subArray = Arrays.copyOfRange(bytesMaze, 0, 8);
        BigInteger bigInteger = new BigInteger(1, subArray);
        this.rows_number = bigInteger.intValue();
    }

    public int[][] getMaze_board(){
        return maze_board;
    }
    public Position getStartPosition(){
        return StartPosition;
    }
    public Position getGoalPosition(){
        return GoalPosition;
    }

    public int getRows_number() {
        return rows_number;
    }

    public int getColumns_number() {
        return columns_number;
    }

    public void print(){
        for(int i=0; i<rows_number; i++){
            System.out.print("[");
            for (int j=0; j<columns_number; j++){
                if(getStartPosition().getRowIndex()==i && getStartPosition().getColumnIndex()==j)
                    System.out.print("S");
                else if(getGoalPosition().getRowIndex()==i && getGoalPosition().getColumnIndex()==j)
                    System.out.print("E");
                else
                    System.out.print(maze_board[i][j]);

                if(j!=columns_number-1)
                    System.out.print(", ");
            }
            System.out.println("]");
        }
    }

    public void set_value_of_position(int row, int col, int val){
        if(val < 2 && val >=0 && row >=0 && row < maze_board.length && col >=0 && col < maze_board[0].length) // check the validity of the position and the value
            this.maze_board[row][col] = val;
    }

    public void setStartPosition(Position startPosition) {
        StartPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        GoalPosition = goalPosition;
    }

    public int get_value_of_position(int row, int col){
        if(row>=0 && row<this.maze_board.length && col>=0 && col < this.maze_board[0].length){ // check the validity of the position
            return maze_board[row][col];
        }else{
            return -1;
        }
    }

    public byte[] toByteArray(){
        byte[] array = new byte[(rows_number*columns_number)+16];
        
        byte[] rows = convertToByteArray(rows_number);
        byte[] columns = convertToByteArray(columns_number);

        System.arraycopy(rows, 0, array, 0, 8);
        System.arraycopy(columns, 0, array, 8, 8);

        int index = 16;
        for (int i=0; i<rows_number; i++){
            for(int j=0; j<columns_number; j++){
                array[index] = (byte) maze_board[i][j];
                index++;
            }
        }
        return array;
    }

    public static byte[] convertToByteArray(int value){
        BigInteger bigInteger = BigInteger.valueOf(value);
        byte[] valueInBytes = bigInteger.toByteArray();
        byte[] returnByte = new byte[8];

        int paddingLength = Math.max(0, 8 - valueInBytes.length);
        System.arraycopy(valueInBytes, 0, returnByte, paddingLength, Math.min(8, valueInBytes.length));
        return returnByte;
    }

    public static void main(String[] args) {
        int[][] array = new int[1000][950];

// fill the array with random 0's and 1's
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 950; j++) {
                array[i][j] = (int) (Math.random() * 2);
            }
        }
        Maze m = new Maze(array, 1000, 950);
        byte[] b = m.toByteArray();
        System.out.println(b);
        Maze m1 = new Maze(b);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 950; j++) {
                if (!(m.maze_board[i][j] == m1.maze_board[i][j])) {
                    System.out.println("fuck");
                }
            }
        }
        System.out.println("all_good");
    }


}
