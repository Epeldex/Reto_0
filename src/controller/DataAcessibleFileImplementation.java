package controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import classes.Convocatoria;
import classes.Enunciado;
import classes.UnidadDidactica;
import exceptions.MyException;
import interfaces.DataAccessible;

public class DataAcessibleFileImplementation implements DataAccessible {

    private File convocatoriasFile = new File("convocatorias.obj");

    @Override
    public Integer addUnidadDidactica(UnidadDidactica unidad) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUnidadDidactica'");
    }

    @Override
    public void addConvocatoria(Convocatoria convocatoria) throws MyException {
        ObjectOutputStream oos = null;
        // abrimos el fichero
        if (convocatoriasFile.exists()) {
            try {
                oos = new ObjectOutputStream(new FileOutputStream(convocatoriasFile, true));
                oos.writeObject(convocatoria);
            } catch (IOException e) {
                throw new MyException("Error anadiendo convocatoria");
            }
        } else {
            try {
                oos = new ObjectOutputStream(new FileOutputStream(convocatoriasFile));
                oos.writeObject(convocatoria);
            } catch (IOException e) {
                throw new MyException("Error anadiendo convocatoria");
            }
        }
        try {
            oos.close();
        } catch (IOException e) {
            throw new MyException("Error cerrando el fichero");
        }
    }

    @Override
    public Integer addEnunciado(Enunciado enunciado) throws MyException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEnunciado'");
    }

    @Override
    public Set<Convocatoria> getConvocatoria(Integer id) throws MyException {
        Set<Convocatoria> convocatorias = new HashSet<>();
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(convocatoriasFile));
            while (true) {
                try {
                    Convocatoria c = (Convocatoria) ois.readObject();
                    if (c.getId().equals(id)) {
                        convocatorias.add(c);
                    }
                } catch (EOFException e) {
                    // Llegamos al final del archivo, salimos del bucle
                    break;
                }
            }
        } catch (EOFException | StreamCorruptedException e) {
        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
            throw new MyException("Error recogiendo convocatorias");
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                throw new MyException("Error cerrando flujos con ficheros");
            }
        }

        return convocatorias;
    }
    // METODOS AUXILIARES

    // Devuelve el numero de objetos de un fichero

    @Override
    public Map<Integer, Enunciado> getEnunciados() throws MyException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEnunciados'");
    }

    @Override
    public Map<Integer, UnidadDidactica> getUnidadesDidacticas() throws MyException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUnidadesDidacticas'");
    }

    @Override
    public Set<Convocatoria> getConvocatorias() throws MyException {
        Set<Convocatoria> convocatorias = new HashSet<>();
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(convocatoriasFile));

            while (true) {
                try {
                    Convocatoria c = (Convocatoria) ois.readObject();
                    convocatorias.add(c);
                } catch (EOFException e) {
                    // Llegamos al final del archivo, salimos del bucle
                    break;
                }
            }
        } catch (EOFException | StreamCorruptedException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new MyException("Error recogiendo convocatorias");
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                throw new MyException("Error cerrando flujos con ficheros");
            }
        }

        return convocatorias;
    }

    @Override
    public Map<Integer, UnidadDidactica> getUnidadDidactica(Integer id) throws MyException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUnidadDidactica'");
    }

}
