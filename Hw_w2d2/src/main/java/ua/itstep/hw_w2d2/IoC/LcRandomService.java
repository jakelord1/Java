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
import static java.lang.Math.PI;

@Singleton
public class LcRandomService implements RandomService {
    private double prev = System.nanoTime() / 1e9;

    @Override
    public double value() {
        prev = (prev + PI) * (prev + PI) + PI * (prev + PI) + PI;
        prev = prev - Math.floor(prev);
        return prev;
    }
}
