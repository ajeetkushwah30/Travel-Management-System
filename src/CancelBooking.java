import java.sql.*;
import java.util.Scanner;

public class CancelBooking {
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
            String  check = "SELECT * FROM booking WHERE booking_id = ?";
            String  delete = "DELETE FROM booking WHERE booking_id = ?";

            PreparedStatement ps_ck = connection.prepareStatement(check);
            PreparedStatement ps_dl = connection.prepareStatement(delete);

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter Booking ID to cancel:");
            int book_id = sc.nextInt();
            ps_ck.setInt(1,book_id);

               // check booking
            ResultSet rs = ps_ck.executeQuery();
            if(rs.next()){
                     System.out.println("✅ Booking Found! Deleting...");

                     // delete booking
                     ps_dl.setInt(1,book_id);
                     int row = ps_dl.executeUpdate();
                      if(row>0){
                          System.out.println("✅ Booking Cancelled Successfully!");
                      }
                 }else {
                     System.out.println("❌ Booking not found!");
                 }

            rs.close();
            ps_dl.close();
            ps_ck.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
