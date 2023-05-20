package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;

    /**
     * Constructs a SimpleDecompressorInputStream object with the specified input stream.
     *
     * @param input The input stream to be decompressed.
     */
    public SimpleDecompressorInputStream(InputStream input){
        this.in = input;
    }

    /**
     * Reads the next byte of data from the input stream.
     * This method is not used in the decompression process and always returns 0.
     *
     * @return Always returns 0.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public int read() throws IOException {
        return 0;
    }

    /**
     * Reads some number of bytes from the input stream and stores them into the specified byte array.
     * Decompresses the data read from the input stream and writes the decompressed data into the specified byte array.
     *
     * @param decompressed The byte array into which the decompressed data is written.
     * @return Always returns 0.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public int read(byte[] decompressed) throws IOException {
        byte[] compressed_list = in.readAllBytes();
        int i=0;
        int decompressed_index = 0;
        while(compressed_list.length > i){
            i++;
            if(compressed_list.length > i){
                for(int j=0; j<compressed_list[i]; j++){
                    decompressed[decompressed_index] = 1;
                    decompressed_index++;
                }
                i += 2;
            }
            if(compressed_list.length > i){
                for(int j=0; j<compressed_list[i]; j++){
                    decompressed[decompressed_index] = 0;
                    decompressed_index++;
                }
                i++;
            }
        }
        return 0;
    }
}
