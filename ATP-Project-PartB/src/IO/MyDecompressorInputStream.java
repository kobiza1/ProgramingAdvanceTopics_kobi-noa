package IO;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class MyDecompressorInputStream extends InputStream {

    InputStream in;
    byte[] compressed_list;

    public MyDecompressorInputStream(InputStream input){
        this.in = input;
    }
    @Override
    public int read() throws IOException {
        return 0;
    }

    public int read(byte[] decompressed) throws IOException {
        compressed_list = in.readAllBytes();
        ArrayList<Byte> decompressed_list = new ArrayList<>();
        int Row_number = get_int_from_indexes(compressed_list, 0, 4);
        int Col_number = get_int_from_indexes(compressed_list, 4, 8);
        byte[] rows_number_in_bytes = convertToByteArray(Row_number);
        byte[] cols_number_in_bytes = convertToByteArray(Col_number);
        for(int i=0;i<rows_number_in_bytes.length; i++){
            decompressed_list.add(rows_number_in_bytes[i]);
        }
        for(int i=0;i<cols_number_in_bytes.length; i++){
            decompressed_list.add(cols_number_in_bytes[i]);
        }
        int index = 8;
        for(int i=0; i<Row_number; i++){
            byte[] RowOrCol = find_Row_byteArray(compressed_list, index);
            index += RowOrCol.length*2;
            BigInteger bigInt =  new BigInteger(1, RowOrCol);
            String BigIntegerString = bigInt.toString(2);
            BigIntegerString = add_zeros_to_str(BigIntegerString, Col_number-BigIntegerString.length());
            add_row_to_list(BigIntegerString, decompressed_list);
        }
        for(int i=0; i<decompressed_list.size(); i++){
            decompressed[i] = decompressed_list.get(i);
        }

        /*try {
            in.read(decompressed);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        finally {
            try {
                in.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }*/
        return 0;
    }

    private void add_row_to_list(String bigIntegerString, ArrayList<Byte> decompressedList) {
        char[] chars = bigIntegerString.toCharArray();
        int temp;
        for(char bit : chars){
            temp = bit - 48;
            decompressedList.add((byte)temp);
        }
    }

    private String add_zeros_to_str(String our_String, int zeros_to_add) {
        String to_return = "";
        for(int i=0; i<zeros_to_add; i++){
            to_return += "0";
        }
        to_return += our_String;
        return to_return;
    }

    private int get_int_from_indexes(byte[] b, int start, int end) {
        byte[] subArray1 = Arrays.copyOfRange(b, start, end);
        BigInteger bigInteger1 = new BigInteger(1, subArray1);
        return bigInteger1.intValue();
    }
    private byte[] find_Row_byteArray(byte[] compressedList, int start_index) {
        ArrayList<Byte> res = new ArrayList<>();
        int RowOrCol = compressedList[start_index];
        int i = 0;
        while (compressedList.length > start_index + i && compressedList[start_index + i] == RowOrCol){
            res.add(compressedList[start_index + i + 1]);
            i+=2;
        }
        return from_list_to_array(res);
    }

    private byte[] from_list_to_array(ArrayList<Byte> compressedMaze) {
        byte[] to_return = new byte[compressedMaze.size()];
        for(int i=0; i<compressedMaze.size(); i++){
            to_return[i] = compressedMaze.get(i);
        }
        return to_return;
    }

    public static int convertToInt(byte[] byteArray) {
        if (byteArray.length != 2) {
            throw new IllegalArgumentException("Byte array length must be 2.");
        }

        int number = ((byteArray[0] & 0xFF) << 8) | (byteArray[1] & 0xFF);
        return number;
    }
    private static byte[] convertToByteArray(int value){
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(value);
        byte[] bytes = buffer.array();
        return bytes;
    }
}
