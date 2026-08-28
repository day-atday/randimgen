package randimgen;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

//* READ BYTES FROM FILE */
// https://www.delftstack.com/howto/java/java-read-bytes-from-file/

public class test {
    public static void main(String[] args) throws IOException{
        
        Path filepath = Paths.get("FULL_PATHTOFILE");

        
        
        //byte[] byteArray = new byte[(int) pathtofile.length()];
        byte[] byteArray = Files.readAllBytes(filepath);

        //FileInputStream.read(byteArray);
        //FileInputStream.close();

        System.out.print(Arrays.toString(byteArray));
    }    
}
