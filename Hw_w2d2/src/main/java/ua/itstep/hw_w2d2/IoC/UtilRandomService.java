/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.hw_w2d2.IoC;

/**
 *
 * @author pronc
 */
import com.google.inject.Singleton;
import java.util.Random;

@Singleton
public class UtilRandomService implements RandomService {
    private final static Random random = new Random();
    
    @Override
    public double value() {
        return random.nextDouble();
    }
}
