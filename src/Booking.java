import java.sql.*;
import java.util.Scanner;

public class Booking {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/travel_company";
    private static final String user = "root";
    private static final String password = "your_password";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String add_booking = "INSERT INTO booking(bus_id, pass_id,seat_number,ticket_price) VALUES (?,?,?,?)";
            PreparedStatement ps_book = connection.prepareStatement(add_booking);
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Enter Bus Id ");
                int bus_id = Integer.parseInt(sc.nextLine());
                System.out.println("Enter Pass Id");
                int pass_id = Integer.parseInt(sc.nextLine());
                System.out.println("Enter Seat number");
                int seat_number = Integer.parseInt(sc.nextLine());
                System.out.println("Ticket Price");
                double ticket_price = Double.parseDouble(sc.nextLine());

                // ✅ CHECK CALL
                if(check_booking(connection, bus_id, seat_number)){
                    System.out.println("❌ Seat already booked!");
                    continue;
                }



                ps_book.setInt(1, bus_id);
                ps_book.setInt(2, pass_id);
                ps_book.setInt(3, seat_number);
                ps_book.setDouble(4, ticket_price);

                ps_book.addBatch();





                System.out.println("Enter More Booking : (Y/N)");
                String choice = sc.nextLine();



                if (choice.equalsIgnoreCase("N")) {
                    break;
                }
            }

            int[] row3 = ps_book.executeBatch();

            if (row3.length > 0) {
                System.out.println("Data Insert Succesfull!");
            } else {
                System.out.println("Data Insert faild!");
            }

            sc.close();
            ps_book.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static boolean check_booking(Connection connection, int bus_id , int seat_number) {
          try{
              String query = "SELECT * FROM booking WHERE bus_id = ? AND seat_number = ?";
              PreparedStatement check_seat = connection.prepareStatement(query);
              check_seat.setInt(1,bus_id);
              check_seat.setInt(2,seat_number);

              ResultSet rs = check_seat.executeQuery();

              return rs.next();

    } catch (SQLException e) {
              System.out.println(e.getMessage());
          }
          return false;


    }
}



