package classes;

import java.util.Map;
import java.util.Set;

public class Enunciado {
   private Integer id;
   private String descripcion;
   private enum nivel {ALTA, MEDIA, BAJA};
   private boolean disponible;
   private String ruta;
   private Set<Convocatoria> convocatorias;
   private Map <Integer, UnidadDidactica> unidadesDidacticas;

   public Integer getId() {
      return id;
   }
   public void setId(Integer id) {
      this.id = id;
   }
   public String getDescripcion() {
      return descripcion;
   }
   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }
   public boolean isDisponible() {
      return disponible;
   }
   public void setDisponible(boolean disponible) {
      this.disponible = disponible;
   }
   public String getRuta() {
      return ruta;
   }
   public void setRuta(String ruta) {
      this.ruta = ruta;
   }
}
