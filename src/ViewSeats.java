import java.sql.*;

public class ViewSeats {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/travel_company";
    private static final String user = "root";
    private static final String password = "your_password";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, user, password);

            String query = """
                SELECT 
                    bus.bus_id,
                    bus.bus_name,
                    bus.total_seats,
                    COUNT(booking.seat_number) AS booked_seats,
                    (bus.total_seats - COUNT(booking.seat_number)) AS available_seats
                FROM bus 
                LEFT JOIN booking ON bus.bus_id = booking.bus_id
                GROUP BY bus.bus_id, bus.bus_name, bus.total_seats
            """;

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("\n===== SEAT AVAILABILITY =====\n");

            while (rs.next()) {
                System.out.println("Bus ID        : " + rs.getInt("bus_id"));
                System.out.println("Bus Name      : " + rs.getString("bus_name"));
                System.out.println("Total Seats   : " + rs.getInt("total_seats"));
                System.out.println("Booked Seats  : " + rs.getInt("booked_seats"));
                System.out.println("Available     : " + rs.getInt("available_seats"));
                System.out.println("-----------------------------");
            }

            rs.close();
            stmt.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
