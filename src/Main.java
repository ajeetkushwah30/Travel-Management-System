import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Travel Management System Started ===");

        while (true) {

            System.out.println("\n1. Add Bus");
            System.out.println("2. Add Passenger");
            System.out.println("3. Booking");
            System.out.println("4. View Booking");
            System.out.println("5. Cancel Booking");
            System.out.println("6. View Seats");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    AddBus.main(null);
                    break;

                case 2:
                    AddPassenger.main(null);
                    break;

                case 3:
                    Booking.main(null);
                    break;

                case 4:
                    ViewBooking.main(null);
                    break;

                case 5:
                    CancelBooking.main(null);
                    break;

                case 6:
                    ViewSeats.main(null);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
