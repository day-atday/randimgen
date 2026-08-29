package randimgen;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;


public class create_File {

    public static class genConf {
        public boolean isRandom;
        public boolean isXGradient;
        public boolean isStatic;
    }
    
    public static void getOutDataToFile() throws IOException {
        
        File makeFile = new File("testerpicture.bmp");
        
        int x_total = 1280;
        int y_total = 720;
        
        ByteArrayOutputStream outputData = new ByteArrayOutputStream();

        // HEADER
        // TO CHANGE RESOLUTION SEARCH FOR COMMENTED BYTES
        // THE BYTES CHANGED NEED TO BE IN LITTLE ENDIAN
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
        (byte) 0x00, // W == 1280 = Little Endian == 0x500
        (byte) 0x05,
        (byte) 0x00,
        (byte) 0x00,
        (byte) 0xD0, // Y == 720 == 0xD002
        (byte) 0x02,
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
        outputData.write(hardcoded_header);

        byte[] outDataToFile = makePixelData(y_total, x_total, outputData);
        
        write_File(outDataToFile, makeFile);

        // Get index
        //int ind_y = 0;
        //int ind_x = 0;

        // HEIGHT OF THE WANTED INDEX * TOTAL WIDTH / IT REACHES THE NECESSARY HEIGHT
        // LINE 3 == 3 * 1280 IT GOES 3 TIMES THROUGH 1280 PIXELS OR THE FULL WIDTH OF THE PICTURE 
        // IT ADDS INDEX X TO REACH THE SELECTED WIDTH == 3 * 1280 + 5 IF PIXEL 5
        // MULTIPLIED BY 3 BECAUSE THE PIXELS HAVE 3 BYTES = B G R

        //int index = (ind_y * x_total + ind_x) *3;        
    }

    private static byte[] makePixelData(int y_total, int x_total, ByteArrayOutputStream outputData) throws IOException{

        genConf configurationB = new genConf();
        configurationB.isRandom = true;

        genConf configurationG = new genConf();
        configurationG.isXGradient = true;
        
        genConf configurationR = new genConf();
        configurationR.isXGradient = true;
        
        int blue = 0;
        int green = 0;
        int red = 0;

        // PIXEL DATA
        // VERTICAL
        for(int y = 0; y < y_total; y++){
                
            ByteArrayOutputStream row = new ByteArrayOutputStream();

            //HORIZONTAL
            for(int x = 0 ; x < x_total ; x++){
                blue = set_color(blue, x, y, x_total, y_total, configurationB);;
                green = 0;
                red = set_color(red, x, y, x_total, y_total, configurationR);

                row.write(blue);
                row.write(green);
                row.write(red);
            }

            // ADD THE DATA
            outputData.write(row.toByteArray());
        }
        
        byte[] outDataToFile = outputData.toByteArray();
        return outDataToFile;
    }

    private static void read() throws IOException{

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

    private static void write_File(byte[] outDataToFile, File makeFile) throws IOException{
        // WRITING
        try(FileOutputStream outputStream = new FileOutputStream(makeFile)){
            outputStream.write(outDataToFile);
        }
    }

    private static int set_color(int color, int x, int y ,  int x_total, int y_total, genConf configuration){
        int color_limit = 255;

        Random random = new Random();

        if(configuration.isRandom){
            return random.nextInt(0,color_limit);
        }

        else if(configuration.isXGradient){
            if(x < 1280) {
                color += 1;
            }
            if(color == 255){
                color = 0;
            }
        }


        return Math.max(0, Math.min(color, color_limit));
    }
}
