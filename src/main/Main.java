package main;

import controller.Controller;
import factories.DataAcessibleFactory;

public class Main {
    public static void main(String[] args) {

        Controller controller = new Controller(DataAcessibleFactory.getConvocatorias(), DataAcessibleFactory.getUdEnunciados());
        controller.run();
        
    }   
    
    }
