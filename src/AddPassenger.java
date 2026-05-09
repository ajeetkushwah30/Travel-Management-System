import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddPassenger {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/travel_company";
    private static final String user = "root";
    private static final String password = "your_password";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url,user,password);
            String add_pass = "INSERT INTO passenger(pass_name,pass_age,pass_gender,pass_phone) VALUES(?,?,?,?)";
            PreparedStatement ps_pass = connection.prepareStatement(add_pass);

            Scanner sc = new Scanner(System.in);


            while (true) {
                System.out.print("Enter Passenger Name ");
                String pass_name = sc.next();
                System.out.print("Enter Passenger Age ");
                int pass_age = sc.nextInt();
                System.out.print("Enter Passenger Gender ");
                String pass_gender = sc.next();
                System.out.print("Enter Passenger Number ");
                String pass_phone = sc.next();
                System.out.print("Enter More Passenger Data : (Y/N) ");
                String choice = sc.next();

                ps_pass.setString(1,pass_name);
                ps_pass.setInt(2,pass_age);
                ps_pass.setString(3,pass_gender);
                ps_pass.setString(4,pass_phone);

                ps_pass.addBatch();

                if (choice.equalsIgnoreCase("N")) {
                    break;
                }
            }

            int [] row1 = ps_pass.executeBatch();
            if(row1.length > 0){
                System.out.println("Data Insert Succesfull!");
            }else{
                System.out.println("Data Insert faild!");
            }

            sc.close();
            ps_pass.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
