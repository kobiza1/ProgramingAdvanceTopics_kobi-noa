package IO;

import java.io.IOException;
import java.io.OptionalDataException;
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

    @Override
    public void write(byte[] b){
        ArrayList<Byte> compressed_maze = new ArrayList<>();
        // get the number of rows and columns
        int number_of_rows = get_int_from_indexes(b, 0, 4), number_of_cols = get_int_from_indexes(b, 4, 8);
        String binary_Row;
        byte[] bytes;

        // convert the rows and cols values into byte array and put it into the compressed list
       byte[] rows_in_bytes = convertToByteArray(number_of_rows);
       byte[] cols_in_bytes = convertToByteArray(number_of_cols);

       for(int i=0;i<rows_in_bytes.length;i++){
           compressed_maze.add(rows_in_bytes[i]);
       }
       for(int i=0;i<cols_in_bytes.length;i++){
           compressed_maze.add(cols_in_bytes[i]);
       }
         // get string that represent all the map
        binary_Row = get_all_map_as_string(b);

        bytes = new BigInteger(binary_Row, 2).toByteArray();
        // add the byte to the array
        for (byte aByte : bytes) {
            compressed_maze.add(aByte);
        }
        // convert into byte array
        byte[] res = from_list_to_array(compressed_maze);
        // write into the output stream
        try {
            out.write(res);
            out.flush();
            out.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


    private int get_int_from_indexes(byte[] b, int start, int end) {
        byte[] subArray1 = Arrays.copyOfRange(b, start, end);
        BigInteger bigInteger1 = new BigInteger(1, subArray1);
        return bigInteger1.intValue();
    }

    // copy the list into array
    private byte[] from_list_to_array(ArrayList<Byte> compressedMaze) {
        byte[] to_return = new byte[compressedMaze.size()];
        for(int i=0; i<compressedMaze.size(); i++){
            to_return[i] = compressedMaze.get(i);
        }
        return to_return;
    }

    // get int and convert it into array
    public byte[] convertToByteArray(int number) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(number);
        byte[] bytes = buffer.array();
        return bytes;
    }

    // convert the bytrs array into 1 string of 1's and 0's
    private String get_all_map_as_string(byte[] b) {
        int const_gap = 8;
        String row_str = "";
        for(int i=8; i<b.length; i++){
            row_str += b[i];
        }
        return row_str;
    }
}




