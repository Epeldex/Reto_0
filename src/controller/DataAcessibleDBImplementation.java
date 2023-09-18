package controller;

import java.util.Map;
import java.util.Set;

import classes.Convocatoria;
import classes.Enunciado;
import classes.UnidadDidactica;
import interfaces.DataAccessible;

public class DataAcessibleDBImplementation implements DataAccessible {

    @Override
    public void addUnidadDidactica(UnidadDidactica unidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUnidadDidactica'");
    }

    @Override
    public void addConvocatoria(Convocatoria Convocatoria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addConvocatoria'");
    }

    @Override
    public void addEnunciado(Enunciado enunciado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEnunciado'");
    }
    @Override
    public Map<Integer, Enunciado> getEnunciados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEnunciados'");
    }

    @Override
    public Map<Integer, UnidadDidactica> getUnidadDidactica() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUnidadDidactica'");
    }

    @Override
    public Set<Convocatoria> getConvocatoria(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConvocatoria'");
    } 
    
}
