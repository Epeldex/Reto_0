package classes;

import java.io.Serializable;
import java.sql.Date;

public class Convocatoria implements Serializable {

    private Integer id;
    private String convocatoria;
    private String descripcion;
    private Date fecha;
    private String curso;

    public Convocatoria() {
        super();
    }

    public Convocatoria(Integer id) {
        super();
        this.id = id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

}
