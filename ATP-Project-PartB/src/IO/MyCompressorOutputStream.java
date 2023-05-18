package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;
    public  MyCompressorOutputStream(OutputStream outputStream){
        out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        // DO NOTHING
    }

   // @Override
    public void write(byte[] b){
        ArrayList<Byte> compressed_maze = new ArrayList<>();

        int number_of_rows = get_int_from_indexes(b, 0, 8), number_of_cols = get_int_from_indexes(b, 8, 16);
        int num_of_RowOrCol=0, min = Math.min(number_of_rows, number_of_cols);
        String binary_RowOrCol;
        byte[] bytes;

        if(min != number_of_rows){
            compressed_maze.add((byte)1);
        }
        else
            compressed_maze.add((byte)0);

       byte[] min_in_bytes = convertToByteArray(min);
       if(min_in_bytes == null){
           return;
       }
       for(int i=0;i<2;i++){
           compressed_maze.add(min_in_bytes[i]);
       }

        while (num_of_RowOrCol > min){
            if(min != number_of_rows){
                binary_RowOrCol = col_to_str(b, number_of_rows, num_of_RowOrCol, number_of_cols);
            }
            else {
                binary_RowOrCol = row_to_str(b, number_of_cols, num_of_RowOrCol*number_of_cols);
            }

            num_of_RowOrCol++;

           bytes = new BigInteger(binary_RowOrCol, 2).toByteArray();

            for (byte aByte : bytes) {
                compressed_maze.add((byte)num_of_RowOrCol);
                compressed_maze.add(aByte);
            }
        }

        byte[] res = from_list_to_array(compressed_maze);
        try {
            out.write(res);
            out.flush();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                out.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    private String row_to_str(byte[] b, int number_of_cols, int start_index) {
        int const_gap = 16;
        String row_str = "";
        boolean first_zeros_seq = true;
        for(int i=start_index; i<start_index+number_of_cols; i++){
            if(first_zeros_seq && b[i+const_gap] == 0){}
            else {
                first_zeros_seq = false;
                row_str += b[i + const_gap];
            }

        }
        return row_str;
    }

    private String col_to_str(byte[] b, int number_of_rows, int start_index, int number_of_cols) {
        int const_gap = 16;
        String row_str = "";
        boolean first_zeros_seq = true;
        for(int i=0; i<number_of_rows; i++){
            if(first_zeros_seq && b[i*number_of_cols + const_gap + start_index] == 0){}
            else {
                first_zeros_seq = false;
                row_str += b[i*number_of_cols + const_gap + start_index];
            }
        }
        return row_str;
    }

    private int get_int_from_indexes(byte[] b, int start, int end) {
        byte[] subArray1 = Arrays.copyOfRange(b, start, end);
        BigInteger bigInteger1 = new BigInteger(1, subArray1);
        return bigInteger1.intValue();
    }

    private byte[] from_list_to_array(ArrayList<Byte> compressedMaze) {
        byte[] to_return = new byte[compressedMaze.size()];
        for(int i=0; i<compressedMaze.size(); i++){
            to_return[i] = compressedMaze.get(i);
        }
        return to_return;
    }


    public static byte[] convertToByteArray(int number) {
        if (number >= 0 && number <= 1000) {
            byte[] byteArray = new byte[2];
            byteArray[0] = (byte) ((number >> 8) & 0xFF);
            byteArray[1] = (byte) (number & 0xFF);
            return byteArray;
        } else {
            System.out.println("Number is out of range (0 to 1000).");
            return null;
        }
    }



        public static void main(String[] args) {
            int number = 0; // Example integer less than or equal to 1000
            byte[] byteArray = convertToByteArray(number);

            System.out.println("Number: " + number);
            System.out.print("Byte Array: [ ");
            for (byte b : byteArray) {
                System.out.print((b & 0xFF) + " ");
            }
            System.out.println("]");

        }

}




