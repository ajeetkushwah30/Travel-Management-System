import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main{
    private static final String url = "jdbc:mysql://127.0.0.1:3306/travel_company";
    private static final String user = "root";
    private static final String password = "your_password";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url,user,password);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }




    }
}