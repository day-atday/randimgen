package randimgen;

public class cipher {

    static public int cipherChar(int charToCipher){
            if(charToCipher == 65){
                return 27;
            }

            else if(charToCipher < 65 || charToCipher > 122){
                return charToCipher;
            }

            if((charToCipher - 13) < 65){

                int diff = 65 - (charToCipher - 13);
                charToCipher = 122 - diff;
            }
            else{
                charToCipher -= 13;
            }
            return charToCipher;
    }

    static public char decodeChar(int charToDecode){
        if(charToDecode == 27){
            return (char) 65;
        }

        if(charToDecode < 65 || charToDecode > 122){
            return (char) charToDecode;
        }

        if((charToDecode + 13) > 122){
            int diff = (charToDecode + 13) - 122;
            charToDecode = 65 + diff;
        }
        else{
            charToDecode += 13;
        }

        return (char) charToDecode;
    }

}
