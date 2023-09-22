package controller;

import java.sql.Connection;
import java.util.Map;
import java.util.Set;

import classes.Convocatoria;
import classes.Enunciado;
import classes.UnidadDidactica;
import exceptions.MyException;
import interfaces.DataAccessible;
import view.Console;

public class Controller {

    private static Console view = new Console();
    private static DataAccessible dao1;
    private static DataAccessible dao2;
    private static Map<Integer, Enunciado> enunciados;
    private static Map<Integer, UnidadDidactica> unidadesDidacticas;
    private static Set<Convocatoria> convocatorias;

    public Controller(DataAccessible Dao1, DataAccessible Dao2) {
        super();
        dao1 = Dao1;
        dao2 = Dao2;
    }

    public void run() {
        enunciados = dao1.getEnunciados();
        unidadesDidacticas = dao1.getUnidadesDidacticas();
        convocatorias = dao2.getConvocatorias();

        try {
            view.menu();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static void crearUD(UnidadDidactica ud) throws MyException {
        dao1.addUnidadDidactica(ud);
    }

    public static void crearConvocatoria() {

    }

    public static void crearEnunciado(Enunciado enunciado){

    }

    public static boolean udExiste(Integer id) {
        for (Integer i : unidadesDidacticas.keySet()) {
            if (id == i)
                return true;
        }

        return false;
    }

    public static boolean enunExiste(Integer id) {
        for (Integer i : enunciados.keySet()) {
            if (id == i)
                return true;
        }

        return false;
    }

    

}
