/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.utilities;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class Md5 {

    private static MessageDigest md;
    private static final String ALGORITHM = "MD5";
    private static final String PROVIDER = "SUN";
    private static final String CHARSET = "UTF-8";
    private static final String PRESALT = "<PWD>";
    private static final String SUFSALT = "</PWD>";

    public static String encode(String password) {
        try {
            md = MessageDigest.getInstance(ALGORITHM, PROVIDER);

            if (md != null) {
                md.reset();
                return processPassword(password);
            }
        } catch (NoSuchProviderException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String processPassword(String password) throws IOException {
        String s = PRESALT + password + SUFSALT;
        md.update(s.getBytes(CHARSET));
        byte[] byteData = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
