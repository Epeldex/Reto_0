package classes;

import java.io.Serializable;

public class UnidadDidactica implements Serializable{
  private Integer id;
  private String acronimo;
  private String titulo;
  private String evaluacion;
  private String descripcion;
  private Integer[] enunciados;

  
  public Integer[] getEnunciados() {
    return enunciados;
  }
  public void setEnunciados(Integer[] enunciados) {
    this.enunciados = enunciados;
  }
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getAcronimo() {
    return acronimo;
  }
  public void setAcronimo(String acronimo) {
    this.acronimo = acronimo;
  }
  public String getTitulo() {
    return titulo;
  }
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public String getEvaluacion() {
    return evaluacion;
  }
  public void setEvaluacion(String evaluacion) {
    this.evaluacion = evaluacion;
  }
  public String getDescripcion() {
    return descripcion;
  }
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
}
