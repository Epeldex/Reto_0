package view;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import classes.Convocatoria;
import classes.Enunciado;
import classes.UnidadDidactica;
import controller.Controller;
import controller.Util;
import exceptions.MyException;

public class Console {

    public void menu() throws MyException {

        System.out.println("SELECCIONA UNA OPCIÓN:\n"
                + "\t1. Añadir Unidad Didáctica\n"
                + "\t2. Añadir Convocatoria\n"
                + "\t3. Añadir Enunciado\n"
                + "\t4. Consultar enunciados que contienen una unidad didactica concreta\n"
                + "\t5. Consultar Convocatoria\n"
                + "\t6. Visualizar Enunciado\n"
                + "\t7. Salir\n");

        switch (Util.leerInt()) {
            case 1:
                Controller.crearUD(construirUD());
                break;
            case 2:
                Controller.crearConvocatoria(construirConvocatoria());
                break;
            case 3:
                Controller.crearEnunciado(construirEnunciado());
                break;
            case 4:
                mostrarEnunciados();
                break;
            default:

                break;
        }

    }

    private void mostrarEnunciados() {
        System.out.println("Introduzca el numero de la unidad didactica que deasea seleccionar:");
        for (UnidadDidactica u : Controller.getUnidadesDidacticas().values())
            System.out.println(u.getId() + ")" + u.getAcronimo().toString());

        int selectedID = Util.leerInt();
        while (!Controller.getConvocatorias().containsKey(selectedID)){
            System.out.println("El ID introducido no existe, introduzcalo de nuevo:");
            selectedID = Util.leerInt();
        }
        
        for (Enunciado e : Controller.getEnunciados().values()){
            if (e.getUnidadesDidacticas().containsKey(selectedID))
                System.out.println(e.getDescripcion().toString());
        }

    }

    private UnidadDidactica construirUD() {
        UnidadDidactica ud = new UnidadDidactica();

        System.out.println("Introduce el ID de la Unidad Didáctica:");
        ud.setId(Util.leerInt());

        while (Controller.udExiste(ud.getId())) {
            System.out.println("Ya existe una Unidad Didáctica con ese ID. Por favor, introduce otro ID:");
            ud.setId(Util.leerInt());
        }

        System.out.println("Introduce el acrónimo (por ejemplo: BDA, SGE...) de la Unidad Didáctica:");
        ud.setAcronimo(Util.leerCadena());
        System.out.println("Introduce el título de la Unidad Didáctica:");
        ud.setTitulo(Util.leerCadena());
        System.out.println("Introduce la evaluación de la Unidad Didáctica:");
        ud.setEvaluacion(Util.leerCadena());
        System.out.println("Introduce la descripción de la Unidad Didáctica:");
        ud.setDescripcion(Util.leerCadena());
        ud.setEnunciados(getEnunciadosUD());

        return ud;
    }

    private Enunciado construirEnunciado() {
        Enunciado enu = new Enunciado();

        System.out.println("Introduce el ID del enunciado:");
        enu.setId(Util.leerInt());

        while (Controller.enunExiste(enu.getId())) {
            System.out.println("Ya existe un enuniado con ese ID. Por favor, introduce otro ID:");
            enu.setId(Util.leerInt());
        }

        System.out.println("Introduce la descripción del enunciado:");
        enu.setDescripcion(Util.leerCadena());
        System.out.println("Introduce el nivel del enunciado (ALTA / MEDIA / BAJA):");
        String niv = Util.leerCadena();

        while (!niv.equalsIgnoreCase("ALTA")
                && !niv.equalsIgnoreCase("MEDIA")
                && !niv.equalsIgnoreCase("BAJA")) {
            System.out.println("Error. Introduce ALTA, MEDIA o BAJA:");
            niv = Util.leerCadena();
        }

        enu.setNivel(niv);

        System.out.println(
                "Introduce 1 si el Enunciado está disponible o 0 si no lo está (1 = DISPONIBLE / 0 NO DISPONIBLE):");
        int dis = Util.leerInt();

        while (dis != 1 && dis != 0) {
            System.out.println("Error. Introduce 1 si el Enunciado está disponible o 0 si no lo está:");
            dis = Util.leerInt();
        }

        if (dis == 1)
            enu.setDisponible(true);
        else
            enu.setDisponible(false);

        enu.setConvocatorias(getConvocatoriaDeEnunciado());
        enu.setUnidadesDidacticas(getUdDeEnunciado());

        return enu;
    }

