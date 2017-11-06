import java.util.Formatter;

/**
 * Created by katelynxnay on 5. 11. 2017.
 */

// source : https://en.wikipedia.org/wiki/Pearson_hashing

public class BSSIDGen_pearson {

    public static void main(String[] args) {

        String mac_addr = "001641342ca6";
        String bssid_addr; //final BSSID

        byte[] mac_addr_byte = hex_to_byte(mac_addr);

        int i;
        int j;

        byte h;
        byte[] hash = new byte[6];
        int T[] = new int[]{
                   // 0-255 shuffled in any (random) order suffices
                    98,  6, 85,150, 36, 23,112,164,135,207,169,  5, 26, 64,165,219, //  1
                    61, 20, 68, 89,130, 63, 52,102, 24,229,132,245, 80,216,195,115, //  2
                    90,168,156,203,177,120,  2,190,188,  7,100,185,174,243,162, 10, //  3
                   237, 18,253,225,  8,208,172,244,255,126,101, 79,145,235,228,121, //  4
                   123,251, 67,250,161,  0,107, 97,241,111,181, 82,249, 33, 69, 55, //  5
                    59,153, 29,  9,213,167, 84, 93, 30, 46, 94, 75,151,114, 73,222, //  6
                   197, 96,210, 45, 16,227,248,202, 51,152,252,125, 81,206,215,186, //  7
                    39,158,178,187,131,136,  1, 49, 50, 17,141, 91, 47,129, 60, 99, //  8
                   154, 35, 86,171,105, 34, 38,200,147, 58, 77,118,173,246, 76,254, //  9
                   133,232,196,144,198,124, 53,  4,108, 74,223,234,134,230,157,139, // 10
                   189,205,199,128,176, 19,211,236,127,192,231, 70,233, 88,146, 44, // 11
                   183,201, 22, 83, 13,214,116,109,159, 32, 95,226,140,220, 57, 12, // 12
                   221, 31,209,182,143, 92,149,184,148, 62,113, 65, 37, 27,106,166, // 13
                     3, 14,204, 72, 21, 41, 56, 66, 28,193, 40,217, 25, 54,179,117, // 14
                   238, 87,240,155,180,170,242,212,191,163, 78,218,137,194,175,110, // 15
                    43,119,224, 71,122,142, 42,160,104, 48,247,103, 15, 11,138,239  // 16
                   };


        for(j = 0; j<6 ; j++) {
            h = (byte)T[((mac_addr_byte[0] & 0xFF) + j) % 256];
            for(i = 1; i< mac_addr_byte.length; i++) {
                h = (byte)T[(h & 0xFF) ^ (mac_addr_byte[i] & 0xFF)];
            }

         hash[j] = h;
        }

        //System.out.println("Pearson fingerprint: " + byteToHex(hash));

        StringBuilder builder = new StringBuilder(byteToHex(hash));
        builder.setCharAt(1, 'e');
        bssid_addr = builder.toString();

    }

    public static byte[] hex_to_byte(String s) {
        int len = s.length();
        byte[] result = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            result[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return result;
    }

    static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
