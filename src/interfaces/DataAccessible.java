package interfaces;

import java.util.Map;
import java.util.Set;

import classes.UnidadDidactica;
import exceptions.MyException;
import classes.Enunciado;
import classes.Convocatoria;

public interface DataAccessible {

    public Integer addUnidadDidactica(UnidadDidactica unidad) throws MyException;
    public void addConvocatoria(Convocatoria Convocatoria) throws MyException;
    public Integer addEnunciado(Enunciado enunciado) throws MyException;
    public Map<Integer, Enunciado> getEnunciados() throws MyException; 
    public Map<Integer, UnidadDidactica> getUnidadesDidacticas() throws MyException;
    public Set<Convocatoria> getConvocatoria(Integer id) throws MyException;
    public Set<Convocatoria> getConvocatorias() throws MyException;

    
}
