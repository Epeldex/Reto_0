    package controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
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
    private static DataAccessible dao;
    private static Map<Integer, Enunciado> enunciados;
    private static Map<Integer, UnidadDidactica> unidadesDidacticas;
    private static Set<Convocatoria> convocatorias;

    public Controller(DataAccessible data) {
        super();
        dao = data;
    }

    public void run() {
        try {
            enunciados = dao.getEnunciados();
            unidadesDidacticas = dao.getUnidadesDidacticas();
            convocatorias = dao.getConvocatorias();

            view.menu();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static void crearUD(UnidadDidactica ud) throws MyException {
        dao.addUnidadDidactica(ud);
        unidadesDidacticas.put(ud.getId(), ud);
    }

    public static void crearConvocatoria(Convocatoria convocatoria) throws MyException {
        dao.addConvocatoria(convocatoria);
        convocatorias.add(convocatoria);
    }

    public static void crearEnunciado(Enunciado enunciado) throws MyException {
        if (dao.addEnunciado(enunciado) > 0)
            view.mostrarMensaje("Enunciado anadido correctamente");
        enunciados.put(enunciado.getId(), enunciado);
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

    public static boolean convExiste(String convocatoria) {
        for (Convocatoria c : convocatorias) {
            if (c.getConvocatoria().equalsIgnoreCase(convocatoria))
                return true;
        }
        return false;
    }

    public static Map<Integer, Convocatoria> getConvocatorias() {
        Map<Integer, Convocatoria> cov = new LinkedHashMap<>();

        int i = 1;
        for (Convocatoria c : convocatorias) {
            cov.put(i, c);
            i++;
        }
        return cov;
    }

    public static boolean comprobarPatron(String patron) {
        return patron.matches("^(\\d+(,\\s\\d+)*)?$");
    }

    public static int[] stringToIntArray(String input) {
        return Arrays.stream(input.split(",\\s")).mapToInt(Integer::parseInt).toArray();
    }

    public static boolean comprobarFecha(String fecha) {
        return fecha.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    public static Date parseStringToSqlDate(String date) throws ParseException {
        return new Date(new SimpleDateFormat("dd/MM/yyyy").parse(date).getTime());
    }

    public static Map<Integer, Enunciado> getEnunciados() {
        return enunciados;
    }

    public static Map<Integer, UnidadDidactica> getUnidadesDidacticas() {
        return unidadesDidacticas;
    }
}