    private Convocatoria construirConvocatoria() {
        Convocatoria convocatoria = new Convocatoria((int) Math.random() * (100));

        System.out.println("Introduce la convocatoria");
        convocatoria.setConvocatoria(Util.leerCadena());
        System.out.println("Introduce la descripcion de la convocatoria");
        convocatoria.setDescripcion(Util.leerCadena());
        System.out.println("Introduce la fecha de la convoctoria con este patron:"
                + " dd/MM/aaaa || e.x: 03/03/2003");
        String fecha = Util.leerCadena();
        while (!Controller.comprobarFecha(fecha)) {
            System.out.println("Patron incorrecto, intruduzca fecha de nuevo: dd/MM/aaaa");
            fecha = Util.leerCadena();
        }
        try {
            convocatoria.setFecha(Controller.parseStringToSqlDate(fecha));
        } catch (ParseException e) {
            System.out.println("Error parsing date");
        }
        System.out.println("Seleccione a que enunciado ira ligada la convocatoria");

        for (Enunciado e : Controller.getEnunciados().values())
            System.out.println(e.getId() + ") " + e.getDescripcion());

        Integer id = Util.leerInt();
        while (!Controller.getEnunciados().containsKey(id)) {
            System.out.println("Numero incorrecto, introduzcalo de nuevo");
            id = Util.leerInt();
        }
        convocatoria.setId(id);

        return convocatoria;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    private Set<Convocatoria> getConvocatoriaDeEnunciado() {
        System.out.println("Introduzca el/los numero(s) de las convocatorias que pertenecen a este enunciado"
                + "separadas por una coma y un espacio tras la coma, e.x: 1, 3, 7, 2");

        Map<Integer, Convocatoria> aux = Controller.getConvocatorias();
        for (Integer i : aux.keySet()) {
            System.out.println(i + ") " + aux.get(i).getConvocatoria().toString());
        }
        String idListString = Util.leerCadena();
        while (!Controller.comprobarPatron(idListString)) {
            System.out.println("Patron incorrecto, introduzcalo de nuevo");
            idListString = Util.leerCadena();
        }

        int[] idList = Controller.stringToIntArray(idListString);
        Set<Convocatoria> conv = new HashSet<>();

        for (int i = 0; i < idList.length; i++) {
            if (aux.containsKey(idList[i])) {
                conv.add(aux.get(idList[i]));
            } else {
                System.out.println("La convocatoria " + idList[i] + " no existe, no se añadirá");
            }
        }
        return conv;
    }

    private Map<Integer, UnidadDidactica> getUdDeEnunciado() {
        System.out.println("Introduzca el/los numero(s) de las unidades didacticas que "
                + "pertenecen a este enunciado separadas por una coma y un espacio tras la coma, "
                + "e.x: 1, 3, 7, 2");

        Map<Integer, UnidadDidactica> aux2 = Controller.getUnidadesDidacticas();
        for (Integer i : aux2.keySet()) {
            System.out.println(i + ") " + aux2.get(i).getAcronimo().toString());
        }
        String idListString = Util.leerCadena();
        while (!Controller.comprobarPatron(idListString)) {
            System.out.println("Patron incorrecto, introduzcalo de nuevo");
            idListString = Util.leerCadena();
        }
        int[] idList = Controller.stringToIntArray(idListString);
        Map<Integer, UnidadDidactica> udMap = new LinkedHashMap<>();

        for (int i = 0; i < idList.length; i++) {
            if (aux2.containsKey(idList[i])) {
                udMap.put(idList[i], aux2.get(idList[i]));
            } else {
                System.out.println("La unidad didactica " + idList[i] + " no existe, no se añadirá");
            }
        }

        return udMap;
    }

    private Integer[] getEnunciadosUD() {
        System.out.println("Introduzca el/los numero(s) de los Enunciados que "
                + "que guardan relacion con esta unidad didactica, e.x: 1, 3, 7, 2");

        Map<Integer, Enunciado> aux2 = Controller.getEnunciados();
        for (Integer i : aux2.keySet()) {
            System.out.println(i + ") " + aux2.get(i).getDescripcion().toString());
        }
        String idListString = Util.leerCadena();
        while (!Controller.comprobarPatron(idListString)) {
            System.out.println("Patron incorrecto, introduzcalo de nuevo");
            idListString = Util.leerCadena();
        }
        int[] idList = Controller.stringToIntArray(idListString);
        List<Integer> ids = new ArrayList<>();

        for (int i = 0; i < idList.length; i++) {
            if (aux2.containsKey(idList[i])) {
                ids.add(idList[i]);
            } else {
                System.out.println("El enunciado " + idList[i] + " no existe, no se añadirá");
            }
        }
        return ids.toArray(new Integer[0]);
    }

}
