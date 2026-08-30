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
        public boolean isRandomNoise;
        public boolean isBigLines;
        public boolean isMediumLines;
        public boolean isSmallLines;
        public boolean isCheckerboard;
        public boolean isReverseCheckOrDiag;
        public boolean isRandomDiag;
    }


    public static void getOutDataToFile() throws IOException {
        
        File makeFile = new File("testerpicture.bmp");
        
        int x_total = 1280;
        int y_total = 720;
        
        ByteArrayOutputStream outputData = new ByteArrayOutputStream();

        byte[] hardcoded_header = getHeader();
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
        
        Random randomConf = new Random();


        genConf configurationB = new genConf();
        //configurationB.isCheckerboard = true;

        genConf configurationG = new genConf();
        //configurationG.isReverseCheckOrDiag = true;
        
        genConf configurationR = new genConf();
        //configurationR.isRandomDiag = true;

        configurationB.isCheckerboard = randomConf.nextBoolean();
        configurationB.isBigLines = randomConf.nextBoolean();
        configurationB.isMediumLines = randomConf.nextBoolean();
        configurationB.isSmallLines = randomConf.nextBoolean();

        configurationG.isCheckerboard = randomConf.nextBoolean();
        configurationG.isBigLines = randomConf.nextBoolean();
        configurationG.isMediumLines = randomConf.nextBoolean();
        configurationG.isSmallLines = randomConf.nextBoolean();

        configurationR.isCheckerboard = randomConf.nextBoolean();
        configurationR.isBigLines = randomConf.nextBoolean();
        configurationR.isMediumLines = randomConf.nextBoolean();
        configurationR.isSmallLines = randomConf.nextBoolean();        
        
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
                green = set_color(green, x, y, x_total, y_total, configurationG);
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

        int randomC = random.nextInt(0,color_limit);
        int randomX = random.nextInt(0,x_total);
        int randomY = random.nextInt(0,y_total);


        if(configuration.isRandomNoise){
            return randomC;
        }

        else if(configuration.isBigLines){
            if(x == randomX){
                color = 0;
            }
            if(x > (x_total / 2)){
                color += 1;
            }
        }
        else if(configuration.isMediumLines){
            if(x == randomX){
                color = 255;
            }
            if(x > (x_total / 2)){
                color -= 1;
            }
        }

        else if(configuration.isSmallLines){

            if(y == randomY){
                color = 255;
            }
            else{
                color -= 1;
            }
        }

        else if(configuration.isCheckerboard || configuration.isReverseCheckOrDiag || configuration.isRandomDiag){
            int result = 0;
            int size = 20;

            if(configuration.isReverseCheckOrDiag){
                result = 1;
            }
            if(configuration.isRandomDiag){
                size = random.nextInt(1,128);
            }

            if( (x / size + y / size) % 2 == result ){
                color = 255;
            }
            else{
                color = 0;
            }
        }


        return Math.max(0, Math.min(color, color_limit));
    }

    private static byte[] getHeader(){
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
        
        return hardcoded_header;
    }
}
