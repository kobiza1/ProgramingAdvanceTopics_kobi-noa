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
        // get the number of rows and cols and add it to the decompressed list
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

        // get the compressed number as string and convert it to biginteger
        byte[] RowOrCol = Arrays.copyOfRange(compressed_list, 8, compressed_list.length);
        BigInteger bigInt =  new BigInteger(1, RowOrCol);
        String BigIntegerString = bigInt.toString(2);
        BigIntegerString = add_zeros_to_str(BigIntegerString, Col_number*Row_number-BigIntegerString.length());
        add_row_to_list(BigIntegerString, decompressed_list);

        // add the values to the decompressed list
        for(int i=0; i<decompressed_list.size(); i++){
            decompressed[i] = decompressed_list.get(i);
        }

        return 0;
    }

    // add the whole number to the list
    private void add_row_to_list(String bigIntegerString, ArrayList<Byte> decompressedList) {
        char[] chars = bigIntegerString.toCharArray();
        int temp;
        for(char bit : chars){
            temp = bit - 48;
            decompressedList.add((byte)temp);
        }
    }

    // add zeros to the string as needed
    private String add_zeros_to_str(String our_String, int zeros_to_add) {
        String to_return = "";
        for(int i=0; i<zeros_to_add; i++){
            to_return += "0";
        }
        to_return += our_String;
        return to_return;
    }

    // get 2 indexes and return the int value the range represent
    private int get_int_from_indexes(byte[] b, int start, int end) {
        byte[] subArray1 = Arrays.copyOfRange(b, start, end);
        BigInteger bigInteger1 = new BigInteger(1, subArray1);
        return bigInteger1.intValue();
    }

    // convert from int to byte[]
    private static byte[] convertToByteArray(int value){
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(value);
        byte[] bytes = buffer.array();
        return bytes;
    }

}
