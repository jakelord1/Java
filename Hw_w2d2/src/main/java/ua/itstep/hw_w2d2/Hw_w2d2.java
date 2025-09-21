/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ua.itstep.hw_w2d2;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ua.itstep.hw_w2d2.IoC.ConfigModule;
import ua.itstep.hw_w2d2.IoC.IoC;

/**
 *
 * @author pronc
 */
public class Hw_w2d2 {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ConfigModule());
        IoC ioc = injector.getInstance(IoC.class);
        ioc.demo();
    }
}
