/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.hw_w2d2.IoC;

/**
 *
 * @author pronc
 */
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class SaltGenerator {

    private final RandomService randomService;

    @Inject
    public SaltGenerator(@Named("util")RandomService randomService) {
        this.randomService = randomService;
        System.out.println("SaltGenerator: " 
                + randomService.getClass().getName() 
                + " " 
                + randomService.hashCode());
    }

    public String getSalt(int length) {
        char[] chars = new char[length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char)(33 + (int)(randomService.value() * 93));
        }
        return new String(chars);
    }
}