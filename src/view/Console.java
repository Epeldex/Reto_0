package view;

import classes.Convocatoria;
import classes.Enunciado;
import classes.UnidadDidactica;
import controller.Controller;
import controller.Util;
import exceptions.MyException;

public class Console {

    public void menu() throws MyException {

        int opcion = 2;

        System.out.println("SELECCIONA UNA OPCIÓN:\n"
                + "\t1. Añadir Unidad Didáctica\n"
                + "\t2. Añadir Convocatoria\n"
                + "\t3. Añadir Enunciado\n"
                + "\t4. Consultar Enunciado\n"
                + "\t5. Consultar Convocatoria\n"
                + "\t6. Visualizar Enunciado\n"
                + "\t7. Salir\n");

        switch (opcion) {
            case 1:
                Controller.crearUD(preguntas1());
                break;
            case 2:
                Controller.crearConvocatoria();
                break;
            case 3:
                Controller.crearEnunciado(preguntas3());
                break;
            default:

                break;
        }

    }

    private static UnidadDidactica preguntas1() {
        UnidadDidactica ud = new UnidadDidactica();

        System.out.println("Introduce el ID de la Unidad Didáctica:");
        ud.setId(Util.leerInt());

        while (Controller.udExiste(ud.getId())) {
            System.out.println("Ya existe una Unidad Didáctica con ese ID. Por favor, introduce otro ID:");
            ud.setId(Util.leerInt());
        }

        System.out.println("Introduce el acrónimo (por ejemplo: BDA, SGE...) de la Unidad Didáctica:");
        ud.setAcronimo(Util.introducirCadena());
        System.out.println("Introduce el título de la Unidad Didáctica:");
        ud.setTitulo(Util.introducirCadena());
        System.out.println("Introduce la evaluación de la Unidad Didáctica:");
        ud.setEvaluacion(Util.introducirCadena());
        System.out.println("Introduce la descripción de la Unidad Didáctica:");
        ud.setDescripcion(Util.introducirCadena());

        return ud;
    }

    private static Enunciado preguntas3() {
        Enunciado enu = new Enunciado();

        System.out.println("Introduce el ID del enunciado:");
        enu.setId(Util.leerInt());

        while (Controller.enunExiste(enu.getId())) {
            System.out.println("Ya existe un enuniado con ese ID. Por favor, introduce otro ID:");
            enu.setId(Util.leerInt());
        }

        System.out.println("Introduce la descripción del enunciado:");
        enu.setDescripcion(Util.introducirCadena());
        System.out.println("Introduce el nivel del enunciado (ALTA / MEDIA / BAJA):");
        String niv = Util.introducirCadena();

        while (!niv.equalsIgnoreCase("ALTA") && !niv.equalsIgnoreCase("MEDIA") && !niv.equalsIgnoreCase("BAJA")) {
            System.out.println("Error. Introduce ALTA, MEDIA o BAJA:");
            niv = Util.introducirCadena();
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
        
        

        return enu;
    }

}
