/*
 * Java 2 Practical - HaNoi Aptech
 */
package vn.aptech.quanlybanhang.utilities;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 */
public class FileUtils {

    public File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());
            return new File(resource.toURI());
        }

    }
}
