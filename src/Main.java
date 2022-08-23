import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost/postgres?user=postgres&password=Timon1";
    private static Connection con;
    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection(URL);
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
