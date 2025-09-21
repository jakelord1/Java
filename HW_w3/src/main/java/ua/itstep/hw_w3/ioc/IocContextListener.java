/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.hw_w3.ioc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 *
 * @author pronc
 */
 public class IocContextListener extends GuiceServletContextListener { 
    @Override
    protected Injector getInjector() {
        System.out.println("IocContextListener::getInjector");
        return Guice.createInjector(
                new ServicesConfig(),
                new ServletsConfig()
        );
    }
}
