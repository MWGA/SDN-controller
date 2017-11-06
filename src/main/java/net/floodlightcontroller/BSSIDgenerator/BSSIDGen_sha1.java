/**
 * Created by katelynxnay on 4. 11. 2017.
 */

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;


    public class BSSIDGen_sha1 {

        static String mac_addr = "001641342ca6";
        static String bssid_addr;

        public static void main(String[] args) {

            byte[] buffer = mac_addr.getBytes(StandardCharsets.UTF_8);

            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                md.update(buffer);
                byte[] digest = md.digest();
                bssid_addr = byteToHex(digest).substring(0, Math.min(byteToHex(digest).length(), 12));

                //System.out.println("SHA1 fingerprint: " + byteToHex(digest));
                //System.out.println("BSSID address: " + bssid_addr);

                StringBuilder builder = new StringBuilder(bssid_addr);
                builder.setCharAt(1, 'e');
                bssid_addr = builder.toString();

            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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


