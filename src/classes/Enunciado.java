package classes;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class Enunciado implements Serializable {

   private enum Nivel {
      ALTA, MEDIA, BAJA
   };

   private Integer id;
   private String descripcion;
   private boolean disponible;
   private String ruta;
   private Nivel nivel;
   private Set<Convocatoria> convocatorias;
   private Map<Integer, UnidadDidactica> unidadesDidacticas;

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

   public String getNivel() {

      return nivel.toString();
   }

   public void setNivel(String lvl) {

      for (Nivel n : Nivel.values()) {
         if (n.name().equalsIgnoreCase(lvl)) {
            this.nivel = n;
         }
      }
   }

   public Set<Convocatoria> getConvocatorias() {
      return convocatorias;
   }

   public void setConvocatorias(Set<Convocatoria> convocatorias) {
      this.convocatorias = convocatorias;
   }

   public Map<Integer, UnidadDidactica> getUnidadesDidacticas() {
      return unidadesDidacticas;
   }

   public void setUnidadesDidacticas(Map<Integer, UnidadDidactica> unidadesDidacticas) {
      this.unidadesDidacticas = unidadesDidacticas;
   }

   @Override
   public String toString() {
      return "Enunciado [id=" + id + ", descripcion=" + descripcion + ", disponible=" + disponible + ", ruta=" + ruta
            + ", nivel=" + nivel + ", convocatorias=" + convocatorias.toString() + ", unidadesDidacticas="; //unidadesDidacticas.toString();
   }
}
