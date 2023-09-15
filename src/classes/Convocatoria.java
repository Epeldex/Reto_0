package classes;

import java.time.LocalDate;

public class Convocatoria {

    private String convocatoria;
    private String descripcion;
    private LocalDate fecha;
    private String curso;
    
    
    public String getConvocatoria() {

        return convocatoria;
    }
    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    
}
