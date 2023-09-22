package controller;

import interfaces.DataAccessible;
import view.Console;

public class Controller {

    private static Console view = new Console();
    private static DataAccessible dao1;
    private static DataAccessible dao2;

    public Controller(DataAccessible Dao1, DataAccessible Dao2){
        super();
        dao1 = Dao1;
        dao2 = Dao2;
    }

    public void run (){

    }
    
    


    

    
}
