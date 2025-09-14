/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.hw_w2d2;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.sql.SQLException;
//import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import ua.itstep.data.DAO.RandomItemDao;
import ua.itstep.data.DTO.random_item;
/**
 *
 * @author pronc
 */
public class Db {
      private Map<String, String> loadConfig(String iniFileName) throws Exception {
        Map<String, String> config = new HashMap<>();
        try(InputStream inputStream = Objects.requireNonNull(
                this
                    .getClass()
                    .getClassLoader()
                    .getResourceAsStream(iniFileName));
            Scanner scanner = new Scanner(inputStream)
        ) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                String uncommentedPart = parts[0];
                parts = uncommentedPart.split("=", 2);
                if(parts.length != 2) continue;                
                config.put(parts[0].trim(), parts[1].trim());
            }
        } 
        catch (IOException ex) {
            throw new Exception(ex);
        }
        catch (NullPointerException ex) {
            throw new Exception("Resource not found");
        }
        return config;
    }

       private Map<String, String> loadAllConfigs() throws Exception {
        Map<String, String> all = new HashMap<>();
        try {
            var cl = this.getClass().getClassLoader();
            var rootUrl = Objects.requireNonNull(cl.getResource(""));
            File root = new File(rootUrl.toURI());
            File[] iniFiles = root.listFiles((dir, name) -> name.toLowerCase().endsWith(".ini"));

            if (iniFiles == null || iniFiles.length == 0) {
                all.putAll(loadConfig("db.ini"));
                return all;
            }

            java.util.Arrays.sort(iniFiles, java.util.Comparator.comparing(File::getName));

            for (File f : iniFiles) {
                all.putAll(loadConfig(f.getName()));
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return all;
    }
      
    public void demo() {
        Driver mysqlDriver;
        Connection connection;
        String connectionString;
        try {
            Map<String, String> config = loadAllConfigs();
            connectionString = String.format(
                    "%s:%s://%s:%s/%s?user=%s&password=%s&%s",
                    config.get("protocol"),
                    config.get("dbms"),
                    config.get("host"),
                    config.get("port"),
                    config.get("scheme"),
                    config.get("user"),
                    config.get("password"),
                    config.get("params")
            );
            System.out.println(connectionString);
        } catch (Exception ex) {
            System.err.println("Config error: " + ex.getMessage());
            return;
        }
        
        try {
            mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            System.err.println("Start error: " + ex.getMessage());
            return;
        }

        System.out.println("Connection Ok");
        insertDao(connection);
        queries(connection);
        try {
            connection.close();
            DriverManager.deregisterDriver(mysqlDriver);
        } catch (SQLException ex) {
            System.err.println("Finish error: " + ex.getMessage());
        }
        
    }
        private void insertDao(Connection connection) {
        Random random = new Random();
        RandomItemDao rid = new RandomItemDao(connection);
        rid.add(new random_item(
                random.nextInt(),
                random.nextDouble(),
                "Випадковість: " + random.nextFloat()
        ));
    }

private void queries(Connection connection) {
    RandomItemDao rid = new RandomItemDao(connection);
    for (random_item item : rid.getAll()){
        System.out.println(item);
    }
    }
}
