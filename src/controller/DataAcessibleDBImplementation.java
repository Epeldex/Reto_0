package controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

    /**
     * Connection to the database
     */
    private Connection con;

    /**
     * Statement to execute a query
     */
    private PreparedStatement stmt;

    /**
     * Object to open and close the connection to the database
     */
    private OpenCloseConnection occ = new OpenCloseConnection();

    /**
     * String to store the query
     */
    private String query = null;

    @Override
    public Integer addUnidadDidactica(UnidadDidactica unidad) throws MyException {
        MyException me = null;

        con = occ.openConnection();
        int successFlag = -1;

        String sql = "INSERT INTO unidad VALUES (id, acronimo, titulo, evaluacion, descripcion)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, unidad.getId());
            stmt.setString(2, unidad.getAcronimo());
            stmt.setString(3, unidad.getTitulo());
            stmt.setString(4, unidad.getEvaluacion());
            stmt.setString(5, unidad.getDescripcion());

            successFlag = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            occ.closeConnection(stmt, con);
        }
        return successFlag;

    }

    /* ESTE NO */
    @Override
    public void addConvocatoria(Convocatoria Convocatoria) {

        throw new UnsupportedOperationException("Unimplemented method 'addConvocatoria'");
    }

    @Override
    public Integer addEnunciado(Enunciado enunciado) throws MyException {
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
        }
        return check;
    }

    @Override
    public Map<Integer, Enunciado> getEnunciados(DataAcessibleFileImplementation fileImp) throws MyException {
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
                e.setConvocatorias(fileImp.getConvocatoria(e.getId()));
            }
        } catch (SQLException e) {
            throw new MyException("Error obteniendo enunciados");
        }
        return enunciados;
    }

    @Override
    public Map<Integer, UnidadDidactica> getUnidadDidactica(Integer wId) throws MyException {
        Map<Integer, UnidadDidactica> unidadesMap = new HashMap<>();

        con = occ.openConnection();
        String sql = "SELECT id, acronimo, titulo, evaluacion, descripcion FROM unidad";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.getInt("id") == wId) {
                while (rs.next()) {
                    UnidadDidactica unidad = new UnidadDidactica();
                    unidad.setId(rs.getInt("id"));
                    unidad.setAcronimo(rs.getString("acronimo"));
                    unidad.setTitulo(rs.getString("titulo"));
                    unidad.setEvaluacion(rs.getString("evaluacion"));
                    unidad.setDescripcion(rs.getString("descripcion"));

                    unidadesMap.put(unidad.getId(), unidad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            occ.closeConnection(stmt, con);
        }

        return unidadesMap;
    }

    /* ESTE NO */
    @Override
    public Set<Convocatoria> getConvocatoria(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConvocatoria'");
    }

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
            e.printStackTrace();
        } finally {
            occ.closeConnection(stmt, con);
        }
        return unidadesMap;
    }

    @Override
    public Set<Convocatoria> getConvocatorias() throws MyException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConvocatorias'");
    }

}
