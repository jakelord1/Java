/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.hw_w3.services.config;

/**
 *
 * @author pronc
 */
public interface ConfigService {
    void addFile(String filename);
    String get(String path);
}
