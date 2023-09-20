package interfaces;

import java.util.Map;
import java.util.Set;

import classes.UnidadDidactica;
import exceptions.MyException;
import classes.Enunciado;
import classes.Convocatoria;

public interface DataAccessible {

    public void addUnidadDidactica(UnidadDidactica unidad) throws MyException;
    public void addConvocatoria(Convocatoria Convocatoria);
    public void addEnunciado(Enunciado enunciado);
    public Map<Integer, Enunciado> getEnunciados();
    public Map<Integer, UnidadDidactica> getUnidadDidactica() throws MyException;
    public Set<Convocatoria> getConvocatoria(Integer id);

    
}
