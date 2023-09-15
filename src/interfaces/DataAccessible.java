package interfaces;

import java.util.Map;

import classes.UnidadDidactica;
import classes.Enunciado;
import classes.Convocatoria;

public interface DataAccessible {

    public void addUnidadDidactica(UnidadDidactica unidad);
    public void addConvocatoria(Convocatoria Convocatoria);
    public void addEnunciado(Enunciado enunciado);
    public Map<Integer, Enunciado> getEnunciados();
    public Map<Integer, Enunciado> getUnidadDidactica();
    public Map<Integer, Enunciado> getConvocatoria();

    
}
