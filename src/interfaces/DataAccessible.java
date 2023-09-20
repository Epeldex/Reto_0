package interfaces;

import java.util.Map;
import java.util.Set;

import classes.UnidadDidactica;
import controller.DataAcessibleFileImplementation;
import exceptions.MyException;
import classes.Enunciado;
import classes.Convocatoria;

public interface DataAccessible {

    public void addUnidadDidactica(UnidadDidactica unidad) throws MyException;
    public void addConvocatoria(Convocatoria Convocatoria) throws MyException;
    public Integer addEnunciado(Enunciado enunciado) throws MyException;
    public Map<Integer, Enunciado> getEnunciados(DataAcessibleFileImplementation fileImp) throws MyException;
    public Map<Integer, UnidadDidactica> getUnidadDidactica() throws MyException;
    public Set<Convocatoria> getConvocatoria(Integer id) throws MyException;

    
}
