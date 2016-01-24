package Trabajador.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Trabajador.Dominio.Administrador;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Francisco
 */
public class AdministradorPersistencia {

    private static final ObjectConverter<Administrador> conterter = new ObjectConverter<Administrador>() {

        @Override
        public Administrador convert(ResultSet result) throws SQLException {
            return new Administrador(result.getString(1), result.getString(2));
        }

        @Override
        public String createInsertQuery(Administrador object) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static Administrador getAdministrador(String user) throws SQLException {
        String sql = "SELECT * FROM Administrador T WHERE T.user = '" + user + "'";

        ConexionBD conexion = new ConexionBD();
        Administrador administrador = conexion.search(conterter, sql);
        conexion.close();

        return administrador;
    }
}
