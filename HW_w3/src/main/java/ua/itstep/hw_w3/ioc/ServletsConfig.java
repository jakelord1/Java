/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ua.itstep.hw_w3.ioc;

/**
 *
 * @author pronc
 */
import com.google.inject.servlet.ServletModule;
import ua.itstep.hw_w3.servlets.HomeServlet;
import ua.itstep.hw_w3.servlets.UserServlet;


public class ServletsConfig extends ServletModule {

    @Override
    protected void configureServlets() {
        serve("/").with(HomeServlet.class);
        serve("/user").with(UserServlet.class);
    }
}
