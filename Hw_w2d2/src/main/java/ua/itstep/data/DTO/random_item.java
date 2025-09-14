/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.data.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 *
 * @author pronc
 */
public class random_item {
    private String id;
    private int intVal;
    private double floatVal;
    private String strVal;

    public random_item() {
    }

    
    public random_item(int intVal, double floatVal, String strVal) {
        this.id = UUID.randomUUID().toString();
        this.intVal = intVal;
        this.floatVal = floatVal;
        this.strVal = strVal;
    }
    
    public static random_item fromResultSet(ResultSet rs) throws SQLException {
        random_item randomItem = new random_item();
        randomItem.setId(rs.getString("id"));
        randomItem.setIntVal(rs.getInt("int_val"));
        randomItem.setFloatVal(rs.getDouble("float_val"));
        randomItem.setStrVal(rs.getString("str_val"));
        return randomItem;
    }

    @Override
    public String toString() {
        return String.format(
                "%s %d %f %s",
                this.getId(),
                this.getIntVal(),
                this.getFloatVal(),
                this.getStrVal());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIntVal() {
        return intVal;
    }

    public void setIntVal(int intVal) {
        this.intVal = intVal;
    }

    public double getFloatVal() {
        return floatVal;
    }

    public void setFloatVal(double floatVal) {
        this.floatVal = floatVal;
    }

    public String getStrVal() {
        return strVal;
    }

    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }

}
