import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDAO {
    public void insert(String userName, String password, String cardNumber, String transactionPin, double balance){
        String sql = """
                INSERT INTO users (username, password, cardnumber, transactionpin, balance)
                VALUES(?, ?, ?, ?, ?)
                """;

        try(Connection con = DBConnection.obtainConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.setString(3, cardNumber);
            pstmt.setString(4, transactionPin);
            pstmt.setDouble(5, balance);

            pstmt.executeUpdate();
            System.out.println("User has been added in database");
        }catch (SQLException e){
            System.err.println("Error inserting in database: " + e);
        }
    }

    public User login(String loginName, String loginPass){
        String sql = """
                SELECT username, password, cardNumber, transactionPin, balance
                FROM users
                WHERE username = ? AND password = ?
                """;
        try(Connection con = DBConnection.obtainConnection();
        PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, loginName);
            pstmt.setString(2, loginPass);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    return new User(
                            rs.getString("userName"),
                            rs.getString("password"),
                            rs.getString("cardnumber"),
                            rs.getString("transactionpin"),
                            rs.getDouble("balance")
                    );
                }
            }
        }catch (SQLException e){
            System.err.println("Error searching user: " + e);
        }
        return null;
    }

    public void updatePassword(User loggedUser, String newPassword){
        String userName = loggedUser.getUserName();

        String sql = """
                UPDATE users
                SET password = ?
                WHERE username = ?;
                """;
        try(Connection con = DBConnection.obtainConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, newPassword);
            pstmt.setString(2, userName);
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.err.println("Error trying to update password in data base " + e);
        }
    }

    public void addBalance(User loggedUser, double amount){
        String sql = """
                UPDATE users
                SET balance = balance + ?
                WHERE username = ?;
                """;
        try(Connection con = DBConnection.obtainConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setDouble(1, amount);
            pstmt.setString(2, loggedUser.getUserName());
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.err.println("Error trying to update balance in data base " + e);
        }
    }
    public void subtractMoney(User loggedUser, double amount){
        String sql = """
                UPDATE users
                SET balance = balance - ?
                WHERE username = ?;
                """;
        try(Connection con = DBConnection.obtainConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setDouble(1, amount);
            pstmt.setString(2, loggedUser.getUserName());
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.err.println("Error trying to update balance in data base " + e);
        }
    }

    public User searchUserToSendMoney(String cardNumber, double amountToSend){
        String sql = """
                SELECT username
                FROM users
                WHERE cardnumber = ?
                """;
        try(Connection con = DBConnection.obtainConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1, cardNumber);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    return new User(
                            rs.getString("userName"),
                            null,
                            null,
                            null,
                            0
                            );
                }
            }
        }catch (SQLException e){
            System.err.println("Error searching user: " + e);
        }
        return null;
    }



}
