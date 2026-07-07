import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class UserDAO {
    public void insert(String userName, String password, String cardNumber, String transactionPin){
        String sql = """
                INSERT INTO users (userName, password, cardNumber, transactionPin)
                VALUES(?, ?, ?, ?)
                """;

        try(Connection con = DBConnection.obtainConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, cardNumber);
            pstmt.setString(4, transactionPin);

            pstmt.executeUpdate();
            System.out.println("User has been added in database");
        }catch (SQLException e){
            System.err.println("Error inserting in database: " + e);
        }
    }
}
