package interfaces;

import exceptions.MyException;

public interface Viewable {
    
    public void menu() throws MyException;
    public void mostrarMensaje(String mensaje);

}
