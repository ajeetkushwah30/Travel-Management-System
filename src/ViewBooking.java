import java.sql.*;

public class ViewBooking {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/travel_company";
    private static final String user = "root";
    private static final String password = "your_password";


    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection connection =DriverManager.getConnection(url,user,password);

            String query = "SELECT booking.booking_id, passenger.pass_name, bus.bus_name, booking.seat_number, booking.ticket_price " +
                    "FROM booking  " +
                    "JOIN passenger ON booking.pass_id = passenger.pass_id " +
                    "JOIN bus ON booking.bus_id = bus.bus_id;";






            PreparedStatement ps_viewBook = connection.prepareStatement(query);
            ResultSet rs = ps_viewBook.executeQuery();

            System.out.println("\n===== BOOKING DETAILS =====\n");

            while (rs.next()) {
                int booking_id = rs.getInt("booking_id");
                String pass_name = rs.getString("pass_name");
                String bus_name = rs.getString("bus_name");
                int seat_number = rs.getInt("seat_number");
                double price = rs.getDouble("ticket_price");

                System.out.println("Booking ID : " + booking_id);
                System.out.println("Passenger  : " + pass_name);
                System.out.println("Bus        : " + bus_name);
                System.out.println("Seat No    : " + seat_number);
                System.out.println("Price      : " + price);
                System.out.println("---------------------------");
            }
            rs.close();
            ps_viewBook.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}