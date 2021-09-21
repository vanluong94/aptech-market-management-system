/*
 * Do an Java tai HaNoi Aptech
 */
package vn.aptech.quanlybanhang.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Van Luong Thanh <c2105lm.tlvan@aptech.vn>
 */
public class Config {
    
    private static Config instance;
    private final Properties prop;

    public static Config getInstance() throws IOException {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
    
    private Config() throws IOException {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            this.prop = new Properties();
            this.prop.load(input);
        }
    }

    public Properties getProp() {
        return prop;
    }
    
    public static String get(String name) {
        return instance.getProp().getProperty(name);
    }
    
}
