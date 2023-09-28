package main;

import controller.Controller;
import exceptions.MyException;
import factories.DataAcessibleFactory;
import factories.ViewableFactory;

public class Main {
    public static void main(String[] args) throws MyException{
        new Controller(DataAcessibleFactory.getDataAccessible(), ViewableFactory.getViewable()).run();
    }   
    
    }
