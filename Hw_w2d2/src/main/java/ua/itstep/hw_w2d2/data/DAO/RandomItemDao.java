/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.hw_w2d2.data.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ua.itstep.hw_w2d2.data.DTO.random_item;

/**
 *
 * @author pronc
 */
public class RandomItemDao {
    private final Connection connection;

    public RandomItemDao(Connection connection) {
        this.connection = connection;
    }
    
    public void add(random_item randomItem) {
        String sql = "INSERT INTO `random_items`(`id`, `int_val`, `float_val`, `str_val`)"
                + "VALUES(?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, randomItem.getId());         // !! JDBC індексує від 1
            statement.setInt(2, randomItem.getIntVal());        
            statement.setDouble(3, randomItem.getFloatVal());  
            statement.setString(4, randomItem.getStrVal());
            statement.executeUpdate();
            // System.out.println("INSERT OK");
        }
        catch(SQLException ex) {
            System.err.println("RandomItemDao::add " + ex.getMessage());
        }
    }
    
    public List<random_item> getAll() {
        List<random_item> res = new ArrayList<>();
        String sql = "SELECT r.`id`, r.`int_val`, r.`float_val`, r.`str_val` "
                + "FROM `random_items` r";
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                res.add(random_item.fromResultSet(rs));
            }
        }
        catch(SQLException ex) {
            System.err.println("RandomItemDao::getAll " + ex.getMessage());
        }
        return res;
    }
}
