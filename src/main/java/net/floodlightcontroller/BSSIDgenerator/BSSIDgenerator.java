package net.floodlightcontroller.BSSIDgenerator;

/**
 * Created by katelynx on 11. 11. 2017.
 *
 *
 */

import org.projectfloodlight.openflow.types.MacAddress;
import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.projectfloodlight.openflow.util.HexString;

public class BSSIDgenerator {


    public static MacAddress getUniqueBSSID(MacAddress m) {

        //katka style mac address improv
        //String mac_addr = "00:16:41:34:2c:a6";
        //m = MacAddress.of(mac_addr);

        //will be needed later for byte overwrite
        MacAddress tmp_mac;
        String e_mac = "ee:ee:ee:ee:ee:ee";
        tmp_mac = MacAddress.of(e_mac); // ee:ee:ee:ee:ee:ee MAC
        byte[] tmp_byte = tmp_mac.getBytes(); //6 bytes

        //dumb init
        MacAddress BSSID = null;

        //convert from MacAddress to bytes
        byte[] buffer = m.getBytes();

        //create SHA1 fingerprint & convert to byte array & back to MacAddress
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(buffer);
            byte[] digest = Arrays.copyOfRange(md.digest(),0,6); //take the first 6 bytes from the fingerprint

            //overwrite specific bytes in byte array with byte value of 'e' ...hork's wish
            digest[0] = tmp_byte[0];
            digest[1] = tmp_byte[1];

            BSSID = MacAddress.of(digest);

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //control print
        //System.out.println("Povodna MAC je : "+ toStr(m));
        //System.out.println("BSSID MAC je : "+ toStr(BSSID));

        return BSSID;

    }

    //mine & modified
    public static String toStr(MacAddress mac) {
        return HexString.toHexString(mac.getLong(), 6);
    }


    public static void main(String[] args) {
        MacAddress mac_addr = null;
                mac_addr = getUniqueBSSID(mac_addr);
    }

}
