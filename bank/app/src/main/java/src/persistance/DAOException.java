package src.persistance;

import java.sql.SQLException;

public class DAOException extends RuntimeException {

    public DAOException(String cause, SQLException e) {
        super(cause, e);
    }
    
}
