package main;

import classes.Convocatoria;
import controller.Controller;
import controller.DataAcessibleFileImplementation;
import exceptions.MyException;
import factories.DataAcessibleFactory;

public class Main {
    public static void main(String[] args) throws MyException{
        new Controller(DataAcessibleFactory.getDataAccessible()).run();
    }   
    
    }
