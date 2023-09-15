package controller;

import java.util.Map;

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
    public void addEnunciadoConvocatoria(Enunciado enunciado, Convocatoria convocatoria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEnunciadoConvocatoria'");
    }

    @Override
    public Map getEnunciados(UnidadDidactica unidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEnunciados'");
    }

    @Override
    public Map getEnunciadosConvocatoria(Convocatoria convocatoria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEnunciadosConvocatoria'");
    }
    
}
