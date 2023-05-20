package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SimpleCompressorOutputStream extends OutputStream {

    private OutputStream out;
    public  SimpleCompressorOutputStream(OutputStream outputStream){
        out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        // DO NOTHING
    }

    @Override
    public void write(byte[] b) {
        ArrayList<Byte> compressed_maze = new ArrayList<>();
        int counter = 0;
        int i=16;
        while(i < b.length){
            while(b.length > i && b[i] == 1){
                counter++;
                i++;
            }
            add_int_to_bytes_list(counter, compressed_maze, 1);
            counter = 0;
            while (b.length > i && b[i] == 0){
                counter++;
                i++;
            }
            add_int_to_bytes_list(counter, compressed_maze, 0);
            counter = 0;
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



    private byte[] from_list_to_array(ArrayList<Byte> compressedMaze) {
        byte[] to_return = new byte[compressedMaze.size()];
        for(int i=0; i<compressedMaze.size(); i++){
            to_return[i] = compressedMaze.get(i);
        }
        return to_return;
    }

    private void add_int_to_bytes_list(int counter, ArrayList<Byte> compressedMaze, int num){
        if(counter > 127){
            int dividend  = counter/127;
            int reminder = counter%127;
            if(reminder > 0){
                dividend++;
            }
            for(int j=0; j<dividend-1; j++){
                compressedMaze.add((byte)num);
                compressedMaze.add((byte)127);
                if(num == 1){
                    compressedMaze.add((byte)0);

                }
                else{
                    compressedMaze.add((byte)1);
                }
                compressedMaze.add((byte)0);
            }
            compressedMaze.add((byte)num);
            if(reminder > 0) {
                compressedMaze.add((byte)reminder);
            }
            else{
                compressedMaze.add(((byte)127));
            }
        }
        else{
            compressedMaze.add((byte)num);
            compressedMaze.add((byte)counter);
        }
    }
}
