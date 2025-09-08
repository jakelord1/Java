/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.hw_w2d1;

/**
 *
 * @author pronc
 */
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class FileIo {

   private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public void demo() {
        File corrent = new File("./");
        
        Map<String,String> config = new HashMap();
        try (InputStream inputStream = Objects.requireNonNull(this
                .getClass()
                .getClassLoader()
                .getResourceAsStream("db.ini")); 
            Scanner scanner = new Scanner(inputStream)) {
           int i = 1;
            while (scanner.hasNextLine()) {
                String raw = scanner.nextLine();
                String noComment = raw.split("[#;]", 2)[0].trim();
                if (noComment.isEmpty()) continue;
                int eq = noComment.indexOf('=');
                if (eq < 0) continue;
                String key = noComment.substring(0, eq).trim();
                String value = noComment.substring(eq + 1).trim();
                if (!key.isEmpty()) config.put(key, value);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        for (Map.Entry<String, String> entry : config.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
        }
    }
}