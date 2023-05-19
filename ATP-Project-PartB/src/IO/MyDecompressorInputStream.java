package IO;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class MyDecompressorInputStream extends InputStream {

    InputStream in;

    public MyDecompressorInputStream(InputStream input){
        this.in = input;
    }
    @Override
    public int read() throws IOException {
        return 0;
    }

    public int read(byte[] decompressed) throws IOException {
        byte[] compressed_list = in.readAllBytes();
        ArrayList<Byte> decompressed_list = new ArrayList<>();
        int Row_number = get_int_from_indexes(compressed_list, 0, 1);
        int Col_number = get_int_from_indexes(compressed_list, 1, 8);
        int index = 3;
        for(int i=0; i<Row_number; i++){
            byte[] RowOrCol = find_Row_byteArray(compressed_list, index);
            index += RowOrCol.length*2;
            BigInteger bigInt =  new BigInteger(1, RowOrCol);
            String BigIntegerString = bigInt.toString(2);
            BigIntegerString = add_zeros_to_str(BigIntegerString, Col_number-BigIntegerString.length());
            add_row_to_list(BigIntegerString, decompressed_list);
        }
        return 0;
    }

    private void add_row_to_list(String bigIntegerString, ArrayList<Byte> decompressedList) {
        char[] chars = bigIntegerString.toCharArray();
        for(char bit : chars)
            decompressedList.add((byte)bit);
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
        while (compressedList[start_index + i] == RowOrCol){
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
}
