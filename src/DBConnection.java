import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/davbank_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "TPSOLKN2211";

    public static Connection obtainConnection()throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
