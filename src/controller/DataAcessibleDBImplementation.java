package controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Convocatoria;
import classes.Enunciado;
import classes.UnidadDidactica;
import exceptions.MyException;
import interfaces.DataAccessible;

public class DataAcessibleDBImplementation implements DataAccessible {

    /**
     * Connection to the database
     */
    private static Connection con;

    /**
     * Statement to execute a query
     */
    private static PreparedStatement stmt;

    /**
     * Object to open and close the connection to the database
     */
    private static OpenCloseConnection occ = new OpenCloseConnection();

    /**
     * String to store the query
     */
    private static String query = null;

    @Override
    public Set<Convocatoria> getConvocatorias() throws MyException {
        return new DataAcessibleFileImplementation().getConvocatorias();
    }

    @Override
    public Set<Convocatoria> getConvocatoria(Integer id) throws MyException {
        return new DataAcessibleFileImplementation().getConvocatoria(id);
    }

    @Override
    public Integer addUnidadDidactica(UnidadDidactica unidad) throws MyException {
        con = occ.openConnection();
        int successFlag = -1;

        String sql = "INSERT INTO unidad (id, acronimo, titulo, evaluacion, descripcion) VALUES (?,?,?,?,?) ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, unidad.getId());
            stmt.setString(2, unidad.getAcronimo());
            stmt.setString(3, unidad.getTitulo());
            stmt.setString(4, unidad.getEvaluacion());
            stmt.setString(5, unidad.getDescripcion());

            successFlag = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new MyException("Error anadiendo unidad didactica");
        } finally {
            occ.closeConnection(stmt, con);
        }
        for (Integer i : unidad.getEnunciados())
            addRelacionUdEnunciado(i, unidad.getId());

        return successFlag;
    }

    /* ESTE NO */
    @Override
    public void addConvocatoria(Convocatoria convocatoria) throws MyException {
        new DataAcessibleFileImplementation().addConvocatoria(convocatoria);
    }

    @Override
    public Integer addEnunciado(Enunciado enunciado) throws MyException {
        for (Convocatoria e : enunciado.getConvocatorias())
            new DataAcessibleFileImplementation().addConvocatoria(e);

        Integer check;
        con = occ.openConnection();
        query = "Insert into Enunciado (id, descripcion, nivel, disponible, ruta) values (?,?,?,?,?)";

        try {
            stmt = con.prepareStatement(query);
            stmt.setInt(1, enunciado.getId());
            stmt.setString(2, enunciado.getDescripcion());
            stmt.setString(3, enunciado.getNivel());
            stmt.setBoolean(4, enunciado.isDisponible());
            stmt.setString(5, enunciado.getRuta());
            check = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new MyException("Error anadiendo enunciados");
        } finally {
            occ.closeConnection(stmt, con);
        }
        for (Integer u : enunciado.getUnidadesDidacticas().keySet())
            addRelacionUdEnunciado(enunciado.getId(), u);

        return check;
    }

    @Override
    public Map<Integer, Enunciado> getEnunciados() throws MyException {
        LinkedHashMap<Integer, Enunciado> enunciados = new LinkedHashMap<>();
        query = "Select * from enunciado";
        con = occ.openConnection();

        try {
            stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Enunciado e = new Enunciado();
                e.setId(rs.getInt("id"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setNivel(rs.getString("nivel"));
                e.setDisponible(rs.getBoolean("disponible"));
                e.setRuta(rs.getString("ruta"));
                e.setConvocatorias(new DataAcessibleFileImplementation().getConvocatoria(e.getId()));
                e.setUnidadesDidacticas(getUnidadDidactica(e.getId()));
                enunciados.put(e.getId(), e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Error obteniendo enunciados");
        } finally {
            occ.closeConnection(stmt, con);
        }
        return enunciados;
    }

    /* ESTE NO */

    @Override
    public Map<Integer, UnidadDidactica> getUnidadesDidacticas() throws MyException {
        Map<Integer, UnidadDidactica> unidadesMap = new HashMap<>();

        con = occ.openConnection();
        String sql = "SELECT id, acronimo, titulo, evaluacion, descripcion FROM unidad";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UnidadDidactica unidad = new UnidadDidactica();
                unidad.setId(rs.getInt("id"));
                unidad.setAcronimo(rs.getString("acronimo"));
                unidad.setTitulo(rs.getString("titulo"));
                unidad.setEvaluacion(rs.getString("evaluacion"));
                unidad.setDescripcion(rs.getString("descripcion"));

                unidadesMap.put(unidad.getId(), unidad);
            }
        } catch (SQLException e) {
            throw new MyException("Error obteniendo unidades didacticas");
        } finally {
            occ.closeConnection(stmt, con);
        }
        return unidadesMap;
    }

    private Integer addRelacionUdEnunciado(Integer idEnu, Integer idUd) throws MyException {
        con = occ.openConnection();
        int successFlag = -1;

        String sql = "INSERT INTO unidad_enunciado (unidads_id, enunciados_id) VALUES (?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUd);
            stmt.setInt(2, idEnu);

            successFlag = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new MyException("Error anadiendo relaciones entre unidades didacticas y enunciados");
        } finally {
            occ.closeConnection(stmt, con);
        }
        return successFlag;
    }

    private Map<Integer, UnidadDidactica> getUnidadDidactica(Integer wId) throws MyException {
        Map<Integer, UnidadDidactica> unidadesMap = new HashMap<>();

        String sql = "SELECT id, acronimo, titulo, evaluacion, descripcion\n" + //
                "FROM unidad \n" + //
                "join unidad_enunciado ue on ue.enunciados_id = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, wId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UnidadDidactica unidad = new UnidadDidactica();
                unidad.setId(rs.getInt("id"));
                unidad.setAcronimo(rs.getString("acronimo"));
                unidad.setTitulo(rs.getString("titulo"));
                unidad.setEvaluacion(rs.getString("evaluacion"));
                unidad.setDescripcion(rs.getString("descripcion"));

                unidadesMap.put(unidad.getId(), unidad);

            }
        } catch (SQLException e) {
            throw new MyException("Error obteniendo unidades didacticas");
        }
        return unidadesMap;
    }

}
