package org.APIAutomationFramework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadKey {

    public static  String readkeyData(String key){
        Properties properties = new Properties();

        try {
            FileInputStream file = new FileInputStream("src/test/resources/data.properties");
            try {
                properties.load(file);
            }catch (IOException e){
                throw new RuntimeException(e.getMessage());
            }
        }catch (FileNotFoundException e){
            throw  new RuntimeException(e.getMessage());
        }

        return properties.getProperty(key);
    }
}
