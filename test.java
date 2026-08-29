package randimgen;
import java.io.IOException;
import java.nio.file.Files;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

//* READ BYTES FROM FILE */
// https://www.delftstack.com/howto/java/java-read-bytes-from-file/

// HEX
// https://stackoverflow.com/questions/2817752/how-can-i-convert-a-byte-array-to-hexadecimal-in-java#2817883

// CREATING NEW FILES
// https://www.geeksforgeeks.org/java/java-program-to-create-a-new-file/

// WRITING FILE ARRAY
// https://www.baeldung.com/java-write-byte-array-file

// CONCATENATE BYTE ARRAYS
// https://stackoverflow.com/questions/5513152/easy-way-to-concatenate-two-byte-arrays#9133993

public class test {
    public static void main(String[] args) throws IOException{
        
        //
        File makeFile = new File("testerpicture.bmp");
        makeFile.createNewFile();

        int x_total = 100;
        int y_total = 100;
        Integer color_limit = 255;
        int blue = 0;
        int green = 0;
        int red = 255;

        // HEADER FOR A 100X100 px 24 Bits generated w/ gimp
        byte[] hardcoded_header = {
        (byte) 0x42,
        (byte) 0x4D,
        (byte) 0xBA,
        (byte) 0x75,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x8A,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x7C,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x64,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x64,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x01,
        (byte) 0x00,
        (byte) 0x18,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x30,
        (byte) 0x75,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x23,
        (byte) 0x2E,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x23,
        (byte) 0x2E,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0xFF,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0xFF,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0xFF,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x42,
        (byte) 0x47,
        (byte) 0x52,
        (byte) 0x73,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x02,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0x00
        };
        
        
        ByteArrayOutputStream outputData = new ByteArrayOutputStream();

        outputData.write(hardcoded_header);

        byte[] colorData = new byte[x_total * y_total * 3];

        for(int y = 0; y < y_total; y++){
            ByteArrayOutputStream row = new ByteArrayOutputStream();

            // VERTICAL
            for(int x = 0 ; x < x_total ; x++){
                //HORIZONTAL
                row.write(blue);
                row.write(green);
                row.write(red);
            }

            // ADD THE DATA
            outputData.write(row.toByteArray());
        }
        
        byte[] outDataToFile = outputData.toByteArray();

        // Get index
        //int ind_y = 0;
        //int ind_x = 0;

        // HEIGHT OF THE WANTED INDEX * TOTAL WIDTH / IT REACHES THE NECESSARY HEIGHT
        // LINE 3 == 3 * 1280 IT GOES 3 TIMES THROUGH 1280 PIXELS OR THE FULL WIDTH OF THE PICTURE 
        // IT ADDS INDEX X TO REACH THE SELECTED WIDTH == 3 * 1280 + 5 IF PIXEL 5
        // MULTIPLIED BY 3 BECAUSE THE PIXELS HAVE 3 BYTES = B G R

        //int index = (ind_y * x_total + ind_x) *3;

        // WRITING
        try(FileOutputStream outputStream = new FileOutputStream(makeFile)){
            outputStream.write(outDataToFile);
        }
        
        
        
        

        //read();
        System.out.println(Arrays.toString(outDataToFile));

    }
    
    public static void read() throws IOException{

        Path filepath = Paths.get("testerpicture.bmp");
        // READING
        byte[] byteArray = Files.readAllBytes(filepath);


        // READING


        // THE HEADER ENDS AT BYTE 138
        for(int i = 0; i < Math.min(50000, byteArray.length); i++){
             System.out.printf("         0x%02X%s%n",
                byteArray[i] & 0xFF,
                i < 137 ? "," : "");
                if (i % 16 == 0) {
                    System.out.printf("%n%04X: ", i);
                }
                System.out.printf("%02X ", byteArray[i] & 0xFF);
    
    }
    }
}