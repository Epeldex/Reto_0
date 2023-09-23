package main;

import controller.Controller;
import factories.DataAcessibleFactory;

public class Main {
    public static void main(String[] args) {
        new Controller(DataAcessibleFactory.getDataAccessible()).run();
    }   
    
    }
