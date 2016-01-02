package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Juan Francisco Pascual Palacin
 */
public interface ObjectConverter<T> {
    T convert(ResultSet result) throws SQLException;
    
    String createInsertQuery(T object);
}
