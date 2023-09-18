package interfaces;

import java.util.Map;
import java.util.Set;

import classes.UnidadDidactica;
import classes.Enunciado;
import classes.Convocatoria;

public interface DataAccessible {

    public void addUnidadDidactica(UnidadDidactica unidad);
    public void addConvocatoria(Convocatoria Convocatoria);
    public void addEnunciado(Enunciado enunciado);
    public Map<Integer, Enunciado> getEnunciados();
    public Map<Integer, UnidadDidactica> getUnidadDidactica();
    public Set<Convocatoria> getConvocatoria(Integer id);

    
}
