package main;

import java.util.Iterator;
import java.util.Set;

import classes.Convocatoria;
import controller.Controller;
import controller.DataAcessibleDBImplementation;
import controller.DataAcessibleFileImplementation;
import factories.DataAcessibleFactory;

public class Main {
    public static void main(String[] args) {

        Controller controller = new Controller(DataAcessibleFactory.getConvocatorias(), DataAcessibleFactory.getUdEnunciados());
        

        DataAcessibleFileImplementation dao = new DataAcessibleFileImplementation();

        Convocatoria con;

        Set<Convocatoria> set = dao.getConvocatoria(1);
        Iterator<Convocatoria> it = set.iterator();

        while (it.hasNext()){
            con = it.next();
            System.out.println(con.getId());

        }
        
    }   
    
    }
