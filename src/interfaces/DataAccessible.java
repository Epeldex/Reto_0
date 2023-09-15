package interfaces;

import java.util.Map;

import classes.UnidadDidactica;
import classes.Enunciado;
import classes.Convocatoria;

public interface DataAccessible {

    public void addUnidadDidactica(UnidadDidactica unidad);
    public void addConvocatoria(Convocatoria Convocatoria);
    public void addEnunciado(Enunciado enunciado);
    public Map getEnunciados(UnidadDidactica unidad);
    public Map getEnunciadosConvocatoria(Convocatoria convocatoria);


    
}
