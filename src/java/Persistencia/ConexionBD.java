package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Juan Francisco Pascual Palacin
 */
public class ConexionBD {

    private final Connection conexion;

    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/PGP_grupo11?zeroDateTimeBehavior=convertToNull";
    private static final String DATABASE_USER = "PGP_grupo11";
    private static final String DATABASE_PASSWORD = "P6AbQA8Z";

    public ConexionBD() throws ClassNotFoundException, SQLException {
        Class.forName(DATABASE_DRIVER);
        conexion = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    public Statement createStatement() throws SQLException {
        return conexion.createStatement();
    }

    public <T> void insert(ObjectConverter<T> converter, T object) throws SQLException {
        String sql = converter.createInsertQuery(object);

        execute(sql);
    }

    public <T> T search(ObjectConverter<T> converter, String sql) throws SQLException {
        Statement statement = createStatement();
        ResultSet result = statement.executeQuery(sql);

        if (!result.next()) {
            return null;
        }

        return converter.convert(result);
    }

    public <T> ArrayList<T> searchAll(ObjectConverter<T> converter, String sql) throws SQLException {
        Statement statement = createStatement();
        ResultSet result = statement.executeQuery(sql);

        ArrayList<T> list = new ArrayList<>();
        while (result.next()) {
            T object = converter.convert(result);
            list.add(object);
        }

        return list;
    }

    public boolean execute(String sql) throws SQLException {
        Statement statement = createStatement();

        return statement.execute(sql);
    }

    public boolean existe(String sql) throws SQLException {
        Statement statement = createStatement();
        ResultSet result = statement.executeQuery(sql);

        return result.next();
    }

    public int count(String sql) throws SQLException {
        Statement statement = createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;
        while (result.next()) {
            count++;
        }

        return count;
    }

    public void close() throws SQLException {
        conexion.close();
    }
}
