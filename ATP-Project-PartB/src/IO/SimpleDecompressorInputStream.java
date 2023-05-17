package IO;

import java.io.IOException;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {

    InputStream in;

    public SimpleDecompressorInputStream(InputStream input){
        this.in = input;
    }
    @Override
    public int read() throws IOException {
        return 0;
    }
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
