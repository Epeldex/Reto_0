package controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import classes.Convocatoria;
import classes.Enunciado;
import classes.MyObjectOutputStream;
import classes.UnidadDidactica;
import exceptions.MyException;
import interfaces.DataAccessible;

public class DataAcessibleFileImplementation implements DataAccessible {

    File convocatoriasFile = new File("convocatorias.obj");

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
                oos = new MyObjectOutputStream(new FileOutputStream(convocatoriasFile, true));
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
    public Integer addEnunciado(Enunciado enunciado) throws MyException{
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEnunciado'");
    }
    
    @Override
    public Set<Convocatoria> getConvocatoria(Integer id) throws MyException {
        Set<Convocatoria> convocatorias = new HashSet<>();
        int cont = calculoFichero(convocatoriasFile);
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(convocatoriasFile));
            for (int i = 0; i <cont; i++) {
                Convocatoria c = (Convocatoria) ois.readObject();
                if (c.getId().equals(id)) {
                    convocatorias.add(c);
                } 
            }
        } catch(IOException | ClassNotFoundException e){    
            throw new MyException("Error recogiendo convocatorias");
        }
        finally {
            try {
                ois.close();
            } catch (IOException e) {
                throw new MyException("Error cerrando flujos con ficheros");
            }
        } 
        return convocatorias;
    }

    //                        METODOS AUXILIARES

    //Devuelve el numero de objetos de un fichero
		 public static int calculoFichero(File fich){
            int cont=0;
            if (fich.exists()){
                FileInputStream fis=null;
                ObjectInputStream ois=null;
                try{
                    fis=new FileInputStream(fich);
                    ois=new ObjectInputStream(fis);
       
                    Object aux=ois.readObject();
       
                    while (aux!=null){
                        cont++;
                        aux=ois.readObject();
                    }
                }catch(EOFException e1){
                }catch (Exception e2){
                    e2.printStackTrace();
                }
                try {
                   ois.close();
                   fis.close();
               } catch (IOException e) {
                   System.out.println("Error al cerrar los flujos");
                   
               }
            }
            return cont;
        }

        @Override
        public Map<Integer, Enunciado> getEnunciados(DataAcessibleFileImplementation fileImp) throws MyException {
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
        int cont = calculoFichero(convocatoriasFile);

        // Devuelve el número de personas en el fichero para hacer un for controlado
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(convocatoriasFile));
            for (int i = 0; i <cont; i++) {
                Convocatoria c = (Convocatoria) ois.readObject();
                convocatorias.add(c);
            }
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new MyException("Error recogiendo convocatorias");
        }
        return convocatorias;
        }

        @Override
        public Map<Integer, UnidadDidactica> getUnidadDidactica(Integer id) throws MyException {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getUnidadDidactica'");
        }
  

}
