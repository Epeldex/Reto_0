package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Convocatoria;
import classes.Enunciado;
import classes.UnidadDidactica;
import exceptions.MyException;
import interfaces.DataAccessible;

public class DataAcessibleDBImplementation implements DataAccessible {
    private Connection con;
    private OpenCloseConnection occ;

    @Override
    public void addUnidadDidactica(UnidadDidactica unidad) throws MyException {
        MyException me = null;

        con = occ.openConnection();

        String sql = "INSERT INTO unidad VALUES (id, acronimo, titulo, evaluacion, descripcion)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, unidad.getId());
            stmt.setString(2, unidad.getAcronimo());
            stmt.setString(3, unidad.getTitulo());
            stmt.setString(4, unidad.getEvaluacion());
            stmt.setString(5, unidad.getDescripcion());

            int prueba = stmt.executeUpdate();

            if (prueba == 1) {
                me = new MyException("Esta bien");
            } else {
                me = new MyException("mal");
            }

            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de excepción al cerrar la conexión
            }
        }
        throw me;

    }

    /* ESTE NO */
    @Override
    public void addConvocatoria(Convocatoria Convocatoria) {

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
    public Map<Integer, UnidadDidactica> getUnidadDidactica() throws MyException {
        Map<Integer, UnidadDidactica> unidadesMap = new HashMap<>();

        try {
            con = occ.openConnection();
            String sql = "SELECT id, acronimo, titulo, evaluacion, descripcion FROM unidad";
            try (PreparedStatement stmt = con.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String acronimo = rs.getString("acronimo");
                    String titulo = rs.getString("titulo");
                    String evaluacion = rs.getString("evaluacion");
                    String descripcion = rs.getString("descripcion");

                    UnidadDidactica unidad = new UnidadDidactica();
                    unidadesMap.put(id, unidad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de excepción al cerrar la conexión
            }
        }

        return unidadesMap;
    }

    /* ESTE NO */
    @Override
    public Set<Convocatoria> getConvocatoria(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConvocatoria'");
    }

}
