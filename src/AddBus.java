import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddBus {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/travel_company";
    private static final String user = "root";
    private static final String password = "your_password";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        // ----------------------------------------
        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            String add_bus = "INSERT INTO bus(bus_name,bus_number,bus_type,total_seats,source,destination,departure_time,arrival_time,fare) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps_bus = connection.prepareStatement(add_bus);

            Scanner sc = new Scanner(System.in);


            while (true) {
                System.out.print("Enter Bus Name ");
                String bus_name = sc.nextLine();
                System.out.print("Enter Bus  number ");
                String bus_number = sc.nextLine();
                System.out.print("Enter Bus Type ");
                String bus_type = sc.nextLine().toUpperCase();
                System.out.print("Enter Bus  seats ");
                int total_seats =Integer.parseInt(sc.nextLine());


                System.out.print("Enter Bus source ");
                String source = sc.nextLine();
                System.out.print("Enter Bus destination ");
                String destination = sc.nextLine();
                System.out.print("Enter Bus departure_time ");
                String departure_time = sc.nextLine();
                System.out.print("Enter Bus arrival_time ");
                String arrival_time = sc.nextLine();
                System.out.println("Enter fare");
                float fare = Float.parseFloat(sc.nextLine());
                System.out.print("Enter More Bus Data : (Y/N) ");
                String choice = sc.nextLine();

                ps_bus.setString(1,bus_name);
                ps_bus.setString(2,bus_number);
                ps_bus.setString(3,bus_type);
                ps_bus.setInt(4,total_seats);
                ps_bus.setString(5,source);
                ps_bus.setString(6,destination);
                ps_bus.setString(7,departure_time);
                ps_bus.setString(8,arrival_time);
                ps_bus.setFloat(9,fare);

                ps_bus.addBatch();



                if (choice.equalsIgnoreCase("N")) {
                    break;
                }
            }

            int [] row2 = ps_bus.executeBatch();
            if(row2.length > 0){
                System.out.println("Data Insert Succesfull!");
            }else{
                System.out.println("Data Insert faild!");
            }

            sc.close();
            ps_bus.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



    }
}
